import java.util.ArrayList;
/**
 * malkana1.
 * 313232084.
 * the class of the rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper left point of rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @param x the x value of upper left.
     * @param y the y value of upper left.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * create generic arraylist of point of intersection.
     * @param line the line in which added to the list.
     * @return the array list.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> pointList = new ArrayList<>();
        double rectX = this.upperLeft.getX();
        double rectY = this.upperLeft.getY();
        Point intersect1 = null, intersect2 = null;
        if (line.isIntersecting(this.getUpLine())) {
            if (this.getUpLine().onLine(line.intersectionWith(this.getUpLine()))) {
                pointList.add(line.intersectionWith(this.getUpLine()));
            }
        }
        if (line.isIntersecting(this.getDownLine())) {
            if (this.getDownLine().onLine(line.intersectionWith(this.getDownLine()))) {
                pointList.add(line.intersectionWith(this.getDownLine()));
            }
        }
        if (line.isIntersecting(this.getLeftLine())) {
            if (this.getLeftLine().onLine(line.intersectionWith(this.getLeftLine()))) {
                pointList.add(line.intersectionWith(this.getLeftLine()));
            }
        }
        if (line.isIntersecting(this.getRightLine())) {
            if (this.getRightLine().onLine(line.intersectionWith(this.getRightLine()))) {
                pointList.add(line.intersectionWith(this.getRightLine()));
            }
        }
        return pointList;
    }

    /**
     * Return the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Return the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * return the left line of the rectangle.
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        Line l1 = new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
        return l1;
    }

    /**
     * return the right line of the rectangle.
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        Line l1 = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return l1;
    }

    /**
     * return the upper line of the rectangle.
     * @return the upper line of the rectangle.
     */
    public Line getUpLine() {
        Line l1 = new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX() + this.width,
                this.upperLeft.getY());
        return l1;
    }

    /**
     * return the downer line of the rectangle.
     * @return the downer line of the rectangle.
     */
    public Line getDownLine() {
        Line l1 = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return l1;
    }
}