package connect4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Hjelpeklasse for lagring og lasting av spill fra tekstfiler
public class FileHandler {

    // Lagrer brettet og hvilken spiller som har tur til en fil
    public static void saveToFile(Board board, char currentPlayer, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Går gjennom brettet og skriver én linje per rad
            for (int row = 0; row < 6; row++) {
                StringBuilder line = new StringBuilder();
                for (int col = 0; col < 7; col++) {
                    char piece = board.getPiece(row, col);
                    // Skriv 'X' eller 'O', eller '.' hvis feltet er tomt
                    line.append((piece == 'X' || piece == 'O') ? piece : '.');
                    if (col < 6) line.append(" ");
                }
                writer.write(line.toString());
                writer.newLine();
            }
            // Siste linje = hvilken spiller sin tur det er
            writer.write(currentPlayer + "");
        } catch (IOException e) {
            System.err.println("Feil ved lagring: " + e.getMessage());
        }
    }

    // Leser inn brettet og spilleren fra en fil
    public static LoadedGame loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> lines = new ArrayList<>();

            // Leser inn 6 linjer med brettinformasjon
            for (int i = 0; i < 6; i++) {
                lines.add(reader.readLine());
            }

            // Leser inn spilleren sin tur
            char currentPlayer = reader.readLine().charAt(0);

            Board board = new Board();

            // Går gjennom linjene og legger til brikkene
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

    // Klasse som returneres når vi laster inn et spill
    public static class LoadedGame {
        public final Board board;
        public final char currentPlayer;

        public LoadedGame(Board board, char currentPlayer) {
            this.board = board;
            this.currentPlayer = currentPlayer;
        }
    }
}
