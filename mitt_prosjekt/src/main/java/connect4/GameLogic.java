//Spillmotoren som håndterer regler

package connect4;
import java.util.Scanner;  


public class GameLogic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hva er navnet til spiller 1? ");
        Player player1 = new Player(scanner.nextLine(), 'X');
        System.out.print("Hva er navnet til spiller 2? ");
        Player player2 = new Player(scanner.nextLine(), 'O');

        System.out.println("Spiller 1: " + player1.name + " med brikke: " + player1.piece);
        System.out.println("Spiller 2: " + player2.name + " med brikke: " + player2.piece);

        Board board = new Board();
        board.printBoard();

        //Løkke for spillet
        while(!board.checkWin(player1.piece) && !board.checkWin(player2.piece) && !board.isBoardFull()) {
            System.out.print(player1.piece + " velg kolonne: ");
            

            board.dropPiece(Integer.valueOf(scanner.nextLine()), 'X');

            board.printBoard();


        }

        scanner.close();
    }
}
