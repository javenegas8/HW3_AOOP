package omok.model;

public class Player {
    // Private fields
    private final String name;
    private final int[] LMove;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.LMove = new int[] {-1, -1};
    }

    // Public methods
    public String getName() {
        return this.name;
    }

    public void setMove(int x, int y) {
        this.LMove[0] = x;
        this.LMove[1] = y;
    }

    public int[] getMove() {
        return this.LMove;
    }
}

