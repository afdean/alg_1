import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

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

    private static class RectDim {
        private double xmin; // the point
        private double xmax; // the axis-aligned rectangle corresponding to this node
        private double ymin; // the left/bottom subtree
        private double ymax; // the right/top subtree

        private RectDim(double xmin, double ymin, double xmax, double ymax) {
            this.xmin = xmin;
            this.ymin = ymin;
            this.xmax = xmax;
            this.ymax = ymax;
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
        RectDim rd = new RectDim(0, 0, 1, 1);
        root = insert(root, p, true, rd);
        size++;
    }

    // Substitute for writing comp
    // Horizontal refers to whether or not next point is l/r inserted
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

    private Node insert(Node n, Point2D p, Boolean horizontal, RectDim rd) {
        // Return node where leaf is found
        if (n == null) {
            RectHV rect = new RectHV(rd.xmin, rd.ymin, rd.xmax, rd.ymax);
            return new Node(p, rect);
        }

        // Make the decision on where to add node
        int comparison = compare2D(n, p, horizontal);

        // Add node to left/bottom or right/top
        if (comparison < 0) {
            // Update rd
            if (horizontal) {
                rd.xmax = n.p.x();
            }
            if (!horizontal) {
                rd.ymax = n.p.y();
            }
            n.lb = insert(n.lb, p, !horizontal, rd);
        } else if (comparison > 0) {
            // Update rd
            if (horizontal) {
                rd.xmin = n.p.x();
            }
            if (!horizontal) {
                rd.ymin = n.p.y();
            }
            n.rt = insert(n.rt, p, !horizontal, rd);
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
        Boolean red = true;
        drawPoint(root, null, red);
    }

    private void drawPoint(Node n, Node previous, Boolean red) {
        if (n == null) {
            return;
        }
        // Draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(n.p.x(), n.p.y());

        // Draw the line
        StdDraw.setPenRadius();
        // line(double x1, double y1, double x2, double y2)
        if (red) {
            StdDraw.setPenColor(StdDraw.RED);
            if (previous == null) {
                StdDraw.line(n.p.x(), 0, n.p.x(), 1);
            }

            // TODO: Use rect of n and point from previous node to determine boundaries
            StdDraw.line(n.p.x(), n.rect.ymin(), n.p.x(), n.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);

            // TODO: Use rect of n and point from previous node to determine boundaries
            StdDraw.line(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.p.y());
        }

        // Draw the children
        drawPoint(n.lb, n, !red);
        drawPoint(n.rt, n, !red);
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
