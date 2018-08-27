import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFastCollinearPoints {

    @Before
    public void setUp() throws Exception {
        return;
    }

    // Check if exception when null input
    @Test(expected = IllegalArgumentException.class)
    public void testCompareTo1() {
        FastCollinearPoints fc = new FastCollinearPoints(null);
        return;
    }

    // Check if exception when a point is null input
    @Test(expected = IllegalArgumentException.class)
    public void testCompareTo2() {
        Point[] pts = new Point[4];
        Point a = new Point(6, 3);
        Point b = new Point(0, 4);
        Point c = new Point(10, 2);
        pts[0] = a;
        pts[1] = b;
        pts[3] = c;
        FastCollinearPoints fc = new FastCollinearPoints(pts);
        return;
    }

    // Check if exception when duplicate point given
    @Test(expected = IllegalArgumentException.class)
    public void testCompareTo3() {
        Point[] pts = new Point[4];
        Point a = new Point(6, 3);
        Point b = new Point(0, 4);
        Point c = new Point(10, 2);
        Point d = new Point(6, 3);
        pts[0] = a;
        pts[1] = b;
        pts[2] = c;
        pts[3] = d;
        FastCollinearPoints fc = new FastCollinearPoints(pts);
        return;
    }

    // Scratch test
    @Test
    public void test() {
        Point[] pts = new Point[7];
        Point a = new Point(0, 1);
        Point b = new Point(1, 2);
        Point c = new Point(2, 3);
        Point d = new Point(3, 4);
        Point e = new Point(1, 3);
        Point f = new Point(2, 5);
        Point g = new Point(3, 7);
        pts[0] = a;
        pts[1] = f;
        pts[2] = b;
        pts[3] = c;
        pts[4] = e;
        pts[5] = g;
        pts[6] = d;
        Point test = c;
        Arrays.sort(pts, test.slopeOrder());
        for (Point pt : pts) {
            System.out.println(pt);
            System.out.println(test.slopeTo(pt));
        }
        return;
    }



}
