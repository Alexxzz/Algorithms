package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayRandomizedQueue<Item> implements RandomizedQueue<Item>, Iterable<Item> {
    private Item[] a;
    private int size;

    // construct an empty randomized queue
    public ArrayRandomizedQueue() {
        a = (Item[]) new Object[2];
        size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();

        adjustArraySize();

        a[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(size);

        Item item = dequeuItemAt(randomIndex);

        size--;

        adjustArraySize();

        return item;
    }

    private Item dequeuItemAt(int randomIndex) {
        Item item = a[randomIndex];
        if (randomIndex == size - 1) {
            a[randomIndex] = null;
        } else {
            a[randomIndex] = a[size - 1];
            a[size - 1] = null;
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIdx = StdRandom.uniform(size);

        return a[randomIdx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void adjustArraySize() {
        if (size == a.length) resize(2 * a.length);
        else if (size > 0 && size == a.length/4) resize(a.length/2);
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= size;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) { temp[i] = a[i]; }
        a = temp;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private ArrayRandomizedQueue<Item> rq;

        public RandomizedQueueIterator() {
            this.rq = new ArrayRandomizedQueue<>();

            for (int i = 0; i < size; i++) {
                rq.enqueue(a[i]);
            }
        }

        @Override
        public boolean hasNext() { return !rq.isEmpty(); }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return rq.dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {

    }
}
