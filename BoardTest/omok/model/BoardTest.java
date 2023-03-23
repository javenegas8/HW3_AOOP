package omok.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board normalBoard = new Board();
    private Board changeBoard = new Board(20);
    private Player player1 = new Player("p1");
    private Player player2 = new Player("p2");

    @BeforeEach
    void setUp() {
        normalBoard = new Board();
    }

    @Test
    void testDefaultSize() {
        assertEquals(15, normalBoard.size());
    }

    @Test
    void testCustomSize() {
        assertEquals(20, changeBoard.size());
    }

    @Test
    void testIsFull() {
        assertFalse(normalBoard.isFull());
    }

    @Test
    void testPlaceStone() {
        normalBoard.placeStone(7, 7, player2);
        assertTrue(normalBoard.isOccupied(7, 7));
    }

    @Test
    void testIsEmpty() {
        assertTrue(normalBoard.isEmpty(0, 0));
    }

    @Test
    void testIsOccupied() {
        assertFalse(normalBoard.isOccupied(0, 0));
    }

    @Test
    void testIsOccupiedBy() {
        normalBoard.placeStone(5, 5, player2);
        normalBoard.placeStone(2, 2, player1);

        assertTrue(normalBoard.isOccupiedBy(2, 2, player1));
        assertFalse(normalBoard.isOccupiedBy(2, 2, player2));

        assertFalse(normalBoard.isOccupiedBy(5, 5, player1));
        assertTrue(normalBoard.isOccupiedBy(5, 5, player2));
    }

    @Test
    void testPlayerAt() {
        normalBoard.placeStone(2, 2, player1);

        assertEquals(player1, normalBoard.playerAt(2, 2));
        assertNotEquals(player2, normalBoard.playerAt(2, 2));
    }
    @Test
    void testIsWonByP1() {
        for(int i = 0; i < 5; i++) {
            normalBoard.placeStone(i, i, player1);
        }
        assertTrue(normalBoard.isWonBy(player1));
        assertFalse(normalBoard.isWonBy(player2));
    }
    @Test
    void testIsWonByP2() {
        for(int i = 14; i > 9; i--) {
            normalBoard.placeStone(i, i, player2);
        }
        assertFalse(normalBoard.isWonBy(player1));
        assertTrue(normalBoard.isWonBy(player2));
    }

    @Test
    public void testWinningRows() {
        Board board = new Board(15);

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // horizontal winning row for player1
        board.placeStone(0, 0, player1);
        board.placeStone(1, 0, player1);
        board.placeStone(2, 0, player1);
        board.placeStone(3, 0, player1);
        board.placeStone(4, 0, player1);
        Iterable<Board.Place> winningRow = board.winningRow();
        Arrays Arrays;
        Arrays = null;
        List<Board.Place> expected = Arrays.asList(new Board.Place(0, 0), new Board.Place(1, 0),
                new Board.Place(2, 0), new Board.Place(3, 0),
                new Board.Place(4, 0));
        assertEquals(expected, winningRow);

        // vertical winning row for player2
        board.clear();
        board.placeStone(5, 4, player2);
        board.placeStone(5, 5, player2);
        board.placeStone(5, 6, player2);
        board.placeStone(5, 7, player2);
        board.placeStone(5, 8, player2);
        winningRow = board.winningRow();
        expected = Arrays.asList(new Board.Place(5, 4), new Board.Place(5, 5),
                new Board.Place(5, 6), new Board.Place(5, 7),
                new Board.Place(5, 8));
        assertEquals(expected, winningRow);

        // diagonal winning row for player1
        board.clear();
        board.placeStone(3, 3, player1);
        board.placeStone(4, 4, player1);
        board.placeStone(5, 5, player1);
        board.placeStone(6, 6, player1);
        board.placeStone(7, 7, player1);
        winningRow = board.winningRow();
        expected = Arrays.asList(new Board.Place(3, 3), new Board.Place(4, 4),
                new Board.Place(5, 5), new Board.Place(6, 6),
                new Board.Place(7, 7));
        assertEquals(expected, winningRow);

        // reverse diagonal winning row for player2
        board.clear();
        board.placeStone(9, 5, player2);
        board.placeStone(8, 6, player2);
        board.placeStone(7, 7, player2);
        board.placeStone(6, 8, player2);
        board.placeStone(5, 9, player2);
        winningRow = board.winningRow();
        expected = Arrays.asList(new Board.Place(9, 5), new Board.Place(8, 6),
                new Board.Place(7, 7), new Board.Place(6, 8),
                new Board.Place(5, 9));
        assertEquals(expected, winningRow);
    }
}