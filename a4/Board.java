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

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {

        if (manhattan >= 0) {
            return manhattan;
        }

        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        // Do not swap with a 0
        return null;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        // At most, 4 neighbors at most only
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
