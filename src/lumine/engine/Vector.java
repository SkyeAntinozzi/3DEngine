/**
 * Author: Skye Antinozzi
 * Represents a vector while encapsulating operations on points and vectors.
 */
package lumine.engine;

import static java.lang.Math.toRadians;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.round;

public class Vector {

    private double x, y, z;

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //region Operations
    public Vector addVectorToVector(Vector v){
        return new Vector(x + v.getX(),y + v.getY(),z + v.getZ());
    }

    public Vector subtractVectorFromVector(Vector v){
        return new Vector(x - v.getX(),y - v.getY(),z - v.getZ());
    }

    public Vector rotateXY(double degrees){
        double transformX,
                transformY,
                radians = toRadians(degrees),
                sinValue = sin(radians),
                cosValue = cos(radians);

        transformX = x * cosValue - y * sinValue;
        transformY = x * sinValue + y * cosValue;

        return new Vector(transformX, transformY, z);
    }

    public Vector rotateXZ(double degrees){
        double transformX,
                transformZ,
                radians = toRadians(degrees),
                sinValue = sin(radians),
                cosValue = cos(radians);

        transformX = x * cosValue + z * sinValue;
        transformZ = z * cosValue - x * sinValue;

        return new Vector(transformX, y, transformZ);
    }

    public Vector rotateYZ(double degrees){
        double transformY,
                transformZ,
                radians = toRadians(degrees),
                sinValue = sin(radians),
                cosValue = cos(radians);

        transformY = y * cosValue - z * sinValue;
        transformZ = y * sinValue + z * cosValue;

        return new Vector(x, transformY, transformZ);
    }

    public Vector scale(double scaleX, double scaleY, double scaleZ){
        return new Vector( x * scaleX, y * scaleY, z * scaleZ);
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

    @Override
    public String toString(){
        return "Vector: [" + x + ", " + y + ", " + z + "]";
    }
}
