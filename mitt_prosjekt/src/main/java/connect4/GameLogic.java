//Spillmotoren som håndterer regler

package connect4;
import java.util.Scanner;  


public class GameLogic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hva er navnet til spiller 1?");

        String spiller1 = scanner.nextLine();
        System.out.println(spiller1);
    }
}
