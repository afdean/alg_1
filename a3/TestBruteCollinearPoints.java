import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBruteCollinearPoints {

    @Before
    public void setUp() throws Exception {
        return;
    }

    // // Check if exception when null input
    // @Test(expected = IllegalArgumentException.class)
    // public void testCompareTo1() {
    //     BruteCollinearPoints bc = new BruteCollinearPoints(null);
    //     return;
    // }

    // // Check if exception when a point is null input
    // @Test(expected = IllegalArgumentException.class)
    // public void testCompareTo2() {
    //     Point[] pts = new Point[4];
    //     Point a = new Point(6, 3);
    //     Point b = new Point(0, 4);
    //     Point c = new Point(10, 2);
    //     pts[0] = a;
    //     pts[1] = b;
    //     pts[3] = c;
    //     BruteCollinearPoints bc = new BruteCollinearPoints(pts);
    //     return;
    // }

    // // Check if exception when duplicate point given
    // @Test(expected = IllegalArgumentException.class)
    // public void testCompareTo3() {
    //     Point[] pts = new Point[4];
    //     Point a = new Point(6, 3);
    //     Point b = new Point(0, 4);
    //     Point c = new Point(10, 2);
    //     Point d = new Point(6, 3);
    //     pts[0] = a;
    //     pts[1] = b;
    //     pts[2] = c;
    //     pts[3] = d;
    //     BruteCollinearPoints bc = new BruteCollinearPoints(pts);
    //     return;
    // }

    // // Check for trivial case with 4 points (scrambled)
    // @Test
    // public void testCompareTo4() {
    //     Point[] pts = new Point[4];
    //     Point a = new Point(1, 2);
    //     Point b = new Point(2, 3);
    //     Point c = new Point(7, 8);
    //     Point d = new Point(4, 5);
    //     pts[0] = a;
    //     pts[1] = b;
    //     pts[2] = c;
    //     pts[3] = d;
    //     BruteCollinearPoints bc = new BruteCollinearPoints(pts);

    //     Assert.assertEquals(bc.numberOfSegments(), 1);

    //     return;
    // }

    // // Check for trivial case with >4 points with 4 forming line (scrambled)
    // @Test
    // public void testCompareTo5() {
    //     Point[] pts = new Point[6];
    //     Point a = new Point(1, 2);
    //     Point b = new Point(2, 3);
    //     Point c = new Point(7, 8);
    //     Point d = new Point(4, 5);
    //     Point e = new Point(10, 12);
    //     Point f = new Point(7, 7);
    //     pts[0] = a;
    //     pts[1] = b;
    //     pts[2] = c;
    //     pts[3] = d;
    //     pts[4] = e;
    //     pts[5] = f;
    //     BruteCollinearPoints bc = new BruteCollinearPoints(pts);

    //     Assert.assertEquals(bc.numberOfSegments(), 1);

    //     return;
    // }

    // // Check for horizontal line
    // @Test
    // public void testCompareTo6() {
    //     Point[] pts = new Point[4];
    //     Point a = new Point(1, 2);
    //     Point b = new Point(2, 2);
    //     Point c = new Point(7, 2);
    //     Point d = new Point(8, 2);
    //     pts[0] = a;
    //     pts[1] = b;
    //     pts[2] = c;
    //     pts[3] = d;
    //     BruteCollinearPoints bc = new BruteCollinearPoints(pts);
    //     Assert.assertEquals(bc.numberOfSegments(), 1);
    //     return;
    // }

    // // check for vertical line
    // @Test
    // public void testCompareTo7() {
    //     Point[] pts = new Point[4];
    //     Point a = new Point(1, 2);
    //     Point b = new Point(1, 3);
    //     Point c = new Point(1, 4);
    //     Point d = new Point(1, 7);
    //     pts[0] = a;
    //     pts[1] = b;
    //     pts[2] = c;
    //     pts[3] = d;
    //     BruteCollinearPoints bc = new BruteCollinearPoints(pts);
    //     Assert.assertEquals(bc.numberOfSegments(), 1);
    //     return;
    // }

    // // check for multiple line segments (1x, 2x, horizontal and vertical and
    // // random points)
    // @Test
    // public void testCompareTo8() {
    //     Point[] pts = new Point[16];

    //     // Horizontal
    //     Point a = new Point(310, 30);
    //     Point b = new Point(320, 30);
    //     Point c = new Point(330, 30);
    //     Point d = new Point(340, 30);

    //     // Vertical
    //     Point e = new Point(20, 201);
    //     Point f = new Point(20, 202);
    //     Point g = new Point(20, 203);
    //     Point h = new Point(20, 204);

    //     // 1x
    //     Point i = new Point(1, 2);
    //     Point j = new Point(2, 3);
    //     Point k = new Point(7, 8);
    //     Point l = new Point(4, 5);

    //     // 2x
    //     Point m = new Point(10, 12);
    //     Point n = new Point(12, 14);
    //     Point o = new Point(14, 16);
    //     Point p = new Point(16, 18);

    //     pts[0] = a;
    //     pts[1] = b;
    //     pts[2] = c;
    //     pts[3] = d;

    //     pts[4] = e;
    //     pts[5] = f;
    //     pts[6] = g;
    //     pts[7] = h;

    //     pts[8] = i;
    //     pts[9] = j;
    //     pts[10] = k;
    //     pts[11] = l;

    //     pts[12] = m;
    //     pts[13] = n;
    //     pts[14] = o;
    //     pts[15] = p;

    //     BruteCollinearPoints bc = new BruteCollinearPoints(pts);
    //     Assert.assertEquals(bc.numberOfSegments(), 4);
    //     return;
    // }

    // Check for >4 points on same slope
    @Test
    public void testCompareTo9() {
        Point[] pts = new Point[6];
        Point a = new Point(1, 2);
        Point b = new Point(2, 3);
        Point c = new Point(3, 4);
        Point d = new Point(5, 6);
        Point e = new Point(8, 9);
        Point f = new Point(9, 10);
        pts[0] = a;
        pts[1] = b;
        pts[2] = c;
        pts[3] = d;
        pts[4] = e;
        pts[5] = f;
        BruteCollinearPoints bc = new BruteCollinearPoints(pts);
        Assert.assertEquals(bc.numberOfSegments(), 1);
        return;
    }
}
