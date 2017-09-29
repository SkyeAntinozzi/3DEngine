package lumine.test;


import lumine.engine.Camera;
import lumine.engine.Point;
import lumine.engine.Screen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraTest {

    @Test
    public void testCameraConstruction(){

        int xMax = Screen.WIDTH / 2,
                xMin = -xMax,
                yMax = Screen.HEIGHT / 2,
                yMin = -yMax,
                zMin = -100,
                zMax = 100,
                pointsInWorld;
        Point[] points;

        Camera camera = new Camera();
        String cameraProperties = camera.toString();
        pointsInWorld = 100;
        assertEquals(cameraProperties, "Camera : [xMin="+xMin+",xMax="+xMax+"] [yMin="+yMin+",yMax="+yMax+"] [zMin="+zMin+",zMax="+zMax+"] [points=null]");
        camera.setPointsInWorld(pointsInWorld);
        cameraProperties = camera.toString();
        assertEquals(cameraProperties, "Camera : [xMin="+xMin+",xMax="+xMax+"] [yMin="+yMin+",yMax="+yMax+"] [zMin="+zMin+",zMax="+zMax+"] [points="+pointsInWorld+"]");
        points = camera.getPoints();
        assertEquals(pointsInWorld, points.length);
        for(int i = 0; i < points.length; i++)
            assertEquals(points[i], null);

        camera = new Camera();
        cameraProperties = camera.toString();
        pointsInWorld = 0;
        assertEquals(cameraProperties, "Camera : [xMin="+xMin+",xMax="+xMax+"] [yMin="+yMin+",yMax="+yMax+"] [zMin="+zMin+",zMax="+zMax+"] [points=null]");
        camera.setPointsInWorld(pointsInWorld);
        cameraProperties = camera.toString();
        assertEquals(cameraProperties, "Camera : [xMin="+xMin+",xMax="+xMax+"] [yMin="+yMin+",yMax="+yMax+"] [zMin="+zMin+",zMax="+zMax+"] [points="+pointsInWorld+"]");
        points = camera.getPoints();
        assertEquals(pointsInWorld, points.length);

        camera = new Camera();
        cameraProperties = camera.toString();
        pointsInWorld = 1000;
        assertEquals(cameraProperties, "Camera : [xMin="+xMin+",xMax="+xMax+"] [yMin="+yMin+",yMax="+yMax+"] [zMin="+zMin+",zMax="+zMax+"] [points=null]");
        camera.setPointsInWorld(pointsInWorld);
        cameraProperties = camera.toString();
        assertEquals(cameraProperties, "Camera : [xMin="+xMin+",xMax="+xMax+"] [yMin="+yMin+",yMax="+yMax+"] [zMin="+zMin+",zMax="+zMax+"] [points="+pointsInWorld+"]");
        points = camera.getPoints();
        assertEquals(pointsInWorld, points.length);
        for(int x = 0, y = 2, z = 128; x < points.length; x++, y+=2, z+=128)
            points[x] = new Point(x,y,z);

        for(int x = 0, y = 2, z = 128; x < points.length; x++, y+=2, z+=128){
            assertEquals(points[x].getX(), x);
            assertEquals(points[x].getY(), y);
            assertEquals(points[x].getZ(), z);
        }
    }
}
