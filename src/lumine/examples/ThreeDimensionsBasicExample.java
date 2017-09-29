package lumine.examples;

import lumine.engine.Camera;
import lumine.engine.Point;
import lumine.engine.Screen;
import lumine.engine.Vector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class ThreeDimensionsBasicExample extends Example {

    private static Point[] points;
    private static Camera camera;
    private static Screen screen;
    private static Robot robot = new Robot();
    private static ThreeDimensionsBasicExample threeDimensionsBasicExample;
    private boolean drawWithCulling;
    private static int rotation = 15;
    private static final int numberOfPoints = 1600;
    private static int drawColor = 0x4286F4;

    private ThreeDimensionsBasicExample(){}

    public static ThreeDimensionsBasicExample load(Screen screenArg){
        if(threeDimensionsBasicExample != null)
            return threeDimensionsBasicExample;

        threeDimensionsBasicExample = new ThreeDimensionsBasicExample();
        screen = screenArg;
        camera = new Camera();
        camera.setPointsInWorld(numberOfPoints);
        points = camera.getPoints();
        drawCube();

        screen.addKeyListener(new ThreeDimensionsBasicExampleInputHandler());
        screen.drawTitle("(↓=Scale x0.5 | ↑=Scale x2 | " +
                             "Q=Rotate XY "+rotation+"° | W=Rotate XY -"+rotation+"° | " +
                             "A=Rotate XZ "+rotation+"° | S=Rotate XZ -"+rotation+"° | " +
                             "Z=Rotate YZ "+rotation+"° | X=Rotate YZ -"+rotation+"° | " +
                             "C=Cull | D=No Cull | R=Robot | G=Grid )");

        return threeDimensionsBasicExample;
    }

    private static void drawCube(){
        drawFrontSquare();
        drawBackSquare();

        drawTopSquare();
        drawBottomSquare();
    }

    private static void drawFrontSquare(){
        int base = 0,
            length = numberOfPoints / 16;

        // Bottom Side
        for(int i = base, x = 0; i < base + length; i++, x++){
            points[i] = new Point(length-1, x, 0);
            points[i].drawPoint();
        }

        // Top Side
        for(int i = base + length, x = 0; i < base + length * 2; i++, x++){
            points[i] = new Point(0, x, 0);
            points[i].drawPoint();
        }

        // Left side
        for(int i = base + length * 2, y = 0; i < base + length * 3; i++, y++){
            points[i] = new Point(y, 0, 0);
            points[i].drawPoint();
        }

        // Right side
        for(int i = base + length * 3, y = 0; i < base + length * 4; i++, y++){
            points[i] = new Point(y, length-1, 0);
            points[i].drawPoint();
        }
    }

    private static void drawBackSquare(){
        int base = numberOfPoints / 4,
            length = numberOfPoints / 16;

        // Bottom Side
        for(int i = base, x = 0; i < base + length; i++, x++){
            points[i] = new Point(length-1, x, length);
            points[i].drawPoint();
        }

        // Top Side
        for(int i = base + length, x = 0; i < base + length * 2; i++, x++){
            points[i] = new Point(0, x, length);
            points[i].drawPoint();
        }

        // Left side
        for(int i = base + length * 2, y = 0; i < base + length * 3; i++, y++){
            points[i] = new Point(y, 0, length);
            points[i].drawPoint();
        }

        // Right side
        for(int i = base + length * 3, y = 0; i < base + length * 4; i++, y++){
            points[i] = new Point(y, length-1, length);
            points[i].drawPoint();
        }
    }

    private static void drawTopSquare() {
        int base = numberOfPoints / 2,
            length = numberOfPoints / 16;

        // Bottom Side
        for(int i = base, x = 0; i < base + length; i++, x++){
            points[i] = new Point(length-1, x, 0);
            points[i].drawPoint();
        }

        // Top Side
        for(int i = base + length, x = 0; i < base + length * 2; i++, x++){
            points[i] = new Point(0, x, 0);
            points[i].drawPoint();
        }

        // Left side
        for(int i = base + length * 2, y = 0; i < base + length * 3; i++, y++){
            points[i] = new Point(0, 0, y);
            points[i].drawPoint();
        }

        // Right side
        for(int i = base + length * 3, y = 0; i < base + length * 4; i++, y++){
            points[i] = new Point(0, length-1, y);
            points[i].drawPoint();
        }
    }

    private static void drawBottomSquare(){
        int base = numberOfPoints * 3 / 4,
            length = numberOfPoints / 16;

        // Bottom Side
        for(int i = base, x = 0; i < base + length; i++, x++){
            points[i] = new Point(length-1, x, length);
            points[i].drawPoint();
        }

        // Top Side
        for(int i = base + length, x = 0; i < base + length * 2; i++, x++){
            points[i] = new Point(0, x, length);
            points[i].drawPoint();
        }

        // Left side
        for(int i = base + length * 2, y = 0; i < base + length * 3; i++, y++){
            points[i] = new Point(length, 0, y);
            points[i].drawPoint();
        }

        // Right side
        for(int i = base + length * 3, y = 0; i < base + length * 4; i++, y++){
            points[i] = new Point(length, length-1, y);
            points[i].drawPoint();
        }
    }


    private void scaleByHalf(){
        screen.drawMessage("Scale x0.05");

        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.scale(0.5, 0.5, 0.5));
            points[i].drawPoint(drawColor);
        }

    }

    public void scale(double scale){
        screen.drawMessage("Scale x" + scale);

        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.scale(scale, scale, scale));
            points[i].drawPoint(drawColor);
        }
    }

    private void scaleByDouble(){
        screen.drawMessage("Scale x2");

        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.scale(2.0, 2.0, 2.0));
            points[i].drawPoint(drawColor);
        }
    }

    private void rotateXY(double degrees) {
        screen.drawMessage("Rotate XY " + degrees + "°");
        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.rotateXY(degrees));
            points[i].drawPoint(drawColor);
        }
    }

    private void rotateXZ(double degrees) {
        screen.drawMessage("Rotate XZ " + degrees + "°");
        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.rotateXZ(degrees));
            points[i].drawPoint(drawColor);
        }
    }

    private void rotateYZ(double degrees) {
        screen.drawMessage("Rotate YZ " + degrees + "°");
        Point origin = new Point(0,0,0);
        Vector vectorFromOrigin;

        for(int i = 0; i < points.length; i++){
            vectorFromOrigin = points[i].subtractPointFromPoint(origin);
            points[i].setPointToPoint(origin);
            points[i] = points[i].addVectorToPoint(vectorFromOrigin.rotateYZ(degrees));
            points[i].drawPoint(drawColor);
        }
    }

    public void draw(){
        if(drawWithCulling)
            camera.drawScene();
        else {
            for (int i = 0; i < points.length; i++)
                points[i].drawPoint(drawColor);
        }
    }

    private static class ThreeDimensionsBasicExampleInputHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();

            if(robot.running()) {
                if (keyCode == KeyEvent.VK_R) {
                    robot.stop();
                    screen.drawTitle("(↓=Scale x0.5 | ↑=Scale x2 | " +
                            "Q=Rotate XY "+rotation+"° | W=Rotate XY -"+rotation+"° | " +
                            "A=Rotate XZ "+rotation+"° | S=Rotate XZ -"+rotation+"° | " +
                            "Z=Rotate YZ "+rotation+"° | X=Rotate YZ -"+rotation+"° | " +
                            "C=Cull | D=No Cull | R=Robot | G=Grid )");
                }
                else if(keyCode == KeyEvent.VK_ESCAPE) {
                    System.out.println("Shutting down...");
                    Screen.getScreen().stop();
                    System.exit(0);
                }
            }
            else {
                    switch (keyCode) {
                        case KeyEvent.VK_D:
                            if(threeDimensionsBasicExample.drawWithCulling) {
                                screen.drawMessage("Culling Disabled");
                                threeDimensionsBasicExample.drawWithCulling = false;
                            } else
                                screen.drawMessage("Culling Already Disabled");
                            break;
                        case KeyEvent.VK_C:
                            if(!threeDimensionsBasicExample.drawWithCulling) {
                                screen.drawMessage("Culling Enabled");
                                threeDimensionsBasicExample.drawWithCulling = true;
                            } else
                                screen.drawMessage("Culling Already Enabled");
                            break;
                        case KeyEvent.VK_DOWN:
                            threeDimensionsBasicExample.scaleByHalf();
                            break;
                        case KeyEvent.VK_UP:
                            threeDimensionsBasicExample.scaleByDouble();
                            break;
                        case KeyEvent.VK_Q:
                            threeDimensionsBasicExample.rotateXY(rotation);
                            break;
                        case KeyEvent.VK_W:
                            threeDimensionsBasicExample.rotateXY(-rotation);
                            break;
                        case KeyEvent.VK_A:
                            threeDimensionsBasicExample.rotateXZ(rotation);
                            break;
                        case KeyEvent.VK_S:
                            threeDimensionsBasicExample.rotateXZ(-rotation);
                            break;
                        case KeyEvent.VK_Z:
                            threeDimensionsBasicExample.rotateYZ(rotation);
                            break;
                        case KeyEvent.VK_X:
                            threeDimensionsBasicExample.rotateYZ(-rotation);
                            break;
                        case KeyEvent.VK_R:
                            if (!robot.running()) {
                                Thread t = new Thread(() -> robot.start());
                                t.setName("Robot-Thread");
                                t.start();
                                screen.drawTitle("(Esc=Escape | R=Stop Robot | G=Grid )");
                            }
                            break;
                        case KeyEvent.VK_ESCAPE:
                            System.out.println("Shutting down...");
                            Screen.getScreen().stop();
                            System.exit(0);
                    }//end switch
                }
        }
        
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private static class Robot{

        private boolean isRunning;

        public void start(){

            if(isRunning)
                return;

            isRunning = true;

            double robotRotation = 1;
            long lastTime = System.nanoTime(), currentTime;
            int upScale = 0, downScale = 0;
            int randomOperation = 0;
            boolean justScaled = false;
            Random rng = new Random();

            screen.drawMessage("Starting Robot");
            try{Thread.sleep(1000);}catch(Exception ex){}

            while(isRunning) {

                randomOperation = rng.nextInt(8);

                currentTime = System.nanoTime();

                innerLoop:while(currentTime - lastTime < 2000000000) {
                    try{Thread.sleep(25);}catch(Exception ex){}
                    switch (randomOperation) {
                        case 0:
                            if(upScale < 1 && !justScaled){
                                for(int i = 0; i < 75; i++) {
                                    threeDimensionsBasicExample.scale(1.01);
                                    try{Thread.sleep(25);}catch(Exception ex){}
                                }
                                if(downScale > 0)
                                    downScale--;
                                else
                                    upScale++;

                                justScaled = true;
                            } else {
                                break innerLoop;
                            }
                            break;
                        case 1:
                            if(downScale < 1 && !justScaled){
                                for(int i = 0; i < 75; i++) {
                                    threeDimensionsBasicExample.scale(0.99);
                                    try{Thread.sleep(25);}catch(Exception ex){}
                                }
                                if(upScale > 0)
                                    upScale--;
                                else
                                    downScale++;

                                justScaled = true;
                            } else {
                                break innerLoop;
                            }
                            break;
                        case 2:
                            threeDimensionsBasicExample.rotateXY(robotRotation);
                            justScaled = false;
                            break;
                        case 3:
                            threeDimensionsBasicExample.rotateXY(-robotRotation);
                            justScaled = false;
                            break;
                        case 4:
                            threeDimensionsBasicExample.rotateXZ(robotRotation);
                            justScaled = false;
                            break;
                        case 5:
                            threeDimensionsBasicExample.rotateXZ(-robotRotation);
                            justScaled = false;
                            break;
                        case 6:
                            threeDimensionsBasicExample.rotateYZ(robotRotation);
                            justScaled = false;
                            break;
                        case 7:
                            threeDimensionsBasicExample.rotateYZ(-robotRotation);
                            justScaled = false;
                            break;
                    }//end switch

                    currentTime = System.nanoTime();

                }//end while not 2 seconds

                lastTime = System.nanoTime();

            }//end while isRunning

            screen.drawMessage("Robot Stopped");
        }

        public void stop(){
            isRunning = false;
        }

        public boolean running(){
            return isRunning;
        }
    }



}

