package connect4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;


public class Connect4Controller {
    private final Board board = new Board();
    private char currentPlayer = 'X'; // Rød starter

    @FXML
    private GridPane grid;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        drawBoard();
        statusLabel.setText("🔴 Rød starter");
    }

    @FXML
    private void resetGame() {
        board.resetBoard();
        currentPlayer = 'X';
        drawBoard();
        statusLabel.setText("🔴 Rød starter");
    }

    private void drawBoard() {
        grid.getChildren().clear();

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                char piece = board.getPiece(row, col);
                Button btn = new Button(); // Tom knapp – kun farge
                btn.setMinSize(50, 50);
                btn.getStyleClass().add("button");

                if (piece == 'X') {
                    btn.getStyleClass().add("x-piece");
                } else if (piece == 'O') {
                    btn.getStyleClass().add("o-piece");
                }

                int finalCol = col;
                btn.setOnAction(e -> dropPiece(finalCol));

                grid.add(btn, col, row);
            }
        }
    }

    private void dropPiece(int column) {
        int row = board.dropPiece(column, currentPlayer);
        if (row != -1) {
            drawBoard();

            if (board.checkWin(currentPlayer)) {
                statusLabel.setText((currentPlayer == 'X' ? "🔴 Rød" : "🟡 Gul") + " har vunnet!");
                return;
            } else if (board.isBoardFull()) {
                statusLabel.setText("Uavgjort! Brettet er fullt.");
                return;
            }

            switchPlayer();
            statusLabel.setText((currentPlayer == 'X' ? "🔴 Rød" : "🟡 Gul") + " sin tur");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }


    //Filhåndtering
    @FXML
    private void saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre spill som...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialFileName("mitt_spill.txt");
    
        File file = fileChooser.showSaveDialog(grid.getScene().getWindow());
    
        if (file != null) {
            FileHandler.saveToFile(board, currentPlayer, file.getAbsolutePath());
            statusLabel.setText("Spill lagret til: " + file.getName());
        }
    }
    

@FXML
private void loadGame() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Velg lagret spill");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    File file = fileChooser.showOpenDialog(grid.getScene().getWindow());

    if (file != null) {
        FileHandler.LoadedGame loaded = FileHandler.loadFromFile(file.getAbsolutePath());
        if (loaded != null) {
            board.resetBoard();
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
        } else {
            statusLabel.setText("Klarte ikke å laste spillet.");
        }
    }
}


}
