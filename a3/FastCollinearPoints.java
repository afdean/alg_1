import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private int n;
    private ArrayList<LineSegment> tempSegs;
    private LineSegment[] segs;
    private Point[] slopeCopy;
    private Point[] naturalCopy;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        // Exception if null input
        if (points == null) {
            throw new IllegalArgumentException();
        }

        // Exception if given a null point
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

        // Make a natural order list
        // Exception if duplicates
        naturalCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            naturalCopy[i] = points[i];
        }
        Arrays.sort(naturalCopy);
        for (int i = 0; i < naturalCopy.length - 1; i++) {
            if (naturalCopy[i].compareTo(naturalCopy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        // Set up globals
        n = 0;
        tempSegs = new ArrayList<>();
        slopeCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            slopeCopy[i] = points[i];
        }

        for (int i = 0; i < naturalCopy.length; i++) {

            // First entry of slope copy will be naturalCopy[i]
            Arrays.sort(slopeCopy, naturalCopy[i].slopeOrder());
            double currentSlope = Double.NEGATIVE_INFINITY;
            ArrayList<Point> potentialSeg = new ArrayList<>();
            for (int j = 1; j < slopeCopy.length; j++) {
                double nextSlope = slopeCopy[0].slopeTo(slopeCopy[j]);

                if (nextSlope == currentSlope) {
                    // System.out.println("Incrementing");
                    potentialSeg.add(slopeCopy[j]);
                }

                if (nextSlope != currentSlope || j == slopeCopy.length - 1) {
                    if (potentialSeg.size() >= 3) {
                        // add the line if possible
                        // line consists of origin plus accumulated points
                        potentialSeg.add(slopeCopy[0]);
                        Point[] lineArray = new Point[potentialSeg.size()];
                        for (int k = 0; k < lineArray.length; k++) {
                            lineArray[k] = potentialSeg.get(k);
                        }
                        Arrays.sort(lineArray);
                        // if smallest point period in the potential segment is
                        // equal to the origin sort point, we can add it
                        if (lineArray[0].compareTo(slopeCopy[0]) == 0) {
                            LineSegment ls = new LineSegment(lineArray[0], lineArray[lineArray.length - 1]);
                            tempSegs.add(ls);
                            n++;
                        }
                    }
                    //RESET THINGS
                    currentSlope = nextSlope;
                    potentialSeg = new ArrayList<>();
                    potentialSeg.add(slopeCopy[j]);
                }
            }
        }

        segs = new LineSegment[tempSegs.size()];

        for (int i = 0; i < tempSegs.size(); i++) {
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

    private void printArray(Object[] a) {
        for (Object obj : a) {
            System.out.println(obj);
        }
    }
}

