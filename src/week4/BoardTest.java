package week4;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    /**
     * Immutability.
     */
    @Test
    public void immutability() {
        final int[][] board = {
                {1, 0},
                {3, 2}
        };
        Board sut = new Board(board);

        board[0][1] = 9;

        assertEquals(1, sut.hamming());
    }

    /**
     * Dimension.
     */
    @Test
    public void dimension() {
        final int n = 10;
        Board sut = new Board(new int[n][n]);

        int dimension = sut.dimension();

        assertEquals(n, dimension);
    }

    /**
     * Equals.
     */
    @Test
    public void equals_should_equal() {
        final int[][] array = {
                {1, 0},
                {3, 2}
        };
        Board board1 = new Board(array);
        Board board2 = new Board(array);

        assertEquals(board1, board2);
    }

    @Test
    public void equals_compare_to_string() {
        final int n = 10;
        Board sut = new Board(new int[n][n]);

        assertNotEquals(sut, "test");
    }

    @Test
    public void equals_compare_to_null() {
        final int n = 10;
        Board sut = new Board(new int[n][n]);

        assertNotEquals(sut, null);
    }

    @Test
    public void equals_compare_to_board_of_different_dimension() {
        final int n = 10;
        Board board1 = new Board(new int[n][n]);
        Board board2 = new Board(new int[n+1][n+1]);

        assertNotEquals(board1, board2);
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

    /**
     * Twin
     */
    @Test
    public void twin_2x2() {
        StdRandom.setSeed(41);

        final int[][] board = {
                {1, 0},
                {3, 2}
        };
        Board sut = new Board(board);

        Board twin = sut.twin();

        assertNotEquals(sut.toString(), twin.toString());
    }

    @Test
    public void twin_2x2_should_not_the_same_block() {
        StdRandom.setSeed(42); // seed where ij=1,0 and i1j1 = 1,0

        final int[][] board = {
                {1, 0},
                {3, 2}
        };
        Board sut = new Board(board);

        Board twin = sut.twin();

        assertNotEquals(sut.toString(), twin.toString());
    }

    @Test
    public void twin_2x2_should_not_exchange_0() {
        StdRandom.setSeed(40); // seed where one of the exchange blocks is 0

        final int[][] board = {
                {1, 0},
                {3, 2}
        };
        Board sut = new Board(board);

        Board twin = sut.twin();

        assertNotEquals(sut.toString(), twin.toString());
    }
}
