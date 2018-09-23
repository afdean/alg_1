import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.lang.Math;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class TestKdTree {

    Point2D pt1;
    Point2D pt2;
    Point2D pt3;
    Point2D pt4;

    @Before
    public void setUp() throws Exception {
        pt1 = new Point2D(0.1, 0.1);
        pt2 = new Point2D(0.2, 0.2);
        pt3 = new Point2D(0.3, 0.3);
        pt4 = new Point2D(0.4, 0.4);
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

    // //
    // @Test(expected = IllegalArgumentException.class)
    // public void testInsert1() {
    //     PointSET p = new PointSET();
    //     p.insert(null);
    // }

    // //
    // @Test(expected = IllegalArgumentException.class)
    // public void testContains1() {
    //     PointSET p = new PointSET();
    //     p.contains(null);
    // }

    // //
    // @Test
    // public void testContains2() {
    //     PointSET p = new PointSET();
    //     p.insert(pt1);
    //     Assert.assertEquals(p.contains(pt1), true);
    // }

    // // Just a check for the function itself
    // @Test
    // public void testNearest1() {
    //     PointSET p = new PointSET();
    //     p.insert(pt3);
    //     p.insert(pt4);
    //     Assert.assertEquals(p.nearest(pt1), pt3);
    // }

    // // Just a check for the function itself
    // @Test
    // public void testRange1() {
    //     PointSET p = new PointSET();
    //     RectHV r = new RectHV(.25, .25, .5, .5);
    //     p.insert(pt2);
    //     p.insert(pt3);
    //     p.insert(pt4);
    //     Iterable<Point2D> al = p.range(r);
    //     // for (Point2D pt : al) {
    //     // System.out.println(pt);
    //     // }
    // }
}
