import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first = null;
    private Node last = null;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public Node(Item element, Node next, Node prev) {
            this.item = element;
            this.next = next;
            this.previous = prev;
        }
    }

    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the deque
    public int size() { return size; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();

        Node newFirst = new Node(item, first, null);
        if (first != null) { first.previous = newFirst; }
        first = newFirst;

        if (last == null) { last = first; }

        size += 1;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();

        Node newLast = new Node(item, null, last);
        if (last != null) { last.next = newLast; }
        last = newLast;

        if (first == null) { first = last; }

        size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();

        size -= 1;

        Node removedNode = first;
        first = first.next;
        if (first != null) first.previous = null;

        return removedNode.item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();

        size -= 1;

        Node removedNode = last;
        last = last.previous;
        if (last != null) last.next = null;

        return removedNode.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() { return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public void remove() { throw new UnsupportedOperationException(); }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public boolean hasNext() { return current != null; }
    }

    // unit testing
    public static void main(String[] args) {

    }
}
