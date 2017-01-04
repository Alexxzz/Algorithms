package week4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
}
