package week3;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by AlexZ on 20/12/2016.
 */
public class PointTest {
    @Test
    public void slopeTo_null() {
        Point point = new Point(1, 1);

        try {
            point.slopeTo(null);
            fail("Should throw NullPointerException when passing null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    public void compareTo_null() {
        Point point = new Point(1, 1);

        try {
            point.compareTo(null);
            fail("Should throw NullPointerException when passing null");
        } catch (NullPointerException ignored) { }
    }

    /**
     * slopeTo
     */
    @Test
    public void slopeTo_horizontally() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 1);

        double slope = point1.slopeTo(point2);

        assertEquals(0, slope);
    }

    @Test
    public void slopeTo_vertically() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 2);

        double slope = point1.slopeTo(point2);

        assertEquals(Double.POSITIVE_INFINITY, slope);
    }

    @Test
    public void slopeTo_equal() {
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 2);

        double slope = point1.slopeTo(point2);

        assertEquals(Double.NEGATIVE_INFINITY, slope);
    }

    @Test
    public void slopeTo_negativeResult() {
        Point point1 = new Point(400, 60);
        Point point2 = new Point(123, 201);

        double slope = point1.slopeTo(point2);

        assertEquals(-0.5090252707581228, slope);
    }

    @Test
    public void slopeTo_positiveResult() {
        Point point1 = new Point(3810, 15735);
        Point point2 = new Point(22622, 20919);

        double slope = point1.slopeTo(point2);

        assertEquals(0.2755687858813523, slope);
    }

    @Test
    public void slopeTo_positiveZeroResult() {
        Point point1 = new Point(405, 467);
        Point point2 = new Point(300, 467);

        double slope = point1.slopeTo(point2);

        assertEquals(0, slope);
    }

    /**
     * compareTo
     */
    @Test
    public void compareTo_equals() {
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 2);

        int res = point1.compareTo(point2);

        assertEquals(0, res);
    }

    @Test
    public void compareTo_equal_by_x_less_by_y() {
        Point point1 = new Point(2, 1);
        Point point2 = new Point(2, 2);

        int res = point1.compareTo(point2);

        assertTrue(res < 0);
    }

    @Test
    public void compareTo_less_by_x_equal_by_y() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(2, 2);

        int res = point1.compareTo(point2);

        assertTrue(res < 0);
    }

    @Test
    public void compareTo_less_by_x_less_by_y() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 2);

        int res = point1.compareTo(point2);

        assertTrue(res < 0);
    }

    @Test
    public void compareTo_greater_by_x_less_by_y() {
        Point point1 = new Point(3, 1);
        Point point2 = new Point(2, 2);

        int res = point1.compareTo(point2);

        assertTrue(res < 0);
    }

    @Test
    public void compareTo_greater() {
        Point point1 = new Point(3, 4);
        Point point2 = new Point(2, 2);

        int res = point1.compareTo(point2);

        assertTrue(res > 0);
    }

    /**
     * Slope order.
     */
    @Test
    public void slopeOrder_equal() {
        Comparator<Point> sut = new Point(1, 2).slopeOrder();

        int res = sut.compare(new Point(2,3), new Point(2, 3));

        assertEquals(0, res);
    }

    @Test
    public void slopeOrder_less() {
        Comparator<Point> sut = new Point(1, 2).slopeOrder();

        int res = sut.compare(new Point(2,3), new Point(3, 8));

        assertTrue(res < 0);
    }


    @Test
    public void slopeOrder_greater() {
        Comparator<Point> sut = new Point(1, 2).slopeOrder();

        int res = sut.compare(new Point(2,8), new Point(3, 4));

        assertTrue(res > 0);
    }
}
