/**
 * Author: Skye Antinozzi
 * Represents a point while encapsulating operations on points and vectors.
 */
package lumine.engine;

public class Point {

    private double x, y, z;

    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //region Operations
    public Point addVectorToPoint(Vector v){
        return new Point(x + v.getX(), y + v.getY(), z + v.getZ());
    }

    public Point subtractVectorFromPoint(Vector v){
        return new Point(x - v.getX(), y - v.getY(), z - v.getZ());
    }

    public Vector subtractPointFromPoint(Point p){
        return new Vector(x - p.getX(), y - p.getY(), z - p.getZ());
    }

    public void setPointToPoint(Point setToThisPoint){
        x = setToThisPoint.getX();
        y = setToThisPoint.getY();
        z = setToThisPoint.getZ();
    }
    //endregion

    //region Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    //endregion

    public void drawPoint(){
        Screen.getScreen().drawPixel((int) Math.round(x), (int) Math.round(y));
    }

    public void drawPoint(int color){
        Screen.getScreen().drawPixel((int) Math.round(x), (int) Math.round(y), color);
    }

    @Override
    public String toString(){
        return "Point: (" + x + ", " + y + ", " + z + ")";
    }

}
