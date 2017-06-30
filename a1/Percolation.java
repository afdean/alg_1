import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int open = 1;
    private final int closed = 0;
    private final int top = 0;
    private final int bottom;
    private final int size;
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

        //0 is index of virtual top
        //1 is (1,1)
        //2 is (1,2)...
        //size^2 + 1 index is virtual bottom
        wquf = new WeightedQuickUnionUF((size * size) + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkValidInput(row);
        checkValidInput(col);
        int index = getArrayIndex(row, col);
        grid[row - 1][col - 1] = open;
        openAmount++;

        //Make unions around surrounding entries

        //Make unions at virtual's if applicable
        if (row == 0) {
            wquf.union(0, index);
        } else if (row == size) {
            wquf.union(index, (size * size) + 1);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkValidInput(row);
        checkValidInput(col);
        if (grid[row - 1][col - 1] == open) {
            return true;
        } else {
            return false;
        }
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        checkValidInput(row);
        checkValidInput(col);
        int index = getArrayIndex(row, col);
        if (wquf.connected(0, index)) {
            return true;
        } else {
            return false;
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openAmount;
    }

    // does the system percolate?
    public boolean percolates() {
        // if virtual top and virtual bottom union, yes, else no.
        if (wquf.connected(0, (size * size) + 1)) {
            return true;
        } else {
            return false;
        }
    }

    // Should argument be thrown?
    public boolean checkValidInput(int index) {
        if (index < 1 | index > size) {
            throw new IllegalArgumentException();
            return false;
        }
        return true;
    }

    //Takes row col inputs from functions and returns what equivalent entry
    //should be for wquf
    public int getArrayIndex(int row, int col) {
        int index = ((row - 1) * size) + col;
        return index;
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}