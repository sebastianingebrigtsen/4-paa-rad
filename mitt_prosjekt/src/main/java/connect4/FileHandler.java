package connect4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class FileHandler {

    public static void saveToFile(Board board, char currentPlayer, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int row = 0; row < 6; row++) {
                StringBuilder line = new StringBuilder();
                for (int col = 0; col < 7; col++) {
                    char piece = board.getPiece(row, col);
                    line.append(piece == 'X' || piece == 'O' ? piece : '.');
                    if (col < 6) line.append(" ");
                }
                writer.write(line.toString());
                writer.newLine();
            }
            writer.write(currentPlayer + ""); // Siste linje = spiller
        } catch (IOException e) {
            System.err.println("Feil ved lagring: " + e.getMessage());
        }
    }

    public static LoadedGame loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> lines = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                lines.add(reader.readLine());
            }
            char currentPlayer = reader.readLine().charAt(0);

            Board board = new Board();
            for (int row = 0; row < 6; row++) {
                String[] tokens = lines.get(row).split(" ");
                for (int col = 0; col < 7; col++) {
                    char piece = tokens[col].charAt(0);
                    if (piece == 'X' || piece == 'O') {
                        board.dropPiece(col, piece);
                    }
                }
            }

            return new LoadedGame(board, currentPlayer);
        } catch (IOException e) {
            System.err.println("Feil ved lasting: " + e.getMessage());
            return null;
        }
    }

    public static class LoadedGame {
        public final Board board;
        public final char currentPlayer;

        public LoadedGame(Board board, char currentPlayer) {
            this.board = board;
            this.currentPlayer = currentPlayer;
        }
    }
}
