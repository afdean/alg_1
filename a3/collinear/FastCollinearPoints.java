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
        for (int i = 0; i < naturalCopy.length; i++) {
            Arrays.sort(slopeCopy, naturalCopy[i].slopeOrder());

            for (int j = 0; j < slopeCopy.length - 4; j++) {
                maximalCounter = 0;
                for (int k = j + 1; k < slopeCopy.length; k++) {
                    if(slopeCopy[j].slopeTo(slopeCopy[k]) == 0) {
                        maximalCounter++;
                    } else {
                        if (maximalCounter >= 4) {
                            // gather every point until this point and natural
                            // sort the line
                            Point[] lineArray = new Point[k - j];
                            int pointer = j;
                            for (int l = 0; l < lineArray.length; l++) {
                                lineArray[l] = slopeCopy[pointer];
                                pointer++;
                            }
                            Arrays.sort(lineArray);
                            // if smallest point is equal to ith point, add it
                            // using end points
                            if (lineArray[0].compareTo(naturalCopy[i]) == 0) {
                                LineSegment ls = new LineSegment(lineArray[0], lineArray[lineArray.length - 1]);
                                tempSegs.add(ls);
                                n++;
                            }

                        }
                        if (k < slopeCopy.length - 4) {
                            j = k;
                        } else {
                            break;
                        }
                    }
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
