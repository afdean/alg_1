import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private int n;
    private ArrayList<LineSegment> tempSegs;
    private LineSegment[] segs;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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

        // ALGO HERE


        segs = new LineSegment[tempSegs.size()];

        for (int i = 0; i < tempSegs.size() - 1; i++) {
            segs[i] = tempSegs.get(i);
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
