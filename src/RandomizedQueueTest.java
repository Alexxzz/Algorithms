import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.fail;

public class RandomizedQueueTest {
    /**
     * Exceptions.
     */
    @Test
    void adding_null() {
        RandomizedQueue<Integer> sut = new RandomizedQueue<>();

        try {
            sut.enqueue(null);
            fail("Should throw NullPointerException when adding null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    void dequeue_on_empty() {
        RandomizedQueue<Integer> sut = new RandomizedQueue<>();

        try {
            sut.dequeue();
            fail("Should throw NoSuchElementException when dequeue from empty");
        } catch (NoSuchElementException ignored) { }
    }

    @Test
    void sample_on_empty() {
        RandomizedQueue<Integer> sut = new RandomizedQueue<>();

        try {
            sut.sample();
            fail("Should throw NoSuchElementException when sample from empty");
        } catch (NoSuchElementException ignored) { }
    }

    @Test
    void iterator_remove() {
        RandomizedQueue<Integer> sut = new RandomizedQueue<>();

        try {
            sut.iterator().remove();
            fail("Should throw UnsupportedOperationException when calling remove on iterator");
        } catch (UnsupportedOperationException ignored) { }
    }

    @Test
    void iterator_next_on_empty() {
        RandomizedQueue<Integer> sut = new RandomizedQueue<>();

        try {
            sut.iterator().next();
            fail("Should throw NoSuchElementException when calling next on iterator in empty queue");
        } catch (NoSuchElementException ignored) { }
    }
}
