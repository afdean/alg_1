import java.util.NoSuchElementException;
import java.util.Random;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRandomizedQueue {

    @Before
    public void setUp() throws Exception {
        int a = 2;
        return;
    }

    @Test
    public void testRQ() {
        RandomizedQueue test = new RandomizedQueue<String>();
        return;
    }

    // Check if empty upon creation
    @Test
    public void testIsEmpty() {
        RandomizedQueue test = new RandomizedQueue<String>();
        Assert.assertEquals(test.isEmpty(), true);
        return;
    }

    // Check if size is 0 upon creation
    @Test
    public void testSize() {
        RandomizedQueue test = new RandomizedQueue<String>();
        Assert.assertEquals(test.size(), 0);
        return;
    }

    // Check if enqueue throws exception
    @Test(expected = IllegalArgumentException.class)
    public void testEnqueue1() {
        RandomizedQueue test = new RandomizedQueue<String>();
        String testString1 = null;
        test.enqueue(testString1);
        return;
    }

    // Check if 3 items can be added
    @Test
    public void testEnqueue2() {
        RandomizedQueue test = new RandomizedQueue<String>();
        String testString1 = "hello1";
        String testString2 = "hello2";
        String testString3 = "hello3";
        test.enqueue(testString1);
        test.enqueue(testString2);
        test.enqueue(testString3);
        Assert.assertEquals(test.size(), 3);
        return;
    }

    // Check if dequeue throws exception
    @Test(expected = NoSuchElementException.class)
    public void testDequeue1() {
        RandomizedQueue test = new RandomizedQueue<String>();
        test.dequeue();
        return;
    }

    // Check if dequeue functions
    @Test()
    public void testDequeue2() {
        RandomizedQueue test = new RandomizedQueue<String>();
        String testString1 = "hello";
        String testString2 = "hello";
        String testString3 = "hello";
        test.enqueue(testString1);
        test.enqueue(testString2);
        test.enqueue(testString3);
        Assert.assertEquals(test.dequeue(), "hello");
        Assert.assertEquals(test.size(), 2);
        return;
    }

    // Check for iterator creation
    @Test
    public void testIterator1() {
        RandomizedQueue test = new RandomizedQueue<String>();
        Iterator itr = test.iterator();
    }

    // Check if iterator next throws exception
    @Test(expected = NoSuchElementException.class)
    public void testIterator2() {
        RandomizedQueue test = new RandomizedQueue<String>();
        Iterator itr = test.iterator();
        itr.next();
        return;
    }

    // Check if iterator next throws exception
    @Test(expected = UnsupportedOperationException.class)
    public void testIterator3() {
        RandomizedQueue test = new RandomizedQueue<String>();
        Iterator itr = test.iterator();
        itr.remove();
        return;
    }
}
