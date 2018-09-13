import java.lang.Math;

public class Board {

    private int dim;
    private int[][] blocks;
    private int ham = -1;
    private int manhattan = -1;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dim = blocks.length;
        this.blocks = blocks;
    }

    // board dimension n
    public int dimension() {
        return dim;
    }

    // number of blocks out of place
    public int hamming() {
        // need to check dimension then iterate through n*n to see what the deal
        // is

        if (ham >= 0) {
            return ham;
        }

        int outOfPlace = 0;

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (blocks[i][j] == 0) {
                    continue;
                }
                int correctValue = (i * dim) + j + 1;
                if (blocks[i][j] != correctValue) {
                    outOfPlace++;
                }
            }
        }

        ham = outOfPlace;

        return ham;
    }

    private int calculateMan(int x0, int y0, int x1, int y1) {
        // |x1 - x2| + |y1 - y2|
        return (Math.abs(x0 - x1) + Math.abs(y0 - y1));
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {

        // Calculate manhattan distance for every point in linear time
        if (manhattan >= 0) {
            return manhattan;
        }

        int sum = 0;

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (blocks[i][j] == 0) {
                    continue;
                }

                int correctI = (blocks[i][j] - 1) / dim;
                int correctJ = blocks[i][j] - 1 - (correctI * dim);

                sum += calculateMan(i, j, correctI, correctJ);
            }
        }

        manhattan = sum;
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        // Iterate in order, return false if one is out, else return true if can
        // get through the loop
        return false;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        // Do not swap with a 0
        return null;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        // Same idea as isgoal, but just matching object y
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        // At most, 4 neighbors at most only
        // Find 0, then swap all with 0 going up, left, right, down, if possible;
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dim + "\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }

}
