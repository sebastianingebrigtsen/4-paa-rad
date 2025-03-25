package connect4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;

// Kontrollerklassen styrer all logikk mellom brukergrensesnitt og spillmodell
public class Connect4Controller {

    private final Board board = new Board();     // Brettet
    private char currentPlayer = 'X';            // Rød starter
    private boolean gameOver = false;


    @FXML
    private GridPane grid;                       // Spillbrettet i GUI

    @FXML
    private Label statusLabel;                   // Viser tur og meldinger

    // Kalles automatisk når FXML lastes inn
    @FXML
    public void initialize() {
        drawBoard();                             // Tegner tomt brett
        statusLabel.setText("🔴 Rød starter");
    }

    // Starter nytt spill
    @FXML
    private void resetGame() {
        board.reset();
        currentPlayer = 'X';
        gameOver = false; // Tillater nye trekk
        drawBoard();
        statusLabel.setText("🔴 Rød starter");
    }

    // Tegner hele brettet med knapper
    private void drawBoard() {
        grid.getChildren().clear();              // Fjerner alt fra brettet

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                char piece = board.getPiece(row, col);
                Button btn = new Button();       // Hver celle er en knapp
                btn.setMinSize(50, 50);          // Fast størrelse
                btn.getStyleClass().add("button");

                // Legg til riktig farge ut fra brikke
                if (piece == 'X') {
                    btn.getStyleClass().add("x-piece"); // Rød
                } else if (piece == 'O') {
                    btn.getStyleClass().add("o-piece"); // Gul
                }

                int finalCol = col;              // Husk kolonnen for knappetrykk
                btn.setOnAction(e -> dropPiece(finalCol));

                grid.add(btn, col, row);         // Legg til i GUI
            }
        }
    }

    // Når en spiller trykker på en kolonne
    private void dropPiece(int column) {
        if (gameOver) return; // Forhindrer trekk etter at noen har vunnet
    
        int row = board.dropPiece(column, currentPlayer);
        if (row != -1) {
            drawBoard();
    
            if (board.checkWin(currentPlayer)) {
                statusLabel.setText((currentPlayer == 'X' ? "🔴 Rød" : "🟡 Gul") + " har vunnet!");
                gameOver = true; // Spill avsluttes
                return;
            } else if (board.isBoardFull()) {
                statusLabel.setText("Uavgjort! Brettet er fullt.");
                gameOver = true; // Spill avsluttes
                return;
            }
    
            switchPlayer();
            statusLabel.setText((currentPlayer == 'X' ? "🔴 Rød" : "🟡 Gul") + " sin tur");
        }
    }

    // Bytter spiller
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Filhåndtering – lagre og last spill

    @FXML
    private void saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre spill som...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialFileName("mitt_spill.txt");

        File file = fileChooser.showSaveDialog(grid.getScene().getWindow());

        if (file != null) {
            try {
                FileHandler.saveToFile(board, currentPlayer, file.getAbsolutePath());
                statusLabel.setText("Spill lagret til: " + file.getName());
            } catch (Exception e) {
                statusLabel.setText("Kunne ikke lagre spill: " + e.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lagring feilet");
                alert.setHeaderText("Kunne ikke lagre filen");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg lagret spill");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(grid.getScene().getWindow());

        if (file != null) {
            try {
                FileHandler.LoadedGame loaded = FileHandler.loadFromFile(file.getAbsolutePath());
                if (loaded == null) {
                    throw new Exception("Filen kunne ikke lastes eller var ugyldig.");
                }

                Resettable resettableBoard = board;
                resettableBoard.reset();

                // Legger til brikker fra fil
                for (int col = 0; col < 7; col++) {
                    for (int row = 0; row < 6; row++) {
                        char piece = loaded.board.getPiece(row, col);
                        if (piece == 'X' || piece == 'O') {
                            board.dropPiece(col, piece);
                        }
                    }
                }

                this.currentPlayer = loaded.currentPlayer;
                drawBoard();
                statusLabel.setText((currentPlayer == 'X' ? "🔴 Rød" : "🟡 Gul") + " sin tur (lastet fra fil)");

            } catch (Exception e) {
                statusLabel.setText("Feil ved lasting: " + e.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lasting feilet");
                alert.setHeaderText("Kunne ikke laste spillet");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
