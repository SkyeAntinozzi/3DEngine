package lumine.test;

import lumine.engine.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {

    @Test
    public void testVectorConstruction(){
        Vector v = new Vector(1,1,1);
        assertEquals(v.getX(), 1);
        assertEquals(v.getY(), 1);
        assertEquals(v.getZ(), 1);
        assertEquals(v.toString(), "Vector: [" + 1.0 + ", " + 1.0 + ", " + 1.0 + "]");

        v = new Vector(1.87234, 0, Double.MAX_VALUE);
        assertEquals(v.getX(), 1.87234);
        assertEquals(v.getY(), 0);
        assertEquals(v.getZ(), Double.MAX_VALUE);
        assertEquals(v.toString(), "Vector: [" + 1.87234 + ", " + 0.0 + ", " + Double.MAX_VALUE + "]");

        v = new Vector(1.87234, -14087.432, 498724.2341);
        assertEquals(v.getX(), 1.87234);
        assertEquals(v.getY(), -14087.432);
        assertEquals(v.getZ(), 498724.2341);
        assertEquals(v.toString(), "Vector: [" + 1.87234 + ", " + -14087.432 + ", " + 498724.2341 + "]");
    }

    @Test
    public void testAddVectorToVector(){
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);
        Vector result;
        result = v1.addVectorToVector(v2);
        assertEquals(result.getX(), 0);
        assertEquals(result.getY(), 0);
        assertEquals(result.getZ(), 0);

        v1 = new Vector(-22,17,Integer.MAX_VALUE);
        v2 = new Vector(14,-7,Integer.MIN_VALUE);
        result = v1.addVectorToVector(v2);
        assertEquals(result.getX(), -8);
        assertEquals(result.getY(), 10);
        assertEquals(result.getZ(), 0xFFFFFFFF);

        v1 = new Vector(-2340.1353,234.22226,39487.222417);
        v2 = new Vector(-3.14523,38237.22,23154.578);
        result = v1.addVectorToVector(v2);
        assertEquals(result.getX(), -2340.1353 - 3.14523);
        assertEquals(result.getY(), 234.22226 + 38237.22);
        assertEquals(result.getZ(), 39487.222417 + 23154.578);

    }

    @Test
    public void testSubtractVectorFromVector(){
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);
        Vector result;
        result = v1.subtractVectorFromVector(v2);
        assertEquals(result.getX(), 2);
        assertEquals(result.getY(), 4);
        assertEquals(result.getZ(), 6);

        v1 = new Vector(-22,17,Double.MAX_VALUE);
        v2 = new Vector(14,-7,Double.MIN_VALUE);
        result = v1.subtractVectorFromVector(v2);
        assertEquals(result.getX(), -22 - 14);
        assertEquals(result.getY(), 17 - -7);
        assertEquals(result.getZ(), Double.MAX_VALUE - Double.MIN_VALUE);

        v1 = new Vector(-2340.1353,234.22226,39487.222417);
        v2 = new Vector(-3.14523,38237.22,23154.578);
        result = v1.subtractVectorFromVector(v2);
        assertEquals(result.getX(), -2340.1353 + 3.14523);
        assertEquals(result.getY(), 234.22226 - 38237.22);
        assertEquals(result.getZ(), 39487.222417 - 23154.578);
    }



}
