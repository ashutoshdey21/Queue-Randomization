import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;
    private static final int MINIMUM_QUEUE_SIZE = 2;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[MINIMUM_QUEUE_SIZE];
        size = 0;

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size < 0) size = 0;


        if (size == items.length)
            resize(2 * items.length);

        items[size++] = item;
    }

    private void resize(int capacity) {
        Item[] transfer = (Item[]) new Object[capacity];
        for (int x = 0; x < size; x++) {
            transfer[x] = items[x];
        }
        items = (Item[]) new Object[capacity];
        items = transfer;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0 && items[size] == null) {
            throw new NoSuchElementException();
        }
        size--;

        if (size < 0) size = 0;

        if(size>0) {
            int randomIndex = StdRandom.uniform(size);
            Item itemReturned = items[randomIndex];
            items[randomIndex] = items[size];
            items[size] = null;
            if(size == items.length / 4)
                resize(items.length / 2);
            return itemReturned;

        }
        else return items[size];


    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0 && items[size] == null) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(size)];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {


        private final Item[] QueueItems;
        private int index=size;

        public RandomIterator(){
            QueueItems=copyRandomQueueItems();
            StdRandom.shuffle(QueueItems);
        }

        private Item[] copyRandomQueueItems() {
            Item[] copyOfItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copyOfItems[i] = items[i];
            }
            return copyOfItems;
        }


        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Item next() {
            if(hasNext())
                return QueueItems[--index];
            else throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue= new RandomizedQueue<>();
        int n=5;
        StdOut.println(randomizedQueue.isEmpty());


        for (int i = 0; i <=n; i++) {
            randomizedQueue.enqueue(i);
        }


        StdOut.println("Size="+randomizedQueue.size());
        for (int a : randomizedQueue) {

            StdOut.println(a);
        }

        StdOut.println("Size="+randomizedQueue.size());

        StdOut.println("This is a sample element:"+randomizedQueue.sample());

        StdOut.println("Is the queue empty?"+randomizedQueue.isEmpty());

        StdOut.println("This element has been removed: "+randomizedQueue.dequeue());

        StdOut.println("Is the queue empty?"+randomizedQueue.isEmpty());

        StdOut.println("Size after removal of one element: "+randomizedQueue.size);

        StdOut.println("This element has been removed: "+randomizedQueue.dequeue());

        StdOut.println("Is the queue empty?"+randomizedQueue.isEmpty());

        StdOut.println("Size after removal of one element: "+randomizedQueue.size);


    }

}