package week5;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;

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
            fail("Should throw NullPointerException when insert null");
        } catch (NullPointerException ignored) { }
    }
}
