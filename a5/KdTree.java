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
        if (contains(p)) {
            return;
        }
        root = insert(root, p, true);
        size++;
    }

    // Substitute for writing comp
    private int compare2D(Node n, Point2D p, Boolean horizontal) {
        if (horizontal) {
            if (p.x() < n.p.x()) {
                return -1;
            } else {
                return 1;
            }
        } else {
            if (p.y() < n.p.y()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private Node insert(Node n, Point2D p, Boolean horizontal) {
        // Return node where leaf is found
        if (n == null) {
            return new Node(p, null);
        }

        // Make the decision on where to add node
        int comparison = compare2D(n, p, horizontal);

        // Add node to left/bottom or right/top
        if (comparison < 0) {
            n.lb = insert(n.lb, p, !horizontal);
        } else if (comparison > 0) {
            n.rt = insert(n.rt, p, !horizontal);
        }

        return n;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return get(p) != null;
    }

    private Point2D get(Point2D p) {
        return get(root, p, true);
    }

    private Point2D get(Node n, Point2D p, Boolean horizontal) {
        if (n == null) {
            return null;
        }
        if (n.p.equals(p)) {
            return p;
        }
        int comparison = compare2D(n, p, horizontal);
        if (comparison < 0) {
            return get(n.lb, p, !horizontal);
        } else {
            return get(n.rt, p, !horizontal);
        }
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
