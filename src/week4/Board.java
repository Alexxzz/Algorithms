package week4;

import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

/**
 * Created by AlexZ on 02/01/2017.
 */
public class Board {
    private final int dimension;
    private final int[][] blocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dimension = blocks.length;
        this.blocks = blocks;
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of blocks out of place
    public int hamming() {
        int hamming = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int b = blocks[i][j];
                if (b == 0) continue;

                int expected = (i * dimension + j) + 1;
                if (b != expected) hamming++;
            }
        }

        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int b = blocks[i][j];
                if (b == 0) continue;

                int iExpected = (b - 1) / dimension;
                int jExpected = (b - 1) % dimension;

                manhattan += Math.abs(i - iExpected);
                manhattan += Math.abs(j - jExpected);
            }
        }

        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return null;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<>();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int b = blocks[i][j];
                if (b == 0) {
                    Board newBoard1 = createBoardExchanging(i, j, i+1, j);
                    Board newBoard2 = createBoardExchanging(i, j, i-1, j);
                    Board newBoard3 = createBoardExchanging(i, j, i, j+1);
                    Board newBoard4 = createBoardExchanging(i, j, i, j-1);

                    if (newBoard1 != null) neighbors.enqueue(newBoard1);
                    if (newBoard2 != null) neighbors.enqueue(newBoard2);
                    if (newBoard3 != null) neighbors.enqueue(newBoard3);
                    if (newBoard4 != null) neighbors.enqueue(newBoard4);

                    return neighbors;
                }
            }
        }

        return neighbors;
    }

    private Board createBoardExchanging(int i, int j, int i1, int j1) {
        if (coordinateIsOutOfDimensions(i1) || coordinateIsOutOfDimensions(j1)) return null;

        int[][] newBlocks = blocks.clone();

        newBlocks[i][j] = blocks[i1][j1];
        newBlocks[i1][j1] = blocks[i][j];

        return new Board(newBlocks);
    }

    private boolean coordinateIsOutOfDimensions(int coord) {
        return coord >= dimension || coord < 0;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimension);

        for (int i = 0; i < dimension; i++) {
            sb.append("\n");
            for (int j = 0; j < dimension; j++) {
                sb.append(" ").append(blocks[i][j]).append(" ");
            }
        }

        return sb.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
