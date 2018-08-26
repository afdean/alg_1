import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // Size of the Deque
    private int n;
    // Initially, first and last are the same node
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {

        // Per requirements
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // Link the new first the old first
        Node oldFirst = first;
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = oldFirst;

        // Link the old first to the new first
        if (n == 0) {
            last = newFirst;
            newFirst.previous = null;
        } else {
            oldFirst.previous = newFirst;
        }

        first = newFirst;
        n++;
    }

    // add the item to the end
    public void addLast(Item item) {

        // Per requirements
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // Link the new last the old last
        Node oldLast = last;
        Node newLast = new Node();
        newLast.item = item;
        newLast.previous = oldLast;

        // Link the old last to the new last
        if (n == 0) {
            first = newLast;
            newLast.next = null;
        } else {
            oldLast.next = newLast;
        }

        last = newLast;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item removed;

        // Per requirements
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        removed = first.item;

        if (size() == 1) {
            first = null;
            last = null;
        } else {
            Node newFirst;
            newFirst = first.next;
            newFirst.previous = null;
            first = newFirst;
        }

        n--;

        return removed;
    }

    // remove and return the item from the end
    public Item removeLast() {

        Item removed;

        // Per requirements
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        removed = last.item;

        if (size() == 1) {
            first = null;
            last = null;
        } else {
            Node newLast;
            newLast = last.previous;
            newLast.next = null;
            last = newLast;
        }

        n--;

        return removed;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (optional)
    public static void main(String[] args) {
        return;
    }

}

// Throw a java.lang.IllegalArgumentException if the client calls either
// addFirst() or addLast() with a null argument.

// Throw a java.util.NoSuchElementException if the client calls either
// removeFirst() or removeLast when the deque is empty.

// Throw a java.util.NoSuchElementException if the client calls the next()
// method in the iterator when there are no more items to return.

// Throw a java.lang.UnsupportedOperationException if the client calls the
// remove() method in the iterator.
