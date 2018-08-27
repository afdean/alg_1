import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 0;
        q = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // resize
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (n == q.length) {
            resize(2 * n);
        }
        q[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int rand = StdRandom.uniform(n);
        Item removed = q[rand];

        q[rand] = q[n - 1];
        q[n - 1] = null;

        n--;

        if (n > 0 && n <= q.length / 4) {
            resize(q.length / 2);
        }

        return removed;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int rand = StdRandom.uniform(n);
        Item removed = q[rand];
        return removed;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    // make the iterator use knuth shuffle for linear time
    private class QueueIterator implements Iterator<Item> {
        int current = 0;
        Item[] copied = (Item[]) new Object[n];

        private QueueIterator() {
            for (int i = 0; i < n; i++) {
                copied[i] = q[i];
            }
            StdRandom.shuffle(copied);
        }

        public boolean hasNext() {
            if (isEmpty() || current == copied.length) {
                return false;
            } else {
                return true;
            }
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item returned = copied[current];
            current++;
            return returned;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
    }
}

// Throw a java.lang.IllegalArgumentException if the client calls enqueue() with
// a null argument.

// Throw a java.util.NoSuchElementException if the client calls either sample()
// or dequeue() when the randomized queue is empty.

// Throw a java.util.NoSuchElementException if the client calls the next()
// method in the iterator when there are no more items to return.

// Throw a java.lang.UnsupportedOperationException if the client calls the
// remove() method in the iterator.
