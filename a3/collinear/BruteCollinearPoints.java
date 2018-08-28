import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BruteCollinearPoints {
    private int n;
    private Point[] pointsCopy;
    private ArrayList<LineSegment> tempSegs;
    private LineSegment[] segs;
    private boolean alreadyRun = false;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

        // Allows us to find duplicates as well as make the algo easy to connect
        pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pointsCopy[i] = points[i];
        }
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        n = 0;
        tempSegs = new ArrayList<>();

        // NOTE: This implementation breaks if the below weren't true
        // For simplicity, we will not supply any input to BruteCollinearPoints
        // that has 5 or more collinear points

        // <= ~N^4 brute force, as told to do
        for (int i = 0; i < pointsCopy.length - 3; i++) {
            for (int j = i + 1; j < pointsCopy.length - 2; j++) {
                if (i == j) {
                    break;
                }
                for (int k = j + 1; k < pointsCopy.length - 1; k++) {
                    if (j == k) {
                        break;
                    }
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        if (k == l) {
                            break;
                        }
                        checkCollinear(pointsCopy[i], pointsCopy[j], pointsCopy[k], pointsCopy[l]);
                    }
                }
            }
        }

        segs = new LineSegment[tempSegs.size()];

        for (int i = 0; i < tempSegs.size(); i++) {
            segs[i] = tempSegs.get(i);
        }


    }

    private void checkCollinear(Point a, Point b, Point c, Point d) {
        // Since sorted above, we just need to check a->b, a->c, a->d
        // using == is bad practice but assignment says we can make assumptions

        Comparator<Point> comp = a.slopeOrder();
        if (comp.compare(b, c) == 0) {
            if(comp.compare(c, d) == 0) {
                LineSegment ls = new LineSegment(a, d);
                tempSegs.add(ls);
                n++;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return n;
    }

    // the line segments
    public LineSegment[] segments() {
        return segs;
    }
}

// Use
