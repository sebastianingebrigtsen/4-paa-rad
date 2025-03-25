package connect4;

public class Board {
    private final int rows = 6;
    private final int cols = 7;
    private final char[][] board;
    private final char empty = ' ';

    public Board() {
        board = new char[rows][cols];
        resetBoard();
    }

    public int dropPiece(int column, char piece) {
        if (column < 0 || column >= cols || board[0][column] != empty) {
            return -1;
        }
        for (int r = rows - 1; r >= 0; r--) {
            if (board[r][column] == empty) {
                board[r][column] = piece;
                return r;
            }
        }
        return -1;
    }

    public boolean checkWin(char piece) {
        return checkHorizontalWin(piece) || checkVerticalWin(piece) || checkDiagonalWin(piece);
    }

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

    private boolean checkDiagonalWin(char piece) {
        // høyre-ned
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols - 3; j++) {
                if (board[i][j] == piece &&
                    board[i+1][j+1] == piece &&
                    board[i+2][j+2] == piece &&
                    board[i+3][j+3] == piece)
                    return true;
            }
        }
        // venstre-ned
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

    public boolean isBoardFull() {
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == empty) return false;
        }
        return true;
    }

    public char getPiece(int row, int col) {
        return board[row][col];
    }

    public void resetBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = empty;
            }
        }
    }
}
