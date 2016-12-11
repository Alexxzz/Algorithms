import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first, last;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public Node(Item element, Node next, Node previous) {
            this.item = element;
            this.next = next;
            this.previous = previous;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

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

        Node newElement = new Node(item, null, last);
        if (first == null) { first = newElement; }
        else { last.next = newElement; }
        last = newElement;

        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIdx = StdRandom.uniform(size);

        return removeAt(randomIdx).item;
    }

    private Node removeAt(int index) {
        Node node = nodeAt(index);

        if (node.previous != null) { node.previous.next = node.next; }
        else { first = node.next; }

        size--;

        return node;
    }

    private Node nodeAt(int index) {
        Node node = first;
        for (int i = 1; i < index; i++) { node = node.next; }
        return node;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIdx = StdRandom.uniform(size);

        return nodeAt(randomIdx).item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            throw new NoSuchElementException();
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
