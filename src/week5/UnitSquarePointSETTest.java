package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import week3.Point;

import java.util.Arrays;
import java.util.Collection;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertFalse(sut.contains(new Point2D(1, 2)));
    }

    @Test
    public void nearest_emptySet() {
        assertEquals(null, sut.nearest(new Point2D(1, 2)));
    }

    @Test
    public void range_emptySet() {
        int pointsCount = 0;
        for (Point2D p: sut.range(new RectHV(1, 2, 3, 4))) {
            pointsCount++;
        }

        assertEquals(0, pointsCount);
    }

    /**
     * isEmpty, size and contains after insert
     */
    @Test
    public void isEmpty_afterOneInsert() {
        sut.insert(new Point2D(1, 2));

        assertFalse(sut.isEmpty());
    }

    @Test
    public void size_afterOneInsert() {
        sut.insert(new Point2D(1, 2));

        assertEquals(1, sut.size());
    }

    @Test
    public void size_afterThreeInsert() {
        sut.insert(new Point2D(1, 2));
        sut.insert(new Point2D(2, 3));
        sut.insert(new Point2D(3, 4));

        assertEquals(3, sut.size());
    }

    @Test
    public void contains_afterOneInsert() {
        Point2D p = new Point2D(1, 2);

        sut.insert(p);

        assertTrue(sut.contains(p));
    }

    @Test
    public void contains_afterThreeInsert() {
        Point2D p1 = new Point2D(1, 2);
        Point2D p2 = new Point2D(2, 3);
        Point2D p3 = new Point2D(3, 4);

        sut.insert(p1);
        sut.insert(p2);
        sut.insert(p3);

        assertTrue(sut.contains(p2));
        assertFalse(sut.contains(new Point2D(10, 10)));
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
    public void range_onePoint_outOfRange() {
        Point2D p = new Point2D(0.5, 0.5);

        sut.insert(p);

        boolean pointInRange = false;
        for (Point2D point: sut.range(new RectHV(0, 0, 0.1, 0.4))) {
            pointInRange = point.equals(p);
        }

        assertFalse(pointInRange);
    }

    @Test
    public void nearest_threePoints() {
        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.6, 0.7);
        Point2D p3 = new Point2D(0.8, 0.9);

        sut.insert(p1);
        sut.insert(p2);
        sut.insert(p3);

        assertEquals(p2, sut.nearest(new Point2D(0.6, 0.6)));
    }
}
