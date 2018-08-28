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
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

        // Allows us to find duplicates as well as make the algo easy to connect
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

        int maximalCounter;
        // printArray(naturalCopy);
        for (int i = 0; i < naturalCopy.length; i++) {
            // First entry of slope copy will be naturalCopy[i]
            Arrays.sort(slopeCopy, naturalCopy[i].slopeOrder());

            // System.out.println("Next i iteration");
            // printArray(slopeCopy);

            double currentSlope = Double.NEGATIVE_INFINITY;
            // Need this to hit 3 (origin + 3 more at least)
            maximalCounter = 0;

            ArrayList<Point> potentialSeg = new ArrayList<>();
            potentialSeg.add(slopeCopy[0]);

            // TODO: Fix maximal counter off, this is a bad implementation overall
            for (int j = 1; j < slopeCopy.length; j++) {
                // if the slope is equal to current scope increment maximal
                // counter
                double nextSlope = slopeCopy[0].slopeTo(slopeCopy[j]);

                if (nextSlope == currentSlope) {
                    // System.out.println("Incrementing");
                    maximalCounter++;
                }

                if (nextSlope != currentSlope || j == slopeCopy.length - 1) {
                    if (maximalCounter >= 3) {
                        // add the line if possible
                        // line consists of origin plus accumulated points
                        Point[] lineArray = new Point[maximalCounter + 1];
                        lineArray[0] = slopeCopy[0];
                        int pointer;
                        if (j == slopeCopy.length - 1) {
                            pointer = j - maximalCounter + 1;
                        } else {
                            pointer = j - maximalCounter;
                        }
                        for (int l = 1; l < lineArray.length; l++) {
                            lineArray[l] = slopeCopy[pointer];
                            pointer++;
                        }
                        Arrays.sort(lineArray);
                        // System.out.println(lineArray);
                        // if smallest point period is equal to point we started our
                        // sort on the slopecopy, add it using end points
                        if (lineArray.length >= 4 && lineArray[0].compareTo(naturalCopy[i]) == 0) {
                            // System.out.println("I'm here");
                            LineSegment ls = new LineSegment(lineArray[0], lineArray[lineArray.length - 1]);
                            tempSegs.add(ls);
                            n++;
                        }
                    }
                    //RESET THINGS
                    currentSlope = nextSlope;
                    maximalCounter = 1;
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

// GO in original natural order
// for each point in natural order, sort copy using its comparator
// keep 2 indices and iterate through copy starting at i=j and j=I+3
// If the slope at i an j is the same, j ++ keep going
// If the slope is not the same but counter is at 4, maximal line is found
// take the line from i to j-1, sort by natural order
// if the origin of the line is the original point chosen make a line segment
// if not, this line already exists in our line segments
// now, increment i to where j is and increment j to 3 spaces forward, as long
// as its part of the array still!
