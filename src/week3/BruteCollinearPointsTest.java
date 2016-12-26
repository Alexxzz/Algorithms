package week3;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class BruteCollinearPointsTest {
    /**
     * Corner cases.
     */
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

    @Test
    public void constructor_duplicate_points() {
        Point[] points = {
                new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2),
        };
        try {
            BruteCollinearPoints sut = new BruteCollinearPoints(points);
            fail("Should throw IllegalArgumentException when passing array containing duplicate points");
        } catch (IllegalArgumentException ignored) { }
    }

    /**
     * Finding the segments.
     */
    @Test
    public void numberOfSegments_one_segment() {
        Point[] points = {
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4),
        };

        BruteCollinearPoints sut = new BruteCollinearPoints(points);

        assertEquals(1, sut.numberOfSegments());
        assertEquals("(1, 1) -> (4, 4)", sut.segments()[0].toString());
    }

    @Test
    public void numberOfSegments_two_segments() {
        Point[] points = {
                new Point(10000    ,  0),
                new Point(0,  10000),
                new Point(3000 ,  7000),
                new Point(7000 ,  3000),

                new Point(20000,  21000),
                new Point(3000 ,  4000),
                new Point(14000,  15000),
                new Point(6000 ,  7000),
        };

        BruteCollinearPoints sut = new BruteCollinearPoints(points);

        assertEquals(2, sut.numberOfSegments());
        assertEquals("(10000, 0) -> (0, 10000)", sut.segments()[0].toString());
        assertEquals("(3000, 4000) -> (20000, 21000)", sut.segments()[1].toString());
    }

    @Test
    public void numberOfSegments_and_segments_is_consistent() {
        Point[] points = {
                new Point(10000, 0),
                new Point(8000, 2000),
                new Point(2000, 8000),
                new Point(0 , 10000),

                new Point(20000, 0),
                new Point(18000, 2000),
                new Point(2000, 18000),

                new Point(10000, 20000),
                new Point(30000, 0),
                new Point(0, 30000),
                new Point(20000, 10000),

                new Point(13000, 0),
                new Point(11000, 3000),
                new Point(5000, 12000),
                new Point(9000, 6000),
        };

        BruteCollinearPoints sut = new BruteCollinearPoints(points);

        assertTrue(sut.segments()[0] != null);
        assertTrue(sut.segments()[1] != null);

        assertEquals(sut.numberOfSegments(), sut.segments().length);
        assertEquals(4, sut.numberOfSegments());
    }

    @Test
    public void equidistant() {
        Point[] points = {
                new Point(10000, 0),
                new Point(8000, 2000),
                new Point(2000, 8000),
                new Point(0, 10000),

                new Point(20000, 0),
                new Point(18000, 2000),
                new Point(2000, 18000),

                new Point(10000, 20000),
                new Point(30000, 0),
                new Point(0, 30000),
                new Point(20000, 10000),

                new Point(13000, 0),
                new Point(11000, 3000),
                new Point(5000, 12000),
                new Point(9000, 6000),
        };

        BruteCollinearPoints sut = new BruteCollinearPoints(points);

        LineSegment[] segs = sut.segments();

    }
}
