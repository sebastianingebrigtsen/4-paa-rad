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
    public int dropPiece(int column, char piece) {
        if (column < 0 || column >= cols ) {
            System.out.println("Ugyldig kolonne: " + column);
            return -1; 
        } else if (board[0][column] != empty){ //Sjekk om der er fullt i en kolonne
            System.out.println("Det er allerede fullt i den kolonnen");
            return -1;
        }
        for (int r = rows -1; r >= 0; r--) {
            if (board[r][column] == empty) {
                board[r][column] = piece;
                return r; // Returnerer raden hvor brikken ble plassert
            }
        }
        return -1; // Ugyldig trekk
    }
    

    //Sjekker om det er fire på rad
    public boolean checkWin(char piece) {
        return checkHorizontalWin(piece) || checkVerticalWin(piece) || checkDiagonalWin(piece);
    }

    //Metoder for å sjekke 4 på rad
    private boolean checkHorizontalWin(char piece) { 
        for (int i = 0; i < rows; i++) {
            int counter = 0; //Teller nullstilles for hver rad
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == piece) {
                    counter += 1;
                    if (counter == 4) {
                        return true;
                    }
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    } 
    private boolean checkVerticalWin(char piece) { 
        for (int j = 0; j < cols; j++) {
            int counter = 0; //Teller nullstilles for hver kolonne
            for (int i = 0; i < rows; i++) {
                if (board[i][j] == piece) {
                    counter += 1;
                    if (counter == 4) {
                        return true;
                    }
                } else {
                    counter = 0;
                }
            }
        }
        return false;
    } 
    private boolean checkDiagonalWin(char piece) {
        // Sjekker høyre-ned diagonaler
        for (int i = 0; i < rows - 3; i++) { // Må ha plass til 4 rader nedover
            for (int j = 0; j < cols - 3; j++) { // Må ha plass til 4 kolonner til høyre
                if (board[i][j] == piece && 
                    board[i+1][j+1] == piece && 
                    board[i+2][j+2] == piece && 
                    board[i+3][j+3] == piece) {
                    return true;
                }
            }
        }
    
        // Sjekker venstre-ned diagonaler
        for (int i = 0; i < rows - 3; i++) { // Må ha plass til 4 rader nedover
            for (int j = 3; j < cols; j++) { // Må ha plass til 4 kolonner til venstre
                if (board[i][j] == piece && 
                    board[i+1][j-1] == piece && 
                    board[i+2][j-2] == piece && 
                    board[i+3][j-3] == piece) {
                    return true;
                }
            }
        }
    
        return false;
    }
    

    // Printer brettet til konsollen (for debugging)
    public void printBoard() {
        System.out.println(" 0 1 2 3 4 5 6 ");
        System.out.println("-----------------");
        for (int r = 0; r < rows; r++) {
            System.out.print("|");
            for (int c = 0; c < cols; c++) {
                System.out.print(board[r][c] + "|");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
    

    //Sjekk om spillbrettet er fullt (Uavgjort)
    public boolean isBoardFull() {
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == empty) {
                return false; 
            }
        }
        return true; //Brettet er fullt
    }

    public char getPiece(int row, int col) {
        return board[row][col];
    }
    
    


    // Tilbakestiller brettet
    public void resetBoard() {
        initializeBoard();
    } }
