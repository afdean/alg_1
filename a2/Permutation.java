import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int printed = Integer.parseInt(args[0]);

        boolean flag = false;

        String next = null;

        RandomizedQueue rq = new RandomizedQueue<String>();

        while(!flag) {

            try {
                next = StdIn.readString();
            } catch (NoSuchElementException e) {
                break;
            }

            if (next == null) {
                continue;
            }

            rq.enqueue(next);
        }

        Iterator<String> itr = rq.iterator();

        for (int i = 0; i < printed; i++) {
            System.out.println(itr.next());
        }

    }
}
