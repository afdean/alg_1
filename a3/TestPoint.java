import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPoint {

    @Before
    public void setUp() throws Exception {
        return;
    }

    Point p = new Point(1, 2);

    // Check if compareTo is functioning properly
    @Test
    public void testCompareTo1() {
        Point q = new Point(1,3);
        Assert.assertEquals(p.compareTo(q), -1);
        return;
    }

    // Check if compareTo is functioning properly
    @Test
    public void testCompareTo2() {
        Point q = new Point(1, 3);
        Assert.assertEquals(q.compareTo(p), 1);
        return;
    }

    // Check if compareTo is functioning properly
    @Test
    public void testCompareTo3() {
        Point q = new Point(1, 2);
        Assert.assertEquals(q.compareTo(p), 0);
        return;
    }

    // Check if compareTo is functioning properly
    @Test
    public void testCompareTo4() {
        Point q = new Point(0, 2);
        Assert.assertEquals(p.compareTo(q), 1);
        return;
    }

    // Check if compareTo is functioning properly
    @Test
    public void testCompareTo5() {
        Point q = new Point(2, 2);
        Assert.assertEquals(p.compareTo(q), -1);
        return;
    }

    // Check if slope is functioning properly
    @Test
    public void testSlopeTo1() {
        Point q = new Point(2, 3);
        Assert.assertEquals(p.slopeTo(q), 1, .01);
        return;
    }

    // Check if slope is functioning properly
    @Test
    public void testSlopeTo2() {
        Point q = new Point(2, 3);
        Assert.assertEquals(q.slopeTo(p), 1, .01);
        return;
    }

    // Check if slope is functioning properly
    @Test
    public void testSlopeTo3() {
        Point q = new Point(1, 2);
        Assert.assertEquals(p.slopeTo(q), Double.NEGATIVE_INFINITY, .01);
        return;
    }

    // Check if slope is functioning properly
    @Test
    public void testSlopeTo4() {
        Point q = new Point(1, 3);
        Assert.assertEquals(p.slopeTo(q), Double.POSITIVE_INFINITY, .01);
        return;
    }

    // Check if slope is functioning properly
    @Test
    public void testSlopeTo5() {
        Point q = new Point(1, 1);
        Assert.assertEquals(p.slopeTo(q), Double.POSITIVE_INFINITY, .01);
        return;
    }

    // Check if slope is functioning properly
    @Test
    public void testSlopeTo6() {
        Point q = new Point(3, 3);
        Assert.assertEquals(p.slopeTo(q), 0.5, .01);
        return;
    }

    // Check if slope order is functioning properly
    @Test
    public void testSlopeOrder1() {
        Point q = new Point(2, 3);
        Point r = new Point(2, 4);
        Comparator<Point> comp = p.slopeOrder();
        Assert.assertEquals(comp.compare(q, r), -1);
        return;
    }

    // Check if slope order is functioning properly
    @Test
    public void testSlopeOrder2() {
        Point q = new Point(2, 3);
        Point r = new Point(2, 4);
        Comparator<Point> comp = p.slopeOrder();
        Assert.assertEquals(comp.compare(r, q), 1);
        return;
    }

    // Check if slope order is functioning properly
    @Test
    public void testSlopeOrder3() {
        Point q = new Point(2, 3);
        Point r = new Point(2, 3);
        Comparator<Point> comp = p.slopeOrder();
        Assert.assertEquals(comp.compare(q, r), 0);
        return;
    }
}
