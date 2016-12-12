import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


/**
 * The type Randomized queue test.
 */
public class RandomizedQueueTest {
    /**
     * Exceptions.
     */
    @Test
    void adding_null() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        try {
            sut.enqueue(null);
            fail("Should throw NullPointerException when adding null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    void dequeue_on_empty() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        try {
            sut.dequeue();
            fail("Should throw NoSuchElementException when dequeue from empty");
        } catch (NoSuchElementException ignored) { }
    }

    @Test
    void sample_on_empty() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        try {
            sut.sample();
            fail("Should throw NoSuchElementException when sample from empty");
        } catch (NoSuchElementException ignored) { }
    }

    @Test
    void iterator_remove() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        try {
            sut.iterator().remove();
            fail("Should throw UnsupportedOperationException when calling remove on iterator");
        } catch (UnsupportedOperationException ignored) { }
    }

    @Test
    void iterator_next_on_empty() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        try {
            sut.iterator().next();
            fail("Should throw NoSuchElementException when calling next on iterator in empty queue");
        } catch (NoSuchElementException ignored) { }
    }

    /**
     * size, isEmpty().
     */
    @Test
    void isEmpty_on_empty() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        assertTrue(sut.isEmpty());
    }

    @Test
    void size_on_empty() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        assertEquals(0, sut.size());
    }

    @Test
    void isEmpty_on_not_empty() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(1);

        assertFalse(sut.isEmpty());
    }

    @Test
    void size_on_not_empty() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        assertEquals(0, sut.size());

        sut.enqueue(0);
        assertEquals(1, sut.size());

        sut.enqueue(1);
        assertEquals(2, sut.size());
    }


    /**
     * Enqueue/dequeue.
     */
    @Test
    void enqueue_dequeue_on_empty() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(0);
        int res = sut.dequeue();

        assertEquals(0, res);
    }

    @Test
    void enqueue_x3_dequeue_x3() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(1);
        sut.enqueue(2);
        sut.enqueue(3);

        ArrayList<Integer> res = new ArrayList<>();
        res.add(sut.dequeue());
        res.add(sut.dequeue());
        res.add(sut.dequeue());

        assertTrue(res.contains(1));
        assertTrue(res.contains(2));
        assertTrue(res.contains(3));
    }

    @Test
    void dequeue_decreases_size() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(1);
        sut.enqueue(2);
        sut.enqueue(3);

        assertEquals(3, sut.size());

        sut.dequeue();
        assertEquals(2, sut.size());

        sut.dequeue();
        assertEquals(1, sut.size());

        sut.dequeue();
        assertEquals(0, sut.size());

        assertTrue(sut.isEmpty());
    }

    @Test
    void enqueue_dequeue_sequentionaly_x2() {
        ArrayRandomizedQueue<Integer> rq = new ArrayRandomizedQueue<>();

        rq.enqueue(646);
        rq.dequeue();

        rq.enqueue(198);
        int res = rq.dequeue();

        assertEquals(198, res);
    }

    /**
     * Sample.
     */
    @Test
    void enqueue_sample() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(1);

        int res = sut.sample();
        assertEquals(1, res);
    }

    @Test
    void sample_does_not_removes_items() {
        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(1);

        int res = sut.sample();
        assertEquals(1, res);

        assertEquals(1, sut.size());
        assertFalse(sut.isEmpty());
    }

    /**
     * Randomized dequeue.
     */
    @Test
    void randomized_dequeue() {
        // Should probably be a part of ArrayRandomizedQueue interface
        StdRandom.setSeed(42);

        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(1);
        sut.enqueue(2);
        sut.enqueue(3);

        int res = sut.dequeue();
        assertNotEquals(1, res);
    }

    @Test
    void randomized_sample() {
        // Should probably be a part of ArrayRandomizedQueue interface
        StdRandom.setSeed(42);

        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(1);
        sut.enqueue(2);
        sut.enqueue(3);

        int res = sut.sample();
        assertNotEquals(1, res);
    }

    @Test
    void testing_randomness_of_sample() {
        StdRandom.setSeed(44);

        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();
        sut.enqueue(1);
        sut.enqueue(2);
        sut.enqueue(3);
        sut.enqueue(4);
        sut.enqueue(5);

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            res.add(sut.sample());
        }

        assertTrue(res.contains(1));
        assertTrue(res.contains(2));
        assertTrue(res.contains(3));
        assertTrue(res.contains(4));
        assertTrue(res.contains(5));
    }

    @Test
    void n_random_calls() {
        StdRandom.setSeed(42);

        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        for (int i = 0; i < 1024; i++) {
            int operation = StdRandom.uniform(5);
            switch (operation) {
                case 0: sut.enqueue(i); break;
                case 1: if (!sut.isEmpty()) sut.sample(); break;
                case 2: if (!sut.isEmpty()) sut.dequeue(); break;
                case 3: sut.isEmpty(); break;
                case 4: sut.size(); break;
            }
        }
    }

    /**
     * Iterator.
     */
    @Test
    void iterator() {
        StdRandom.setSeed(42);

        ArrayRandomizedQueue<Integer> sut = new ArrayRandomizedQueue<>();

        sut.enqueue(1);
        sut.enqueue(2);
        sut.enqueue(3);

        ArrayList<Integer> res = new ArrayList<>();
        for (Integer i: sut) {
            res.add(i);
        }

        assertNotEquals(1, res.get(0));
    }
}
