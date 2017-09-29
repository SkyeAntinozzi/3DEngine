package lumine.test;

import lumine.engine.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static org.junit.jupiter.api.Assertions.*;

public class ScreenTest {

    @Test
    public void testScreenConstruction(){

        Screen s = Screen.getScreen();

        BufferStrategy bs = s.getBufferStrategy();
        assertNull(bs);

        Dimension size = s.getPreferredSize();
        assertEquals(size.getHeight(), Screen.HEIGHT);
        assertEquals(size.getWidth(), Screen.WIDTH);

    }

    @Test
    public void testDrawPixelQuadrant1(){
        Screen s = Screen.getScreen();
        int[] pixels = Screen.getScreen().getPixels();
        int arrayTestLocation,
            xPoint,
            yPoint,
            pointColor,
            w = Screen.WIDTH,
            h = Screen.HEIGHT;

            xPoint = 170;
            yPoint = 53;
            pointColor = 0x12345678;
            arrayTestLocation = (
                            (w / 2)     +              // Offset half of width for center X origin
                            (h / 2 * w) +              // Offset half of height for center Y origin
                            (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
            s.drawPixel(xPoint,yPoint, pointColor);
            assertEquals(pixels[arrayTestLocation], pointColor);
            pointColor = 0x87654321;
            s.drawPixel(xPoint, yPoint, pointColor);
            assertEquals(pixels[arrayTestLocation], pointColor);
    }

    @Test
    public void testDrawPixelQuadrant2(){
        Screen s = Screen.getScreen();
        int[] pixels = Screen.getScreen().getPixels();
        int arrayTestLocation,
            xPoint,
            yPoint,
            pointColor,
            w = Screen.WIDTH,
            h = Screen.HEIGHT;

        xPoint = -84;
        yPoint = 2;
        pointColor = 0x12345678;
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        s.drawPixel(xPoint,yPoint, pointColor);
        assertEquals(pixels[arrayTestLocation], pointColor);
        pointColor = 0x87654321;
        s.drawPixel(xPoint, yPoint, pointColor);
        assertEquals(pixels[arrayTestLocation], pointColor);

    }

    @Test
    public void testDrawPixelQuadrant3(){
        Screen s = Screen.getScreen();
        int[] pixels = Screen.getScreen().getPixels();
        int arrayTestLocation,
            xPoint,
            yPoint,
            pointColor,
            w = Screen.WIDTH,
            h = Screen.HEIGHT;

        xPoint = -107;
        yPoint = -17;
        pointColor = 0x12345678;
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        s.drawPixel(xPoint,yPoint, pointColor);
        assertEquals(pixels[arrayTestLocation], pointColor);
        pointColor = 0x87654321;
        s.drawPixel(xPoint, yPoint, pointColor);
        assertEquals(pixels[arrayTestLocation], pointColor);

    }

    @Test
    public void testDrawPixelQuadrant4(){
        Screen s = Screen.getScreen();
        int[] pixels = Screen.getScreen().getPixels();
        int arrayTestLocation,
            xPoint,
            yPoint,
            pointColor,
            w = Screen.WIDTH,
            h = Screen.HEIGHT;


        xPoint = 118;
        yPoint = -17;
        pointColor = 0x12345678;
        arrayTestLocation = (
                        (w / 2)     +              // Offset half of width for center X origin
                        (h / 2 * w) +              // Offset half of height for center Y origin
                        (w * -yPoint + xPoint) );  // Orientate pixel from new center origin
        s.drawPixel(xPoint,yPoint, pointColor);
        assertEquals(pixels[arrayTestLocation], pointColor);
        pointColor = 0x87654321;
        s.drawPixel(xPoint, yPoint, pointColor);
        assertEquals(pixels[arrayTestLocation], pointColor);
    }

    @Test
    public void testBugFixXNeg384YNeg384(){
        Screen s = Screen.getScreen();
        s.drawPixel(-384, -384);            // If incorrect then exception is thrown
    }

}
