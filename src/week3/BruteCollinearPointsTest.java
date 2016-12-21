package week3;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class BruteCollinearPointsTest {
    @Test
    public void constructor_null() {
        try {
            BruteCollinearPoints sut = new BruteCollinearPoints(null);
            fail("Should throw NullPointerException when passing null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    public void constructor_contains_null() {
        Point[] points = {
                new Point(1, 1),
                null,
                new Point(2, 2),
        };

        try {
            BruteCollinearPoints sut = new BruteCollinearPoints(points);
            fail("Should throw NullPointerException when passing array containing null");
        } catch (NullPointerException ignored) { }
    }
}
