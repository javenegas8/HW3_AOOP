package omok.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstraction of an Omok board, which consists of n x n intersections
 * or places where players can place their stones. The board can be
 * accessed using a pair of 0-based indices (x, y), where x and y
 * denote the column and row number, respectively. The top-left
 * intersection is represented by the indices (0, 0), and the
 * bottom-right intersection is represented by the indices (n-1, n-1).
 */
public class Board {
    private final int size;
    Player[][] intersect;
    List<Place> winningRow;


    /** Create a new board of the default size. */
    public Board() {
        this(15);

    }


    /** Create a new board of the specified size. */
    public Board(int size) {
        this.size= size;
        this.intersect=new Player[size][size];
        winningRow = new ArrayList<>(5);
    }

    /** Return the size of this board. */
    public int size() {
        return size;
    }

    /** Removes all the stones placed on the board, effectively
     * resetting the board to its original state.
     */
    public void clear() {
        for(int i=0; i<size; i++){
            Arrays.fill(intersect[i], null);

        }
    }

    /** Return a boolean value indicating whether all the places
     * on the board are occupied or not.
     */
    public boolean isFull() {
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(intersect[i][j]==null){
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Place a stone for the specified player at a specified
     * intersection (x, y) on the board.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @param player Player whose stone is to be placed
     */
    public void placeStone(int x, int y, Player player) {
        if(x < 0 || y < 0 || x > 14 || y > 14)
            return;
        this.intersect[x][y] = player;
        player.setMove(x,y);
    }

    /**
     * Return a boolean value indicating whether the specified
     * intersection (x, y) on the board is empty or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isEmpty(int x, int y) {
        return this.intersect[x][y] == null;
    }

    /**
     * Is the specified place on the board occupied?
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupied(int x, int y) {
        return intersect[x][y] != null;

    }

    /**
     * Rreturn a boolean value indicating whether the specified
     * intersection (x, y) on the board is occupied by the given
     * player or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupiedBy(int x, int y, Player player) {
        return player.equals(intersect[x][y]);
    }

    /**
     * Return the player who occupies the specified intersection (x, y)
     * on the board. If the place is empty, this method returns null.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public Player playerAt(int x, int y) {
        return this.intersect[x][y];
    }

    /**
     * Return a boolean value indicating whether the given player
     * has a winning row on the board. A winning row is a consecutive
     * sequence of five or more stones placed by the same player in
     * a horizontal, vertical, or diagonal direction.
     */
    public boolean isWonBy(Player player) {
        // Check for horizontal wins
        for (int y = 0; y < size(); y++) {
            int count = 0;
            for (int x = 0; x < size(); x++) {
                if (player.equals(playerAt(x, y))) {
                    count++;
                    if (count >= 5) {
                        // Found a winning row
                        winningRow.clear();
                        for (int i = 0; i < 5; i++) {
                            winningRow.add(new Place(x - i, y));
                        }
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check for vertical wins
        for (int x = 0; x < size(); x++) {
            int count = 0;
            for (int y = 0; y < size(); y++) {
                if (player.equals(playerAt(x, y))) {
                    count++;
                    if (count >= 5) {
                        // Found a winning row
                        winningRow.clear();
                        for (int i = 0; i < 5; i++) {
                            winningRow.add(new Place(x, y - i));
                        }
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check for diagonal (down-right) wins
        for (int x = 0; x < size() - 4; x++) {
            for (int y = 0; y < size() - 4; y++) {
                int count = 0;
                for (int i = 0; i < 5; i++) {
                    if (player.equals(playerAt(x + i, y + i))) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count >= 5) {
                    // Found a winning row
                    winningRow.clear();
                    for (int i = 0; i < 5; i++) {
                        winningRow.add(new Place(x + i, y + i));
                    }
                    return true;
                }
            }
        }

        // Check for diagonal (up-right) wins
        for (int x = 0; x < size() - 4; x++) {
            for (int y = 4; y < size(); y++) {
                int count = 0;
                for (int i = 0; i < 5; i++) {
                    if (player.equals(playerAt(x + i, y - i))) {
                        count++;
                    } else {
                        count = 0;
                        break;
                    }
                }
                if (count >= 5) {
                    // Found a winning row
                    winningRow.clear();
                    for (int i = 0; i < 5; i++) {
                        winningRow.add(new Place(x + i, y - i));
                    }
                    return true;
                }
            }
        }

        // No winning row found
        return false;
    }



    /** Return the winning row. For those who are not familiar with
     * the Iterable interface, you may return an object of
     * List<Place>. */
    public Iterable<Place> winningRow() {
        return this.winningRow;
    }




        /**
         * An intersection on an Omok board identified by its 0-based column
         * index (x) and row index (y). The indices determine the position
         * of a place on the board, with (0, 0) denoting the top-left
         * corner and (n-1, n-1) denoting the bottom-right corner,
         * where n is the size of the board.
         */
        public static class Place {
            /** 0-based column index of this place. */
            public final int x;

            /** 0-based row index of this place. */
            public final int y;

            /** Create a new place of the given indices.
             *
             * @param x 0-based column (vertical) index
             * @param y 0-based row (horizontal) index
             */
            public Place(int x, int y) {
                this.x = x;
                this.y = y;
            }

            // other methods if needed ...
        }
    }

