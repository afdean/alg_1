import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue

   public boolean isEmpty()                 // is the randomized queue empty?

   public int size()                        // return the number of items on the randomized queue

   public void enqueue(Item item)           // add the item

   public Item dequeue()                    // remove and return a random item

   public Item sample()                     // return a random item (but do not remove it)

   public Iterator<Item> iterator()         // return an independent iterator over items in random order

   public static void main(String[] args)   // unit testing (optional)
}

// Throw a java.lang.IllegalArgumentException if the client calls enqueue() with
// a null argument.

// Throw a java.util.NoSuchElementException if the client calls either sample()
// or dequeue() when the randomized queue is empty.

// Throw a java.util.NoSuchElementException if the client calls the next()
// method in the iterator when there are no more items to return.

// Throw a java.lang.UnsupportedOperationException if the client calls the
// remove() method in the iterator.
