import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] p;

    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        p = (Item[]) new Object[2];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        assert capacity >= p.length;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = p[i];
        }
        p = temp;
    }

    // add the item
    public void enqueue(Item item) {
        checkItem(item);
        if (p.length == size) {
            resize(2 * p.length);
        }
        p[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        checkDequeEmpty();
        int index = StdRandom.uniform(0, size);
        Item item = p[index];
        if (index != size - 1) {
            p[index] = p[size - 1];
        }
        p[size - 1] = null;
        size--;
        if( size > 0 && size == p.length/4) {
            resize(p.length/2);
        }
        return item;

    }

    // return (but do not remove) a random item
    public Item sample() {
        checkDequeEmpty();
        int index = StdRandom.uniform(0, size);
        return p[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Item[] copyArray;

        private int point = 0;

        public ListIterator() {
            copyArray = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copyArray[i] = (Item) p[i];
            }
            StdRandom.shuffle(copyArray);
        }

        public boolean hasNext() {
            return point < copyArray.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = (Item) copyArray[point++];
            return item;
        }
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Item should not be null");
        }
    }

    private void checkDequeEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }
}