package connect4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    }

    private void drawBoard() {
        grid.getChildren().clear();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Button btn = new Button(" ");
                btn.setMinSize(50, 50);
                int finalCol = col;
                btn.setOnAction(e -> dropPiece(finalCol)); // Fikset kallet!
                grid.add(btn, col, row);
            }
        }
    }

    private void dropPiece(int column) {
        int row = board.dropPiece(column, currentPlayer);
        if (row != -1) { // Gyldig trekk
            drawBoard();
            if (board.checkWin(currentPlayer)) {
                System.out.println("Spiller " + currentPlayer + " har vunnet!");
            }
            switchPlayer(); // Bytter spiller
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
