import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

public class Solver {

    private boolean solvable;
    private ArrayList<Board> soln;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        if (initial == null) {
            throw new IllegalArgumentException();
        }

        private class Node implements Comparable<Node> {
            // include the board, predecessor board, # moves, and compareto
            // (heuristic + moves)
            private Board board;
            private Board predecessor;
            private int moves;

            private Node(Board board, Board predecessor, int moves) {
                this.board = board;
                this.predecessor = predecessor;
                this.moves = moves;
            }

            @Override
            public int compareTo(Node that) {
                return (board.manhattan() + moves) - (that.board.manhattan() + that.moves);
            }
        }

        // Board to solve in lockstep
        Board twin = initial.twin();

        // Queues for each to run in lockstep
        MinPQ<Node> orgPQ = new MinPQ<>();
        MinPQ<Node> twinPQ = new MinPQ<>();
        orgPQ.insert(new Node(initial, null, 0));
        twinPQ.insert(new Node(twin, null, 0));

        boolean flag = false;
        Node currentNodeOrg;
        Node currentNodeTwin;

        while (flag == false) {
            currentNodeOrg = orgPQ.delMin();
            currentNodeTwin = twinPQ.delMin();

            if (currentNodeOrg.board.isGoal()) {
                solvable = true;
                flag = true;
                break;
            } else if (currentNodeTwin.board.isGoal()) {
                solvable = false;
                flag = true;
                break;
            }
        }

        // Set which board solved it
        // Set the soln arraylist
        // Just keep going back through predecessors!

        // The key for adding to the queue will be moves + manhattan
        // To get steps gtaken so far, take key - manhattan then youure good

        // every time you pop from pq, check if its goal


    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return soln.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return soln;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
