package week4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    @Test
    public void dimension() {
        final int n = 10;
        Board sut = new Board(new int[n][n]);

        int dimension = sut.dimension();

        assertEquals(n, dimension);
    }

    /**
     * Hamming
     */
    @Test
    public void hamming_2x2() {
        final int[][] board = {
                {1, 0},
                {3, 2}
        };
        Board sut = new Board(board);

        int hamming = sut.hamming();

        assertEquals(1, hamming);
    }

    @Test
    public void hamming_3x3() {
        final int[][] board = {
                {8, 6, 7},
                {2, 5, 4},
                {3, 0, 1}
        };
        Board sut = new Board(board);

        int hamming = sut.hamming();

        assertEquals(7, hamming);
    }

    /**
     * Manhattan
     */
    @Test
    public void manhattan_2x2() {
        final int[][] board = {
                {1, 0},
                {3, 2}
        };
        Board sut = new Board(board);

        int manhattan = sut.manhattan();

        assertEquals(1, manhattan);
    }

    @Test
    public void manhattan_3x3() {
        final int[][] board = {
                {8, 6, 7},
                {2, 5, 4},
                {3, 0, 1}
        };
        Board sut = new Board(board);

        int manhattan = sut.manhattan();

        assertEquals(21, manhattan);
    }

    /**
     * Is goal
     */
    @Test
    public void isGoal_Board_is_Not_at_goal_state() {
        final int[][] board = {
                {8, 6, 7},
                {2, 5, 4},
                {3, 0, 1}
        };
        Board sut = new Board(board);

        assertFalse(sut.isGoal());
    }

    @Test
    public void isGoal_Board_is_at_Goal_state() {
        final int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        Board sut = new Board(board);

        assertTrue(sut.isGoal());
    }

    /**
     * To string
     */
    @Test
    public void toString_2x2() {
        final int[][] board = {
                {1, 0},
                {3, 2}
        };
        Board sut = new Board(board);

        String expected =
                "2\n" +
                " 1  0 \n" +
                " 3  2 ";

        assertEquals(expected, sut.toString());
    }

    /**
     * Neighbors
     */
    @Test
    public void neighbors_2x2() {
        final int[][] board = {
                {1, 0},
                {3, 2}
        };
        Board sut = new Board(board);

        int neighborsCount = 0;
        for (Board b: sut.neighbors()) {
            neighborsCount++;
        }

        assertEquals(2, neighborsCount);
    }
}
