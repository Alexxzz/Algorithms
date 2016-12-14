import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Randomized queue test.
 */
@RunWith(Parameterized.class)
public class RandomizedQueueTest {
    private interface RandomizedQueueFactory<Item> {
        RandomizedQueueInterface<Item> createRandomizedQueue();
    }

    private static class ArrayRandomizedQueueFactory<Item> implements RandomizedQueueFactory {
        @Override
        public RandomizedQueueInterface createRandomizedQueue() {
            return new ArrayRandomizedQueue<Item>();
        }
    }

    private static class LinkedListRandomizedQueueFactory<Item> implements RandomizedQueueFactory {
        @Override
        public RandomizedQueueInterface createRandomizedQueue() {
            return new LinkedListRandomizedQueue<Item>();
        }
    }

    private RandomizedQueueInterface<Integer> sut;

    @Parameter
    public RandomizedQueueFactory<Integer> factory;

    @Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
                { new ArrayRandomizedQueueFactory() },
                { new LinkedListRandomizedQueueFactory() }
        });
    }

    @Before
    public void createNewQueue() {
        sut = factory.createRandomizedQueue();
    }

    /**
     * Exceptions.
     */
    @Test
    public void adding_null() {
        try {
            sut.enqueue(null);
            fail("Should throw NullPointerException when adding null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    public void dequeue_on_empty() {
        try {
            sut.dequeue();
            fail("Should throw NoSuchElementException when dequeue from empty");
        } catch (NoSuchElementException ignored) { }
    }

    @Test
    public void sample_on_empty() {
        try {
            sut.sample();
            fail("Should throw NoSuchElementException when sample from empty");
        } catch (NoSuchElementException ignored) { }
    }

    @Test
    public void iterator_remove() {
        try {
            sut.iterator().remove();
            fail("Should throw UnsupportedOperationException when calling remove on iterator");
        } catch (UnsupportedOperationException ignored) { }
    }

    @Test
    public void iterator_next_on_empty() {
        try {
            sut.iterator().next();
            fail("Should throw NoSuchElementException when calling next on iterator in empty queue");
        } catch (NoSuchElementException ignored) { }
    }

    /**
     * size, isEmpty().
     */
    @Test
    public void isEmpty_on_empty() {
        assertTrue(sut.isEmpty());
    }

    @Test
    public void size_on_empty() {
        assertEquals(0, sut.size());
    }

    @Test
    public void isEmpty_on_not_empty() {
        sut.enqueue(1);

        assertFalse(sut.isEmpty());
    }

    @Test
    public void size_on_not_empty() {
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
    public void enqueue_dequeue_on_empty() {
        sut.enqueue(0);
        int res = sut.dequeue();

        assertEquals(0, res);
    }

    @Test
    public void enqueue_x3_dequeue_x3() {
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
    public void dequeue_decreases_size() {
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
    public void enqueue_dequeue_sequentionaly_x2() {
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
    public void enqueue_sample() {
        sut.enqueue(1);

        int res = sut.sample();
        assertEquals(1, res);
    }

    @Test
    public void sample_does_not_removes_items() {
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
    public void randomized_dequeue() {
        // Should probably be a part of ArrayRandomizedQueue interface
        StdRandom.setSeed(42);

        sut.enqueue(1);
        sut.enqueue(2);
        sut.enqueue(3);

        int res = sut.dequeue();
        assertNotEquals(1, res);
    }

    @Test
    public void randomized_sample() {
        // Should probably be a part of ArrayRandomizedQueue interface
        StdRandom.setSeed(42);

        sut.enqueue(1);
        sut.enqueue(2);
        sut.enqueue(3);

        int res = sut.sample();
        assertNotEquals(1, res);
    }

    @Test
    public void testing_randomness_of_sample() {
        StdRandom.setSeed(44);

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
    public void n_random_calls() {
        StdRandom.setSeed(42);

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
    public void iterator() {
        StdRandom.setSeed(42);

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
