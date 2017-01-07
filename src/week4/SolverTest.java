package week4;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {
    /**
     * Corner cases.
     */
    @Test
    public void constructor_null() {
        try {
            Solver sut = new Solver(null);
            fail("Should throw NullPointerException when passing null");
        } catch (NullPointerException ignored) { }
    }

    /**
     * Moves
     */
    @Test
    public void moves_3x3_solvable() {
        final int[][] board = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Solver sut = new Solver(new Board(board));

        assertEquals(4, sut.moves());
    }

    @Test
    public void moves_3x3_unsolvable() {
        final int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        };

        Solver sut = new Solver(new Board(board));

        assertEquals(-1, sut.moves());
    }

    /**
     * Is solvable
     */
    @Test
    public void isSolvable_3x3_solvable() {
        final int[][] board = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Solver sut = new Solver(new Board(board));

        assertTrue(sut.isSolvable());
    }

    @Test
    public void isSolvable_3x3_unsolvable() {
        final int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        };

        Solver sut = new Solver(new Board(board));

        assertFalse(sut.isSolvable());
    }


    /**
     * Solution
     */
    @Test
    public void solution_3x3_solvable() {
        final int[][] board = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Solver sut = new Solver(new Board(board));

        String[] expected = {
                "3\n"+
                " 0  1  3 \n"+
                " 4  2  5 \n"+
                " 7  8  6 ",
                "3\n"+
                " 1  0  3 \n"+
                " 4  2  5 \n"+
                " 7  8  6 ",
                "3\n"+
                " 1  2  3 \n"+
                " 4  0  5 \n"+
                " 7  8  6 ",
                "3\n"+
                " 1  2  3 \n"+
                " 4  5  0 \n"+
                " 7  8  6 ",
                "3\n"+
                " 1  2  3 \n"+
                " 4  5  6 \n"+
                " 7  8  0 "
        };

        int idx = 0;
        String[] result = new String[5];
        for (Board b: sut.solution()) {
            result[idx++] = b.toString();
        }

        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void solution_3x3_unsolvable() {
        final int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        };

        Solver sut = new Solver(new Board(board));

        assertEquals(null, sut.solution());
    }
}
