package week3;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by AlexZ on 20/12/2016.
 */
public class PointTest {
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
