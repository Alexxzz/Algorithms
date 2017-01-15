package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(Parameterized.class)
public class UnitSquarePointSETTest {
    private interface UnitSquarePointSETFactory {
        UnitSquarePointSET createPointSET();
    }

    private UnitSquarePointSET sut;

    @Parameterized.Parameter
    public UnitSquarePointSETFactory factory;

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
                {(UnitSquarePointSETFactory) PointSET::new},
                {(UnitSquarePointSETFactory) KdTree::new}
        });
    }

    @Before
    public void createNewPointSET() { sut = factory.createPointSET(); }

    /**
     * Corner cases
     */
    @Test
    public void insert_null() {
        try {
            sut.insert(null);
            fail("Should throw NullPointerException when insert parameter is null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    public void contains_null() {
        try {
            sut.contains(null);
            fail("Should throw NullPointerException when contains parameter is null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    public void range_null() {
        try {
            sut.range(null);
            fail("Should throw NullPointerException when range parameter is null");
        } catch (NullPointerException ignored) { }
    }

    @Test
    public void nearest_null() {
        try {
            sut.nearest(null);
            fail("Should throw NullPointerException when nearest parameter is null");
        } catch (NullPointerException ignored) { }
    }

    /**
     * isEmpty, size and contains on empty set
     */
    @Test
    public void isEmpty_emptySet() {
        assertTrue(sut.isEmpty());
    }

    @Test
    public void size_emptySet() {
        assertEquals(0, sut.size());
    }

    @Test
    public void contains_emptySet() {
        assertFalse(sut.contains(new Point2D(0.1, 0.2)));
    }

    @Test
    public void nearest_emptySet() {
        assertEquals(null, sut.nearest(new Point2D(0.1, 0.2)));
    }

    @Test
    public void range_emptySet() {
        int pointsCount = 0;
        for (Point2D p: sut.range(new RectHV(0.1, 0.2, 0.3, 0.4))) {
            pointsCount++;
        }

        assertEquals(0, pointsCount);
    }

    /**
     * isEmpty, size and contains after insert
     */
    @Test
    public void isEmpty_afterOneInsert() {
        sut.insert(new Point2D(0.1, 0.2));

        assertFalse(sut.isEmpty());
    }

    @Test
    public void size_afterOneInsert() {
        sut.insert(new Point2D(0.1, 0.2));

        assertEquals(1, sut.size());
    }

    @Test
    public void size_afterThreeInsert() {
        sut.insert(new Point2D(0.1, 0.2));
        sut.insert(new Point2D(0.2, 0.3));
        sut.insert(new Point2D(0.3, 0.4));

        assertEquals(3, sut.size());
    }

    @Test
    public void size_afterFiveInsertWithDoubles() {
        sut.insert(new Point2D(0.1, 0.3));
        sut.insert(new Point2D(0.2, 0.3));
        sut.insert(new Point2D(0.3, 0.4));
        sut.insert(new Point2D(0.2, 0.3));
        sut.insert(new Point2D(0.1, 0.2));

        assertEquals(4, sut.size());
    }

    @Test
    public void contains_afterOneInsert() {
        Point2D p = new Point2D(0.1, 0.2);

        sut.insert(p);

        assertTrue(sut.contains(p));
    }

    @Test
    public void contains_afterThreeInsert() {
        Point2D p1 = new Point2D(0.1, 0.2);
        Point2D p2 = new Point2D(0.3, 0.4);
        Point2D p3 = new Point2D(0.5, 0.6);

        sut.insert(p1);
        sut.insert(p2);
        sut.insert(p3);

        assertTrue(sut.contains(p2));
        assertFalse(sut.contains(new Point2D(1, 1)));
    }

    @Test
    public void contains_after10Insert() {
        Point2D p1 = new Point2D(0.206107, 0.095492);
        Point2D p2 = new Point2D(0.975528, 0.654508);
        Point2D p3 = new Point2D(0.024472, 0.345492);
        Point2D p4 = new Point2D(0.793893, 0.095492);
        Point2D p5 = new Point2D(0.793893, 0.904508);
        Point2D p6 = new Point2D(0.975528, 0.345492);
        Point2D p7 = new Point2D(0.206107, 0.904508);
        Point2D p8 = new Point2D(0.500000, 0.000000);
        Point2D p9 = new Point2D(0.024472, 0.654508);
        Point2D p10 = new Point2D(0.500000, 1.000000);

        sut.insert(p1);
        sut.insert(p2);
        sut.insert(p3);
        sut.insert(p4);
        sut.insert(p5);
        sut.insert(p6);
        sut.insert(p7);
        sut.insert(p8);
        sut.insert(p9);
        sut.insert(p10);

        assertTrue(sut.contains(p1));
        assertTrue(sut.contains(p2));
        assertTrue(sut.contains(p3));
        assertTrue(sut.contains(p4));
        assertTrue(sut.contains(p5));
        assertTrue(sut.contains(p6));
        assertTrue(sut.contains(p7));
        assertTrue(sut.contains(p8));
        assertTrue(sut.contains(p9));
        assertTrue(sut.contains(p10));

        assertFalse(sut.contains(new Point2D(1, 1)));
        assertFalse(sut.contains(new Point2D(0.965528, 0.694508)));
        assertFalse(sut.contains(new Point2D(0.50000, 0.000010)));
    }

    /**
     * Range
     */
    @Test
    public void range_onePoint_inRange() {
        Point2D p = new Point2D(0.5, 0.5);

        sut.insert(p);

        boolean pointInRange = false;
        for (Point2D point: sut.range(new RectHV(0, 0, 1, 1))) {
            pointInRange = point.equals(p);
        }

        assertTrue(pointInRange);
    }

    @Test
    public void range_10Points() {
        Point2D p1 = new Point2D(0.206107, 0.095492);
        Point2D p2 = new Point2D(0.975528, 0.654508);
        Point2D p3 = new Point2D(0.024472, 0.345492);
        Point2D p4 = new Point2D(0.793893, 0.095492);
        Point2D p5 = new Point2D(0.793893, 0.904508);
        Point2D p6 = new Point2D(0.975528, 0.345492);
        Point2D p7 = new Point2D(0.206107, 0.904508);
        Point2D p8 = new Point2D(0.500000, 0.000000);
        Point2D p9 = new Point2D(0.024472, 0.654508);
        Point2D p10 = new Point2D(0.500000, 1.000000);

        sut.insert(p1);
        sut.insert(p2);
        sut.insert(p3);
        sut.insert(p4);
        sut.insert(p5);
        sut.insert(p6);
        sut.insert(p7);
        sut.insert(p8);
        sut.insert(p9);
        sut.insert(p10);

        ArrayList<Point2D> pointsInRange = new ArrayList<>();
        for (Point2D point: sut.range(new RectHV(0.14, 0.74, 0.9, 1))) {
            pointsInRange.add(point);
        }

        assertEquals(3, pointsInRange.size());
        assertTrue(pointsInRange.contains(p5));
        assertTrue(pointsInRange.contains(p7));
        assertTrue(pointsInRange.contains(p10));
    }

    @Test
    public void range_onePoint_outOfRange() {
        Point2D p = new Point2D(0.5, 0.5);

        sut.insert(p);

        boolean pointInRange = false;
        for (Point2D point: sut.range(new RectHV(0, 0, 0.1, 0.4))) {
            pointInRange = point.equals(p);
        }

        assertFalse(pointInRange);
    }

    /**
     * Nearest
     */
    @Test
    public void nearest_threePoints() {
        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.6, 0.7);
        Point2D p3 = new Point2D(0.8, 0.9);

        sut.insert(p1);
        sut.insert(p2);
        sut.insert(p3);

        Point2D res = sut.nearest(new Point2D(0.6, 0.6));
        assertEquals(p2, res);
    }

    @Test
    public void nearest_10Points() {
        Point2D p1 = new Point2D(0.206107, 0.095492);
        Point2D p2 = new Point2D(0.975528, 0.654508);
        Point2D p3 = new Point2D(0.024472, 0.345492);
        Point2D p4 = new Point2D(0.793893, 0.095492);
        Point2D p5 = new Point2D(0.793893, 0.904508);
        Point2D p6 = new Point2D(0.975528, 0.345492);
        Point2D p7 = new Point2D(0.206107, 0.904508);
        Point2D p8 = new Point2D(0.500000, 0.000000);
        Point2D p9 = new Point2D(0.024472, 0.654508);
        Point2D p10 = new Point2D(0.500000, 1.000000);

        sut.insert(p1);
        sut.insert(p2);
        sut.insert(p3);
        sut.insert(p4);
        sut.insert(p5);
        sut.insert(p6);
        sut.insert(p7);
        sut.insert(p8);
        sut.insert(p9);
        sut.insert(p10);

        Point2D res = sut.nearest(new Point2D(1, 1));
        assertEquals(p5, res);
    }
}
