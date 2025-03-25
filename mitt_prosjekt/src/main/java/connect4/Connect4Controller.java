package connect4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Connect4Controller {
    private Board board;
    private char currentPlayer = 'X'; // Starter med X

    @FXML
    private GridPane grid; // GridPane for brettet

    public Connect4Controller() {
        board = new Board();
    }

    @FXML
    public void initialize() {
        drawBoard();
        statusLabel.setText("Spiller X starter (rød)");

    }

    @FXML
    private Label statusLabel;

    
    //Resett spill
    @FXML
    private void resetGame() {
    statusLabel.setText("Spiller X starter (rød)");
    board.resetBoard();
    currentPlayer = 'X';
    drawBoard();
    }


    private void drawBoard() {
        grid.getChildren().clear();
    
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                char piece = board.getPiece(row, col);
                Button btn = new Button(); // <-- uten tekst
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
        if (row != -1) { // Gyldig trekk
            drawBoard();

            if (board.checkWin(currentPlayer)) {
                statusLabel.setText("Spiller " + currentPlayer + " har vunnet!");
                return;
            } else if (board.isBoardFull()) {
                statusLabel.setText("Uavgjort! Brettet er fullt.");
                return;
            }
            switchPlayer();
            statusLabel.setText("Spiller " + currentPlayer + " sin tur");
            
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    
}
