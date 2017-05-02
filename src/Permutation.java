import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int cnt = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        Iterator<String> is = queue.iterator();
        while (is.hasNext() && cnt-- > 0) {
            System.out.println(is.next());
        }
    }
}