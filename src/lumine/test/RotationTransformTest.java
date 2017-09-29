package lumine.test;

import lumine.engine.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RotationTransformTest {

    @Test
    public void testRotateXY(){

        Vector v1 = new Vector(1,2,3);
        Vector v2 = v1.rotateXY(90);
        assertEquals(v2.getX(), -2, .09);
        assertEquals(v2.getY(), 1, .09);
        assertEquals(v2.getZ(), 3, .09);

        v1 = new Vector(0,3,7);
        v2 = v1.rotateXY(36);               // π/5
        assertEquals(v2.getX(), -1.7, .09);
        assertEquals(v2.getY(), 2.4, .09);
        assertEquals(v2.getZ(), 7, .09);

        v1 = new Vector(-1,2,-5);
        v2 = v1.rotateXY(45);
        assertEquals(v2.getX(), -2.1, .09);
        assertEquals(v2.getY(), 0.7, .09);
        assertEquals(v2.getZ(), -5, .09);
    }

    @Test
    public void testRotateXZ(){

        Vector v1 = new Vector(1,2,3);
        Vector v2 = v1.rotateXZ(90);
        assertEquals(v2.getX(), 3, .09);
        assertEquals(v2.getY(), 2, .09);
        assertEquals(v2.getZ(), -1, .09);

        v1 = new Vector(0,3,7);
        v2 = v1.rotateXZ(36);               // π/5
        assertEquals(v2.getX(), 4.1, .09);
        assertEquals(v2.getY(), 3, .09);
        assertEquals(v2.getZ(), 5.6, .09);

        v1 = new Vector(-1,2,-5);
        v2 = v1.rotateXZ(45);
        assertEquals(v2.getX(), -4.2, .09);
        assertEquals(v2.getY(), 2, .09);
        assertEquals(v2.getZ(), -2.8, .09);
    }

    @Test
    public void testRotateYZ(){

        Vector v1 = new Vector(1,2,3);
        Vector v2 = v1.rotateYZ(90);
        assertEquals(v2.getX(), 1, .09);
        assertEquals(v2.getY(), -3, .09);
        assertEquals(v2.getZ(), 2, .09);

        v1 = new Vector(0,3,7);
        v2 = v1.rotateYZ(36);               // π/5
        assertEquals(v2.getX(), 0, .09);
        assertEquals(v2.getY(), -1.6, .09);
        assertEquals(v2.getZ(), 7.4, .09);

        v1 = new Vector(-1,2,-5);
        v2 = v1.rotateYZ(45);
        assertEquals(v2.getX(), -1, .09);
        assertEquals(v2.getY(), 4.9, .09);
        assertEquals(v2.getZ(), -2.1, .09);

    }



}
