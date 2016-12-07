package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;

    // construct an empty deque
    public Deque() {
        items = (Item[]) new Object[10];
    }

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
            items[size] = item;
            size += 1;
        }
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            size += 1;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size ==  0) {
            throw new NoSuchElementException();
        } else {
            size -= 1;
            return items[0];
        }
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size ==  0) {
            throw new NoSuchElementException();
        } else {
            size -= 1;
            return null;
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
