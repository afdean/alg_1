import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int open = 1;
    private static final int closed = 0;
    private static final int top = 0;
    private int bottom;
    private int size;
    private int openAmount = 0;
    private WeightedQuickUnionUF wquf;
    private int[][] grid;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        size = n;
        grid = new int[size][size];
        bottom = (size * size) + 1;

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                grid[i][j] = closed;
            }
        }

        // 0 is index of virtual top
        // 1 is (1,1)
        // 2 is (1,2)...
        // size^2 + 1 index is virtual bottom
        wquf = new WeightedQuickUnionUF((size * size) + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkValidInput(row);
        checkValidInput(col);
        if (!isOpen(row, col)) {
            int index = getArrayIndex(row, col);
            grid[row - 1][col - 1] = open;
            openAmount++;

            // Connect up
            if (row > 1 && isOpen(row - 1, col)) {
                wquf.union(index, index - size);
            }
            // Connect down
            if (row < size && isOpen(row + 1, col)) {
                wquf.union(index, index + size);
            }
            // Connect left
            if (col > 1 && isOpen(row, col - 1)) {
                wquf.union(index, index - 1);
            }
            // Connect right
            if (col < size && isOpen(row, col + 1)) {
                wquf.union(index, index + 1);
            }
            // Connect virtuals
            if (row == 1) {
                wquf.union(0, index);
            } else if (row == size) {
                wquf.union(index, (size * size) + 1);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkValidInput(row);
        checkValidInput(col);
        return grid[row - 1][col - 1] == open;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        checkValidInput(row);
        checkValidInput(col);
        int index = getArrayIndex(row, col);
        return wquf.connected(top, index);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openAmount;
    }

    // does the system percolate?
    public boolean percolates() {
        // if virtual top and virtual bottom union, yes, else no.
        return wquf.connected(top, bottom);
    }

    // Should argument be thrown?
    private boolean checkValidInput(int index) {
        if (index < 1 || index > size) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    // Takes row/col inputs from functions and returns equivalent entry in wquf
    private int getArrayIndex(int row, int col) {
        int index = ((row - 1) * size) + col;
        return index;
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}