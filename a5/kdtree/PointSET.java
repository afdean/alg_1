import java.util.Set;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class PointSET {

    private SET<Point2D> pset;

    // construct an empty set of points
    public PointSET() {
        pset = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return pset.isEmpty();
    }

    // number of points in the set
    public int size() {
        return pset.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        pset.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return pset.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        // StdDraw.clear();
        for (Point2D pt : pset) {
            pt.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> rng = new ArrayList<>();
        for (Point2D pt : pset) {
            if (rect.contains(pt)) {
                rng.add(pt);
            }
        }
        return rng;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (size() == 0) {
            return null;
        }

        // Arbitrarily grab a starting point
        Point2D nearest = pset.min();
        double dist = nearest.distanceSquaredTo(p);

        for (Point2D pt : pset) {
            if (pt.distanceSquaredTo(p) < dist) {
                nearest = pt;
                dist = pt.distanceSquaredTo(p);
            }
        }

        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
