package connect4;

// Board-klassen er modellen for brettet i 4 på rad.
// Den holder styr på brikkene og sjekker om noen har vunnet.
public class Board implements Resettable {

    private final int rows = 6;
    private final int cols = 7;
    private final char[][] board; // 2D-array som representerer brettet
    private final char empty = ' '; // Tomt felt representeres som et mellomrom

    public Board() {
        board = new char[rows][cols];
        resetBoard(); // Fyller brettet med tomme felter
    }

    // Plasserer en brikke i valgt kolonne
    // Returnerer raden brikken havnet på, eller -1 hvis ugyldig
    public int dropPiece(int column, char piece) {
        if (column < 0 || column >= cols || board[0][column] != empty) {
            return -1; // ugyldig trekk
        }
        // Går nedover i kolonnen og finner første ledige plass
        for (int r = rows - 1; r >= 0; r--) {
            if (board[r][column] == empty) {
                board[r][column] = piece;
                return r;
            }
        }
        return -1;
    }

    // Sjekker om en spiller har vunnet
    public boolean checkWin(char piece) {
        return checkHorizontalWin(piece) || checkVerticalWin(piece) || checkDiagonalWin(piece);
    }

    // Sjekker horisontalt etter fire på rad
    private boolean checkHorizontalWin(char piece) {
        for (int i = 0; i < rows; i++) {
            int counter = 0;
            for (int j = 0; j < cols; j++) {
                counter = (board[i][j] == piece) ? counter + 1 : 0;
                if (counter == 4) return true;
            }
        }
        return false;
    }

    // Sjekker vertikalt etter fire på rad
    private boolean checkVerticalWin(char piece) {
        for (int j = 0; j < cols; j++) {
            int counter = 0;
            for (int i = 0; i < rows; i++) {
                counter = (board[i][j] == piece) ? counter + 1 : 0;
                if (counter == 4) return true;
            }
        }
        return false;
    }

    // Sjekker diagonalt etter fire på rad (begge retninger)
    private boolean checkDiagonalWin(char piece) {
        // Høyre-ned skrå
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (board[i][j] == piece &&
                    board[i+1][j+1] == piece &&
                    board[i+2][j+2] == piece &&
                    board[i+3][j+3] == piece)
                    return true;
            }
        }

        // Venstre-ned skrå
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 3; j < cols; j++) {
                if (board[i][j] == piece &&
                    board[i+1][j-1] == piece &&
                    board[i+2][j-2] == piece &&
                    board[i+3][j-3] == piece)
                    return true;
            }
        }

        return false;
    }

    // Sjekker om brettet er fullt (uavgjort)
    public boolean isBoardFull() {
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == empty) return false;
        }
        return true;
    }

    // Henter brikken på en spesifikk posisjon
    public char getPiece(int row, int col) {
        return board[row][col];
    }

    // Tømmer brettet
    public void resetBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = empty;
            }
        }
    }

    // Implementerer Resettable-interfacet
    @Override
    public void reset() {
        resetBoard();
    }
}
