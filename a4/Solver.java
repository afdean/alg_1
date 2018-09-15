import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {

    private boolean solvable;
    private ArrayList<Board> soln;
    private MinPQ<Node> orgPQ;
    private MinPQ<Node> twinPQ;
    private Board initial;
    private Board twin;

    private class Node implements Comparable<Node> {
        // include the board, predecessor board, # moves, and compareto
        // (heuristic + moves)
        private Board board;
        private Node predecessor;
        private int moves;

        private Node(Board board, Node predecessor, int moves) {
            this.board = board;
            this.predecessor = predecessor;
            this.moves = moves;
        }

        @Override
        public int compareTo(Node that) {
            return (board.manhattan() + moves) - (that.board.manhattan() + that.moves);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        if (initial == null) {
            throw new IllegalArgumentException();
        }

        this.initial = initial;

        // Board to solve in lockstep
        twin = initial.twin();

        // Queues for each to run in lockstep
        orgPQ = new MinPQ<>();
        twinPQ = new MinPQ<>();
        orgPQ.insert(new Node(initial, null, 0));
        twinPQ.insert(new Node(twin, null, 0));

        // Use flag for debugging
        boolean flag = false;
        Node currentNodeOrg;
        Node currentNodeTwin;
        Iterable<Board> itrOrg;
        Iterable<Board> itrTwin;
        Node newNodeOrg;
        Node newNodeTwin;

        while (flag == false) {
            currentNodeOrg = orgPQ.delMin();
            currentNodeTwin = twinPQ.delMin();

            System.out.println("Something");

            if (currentNodeOrg.board.isGoal()) {
                solvable = true;
                flag = true;
                // Set the soln arraylist, reverse it
                soln = new ArrayList<>();
                soln.add(currentNodeOrg.board);
                while (currentNodeOrg.predecessor != null) {
                    soln.add(currentNodeOrg.predecessor.board);
                    currentNodeOrg = currentNodeOrg.predecessor;
                }
                Collections.reverse(soln);
                break;
            } else if (currentNodeTwin.board.isGoal()) {
                solvable = false;
                flag = true;
                break;
            }

            itrOrg = currentNodeOrg.board.neighbors();
            itrTwin = currentNodeTwin.board.neighbors();

            appendQueue(true, itrOrg, currentNodeOrg);
            appendQueue(false, itrTwin, currentNodeTwin);
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (solvable) {
            return soln.size() - 1;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        // TODO: Check if this is just for initial
        if (solvable) {
            return soln;
        } else {
            return null;
        }
    }

    private void appendQueue(boolean original, Iterable<Board> itr, Node currentNode) {
        Node newNode;
        for (Board b : itr) {
            if (!b.equals(currentNode.predecessor)) {
                newNode = new Node(b, currentNode, currentNode.moves + 1);
                if (original) {
                    orgPQ.insert(newNode);
                } else {
                    twinPQ.insert(newNode);
                }

            }
        }
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
