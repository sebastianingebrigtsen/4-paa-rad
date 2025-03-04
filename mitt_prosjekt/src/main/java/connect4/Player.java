//Klassen for spillerne

package connect4;

public class Player {
    String name;
    char piece;
    int score;

    //Konstruktør
    Player(String name, char piece) {
        this.name = name;
        this.piece = piece;
    }
}
