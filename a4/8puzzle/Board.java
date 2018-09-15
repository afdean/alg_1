import java.lang.Math;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;

public class Board {

    private int dim;
    private int[][] blocks;
    private int ham = -1;
    private int manhattan = -1;
    private int goal = -1;

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
                if (blocks[i][j] != calculateCorrect(i, j)) {
                    outOfPlace++;
                }
            }
        }

        ham = outOfPlace;
        return ham;
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
        if (goal >= 0) {
            if (goal == 0) {
                return false;
            } else {
                return true;
            }
        }

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (blocks[i][j] == 0) {
                    continue;
                }
                if (blocks[i][j] != calculateCorrect(i, j)) {
                    goal = 0;
                    return false;
                }
            }
        }

        goal = 1;
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        // NOTE:  Mostly untested

        // Do not swap with a blank block
        Board twin = new Board(copyBlocks());

        int firstI = -1;
        int firstJ = -1;
        int first = -1;
        int secondI = -1;
        int secondJ = -1;
        int second = -1;

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (twin.blocks[i][j] == 0) {
                    continue;
                }
                if (first < 0) {
                    firstI = i;
                    firstJ = j;
                    first = twin.blocks[i][j];
                } else if (second < 0) {
                    secondI = i;
                    secondJ = j;
                    second = twin.blocks[i][j];
                } else {
                    break;
                }
            }
            if (second >= 0) {
                break;
            }
        }

        twin.blocks[firstI][firstJ] = second;
        twin.blocks[secondI][secondJ] = first;

        return twin;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }

        if (y == null) {
            return false;
        }

        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;

        if (that.dim != this.dim) {
            return false;
        }

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // All possible swaps around empty block
    public Iterable<Board> neighbors() {

        int zeroI = -1;
        int zeroJ = -1;

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (blocks[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                    break;
                }
            }
            if (zeroI >= 0) {
                break;
            }
        }

        // if (zeroI < 0 || zeroJ < 0) {
        //     return null;
        // }

        int swapI;
        int swapJ;
        Queue<Board> q = new Queue<>();

        // Get all possible cases, add to queue, and return
        Board neighbor;
        for (int a = -1; a <= 1; a += 2) {
            for (int b = -1; b <= 1; b += 2) {
                // TODO: Make this cleaner
                if (a < 0) {
                    swapI = zeroI + b;
                    swapJ = zeroJ;
                } else {
                    swapJ = zeroJ + b;
                    swapI = zeroI;
                }

                // Create board
                if (swapI >= 0 && swapI <= dim - 1 && swapJ >=0 && swapJ <= dim - 1) {
                    neighbor = new Board(copyBlocks());
                    neighbor.blocks[zeroI][zeroJ] = neighbor.blocks[swapI][swapJ];
                    neighbor.blocks[swapI][swapJ] = 0;
                    q.enqueue(neighbor);
                }
            }
        }

        return q;
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

    private int calculateMan(int x0, int y0, int x1, int y1) {
        // |x1 - x2| + |y1 - y2|
        return (Math.abs(x0 - x1) + Math.abs(y0 - y1));
    }

    private int calculateCorrect(int i, int j) {
        return (i * dim) + j + 1;
    }

    private int[][] copyBlocks() {
        int[][] copy = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                copy[i][j] = blocks[i][j];
            }
        }
        return copy;
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }

}
