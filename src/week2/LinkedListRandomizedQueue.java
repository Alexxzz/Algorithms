package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListRandomizedQueue<Item> implements RandomizedQueueInterface<Item>, Iterable<Item> {
    private int size = 0;
    private Node first, last;

    private class Node {
        private Item item;
        private Node next;

        public Node(Item element, Node next) {
            this.item = element;
            this.next = next;
        }
    }

    // construct an empty randomized queue
    public LinkedListRandomizedQueue() {

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

        Node newElement = new Node(item, null);
        if (first == null) { first = newElement; }
        else { last.next = newElement; }
        last = newElement;

        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIdx = StdRandom.uniform(size);
        Node node = removeAt(randomIdx);

        if (isEmpty()) {
            first = null;
            last = null;
        }

        return node.item;
    }

    private Node removeAt(int index) {
        Node node = first, previous = null;
        for (int i = 1; i <= index; i++) {
            previous = node;
            node = node.next;
        }

        if (previous != null) {
            previous.next = node.next;

            if (node == last) { last = previous; }
        } else { first = node.next; }

        size--;

        return node;
    }

    private Node nodeAt(int index) {
        Node node = first;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
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
        private ArrayRandomizedQueue<Item> rq;

        public RandomizedQueueIterator() {
            this.rq = new ArrayRandomizedQueue<>();
            Node node = first;
            while (node != null) {
                rq.enqueue(node.item);
                node = node.next;
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
