//Klassen for spillbrettet

package connect4;

public class Board {
    int rows = 6;
    int cols = 7;
    char empty = '.';

    private char[][] board;

    //Konstruktør
    public Board() {
        board = new char[rows][cols];
        initializeBoard();
    }

    //Metode for å fylle brettet med empty (.)
    private void initializeBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = empty;
            }
        }
    }

    //Plasserer en brikke i en kolonne
    public boolean dropPiece(int column, char piece) {
        if (column < 0 || column >= cols || board[0][column] != empty) {
            return false; //Ugyldig trekk
        }
        for (int r = rows -1; r >= 0; r--) {
            if (board[r][column] == empty) {
                board[r][column] = piece;
                return true;
            }
        }
        return false;
    }

    //Sjekker om det er fire på rad
    public boolean checkWin(char piece) {
        return checkHorizontalWin(piece) || checkVerticalWin(piece) || checkDiagonalWin(piece);
    }

    //Metoder for å sjekke 4 på rad
    private boolean checkHorizontalWin(char piece) { return false; } 
    private boolean checkVerticalWin(char piece) { return false; } 
    private boolean checkDiagonalWin(char piece) { return false; } 

    // Printer brettet til konsollen (for debugging)
    public void printBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }


    // Tilbakestiller brettet
    public void resetBoard() {
        initializeBoard();
    }
    
    public static void main(String[] args) {
        Board board = new Board();
        
        board.printBoard(); // Printer tomt brett først
        System.out.println("------");
    
        board.dropPiece(5, 'X');
        board.printBoard();
        System.out.println("------");
    
        board.dropPiece(5, 'O');
        board.printBoard();
        System.out.println("------");
    
        board.dropPiece(5, 'X');
        board.printBoard();
        System.out.println("------");
    
        board.dropPiece(5, 'O');
        board.printBoard();
    }
    
}
