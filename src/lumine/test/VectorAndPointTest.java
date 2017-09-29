package lumine.test;

import lumine.engine.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VectorAndPointTest {

    @Test
    public void testMainVectorAndPointTest(){
        Point p1 = new Point(1,2,1);
        Point p2 = new Point(0,4,4);
        Vector v1 = new Vector(2,0,0);
        Vector v2;

        assertEquals(p1.toString(), "Point: (" + 1.0 + ", " + 2.0 + ", " + 1.0 + ")");
        assertEquals(p2.toString(), "Point: (" + 0.0 + ", " + 4.0 + ", " + 4.0 + ")");

        v2 = p1.subtractPointFromPoint(p2);

        v1 = v1.addVectorToVector(v2);

        p1 = p1.addVectorToPoint(v1);
        assertEquals(p1.getX(), 4);
        assertEquals(p1.getY(), 0);
        assertEquals(p1.getZ(), -2);

        p2 = p2.subtractVectorFromPoint(v2);
        assertEquals(p2.getX(), -1);
        assertEquals(p2.getY(), 6);
        assertEquals(p2.getZ(), 7);
    }

}
