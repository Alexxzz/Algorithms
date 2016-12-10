import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Deque test.
 */
class DequeTest {
    /**
     * Exceptions.
     */
    @Test
    void addFirst_addingNull() {
        Deque<String> sut = new Deque<>();

        try {
            sut.addFirst(null);
            fail("Should throw NullPointerException when adding null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    void addLast_addingNull() {
        Deque<String> sut = new Deque<>();

        try {
            sut.addLast(null);
            fail("Should throw NullPointerException when adding null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    void removeFirst_emptyDeque() {
        Deque<String> sut = new Deque<>();

        try {
            sut.removeFirst();
            fail("Should throw NoSuchElementException when removing from empty Deque");
        } catch (NoSuchElementException ignored) { }
    }

    @Test
    void removeLast_emptyDeque() {
        Deque<String> sut = new Deque<>();

        try {
            sut.removeLast();
            fail("Should throw NoSuchElementException when removing from empty Deque");
        } catch (NoSuchElementException ignored) { }
    }

    @Test
    void iterator_remove() {
        Deque<String> sut = new Deque<>();

        try {
            sut.iterator().remove();
            fail("Should throw UnsupportedOperationException when performing remove() on iterator");
        } catch (UnsupportedOperationException ignored) { }
    }

    @Test
    void iterator_next_emptyDeque() {
        Deque<String> sut = new Deque<>();

        try {
            sut.iterator().next();
            fail("Should throw NoSuchElementException when performing next on empty deque");
        } catch (NoSuchElementException ignored) { }
    }

    /**
     * Size.
     */
    @Test
    void size_emptyDeque() {
        Deque<String> sut = new Deque<>();

        assertEquals(sut.size(), 0);
    }

    @Test
    void size_after_addFirst_in_emptyDeque() {
        Deque<String> sut = new Deque<>();

        sut.addFirst("test");

        assertEquals(sut.size(), 1);
    }

    @Test
    void size_after_addFirst_2x_in_emptyDeque() {
        Deque<String> sut = new Deque<>();

        sut.addFirst("test 1");
        sut.addFirst("test 2");

        assertEquals(sut.size(), 2);
    }

    @Test
    void size_after_addLast_in_emptyDeque() {
        Deque<String> sut = new Deque<>();

        sut.addLast("test");

        assertEquals(sut.size(), 1);
    }

    @Test
    void size_after_addLast_2x_in_emptyDeque() {
        Deque<String> sut = new Deque<>();

        sut.addLast("test 1");
        sut.addLast("test 2");

        assertEquals(sut.size(), 2);
    }

    @Test
    void size_after_removeFirst_in_one_elementInDeque() {
        Deque<String> sut = new Deque<>();
        sut.addFirst("test");

        sut.removeFirst();

        assertEquals(sut.size(), 0);
    }

    @Test
    void size_after_removeFirst_in_three_elementInDeque() {
        Deque<String> sut = new Deque<>();
        sut.addFirst("test 1");
        sut.addFirst("test 2");
        sut.addFirst("test 3");

        sut.removeFirst();

        assertEquals(sut.size(), 2);
    }

    @Test
    void size_after_removeLast_in_one_elementInDeque() {
        Deque<String> sut = new Deque<>();
        sut.addFirst("test");

        sut.removeLast();

        assertEquals(sut.size(), 0);
    }

    @Test
    void size_after_removeLast_in_three_elementInDeque() {
        Deque<String> sut = new Deque<>();
        sut.addFirst("test 1");
        sut.addLast("test 2");
        sut.addFirst("test 3");

        sut.removeLast();

        assertEquals(sut.size(), 2);
    }


    /**
     * Is empty.
     */
    @Test
    void isEmpty_emptyDeque() {
        Deque<String> sut = new Deque<>();

        assertTrue(sut.isEmpty());
    }

    @Test
    void isEmpty_after_addFirst_in_emptyDeque() {
        Deque<String> sut = new Deque<>();

        sut.addFirst("test");

        assertFalse(sut.isEmpty());
    }

    @Test
    void isEmpty_after_addFirst_x2_in_emptyDeque() {
        Deque<String> sut = new Deque<>();

        sut.addFirst("test 1");
        sut.addFirst("test 2");

        assertFalse(sut.isEmpty());
    }

    @Test
    void isEmpty_after_removeLast_in_five_elmDeque() {
        Deque<String> sut = new Deque<>();

        sut.addFirst("test 1");
        sut.addLast("test 2");
        sut.addFirst("test 3");
        sut.addLast("test 4");
        sut.addFirst("test 5");

        sut.removeLast();

        assertFalse(sut.isEmpty());
    }

    /**
     * Add/Remove first.
     */
    @Test
    void addRemoveFirst() {
        String firstElm = "test 1";
        String secondElm = "test 2";
        String thirdElm = "test 3";

        Deque<String> sut = new Deque<>();
        sut.addFirst(firstElm);
        sut.addFirst(secondElm);
        sut.addFirst(thirdElm);

        String elm = sut.removeFirst();
        assertEquals(elm, thirdElm);

        elm = sut.removeFirst();
        assertEquals(elm, secondElm);

        elm = sut.removeFirst();
        assertEquals(elm, firstElm);
    }

    /**
     * Add/Remove last.
     */
    @Test
    void addRemoveLast() {
        String firstElm = "test 1";
        String secondElm = "test 2";
        String thirdElm = "test 3";

        Deque<String> sut = new Deque<>();
        sut.addLast(firstElm);
        sut.addLast(secondElm);
        sut.addLast(thirdElm);

        String elm = sut.removeLast();
        assertEquals(elm, thirdElm);

        elm = sut.removeLast();
        assertEquals(elm, secondElm);

        elm = sut.removeLast();
        assertEquals(elm, firstElm);
    }

    /**
     * Add first, Remove last.
     */
    @Test
    void addFirst_removeLast() {
        String firstElm = "test 1";
        String secondElm = "test 2";
        String thirdElm = "test 3";

        Deque<String> sut = new Deque<>();

        sut.addFirst(firstElm);
        String elm = sut.removeLast();
        assertEquals(firstElm, elm);

        sut.addFirst(secondElm);
        elm = sut.removeLast();
        assertEquals(secondElm, elm);

        sut.addFirst(thirdElm);
        elm = sut.removeLast();
        assertEquals(thirdElm, elm);

        assertTrue(sut.isEmpty());
    }

    @Test
    void addFirstX3_removeLastX3() {
        String firstElm = "test 1";
        String secondElm = "test 2";
        String thirdElm = "test 3";

        Deque<String> sut = new Deque<>();

        sut.addFirst(firstElm);
        sut.addFirst(secondElm);
        sut.addFirst(thirdElm);

        String dequeElm1 = sut.removeLast();
        String dequeElm2 = sut.removeLast();
        String dequeElm3 = sut.removeLast();

        assertEquals(firstElm, dequeElm1);
        assertEquals(secondElm, dequeElm2);
        assertEquals(thirdElm, dequeElm3);

        assertTrue(sut.isEmpty());
    }

    @Test
    void addLast_removeFirst() {
        String firstElm = "test 1";
        String secondElm = "test 2";
        String thirdElm = "test 3";

        Deque<String> sut = new Deque<>();

        sut.addLast(firstElm);
        String elm = sut.removeFirst();
        assertEquals(firstElm, elm);

        sut.addLast(secondElm);
        elm = sut.removeFirst();
        assertEquals(secondElm, elm);

        sut.addLast(thirdElm);
        elm = sut.removeFirst();
        assertEquals(thirdElm, elm);

        assertTrue(sut.isEmpty());
    }

    @Test
    void addLastX3_removeFirstX3() {
        String firstElm = "test 1";
        String secondElm = "test 2";
        String thirdElm = "test 3";

        Deque<String> sut = new Deque<>();

        sut.addLast(firstElm);
        sut.addLast(secondElm);
        sut.addLast(thirdElm);

        String dequeElm1 = sut.removeFirst();
        String dequeElm2 = sut.removeFirst();
        String dequeElm3 = sut.removeFirst();

        assertEquals(firstElm, dequeElm1);
        assertEquals(secondElm, dequeElm2);
        assertEquals(thirdElm, dequeElm3);

        assertTrue(sut.isEmpty());
    }
}
