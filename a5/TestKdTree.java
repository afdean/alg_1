import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.lang.Math;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class TestKdTree {

    Point2D pt1;
    Point2D pt2;
    Point2D pt3;
    Point2D pt4;
    Point2D pt5;

    @Before
    public void setUp() throws Exception {
        pt1 = new Point2D(0.1, 0.1);
        pt2 = new Point2D(0.2, 0.2);
        pt3 = new Point2D(0.3, 0.3);
        pt4 = new Point2D(0.4, 0.4);
        pt5 = new Point2D(0.1, 0.05);
    }

    // //
    // @Test
    // public void testDraw1() {
    //     PointSET p = new PointSET();
    //     p.insert(pt1);
    //     p.insert(pt2);
    //     p.insert(pt3);
    //     p.insert(pt4);
    //     p.draw();
    //     StdDraw.show();
    //     // StdDraw.pause(20000);
    // }

    //
    @Test
    public void testIsEmpty1() {
        KdTree k = new KdTree();
        Assert.assertEquals(k.isEmpty(), true);
    }

    //
    @Test
    public void testIsEmpty2() {
        KdTree k = new KdTree();
        k.insert(pt1);
        Assert.assertEquals(k.isEmpty(), false);
    }

    //
    @Test
    public void testSize1() {
        KdTree k = new KdTree();
        k.insert(pt1);
        k.insert(pt2);
        k.insert(pt3);
        k.insert(pt4);
        Assert.assertEquals(k.size(), 4);
    }

    //
    @Test
    public void testSize2() {
        KdTree k = new KdTree();
        Assert.assertEquals(k.size(), 0);
    }

    //
    @Test
    public void testContains2() {
        KdTree k = new KdTree();
        k.insert(pt1);
        k.insert(pt2);
        k.insert(pt3);
        k.insert(pt4);
        Assert.assertEquals(k.contains(pt4), true);
    }

    //
    @Test
    public void testContains3() {
        KdTree k = new KdTree();
        k.insert(pt1);
        k.insert(pt2);
        k.insert(pt3);
        k.insert(pt4);
        k.insert(pt5);
        Assert.assertEquals(k.contains(pt5), true);
    }

    //
    @Test
    public void testIntersects1() {
        KdTree k = new KdTree();
        k.insert(pt1);
        k.insert(pt2);
        k.insert(pt3);
        k.insert(pt4);
        k.insert(pt5);
        RectHV rect = new RectHV(0, 0, 0.25, 0.25);
        Iterable<Point2D> pts = k.range(rect);
        // for (Point2D p : pts) {
        //     System.out.println(p);
        // }
    }

    //
    @Test
    public void testNearest1() {
        KdTree k = new KdTree();
        k.insert(pt1);
        k.insert(pt2);
        k.insert(pt3);
        k.insert(pt4);
        k.insert(pt5);
        Point2D pt = new Point2D(0.5, 0.5);
        Point2D nearest = k.nearest(pt);
        Assert.assertEquals(nearest, pt4);
    }

    // //
    // @Test
    // public void testDraw1() {
    //     KdTree k = new KdTree();
    //     k.insert(pt1);
    //     k.insert(pt2);
    //     k.insert(pt3);
    //     k.insert(pt4);
    //     k.insert(pt5);
    //     k.draw();
    //     StdDraw.show();
    //     StdDraw.pause(200000000);
    // }
}
