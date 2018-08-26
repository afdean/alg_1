import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

public class TestDeque {

    @Before
    public void setUp() throws Exception {
        int a = 2;
        return;
    }

    // Check if deque gets created
    @Test
    public void testDeque() {
        Deque test = new Deque<String>();
        return;
    }

    // Check if empty upon creation
    @Test
    public void testIsEmpty() {
        Deque test = new Deque<String>();
        Assert.assertEquals(test.isEmpty(), true);
        return;
    }

    // Check if size is 0 upon creation
    @Test
    public void testSize() {
        Deque test = new Deque<String>();
        Assert.assertEquals(test.size(), 0);
        return;
    }

    // Check if node gets added using size
    @Test
    public void testAddFirst1() {
        Deque test = new Deque<String>();
        String testString = "hello";
        test.addFirst(testString);
        Assert.assertEquals(test.size(), 1);
        return;
    }

    // Check if null argument throws exception
    @Test(expected = IllegalArgumentException.class)
    public void testAddFirst2() {
        Deque test = new Deque<String>();
        String testString = null;
        test.addFirst(testString);
    }

    // Check if node gets added using size
    @Test
    public void testAddLast1() {
        Deque test = new Deque<String>();
        String testString = "hello";
        test.addLast(testString);
        Assert.assertEquals(test.size(), 1);
        return;
    }

    // Check if null argument throws exception
    @Test(expected = IllegalArgumentException.class)
    public void testAddLast2() {
        Deque test = new Deque<String>();
        String testString = null;
        test.addLast(testString);
    }

    // Check if empty deque throws exception
    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirst1() {
        Deque test = new Deque<String>();
        Assert.assertEquals(test.size(), 0);
        test.removeFirst();
    }

    // Check for functionality after adding 1 item
    @Test
    public void testRemoveFirst2() {
        Deque test = new Deque<String>();
        String testString = "hello";
        test.addFirst(testString);
        Assert.assertEquals(test.removeFirst(), testString);
        Assert.assertEquals(test.size(), 0);
    }

    // Check for functionality after adding 1 item, removing it and removing again
    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirst3() {
        Deque test = new Deque<String>();
        String testString = "hello";
        test.addFirst(testString);
        Assert.assertEquals(test.removeFirst(), testString);
        Assert.assertEquals(test.size(), 0);
        test.removeFirst();
    }

    // Check for functionality after adding >1 item
    @Test
    public void testRemoveFirst4() {
        Deque test = new Deque<String>();
        String testString = "hello";
        String testString2 = "hello2";
        String testString3 = "hello3";
        test.addFirst(testString);
        test.addFirst(testString2);
        test.addFirst(testString3);
        Assert.assertEquals(test.removeFirst(), testString3);
        Assert.assertEquals(test.size(), 2);
    }

    // Check if empty deque throws exception
    @Test(expected = NoSuchElementException.class)
    public void testRemoveLast1() {
        Deque test = new Deque<String>();
        Assert.assertEquals(test.size(), 0);
        test.removeLast();
    }

    // Check for functionality after adding 1 item
    @Test
    public void testRemoveLast2() {
        Deque test = new Deque<String>();
        String testString = "hello";
        test.addLast(testString);
        Assert.assertEquals(test.removeLast(), testString);
        Assert.assertEquals(test.size(), 0);
    }

    // Check for functionality after adding 1 item, removing it and removing again
    @Test(expected = NoSuchElementException.class)
    public void testRemoveLast3() {
        Deque test = new Deque<String>();
        String testString = "hello";
        test.addLast(testString);
        Assert.assertEquals(test.removeLast(), testString);
        Assert.assertEquals(test.size(), 0);
        test.removeLast();
    }

    // Check for functionality after adding >1 item
    @Test
    public void testRemoveLast4() {
        Deque test = new Deque<String>();
        String testString = "hello";
        String testString2 = "hello2";
        String testString3 = "hello3";
        test.addLast(testString);
        test.addLast(testString2);
        test.addLast(testString3);
        Assert.assertEquals(test.removeLast(), testString3);
        Assert.assertEquals(test.size(), 2);
    }

    // Check Addfirst then removelast
    @Test
    public void testRemoves1() {
        Deque test = new Deque<String>();
        String testString = "hello";
        test.addFirst(testString);
        Assert.assertEquals(test.removeLast(), testString);
        Assert.assertEquals(test.size(), 0);
    }

    // check addlast then removefirst
    @Test
    public void testRemoves2() {
        Deque test = new Deque<String>();
        String testString = "hello";
        test.addLast(testString);
        Assert.assertEquals(test.removeFirst(), testString);
        Assert.assertEquals(test.size(), 0);
    }

    // Check for functionality after adding 2 first, then remove last
    @Test
    public void testRemoves3() {
        Deque test = new Deque<String>();
        String testString = "hello";
        String testString2 = "hello2";
        String testString3 = "hello3";
        test.addFirst(testString);
        test.addFirst(testString2);
        test.addFirst(testString3);
        Assert.assertEquals(test.removeLast(), testString);
        Assert.assertEquals(test.size(), 2);
    }

    // Check for functionality after adding 2 first, then remove first
    @Test
    public void testRemoves4() {
        Deque test = new Deque<String>();
        String testString = "hello";
        String testString2 = "hello2";
        String testString3 = "hello3";
        test.addLast(testString);
        test.addLast(testString2);
        test.addLast(testString3);
        Assert.assertEquals(test.removeFirst(), testString);
        Assert.assertEquals(test.size(), 2);
    }

    // General check on adding, and removing from either end
    @Test
    public void testRemoves5() {
        Deque test = new Deque<String>();

        String testString = "hello";
        String testString2 = "hello2";
        String testString3 = "hello3";
        String testString4 = "hello4";
        String testString5 = "hello5";

        test.addFirst(testString);
        test.addLast(testString2);
        test.addFirst(testString3);
        test.addFirst(testString4);
        test.addLast(testString5);

        // Order at this point should be 43125
        Assert.assertEquals(test.removeFirst(), testString4);
        Assert.assertEquals(test.size(), 4);

        // Order at this point should be 3125
        Assert.assertEquals(test.removeLast(), testString5);
        Assert.assertEquals(test.size(), 3);

        // Order at this point should be 312
        Assert.assertEquals(test.removeFirst(), testString3);
        Assert.assertEquals(test.size(), 2);

        // Order at this point should be 12
        Assert.assertEquals(test.removeFirst(), testString);
        Assert.assertEquals(test.size(), 1);

        // Order at this point should be 2
        Assert.assertEquals(test.removeLast(), testString2);
        Assert.assertEquals(test.size(), 0);
    }

}
