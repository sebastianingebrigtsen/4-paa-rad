public class ConnectFourGame {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean gameOver;
    private Player winner;

    public ConnectFourGame() {
        board = new Board(6, 7);  // Standard 6x7 brett
        player1 = new Player("Player 1", 1);
        player2 = new Player("Player 2", 2);
        currentPlayer = player1;
        gameOver = false;
        winner = null;
    }

    public Board getBoard() {
        return board;
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public Player getWinner() {
        return winner;
    }

    // Utfører et trekk i den angitte kolonnen.
    // Returnerer raden hvor brikken ble plassert.
    public int makeMove(int col) {
        if (gameOver) {
            throw new IllegalStateException("Spillet er over.");
        }
        int row = board.addToken(col, currentPlayer.getId());
        if (row == -1) {
            throw new IllegalArgumentException("Kolonnen er full. Velg en annen kolonne.");
        }
        if (board.checkWin(currentPlayer.getId())) {
            gameOver = true;
            winner = currentPlayer;
        } else if (board.isFull()) {
            gameOver = true;
        } else {
            switchPlayer();
        }
        return row;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
    
    // Brukes ved lasting for å sette hvem som skal starte (basert på ID).
    public void setCurrentPlayerById(int id) {
        if (id == player1.getId()) {
            currentPlayer = player1;
        } else if (id == player2.getId()) {
            currentPlayer = player2;
        }
    }
}
