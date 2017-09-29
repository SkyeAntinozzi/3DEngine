/**
 * Author: Skye Antinozzi
 * Represents the camera that will view the 3D scene.
 */
package lumine.engine;

public class Camera {

    private Point[] points;         // Change to generic Objects of some sort later on

    private int xMax = Screen.WIDTH >> 1,
                xMin = -xMax,
                yMax = Screen.HEIGHT >> 1,
                yMin = -yMax,
                zMin = -100,
                zMax = 100;

    public void setPointsInWorld(int numberOfPoints){
        points = new Point[numberOfPoints];
    }

    public Point[] getPoints(){
        return points;
    }

    public void drawScene(){
        double x, y, z;
        for(int i = 0; i < points.length; i++){
            x = points[i].getX();
            y = points[i].getY();
            z = points[i].getZ();
            if( (x >= xMin && x <= xMax) && (y >= yMin && y <= yMax) && (z >= zMin && z <= zMax))
                points[i].drawPoint(0x4286F4);
        }

    }

    @Override
    public String toString(){
        String xBounds = " [xMin=" + xMin + ",xMax=" + xMax + "]";
        String yBounds = " [yMin=" + yMin + ",yMax=" + yMax + "]";
        String zBounds = " [zMin=" + zMin + ",zMax=" + zMax + "]";
        String objects = " [points=" + (points == null ? null : points.length) + "]";
        return "Camera :" + xBounds + yBounds + zBounds + objects;
    }
}
