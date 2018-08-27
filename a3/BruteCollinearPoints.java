import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private int n;
    private ArrayList<LineSegment> tempSegs;
    private LineSegment[] segs;

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
        Arrays.sort(points);
        for (int i = 0; i < points.length - 2; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        n = 0;
        tempSegs = new ArrayList<>();

        // <= ~N^4 brute force, as told to do
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                if (i == j) {
                    break;
                }
                for (int k = j + 1; k < points.length - 1; k++) {
                    if (j == k) {
                        break;
                    }
                    for (int l = k + 1; l < points.length; l++) {
                        if (k == l) {
                            break;
                        }
                        System.out.println(i);
                        System.out.println(j);
                        System.out.println(k);
                        System.out.println(l);
                        checkCollinear(points[i], points[j], points[k], points[l]);
                    }
                }
            }
        }

        segs = new LineSegment[tempSegs.size()];

        for (int i = 0; i < tempSegs.size() - 1; i++) {
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
