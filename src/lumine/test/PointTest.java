package lumine.test;

import lumine.engine.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class PointTest {

    @Test
    public void testPointConstruction(){

        Point p = new Point(2,2,2);
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);
        assertEquals(p.getZ(), 2);
        assertEquals(p.toString(), "Point: (" + 2.0 + ", " + 2.0 + ", " + 2.0 + ")");

        p = new Point(Double.MAX_VALUE, 0, 1.87234);
        assertEquals(p.getX(), Double.MAX_VALUE);
        assertEquals(p.getY(), 0);
        assertEquals(p.getZ(), 1.87234);
        assertEquals(p.toString(), "Point: (" + Double.MAX_VALUE + ", " + 0.0 + ", " + 1.87234 + ")");

        p = new Point(-17.5467, 14.8576, -107.32345);
        assertEquals(p.getX(), -17.5467);
        assertEquals(p.getY(), 14.8576);
        assertEquals(p.getZ(), -107.32345);
        assertEquals(p.toString(), "Point: (" + -17.5467 + ", " + 14.8576 + ", " + -107.32345 + ")");
    }

    @Test
    public void testAddVectorToPoint(){

        Point p = new Point(1,1,1);
        Vector v = new Vector(3,3,3);
        p = p.addVectorToPoint(v);
        assertEquals(p.getX(), 4);
        assertEquals(p.getY(), 4);
        assertEquals(p.getZ(), 4);

        p = new Point(-1, 0, 1);
        v = new Vector(1, 2, 3);
        p = p.addVectorToPoint(v);
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 2);
        assertEquals(p.getZ(), 4);

        p = new Point(-1.52, 7.54127, 23.777);
        v = new Vector(14.8, -107.22, 55.876123);
        p = p.addVectorToPoint(v);
        assertEquals(p.getX(), 14.8 - 1.52);
        assertEquals(p.getY(), 7.54127 - 107.22);
        assertEquals(p.getZ(), 23.777 + 55.876123);
    }

    @Test
    public void testSubtractVectorFromPoint(){
        Point p = new Point(1,1,1);
        Vector v = new Vector(3,3,3);
        p = p.subtractVectorFromPoint(v);
        assertEquals(p.getX(), -2);
        assertEquals(p.getY(), -2);
        assertEquals(p.getZ(), -2);

        p = new Point(-1, 0, 1);
        v = new Vector(1, 3, 5);
        p = p.subtractVectorFromPoint(v);
        assertEquals(p.getX(), -2);
        assertEquals(p.getY(), -3);
        assertEquals(p.getZ(), -4);

        p = new Point(-107.852, 0.0000, 27.8452);
        v = new Vector(11.11, 1000.85672, 14.0);
        p = p.subtractVectorFromPoint(v);
        assertEquals(p.getX(), -107.852 - 11.11);
        assertEquals(p.getY(), 0.0000 - 1000.85672);
        assertEquals(p.getZ(), 27.8452 - 14.0);
    }

    @Test
    public void testSubtractPointFromPoint(){
        Point p1 = new Point(1,1,1);
        Point p2 = new Point(1,2,3);
        Vector result;

        result = p1.subtractPointFromPoint(p2);
        assertEquals(result.getX(), 0);
        assertEquals(result.getY(), -1);
        assertEquals(result.getZ(), -2);

        p1 = new Point(5,6,7);
        p2 = new Point(-1, 0, -10);
        result = p1.subtractPointFromPoint(p2);
        assertEquals(result.getX(), 6);
        assertEquals(result.getY(), 6);
        assertEquals(result.getZ(), 17);

        p1 = new Point(-1.52, 7.54127, 23.777);
        p2 = new Point(-107.852, 0.0000, 27.8452);
        result = p1.subtractPointFromPoint(p2);
        assertEquals(result.getX(), -1.52 + 107.852);
        assertEquals(result.getY(), 7.54127 - 0.0000);
        assertEquals(result.getZ(), 23.777 - 27.8452);

    }

    @Test
    public void testDrawPointQuadrant1(){

        int w = Screen.WIDTH,
            h = Screen.HEIGHT,
            xPoint,
            yPoint,
            pointColor,
            arrayTestLocation;
        int[] pixels = Screen.getScreen().getPixels();

        // (5,5) - Using Point.getX() and Point.getY()
        xPoint = 5;
        yPoint = 5;
        pointColor = 0x11223344;
        Point p = new Point(xPoint,yPoint,0);
        xPoint = (int) Math.round(p.getX());
        yPoint = (int) Math.round(p.getY());
        p.drawPoint(pointColor);
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], pointColor);

        // (5,5) - Using xPoint and yPoint
        xPoint = 5;
        yPoint = 5;
        p = new Point(xPoint,yPoint,0);
        p.drawPoint();
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], Screen.DEFAULT_PIXEL_COLOR_VALUE);

        // Corner - Using Point.getX() and Point.getY()
        xPoint = 319;
        yPoint = 0;
        pointColor = 0x12345678;
        p = new Point(xPoint,yPoint,0);
        xPoint = (int) Math.round(p.getX());
        yPoint = (int) Math.round(p.getY());
        p.drawPoint(pointColor);
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], pointColor);

        // Corner - Using xPoint and yPoint
        xPoint = 319;
        yPoint = 0;
        p = new Point(xPoint,yPoint,0);
        p.drawPoint();
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], Screen.DEFAULT_PIXEL_COLOR_VALUE);
    }

    @Test
    public void testDrawPointQuadrant2(){

        int w = Screen.WIDTH,
            h = Screen.HEIGHT,
            xPoint,
            yPoint,
            pointColor,
            arrayTestLocation;
        int[] pixels = Screen.getScreen().getPixels();

        // (5,5) - Using Point.getX() and Point.getY()
        xPoint = -5;
        yPoint = 5;
        pointColor = 0x11223344;
        Point p = new Point(xPoint,yPoint,0);
        xPoint = (int) Math.round(p.getX());
        yPoint = (int) Math.round(p.getY());
        p.drawPoint(pointColor);
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], pointColor);

        // (5,5) - Using xPoint and yPoint
        xPoint = -5;
        yPoint = 5;
        p = new Point(xPoint,yPoint,0);
        p.drawPoint();
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], Screen.DEFAULT_PIXEL_COLOR_VALUE);

        // Corner - Using Point.getX() and Point.getY()
        xPoint = -319;
        yPoint = 0;
        pointColor = 0x12345678;
        p = new Point(xPoint,yPoint,0);
        xPoint = (int) Math.round(p.getX());
        yPoint = (int) Math.round(p.getY());
        p.drawPoint(pointColor);
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], pointColor);

        // Corner - Using xPoint and yPoint
        xPoint = -319;
        yPoint = 0;
        p = new Point(xPoint,yPoint,0);
        p.drawPoint();
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], Screen.DEFAULT_PIXEL_COLOR_VALUE);
    }

    @Test
    public void testDrawPointQuadrant3(){

        int w = Screen.WIDTH,
            h = Screen.HEIGHT,
            xPoint,
            yPoint,
            pointColor,
            arrayTestLocation;
        int[] pixels = Screen.getScreen().getPixels();

        // (5,5) - Using Point.getX() and Point.getY()
        xPoint = -5;
        yPoint = -5;
        pointColor = 0x11223344;
        Point p = new Point(xPoint,yPoint,0);
        xPoint = (int) Math.round(p.getX());
        yPoint = (int) Math.round(p.getY());
        p.drawPoint(pointColor);
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], pointColor);

        // (5,5) - Using xPoint and yPoint
        xPoint = -5;
        yPoint = -5;
        p = new Point(xPoint,yPoint,0);
        p.drawPoint();
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], Screen.DEFAULT_PIXEL_COLOR_VALUE);

        // Corner - Using Point.getX() and Point.getY()
        xPoint = -319;
        yPoint = -239;
        pointColor = 0x12345678;
        p = new Point(xPoint,yPoint,0);
        xPoint = (int) Math.round(p.getX());
        yPoint = (int) Math.round(p.getY());
        p.drawPoint(pointColor);
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], pointColor);

        // Corner - Using xPoint and yPoint
        xPoint = -319;
        yPoint = -239;
        p = new Point(xPoint,yPoint,0);
        p.drawPoint();
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], Screen.DEFAULT_PIXEL_COLOR_VALUE);
    }

    @Test
    public void testDrawPointQuadrant4(){

        int w = Screen.WIDTH,
            h = Screen.HEIGHT,
            xPoint,
            yPoint,
            pointColor,
            arrayTestLocation;
        int[] pixels = Screen.getScreen().getPixels();

        // (5,5) - Using Point.getX() and Point.getY()
        xPoint = 5;
        yPoint = -5;
        pointColor = 0x11223344;
        Point p = new Point(xPoint,yPoint,0);
        xPoint = (int) Math.round(p.getX());
        yPoint = (int) Math.round(p.getY());
        p.drawPoint(pointColor);
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], pointColor);

        // (5,5) - Using xPoint and yPoint
        xPoint = 5;
        yPoint = -5;
        p = new Point(xPoint,yPoint,0);
        p.drawPoint();
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], Screen.DEFAULT_PIXEL_COLOR_VALUE);

        // Corner - Using Point.getX() and Point.getY()
        xPoint = 319;
        yPoint = -239;
        pointColor = 0x12345678;
        p = new Point(xPoint,yPoint,0);
        xPoint = (int) Math.round(p.getX());
        yPoint = (int) Math.round(p.getY());
        p.drawPoint(pointColor);
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], pointColor);

        // Corner - Using xPoint and yPoint
        xPoint = 319;
        yPoint = -239;
        p = new Point(xPoint,yPoint,0);
        p.drawPoint();
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        assertEquals(pixels[arrayTestLocation], Screen.DEFAULT_PIXEL_COLOR_VALUE);
    }

    @Test
    public void testSetPointToPoint(){

        Point p1 = new Point(1,2,3);
        Point p2 = new Point(5,6,7);
        assertEquals(p1.getX(), 1);
        assertEquals(p1.getY(), 2);
        assertEquals(p1.getZ(), 3);
        p1.setPointToPoint(p2);
        assertEquals(p1.getX(), 5);
        assertEquals(p1.getY(), 6);
        assertEquals(p1.getZ(), 7);

        p1 = new Point(-17,0,17);
        p2 = new Point(Double.MAX_VALUE, 0, 1.87234);
        assertEquals(p1.getX(), -17);
        assertEquals(p1.getY(), 0);
        assertEquals(p1.getZ(), 17);
        p1.setPointToPoint(p2);
        assertEquals(p1.getX(), Double.MAX_VALUE);
        assertEquals(p1.getY(), 0);
        assertEquals(p1.getZ(), 1.87234);

        p1 = new Point(-17.654712,23.6547,1070.7773352);
        p2 = new Point(145.85, 0.00001, 12365485.32154);
        assertEquals(p1.getX(), -17.654712);
        assertEquals(p1.getY(), 23.6547);
        assertEquals(p1.getZ(), 1070.7773352);
        p1.setPointToPoint(p2);
        assertEquals(p1.getX(), 145.85);
        assertEquals(p1.getY(), 0.00001);
        assertEquals(p1.getZ(), 12365485.32154);


    }

}