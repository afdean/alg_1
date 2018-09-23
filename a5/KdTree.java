import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {

    private Node root;
    private int size;

    private static class Node {
        private Point2D p; // the point
        private RectHV rect; // the axis-aligned rectangle corresponding to this node
        private Node lb; // the left/bottom subtree
        private Node rt; // the right/top subtree

        private Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    // construct an empty set of points
    public KdTree() {
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        // TODO: First, don't include duplicates by checking with contains
        root = insert(root, p, true);
        size++;
    }

    private Node insert(Node n, Point2D p, Boolean horizontal) {
        // TODO: Implement rect portion
        if (n == null) {
            return new Node(p, null);
        }
        int comparison;
        if (horizontal) {
            if (p.x() < n.p.x()) {
                comparison = -1;
            } else {
                comparison = 1;
            }
        } else {
            if (p.y() < n.p.y()) {
                comparison = -1;
            } else {
                comparison = 1;
            }
        }
        if (comparison < 0) {
            n.lb = insert(n.lb, p, !horizontal);
        } else if (comparison > 0) {
            n.rt = insert(n.rt, p, !horizontal);
        }
        return n;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return false;
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        return null;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
