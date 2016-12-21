package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.awt.Point;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private int n;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        row--;
        col--;

        validate(row, col);

        Element element = new Element(new Point(row, col));
        grid[row][col] = true;

        if (row == 0) {
            uf.union(getTopConnectionIndex(), rowColToIndex(row, col));
        }
        if (row == n-1) {
            uf.union(getBottomConnectionIndex(), rowColToIndex(row, col));
        }

        connect(element, element.getTopNeighbourLocation());
        connect(element, element.getLeftNeighbourLocation());
        connect(element, element.getBottomNeighbourLocation());
        connect(element, element.getRightNeighbourLocation());
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        row--;
        col--;

        validate(row, col);

        return grid[row][col];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        row--;
        col--;

        validate(row, col);

        return uf.connected(getTopConnectionIndex(), rowColToIndex(row, col));
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(getTopConnectionIndex(), getBottomConnectionIndex());
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    // --------- Private ---------
    private void connect(Element element, Point point) {
        if (point.x < 0 || point.y < 0) {
            return;
        }

        if (point.x >= n || point.y >= n) {
            return;
        }

        int fixedCoordX = point.x + 1;
        int fixedCoordY = point.y + 1;
        if (!isOpen(fixedCoordX, fixedCoordY)) {
            return;
        }

        int idx = rowColToIndex(element.location.x, element.location.y);
        int toIdx = rowColToIndex(point.x, point.y);
        uf.union(idx, toIdx);
    }

    private int getTopConnectionIndex() {
        return n * n;
    }

    private int getBottomConnectionIndex() {
        return n * n + 1;
    }

    private void validate(int row, int col) {
        if (row >= n || col >= n) {
            throw new IndexOutOfBoundsException();
        }

        if (row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int rowColToIndex(int row, int col) {
        return (row * n) + col;
    }

    private class Element {
        private Point location;
        private Point topNeighbourLocation;
        private Point rightNeighbourLocation;
        private Point bottomNeighbourLocation;
        private Point leftNeighbourLocation;

        public Element(Point location) {
            this.location = location;

            topNeighbourLocation = location.getLocation();
            topNeighbourLocation.translate(0, -1);
            rightNeighbourLocation = location.getLocation();
            rightNeighbourLocation.translate(1, 0);
            bottomNeighbourLocation = location.getLocation();
            bottomNeighbourLocation.translate(0, 1);
            leftNeighbourLocation = location.getLocation();
            leftNeighbourLocation.translate(-1, 0);
        }

        public Point getTopNeighbourLocation() {
            return topNeighbourLocation;
        }

        public Point getRightNeighbourLocation() {
            return rightNeighbourLocation;
        }

        public Point getBottomNeighbourLocation() {
            return bottomNeighbourLocation;
        }

        public Point getLeftNeighbourLocation() {
            return leftNeighbourLocation;
        }
    }
}