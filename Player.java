public class Player implements Comparable<Player> {
    private String name;
    private int id; // 1 eller 2

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.id, other.id);
    }
}
