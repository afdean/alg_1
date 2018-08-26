import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDeque {

    @Before
    public void setUp() throws Exception {
        int a = 2;
        return;
    }

    @Test
    public void testDeque() {
        Deque test = new Deque<Integer>();
        return;
    }

    @Test
    public void testIsEmpty() {
        Deque test = new Deque<Integer>();
        Assert.assertEquals(test.isEmpty(), true);
        return;
    }

    @Test
    public void testSize() {
        Deque test = new Deque<Integer>();
        Assert.assertEquals(test.size(), 0);
        return;
    }

    @Test
    public void testAddFirst() {
        Deque test = new Deque<Integer>();
        String testString = "hello";
        test.addFirst(testString);
        Assert.assertEquals(test.size(), 1);
        return;
    }

    @Test
    public void testAddLast() {
        Deque test = new Deque<Integer>();
        String testString = "hello";
        test.addLast(testString);
        Assert.assertEquals(test.size(), 1);
        return;
    }

}
