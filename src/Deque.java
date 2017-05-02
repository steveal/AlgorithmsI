import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;

    private Node<Item> last;

    private int size;

    private static class Node<Item> {
        private Item item;

        private Node<Item> before = null;

        private Node<Item> next = null;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
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

    // add the item to the front
    public void addFirst(Item item) {
        checkItem(item);
        Node<Item> node = new Node<Item>();
        node.item = item;
        if (this.size == 0) {
            first = node;
            last = node;
            size++;
        }
        else {
            first.before = node;
            node.next = first;
            first = node;            
            size++;
        }

    }

    // add the item to the end
    public void addLast(Item item) {
        checkItem(item);
        Node<Item> node = new Node<Item>();
        node.item = item;
        node.next = null;
        if (this.size == 0) {
            first = node;
            last = node;
            size++;
        }
        else {
            last.next = node;
            node.before = last;
            last = node;
            size++;
        }

    }

    // remove and return the item from the front
    public Item removeFirst() {

        checkDequeEmpty();
        if (size() == 1) {
            Item item = first.item;
            first = null;
            last = null;
            size--;
            return item;
        }
        else {
            Node<Item> oldfirst = first;
            first = oldfirst.next;
            first.before = null;
            oldfirst.next = null;
            size--;
            return oldfirst.item;
        }
    }

    // remove and return the item from the end
    public Item removeLast() {
        checkDequeEmpty();
        if (size() == 1) {
            Item item = last.item;
            first = null;
            last = null;
            size--;
            return item;
        }
        else {
            Node<Item> oldlast = last;
            last = oldlast.before;
            last.next = null;
            oldlast.before = null;
            size--;
            return oldlast.item;
        }

    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.removeLast();
    }
}