import java.io.*;

public class ConnectFourFileHandler {

    // Lagrer spillets tilstand til en fil.
    public static void saveGame(ConnectFourGame game, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Board board = game.getBoard();
            int[][] grid = board.getGrid();
            // Skriv dimensjoner:
            writer.write(grid.length + " " + grid[0].length);
            writer.newLine();
            // Skriv brettets innhold:
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    writer.write(grid[i][j] + " ");
                }
                writer.newLine();
            }
            // Skriv hvilken spiller som har tur.
            writer.write(String.valueOf(game.getCurrentPlayer().getId()));
            writer.newLine();
        }
    }

    // Leser inn spilltilstand fra en fil og oppdaterer spillet.
    public static void loadGame(ConnectFourGame game, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            String[] parts = line.split(" ");
            int rows = Integer.parseInt(parts[0]);
            int cols = Integer.parseInt(parts[1]);
            int[][] grid = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                line = reader.readLine();
                parts = line.trim().split(" ");
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = Integer.parseInt(parts[j]);
                }
            }
            // Oppdaterer brettet.
            game.getBoard().setGrid(grid);
            // Leser hvilken spiller som har tur.
            line = reader.readLine();
            int currentId = Integer.parseInt(line.trim());
            game.setCurrentPlayerById(currentId);
        }
    }
}
