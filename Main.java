import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String adjective1;
        String noun1;
        String adjective2;
        String verb1;
        String adjective3;

        System.out.print("Adjektiv: ");
        adjective1 = scanner.nextLine();
        System.out.print("Noun: ");
        noun1 = scanner.nextLine();
        System.out.print("Adjektiv: ");
        adjective2 = scanner.nextLine();
        System.out.print("Verb: ");
        verb1 = scanner.nextLine();
        System.out.print("Adjektiv: ");
        adjective3 = scanner.nextLine();


        System.out.println("Today I went to a " + adjective1 + " Zoo");
        System.out.println("In an exibit, I saw a " + noun1 + ".");
        System.out.println(noun1 + " was " + adjective2 + " and " + verb1 + "!");
        System.out.println("I was " + adjective3);
    }
}