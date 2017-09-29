package lumine.test;

import lumine.engine.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScaleTransformTest {

    @Test
    public void testScale(){

        Vector v1 = new Vector(3,4,0);
        Vector v2 = v1.scale(2,1,1);
        assertEquals(v2.getX(), 6);
        assertEquals(v2.getY(), 4);
        assertEquals(v2.getZ(), 0);

        v1 = new Vector(-7,3,5);
        v2 = v1.scale(3,0,9);
        assertEquals(v2.getX(), -21);
        assertEquals(v2.getY(), 0);
        assertEquals(v2.getZ(), 45);

        v1 = new Vector(0,-1,0);
        v2 = v1.scale(-1,0,-1);
        assertEquals(v2.getX() + 0.0, 0);   // IEEE F-PA 754 defines -0.0, add 0.0 to avoid
        assertEquals(v2.getY() + 0.0, 0);
        assertEquals(v2.getZ() + 0.0, 0);

        v1 = new Vector(1,2,3);
        v2 = v1.scale(0.5,0.5,0.5);
        assertEquals(v2.getX(), 0.5);
        assertEquals(v2.getY(), 1);
        assertEquals(v2.getZ(), 1.5);

    }

}
