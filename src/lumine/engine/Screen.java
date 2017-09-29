/**
 * Author: Skye Antinozzi
 * Provides a viewport for viewing the 3D scene.
 */
package lumine.engine;

import lumine.examples.Example;
import lumine.examples.ThreeDimensionsBasicExample;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;


public class Screen extends Canvas {

    public static final int WIDTH = 1024,
                            HEIGHT = 768,
                            RIGHT_BOUND = (WIDTH >> 1),
                            LEFT_BOUND = -RIGHT_BOUND,
                            TOP_BOUND = (HEIGHT >> 1),
                            BOTTOM_BOUND = -TOP_BOUND;

    public static final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
    public static final int DEFAULT_PIXEL_COLOR_VALUE = 0xFFFFFFFF;
    public static final int DEFAULT_BACKGROUND_COLOR_VALUE = 0x0;
    public static final Color UI_ELEMENT_COLOR = new Color(0xFF,0xFF,0xFF);

    private BufferStrategy bufferStrategy;
    private BufferedImage image;
    private int[] pixels;
    private boolean running;
    private boolean showCartesianGrid;
    private String title,
                   message;
    private Example example;

    private static Screen screen;

    private Screen(){

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        // Default color for image
        for(int i = 0; i < pixels.length; i++)
            pixels[i] = DEFAULT_BACKGROUND_COLOR_VALUE;

        ScreenInputHandler screenInputHandler = new ScreenInputHandler();
        addKeyListener(screenInputHandler);
        addMouseListener(screenInputHandler);

        setIgnoreRepaint(true);
        setPreferredSize(SIZE);
    }

    public static Screen getScreen(){

        if(screen == null)
            screen = new Screen();

        return screen;
    }

    public void start(){

        if(running)
            return;

        running = true;

        // Examples to load onto the screen
        loadExample();

        requestFocus();

        long lastTime = System.nanoTime(),
             currentTime;
        int oneSecond = 1000000000,
            frames = 30,
            thirtyFps = oneSecond / frames;

        while(running){
            currentTime = System.nanoTime();
            if(currentTime - lastTime > thirtyFps) {
                render();
                lastTime = System.nanoTime();
            }
        }
    }

    public void stop(){
        if(!running)
            return;

        running = false;
    }

    private void loadExample(){
        example = ThreeDimensionsBasicExample.load(this);
    }

    private void render(){

        if(bufferStrategy == null) {
            createBufferStrategy(2);
            bufferStrategy = getBufferStrategy();
        }

        clearScreen();

        if(example != null)
            example.draw();

        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(image,0,0,image.getWidth(),image.getHeight(),null);
        checkDrawOptions(g);
        bufferStrategy.show();
        g.dispose();
    }

    public void clearScreen(){
        for(int i = 0; i < pixels.length; i++)
            pixels[i] = DEFAULT_BACKGROUND_COLOR_VALUE;
    }

    public void checkDrawOptions(Graphics g){
        if(showCartesianGrid) {
            g.setColor(UI_ELEMENT_COLOR);
            drawCartesianPlane(g);
        }

        if(title != null) {
            g.setColor(UI_ELEMENT_COLOR);
            g.drawString(title, 0, 10);
        }

        if(message != null) {
            g.setColor(UI_ELEMENT_COLOR);
            g.drawString(message, 0, 25);
        }
    }

    private void drawCartesianPlane(Graphics g){

        int yAxisXStart = WIDTH >> 1,
                yAxisXEnd = yAxisXStart,
                yAxisYStart = 0,
                yAxisYEnd = HEIGHT;

        int xAxisXStart = 0,
                xAxisXEnd = WIDTH,
                xAxisYStart = HEIGHT >> 1,
                xAxisYEnd = xAxisYStart;

        g.drawLine(yAxisXStart, yAxisYStart, yAxisXEnd, yAxisYEnd);
        g.drawLine(xAxisXStart, xAxisYStart, xAxisXEnd, xAxisYEnd);

    }

    public void toggleCartesianPlane(){
        showCartesianGrid = !showCartesianGrid;
    }

    public void drawPixel(int x, int y, int color){
        if( (x > LEFT_BOUND && x < RIGHT_BOUND) && (y > BOTTOM_BOUND && y < TOP_BOUND) )
            pixels[ (WIDTH >> 1)             +  // Offset half of width for center X origin
                    ( (HEIGHT >> 1) * WIDTH) +  // Offset half of height for center Y origin
                    (WIDTH * -y + x) ] = color; // Orientate pixel from new center origin
    }

    public void drawPixel(int x, int y){
            if ((x > LEFT_BOUND && x < RIGHT_BOUND) && (y > BOTTOM_BOUND && y < TOP_BOUND))
                pixels[(WIDTH >> 1) +                       // Offset half of width for center X origin
                        ((HEIGHT >> 1) * WIDTH) +                       // Offset half of height for center Y origin
                        (WIDTH * -y + x)] = DEFAULT_PIXEL_COLOR_VALUE;  // Orientate pixel from new center origin
    }

    public int[] getPixels(){
        return pixels;
    }

    public void drawTitle(String title){
        this.title = title;
    }

    public void drawMessage(String message){
        this.message = message;
    }

    public static class ScreenInputHandler implements MouseListener, KeyListener {

        //region Mouse Events
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("X: " + (e.getX() - (WIDTH >> 1)) + " Y: " + ((HEIGHT >> 1) - e.getY()));
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
        //endregion

        //region Key Events
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if(keyCode == KeyEvent.VK_G)
                getScreen().toggleCartesianPlane();

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
        //endregion
    }
}
