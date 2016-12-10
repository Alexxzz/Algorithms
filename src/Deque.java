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
    }

    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node newFirst = new Node();
            newFirst.item = item;
            if (first != null) {
                newFirst.next = first;
                first.previous = newFirst;
            }
            first = newFirst;

            if (last == null) {
                last = first;
            }

            size += 1;
        }
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node newLast = new Node();
            newLast.item = item;
            if (last != null) {
                newLast.next = last;
                last.previous = newLast;
            }
            last = newLast;

            if (first == null) {
                first = last;
            }

            size += 1;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size ==  0) {
            throw new NoSuchElementException();
        } else {
            size -= 1;

            Node removedNode = first;
            if (first.next == null && first != last) {
                first = first.previous;
                if (first != null) {
                    first.next = null;
                }
            } else {
                first = first.next;
            }
            removedNode.next = null;
            removedNode.previous = null;

            if (first == null) {
                last = null;
            }

            return removedNode.item;
        }
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size ==  0) {
            throw new NoSuchElementException();
        } else {
            size -= 1;

            Node removedNode = last;
            if (last.next == null && last != first) {
                last = last.previous;
                if (last != null) {
                    last.next = null;
                }
            } else {
                last = last.next;
            }
            removedNode.next = null;
            removedNode.previous = null;

            if (last == null) {
                first = null;
            }

            return removedNode.item;
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Item next() {
                throw new NoSuchElementException();
            }

            @Override
            public boolean hasNext() {
                return false;
            }
        };
    }

    // unit testing
    public static void main(String[] args) {

    }
}
