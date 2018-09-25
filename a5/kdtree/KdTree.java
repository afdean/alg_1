import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

public class KdTree {

    private Node root;
    private int size;
    private Point2D nearest;

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
        if (p == null) {
            throw new IllegalArgumentException();
        }
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
        if (p == null) {
            throw new IllegalArgumentException();
        }
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
        drawPoint(root, red);
    }

    private void drawPoint(Node n, Boolean red) {
        if (n == null) {
            return;
        }

        // Draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(n.p.x(), n.p.y());

        // Draw the line
        StdDraw.setPenRadius();
        if (red) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.p.x(), n.rect.ymin(), n.p.x(), n.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.p.y());
        }

        // Draw the children
        drawPoint(n.lb, !red);
        drawPoint(n.rt, !red);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> points = new ArrayList<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node n, RectHV rect, ArrayList<Point2D> points) {
        if (n == null) {
            return;
        }

        // if node rect doesn't intersect, no need to continue going in depth
        if (!n.rect.intersects(rect)) {
            return;
        }

        // Add if point is in the rect
        if (rect.contains(n.p)) {
            points.add(n.p);
        }

        // Check the children
        range(n.lb, rect, points);
        range(n.rt, rect, points);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        // TODO: A better way than using a global variable...
        if (p == null) {
            throw new IllegalArgumentException();
        }
        nearest = null;
        Boolean horizontal = true;
        nearest(root, p, horizontal);
        return nearest;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    private void nearest(Node n, Point2D p, Boolean horizontal) {
        // NOTE: USING DISTANCESQUARED ALWAYS

        if (n == null) {
            return;
        }

        if (nearest == null) {
            nearest = n.p;
        }

        // Pruning optimization
        // If everything in this subtree is farther than the nearest found
        // already, don't bother.
        if (n.rect.distanceSquaredTo(p) > nearest.distanceSquaredTo(p)) {
            return;
        }

        // Check node's current point is closer
        if (n.p.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
            nearest = n.p;
        }

        // Optimize search order
        int comparison = compare2D(n, p, horizontal);
        if (comparison < 0) {
            nearest(n.lb, p, !horizontal);
            nearest(n.rt, p, !horizontal);
        } else {
            nearest(n.rt, p, !horizontal);
            nearest(n.lb, p, !horizontal);
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
