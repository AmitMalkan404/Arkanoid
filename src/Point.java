/**
 * malkana1.
 * 313232084.
 * Point is the class where the point that will be used is defined below.
 */
public class Point {
    private double x;
    private double y;

    /**
     *
     * @param x is the x value.
     * @param y is the y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     * @param other the other point.
     * @return the distance between the point.
     */
    public double distance(Point other) {
        double distanceX = ((other.x - this.x) * (other.x - this.x));
        double distanceY = ((other.y - this.y) * (other.y - this.y));
        double distance = Math.sqrt(distanceX + distanceY);
        return distance;
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     * @param other if 2 points are equal.
     * @return the boolean value if true or not.
     */
    public boolean equals(Point other) {
        if (this.x == other.x) {
            if (this.y == other.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the x and y values of this point.
     * @return x value of point.
     */
    public double getX() {
        return this.x;
    }

    /**
     *
     * @return y value of point.
     */
    public double getY() {
        return this.y;
    }
}