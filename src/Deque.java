import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private Node first, last;
    private int size = 0;

    // construct an empty deque
    public Deque() {
        Deque<Item> deque = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (oldFirst == null) {
            first.next = null;
            last = first;
        } else {

            first.next = oldFirst;
            oldFirst.prev = first;

        }
        first.prev = null;
        size++;

    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (oldLast == null) {
            last.prev = null;
            first = last;
        } else {
            oldLast.next = last;
            last.prev = oldLast;

        }
        last.next = null;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item removedItem = first.item;
        if (first.next != null)
            first = first.next;
        first.prev = null;
        size--;
        return removedItem;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item removedItem = last.item;
        if (last.prev != null) {
            last = last.prev;
        }
        last.next = null;
        size--;
        return removedItem;

    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();

    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> queue = new Deque<>();
        int n = 5;
        StdOut.println(queue.isEmpty());
        for (int i = 0; i <= n; i++) {
            queue.addFirst(i);
        }
        StdOut.println("deque: addFirst completed");
        StdOut.println(queue.isEmpty());
        for (int a : queue)
            StdOut.println(a);

        for (int i = n + 1; i <= n + 5; i++) {
            queue.addLast(i);
        }
        StdOut.println("deque: addLast completed");
        for (int a : queue)
            StdOut.println(a);

        StdOut.println("Deque size=" + queue.size());
        StdOut.println(queue.removeLast());
        StdOut.println("Deque size=" + queue.size());
        StdOut.println(queue.removeFirst());
        StdOut.println("Deque size=" + queue.size());


    }


}