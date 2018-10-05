import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import java.lang.IllegalArgumentException;


public class SAP {

    Digraph G;

    // NOTE: constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }
        this.G = G;
    }

    // TODO: Any vertex argument is outside its prescribed range, throw
    // Not sure if this is what they want exactly
    private boolean isValidVertex(int v) {
        if (v < 0 || v >= G.V()) {
            return false;
        }
        return true;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (!isValidVertex(v) || !isValidVertex(w)) {
            throw new IllegalArgumentException();
        }
        // Create 2 BFS one for v, one for w
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);

        // Get the shortest ancestor from ancestor()
        int ancestor = ancestor(v, w);

        if (ancestor < 0) {
            return -1;
        }


        return bfsV.distTo(ancestor) + bfsW.distTo(ancestor);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (!isValidVertex(v) || !isValidVertex(w)) {
            throw new IllegalArgumentException();
        }

        // Create a BFS for v
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, v);

        // Do a quick 'pseudo' bfs: iterate out from w until find something in
        // v's ancestors. Closest one must be the ancestor
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(w);
        boolean[] marked = new boolean[G.V()];
        marked[w] = true;

        int found = -1;
        while (!q.isEmpty()) {
            int dequeued = q.dequeue();
            for (int iterated : G.adj(dequeued)) {
                if (!marked[iterated]) {
                    if (bfs.hasPathTo(iterated)) {
                        found = iterated;
                        break;
                    }
                    marked[iterated] = true;
                    q.enqueue(iterated);
                }
            }
            if (found >= 0) {
                break;
            }
        }
        return found;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        for (Integer iterated : v) {
            if (iterated == null || !isValidVertex(iterated)) {
                throw new IllegalArgumentException();
            }
        }
        for (Integer iterated : w) {
            if (iterated == null || !isValidVertex(iterated)) {
                throw new IllegalArgumentException();
            }
        }
        return -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        for (Integer iterated : v) {
            if (iterated == null || !isValidVertex(iterated)) {
                throw new IllegalArgumentException();
            }
        }
        for (Integer iterated : w) {
            if (iterated == null || !isValidVertex(iterated)) {
                throw new IllegalArgumentException();
            }
        }
        return -1;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
