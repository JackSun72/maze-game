package geometry;

/**
 * Represents a point
 * @author Ethan.
 * @author Daniel
 */
public class Point {
    public double x,y;

    /**
     * Create a point
     * @param x the x-coordinate of point
     * @param y the y-coordinate of point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Translate the point horizontally/vertically
     * @param p the point being added
     */
    public void add(Point p){
        x += p.x;
        y += p.y;
    }

    /**
     * Stretch/Compress the point
     * @param scalar the factor of stretch/compression
     */
    public void multiply(double scalar){
        x *= scalar;
        y *= scalar;
    }

    /**
     * Get the square of magnitude
     * @return the square of magnitude
     */
    public double lengthSquared() {
        return x * x + y * y;
    }

    /**
     * Get the magnitude
     * @return the magnitude.
     */
    public double length(){
        return (double) Math.sqrt(lengthSquared());
    }

    /**
     * Get the difference vector.
     * @param p the other point
     * @return their difference vector
     */
    public Point distanceVector(Point p) {
        return new Point(x - p.x, y - p.y);
    }

    /**
     * Get the normalized point
     * @return the normalized point
     */
    public Point normalized(){
        double length = length();
        if(length == 0){
            throw new IllegalArgumentException("Cannot normalize the 0 vector");
        }
        return new Point(x/length, y /length);
    }

    /**
     * Determine whether the point is origin.
     * @return whether the point is origin.
     */
    public boolean isZero(){
        return x ==0 && y ==0;
    }

}