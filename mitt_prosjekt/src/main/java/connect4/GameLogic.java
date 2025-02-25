package connect4;
import java.util.Scanner;  

public class GameLogic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Opprett spillere
        System.out.print("Hva er navnet til spiller 1? ");
        Player player1 = new Player(scanner.nextLine(), 'X');
        System.out.print("Hva er navnet til spiller 2? ");
        Player player2 = new Player(scanner.nextLine(), 'O');

        System.out.println(player1.name + " spiller med: " + player1.piece);
        System.out.println(player2.name + " spiller med: " + player2.piece);

        // Start spillet
        Board board = new Board();
        Player currentPlayer = player1; // Spiller 1 starter
        board.printBoard();

        // Spill-loop
        while (!board.checkWin(player1.piece) && !board.checkWin(player2.piece) && !board.isBoardFull()) {
            int column;
            boolean validMove = false;

            // Spilleren må velge en gyldig kolonne
            do {
                System.out.print(currentPlayer.name + " (" + currentPlayer.piece + ") velg kolonne (0-6): ");
                try {
                    column = Integer.parseInt(scanner.nextLine());
                    if (board.dropPiece(column, currentPlayer.piece) != -1) {
                        validMove = true;
                    } else {
                        System.out.println("Ugyldig trekk. Prøv igjen.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldig input! Skriv et tall mellom 0 og 6.");
                    column = -1; // Setter en ugyldig verdi for å fortsette loopen
                }
            } while (!validMove); // Fortsetter til spilleren gjør et gyldig trekk
            
            board.printBoard();

            // Sjekk om nåværende spiller har vunnet
            if (board.checkWin(currentPlayer.piece)) {
                System.out.println(currentPlayer.name + " (" + currentPlayer.piece + ") har vunnet! 🎉");
                break;
            }

            // Sjekk om brettet er fullt (uavgjort)
            if (board.isBoardFull()) {
                System.out.println("Uavgjort! Brettet er fullt.");
                break;
            }

            // Bytt spiller
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        scanner.close();
    }
}
