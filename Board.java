public class Board {
    private int rows;
    private int cols;
    private int[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols]; // 0 = tom, 1 = spiller1, 2 = spiller2
    }

    // Legger til en brikke i den gitte kolonnen for spilleren.
    // Returnerer raden brikken havner i, eller -1 hvis kolonnen er full.
    public int addToken(int col, int player) {
        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("Ugyldig kolonne.");
        }
        for (int row = rows - 1; row >= 0; row--) {
            if (grid[row][col] == 0) {
                grid[row][col] = player;
                return row;
            }
        }
        return -1; // Kolonnen er full
    }

    // Sjekker om brettet er fullt.
    public boolean isFull() {
        for (int col = 0; col < cols; col++) {
            if (grid[0][col] == 0) return false;
        }
        return true;
    }

    // Sjekker om den angitte spilleren har fire på rad.
    public boolean checkWin(int player) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == player) {
                    if (checkDirection(row, col, 0, 1, player) ||   // horisontalt
                        checkDirection(row, col, 1, 0, player) ||   // vertikalt
                        checkDirection(row, col, 1, 1, player) ||   // diagonal ned-høyre
                        checkDirection(row, col, 1, -1, player)) {   // diagonal ned-venstre
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Hjelpemetode for å sjekke i en bestemt retning.
    private boolean checkDirection(int row, int col, int dRow, int dCol, int player) {
        int count = 0;
        int r = row, c = col;
        for (int i = 0; i < 4; i++) {
            if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] != player) {
                return false;
            }
            r += dRow;
            c += dCol;
        }
        return true;
    }

    // Returnerer en kopi av brettets tilstand.
    public int[][] getGrid() {
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, cols);
        }
        return copy;
    }

    // Setter brettets tilstand (brukes ved lasting fra fil).
    public void setGrid(int[][] newGrid) {
        if(newGrid.length != rows || newGrid[0].length != cols){
            throw new IllegalArgumentException("Ugyldige dimensjoner for brettdata.");
        }
        for (int i = 0; i < rows; i++) {
            System.arraycopy(newGrid[i], 0, grid[i], 0, cols);
        }
    }
}
