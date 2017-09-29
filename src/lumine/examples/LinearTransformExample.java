package lumine.examples;

import lumine.engine.Point;
import lumine.engine.Screen;
import lumine.engine.Vector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LinearTransformExample extends Example {

    private static Point[] points = new Point[100];
    private static LinearTransformExample linearTransformExample;

    private LinearTransformExample(){}

    public static LinearTransformExample load(Screen screen){
        if(linearTransformExample != null)
            return linearTransformExample;

        linearTransformExample = new LinearTransformExample();
        drawSquare();
        screen.addKeyListener(new LinearTransformationInputHandler());
        screen.drawTitle("Linear Transformation Example. (A=Scale 0.5 | S=Scale 2 | R=Rotate 15° | E=Rotate -15°)");
        return linearTransformExample;
    }

    private static void drawSquare(){
        // Top Side
        for(int i = 25, x = 0; i < 50; i++, x++){
            points[i] = new Point(0, x, 0);
            points[i].drawPoint();
        }

        // Bottom Side
        for(int i = 0, x = 0; i < 25; i++, x++){
            points[i] = new Point(24, x, 0);
            points[i].drawPoint();
        }

        // Left side
        for(int i = 50, y = 0; i < 75; i++, y++){
            points[i] = new Point(y, 0, 0);
            points[i].drawPoint();
        }

        // Right side
        for(int i = 75, y = 0; i < 100; i++, y++){
            points[i] = new Point(y, 24, 0);
            points[i].drawPoint();
        }
    }

    private void scaleByHalf(){
        System.out.println("Scale by half");

        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.scale(0.5, 0.5, 0.5));
            points[i].drawPoint();
        }

    }

    private void scaleByDouble(){
        System.out.println("Scale by double");

        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.scale(2.0, 2.0, 2.0));
            points[i].drawPoint();
        }
    }

    private void rotateBy15(){
        System.out.println("Rotate by 15°");
        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.rotateXY(15));
            points[i].drawPoint();
        }
    }

    private void rotateByNegative15(){
        System.out.println("Rotate by -15°");

        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.rotateXY(-15));
            points[i].drawPoint();
        }
    }

    public void draw(){
        for(int i = 0; i < points.length; i++)
            points[i].drawPoint();
    }

    private static class LinearTransformationInputHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_ESCAPE){
                System.out.println("Shutting down...");
                Screen.getScreen().stop();
                System.exit(0);
            }
            else if(keyCode == KeyEvent.VK_A)
                linearTransformExample.scaleByHalf();
            else if(keyCode == KeyEvent.VK_S)
                linearTransformExample.scaleByDouble();
            else if(keyCode == KeyEvent.VK_R)
                linearTransformExample.rotateBy15();
            else if(keyCode == KeyEvent.VK_E)
                linearTransformExample.rotateByNegative15();
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
