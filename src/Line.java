/**
 * malkana1.
 * 313232084.
 * Line is the class where the line consists of 2 points; Start and End.
 * In addition, functions are defined that test points of intersection or equality between lines
 */
public class Line {
    /**
     * creating the start and end point.
     */
    private Point start;
    private Point end;
    // constructors

    /**
     * define start and end points.
     *
     * @param start define start point.
     * @param end   define end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @param x1 first x value.
     * @param y1 first y value.
     * @param x2 second x value.
     * @param y2 second y value.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line

    /**
     * @return length of the line
     */
    public double length() {
        double len = this.end.distance(this.start);
        return len;
    }

    // Returns the middle point of the line

    /**
     * @return the middle point.
     */
    public Point middle() {
        double x0 = (start.getX() + end.getX()) / 2;
        double y0 = (start.getY() + end.getY()) / 2;
        Point mid = new Point(x0, y0);
        return mid;
    }

    // Returns the start point of the line.

    /**
     * @return the end point.
     */
    public Point getStart() {
        return this.start;
    }

    // Returns the end point of the line.

    /**
     * @return the end point.
     */
    public Point getEnd() {
        return this.end;
    }

    // Returns true if the lines intersect, false otherwise.

    /**
     * @param other the other line.
     * @return boolean value for intersection.
     */
    public boolean isIntersecting(Line other) {
        Point interS = this.intersectionWith(other);
        double minThisX, maxThisX, minThisY, maxThisY, minOtherX, maxOtherX, minOtherY, maxOtherY;
        double minLimitX, minLimitY, maxLimitX, maxLimitY;
        minThisX = Math.min(this.start.getX(), this.end.getX());
        maxThisX = Math.max(this.start.getX(), this.end.getX());
        minThisY = Math.min(this.start.getY(), this.end.getY());
        maxThisY = Math.max(this.start.getY(), this.end.getY());
        minOtherX = Math.min(other.start.getX(), other.end.getX());
        maxOtherX = Math.max(other.start.getX(), other.end.getX());
        minOtherY = Math.min(other.start.getY(), other.end.getY());
        maxOtherY = Math.max(other.start.getY(), other.end.getY());
        minLimitX = Math.max(minThisX, minOtherX);
        minLimitY = Math.max(minThisY, minOtherY);
        maxLimitX = Math.min(maxThisX, maxOtherX);
        maxLimitY = Math.min(maxThisY, maxOtherY);
        if (interS == null) {
            return false;
        }
        if ((interS.getX() >= minLimitX - 0.1) && (interS.getX() <= maxLimitX + 0.1)) {
            if ((interS.getY() >= minLimitY - 0.1) && (interS.getY() <= maxLimitY + 0.1)) {
                return true;
            }
        }
        return false;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.

    /**
     * @param other the other line.
     * @return Point of intersection.
     */
    public Point intersectionWith(Line other) {
        //what if two lines are compared.
        double cutX, cutY;
        Point intersectionPoint;
        double inclineY1 = (this.start.getY() - this.end.getY());
        double inclineX1 = (this.start.getX() - this.end.getX());
        double inclineY2 = (other.start.getY() - other.end.getY());
        double inclineX2 = (other.start.getX() - other.end.getX());
        // case if the both x's in both point aren't equal.
        if (!(inclineX1 >= -0.1 && inclineX1 <= 0.1) && !(inclineX2 >= -0.1 && inclineX2 <= 0.1)) {
            double incline1 = inclineY1 / inclineX1;
            double permanent1 = ((-incline1) * this.start.getX()) + this.start.getY();
            double incline2 = inclineY2 / inclineX2;
            double permanent2 = ((-incline2) * other.start.getX()) + other.start.getY();
            double incline3 = incline1 - incline2;
            double permanent3 = permanent2 - permanent1;
            cutX = permanent3 / incline3;
            cutY = incline1 * (cutX - this.start.getX()) + this.start.getY();
            intersectionPoint = new Point(cutX, cutY);
            if ((this.onLine(intersectionPoint)) && (other.onLine(intersectionPoint))) {
                return intersectionPoint;
            }
        }
        // cases if the one of the x's in point are equal.
        // case if the both x's in both point equal
        if ((inclineX1 >= -0.1 && inclineX1 <= 0.1) && inclineX2 != 0) {
            cutX = this.start.getX();
            double incline2 = inclineY2 / inclineX2;
            double permanent2 = ((-incline2) * other.start.getX()) + other.start.getY();
            cutY = incline2 * cutX + permanent2;
            intersectionPoint = new Point(cutX, cutY);
            if ((this.onLine(intersectionPoint)) && (other.onLine(intersectionPoint))) {
                return intersectionPoint;
            }
        }
        if ((inclineX2 >= -0.1 && inclineX2 <= 0.1) && inclineX1 != 0) {
            cutX = other.start.getX();
            double incline1 = inclineY1 / inclineX1;
            double permanent1 = ((-incline1) * this.start.getX()) + this.start.getY();
            cutY = incline1 * cutX + permanent1;
            intersectionPoint = new Point(cutX, cutY);
            if ((this.onLine(intersectionPoint)) && (other.onLine(intersectionPoint))) {
                return intersectionPoint;
            }
        } else {
            if (this.getStart().getX() != other.getStart().getX()) {
                return null;
            }
        }
        return null;
    }

    // equals -- return true is the lines are equal, false otherwise

    /**
     * @param other the other line
     * @return boolean value true if the two lines are equal
     */
    public boolean equals(Line other) {
        double inclineY1 = this.start.getY() - this.end.getY();
        double inclineX1 = this.start.getY() - this.end.getY();
        double incline1 = inclineY1 / inclineX1;
        double permanent1 = incline1 * (-(this.start.getX())) + this.start.getY();
        double inclineY2 = other.start.getY() - other.end.getY();
        double inclineX2 = other.start.getY() - other.end.getY();
        double incline2 = inclineY2 / inclineX2;
        double permanent2 = incline2 * (-(other.start.getX())) + other.start.getY();
        if ((incline1 == incline2) && (permanent1 == permanent2)) {
            return true;
        }
        return false;
    }

    /**
     * checks if the point that recieved is on the line.
     * @param point the point that needs to be checked.
     * @return the boolean value of true or false if the given point is on this line.
     */
    public boolean onLine(Point point) {
        double inclineY = this.start.getY() - this.end.getY();
        double inclineX = this.start.getX() - this.end.getX();
        double incline = inclineY / inclineX;
        double permanent = incline * (-(this.start.getX())) + this.start.getY();
        if (inclineY == 0) {
            permanent = this.start.getY();
        }
        double testX = point.getX();
        double testY = (testX * incline) + permanent;


        if ((point.getY() <= this.getStart().getY() + 0.1 && point.getY() >= this.getEnd().getY() - 0.1)
                || (point.getY() <= this.getEnd().getY() + 0.1 && point.getY() >= this.getStart().getY() - 0.1)) {
            if ((point.getX() <= this.getStart().getX() + 0.1 && point.getX() >= this.getEnd().getX() - 0.1)
                    || (point.getX() <= this.getEnd().getX() + 0.1 && point.getX() >= this.getStart().getX() - 0.1)) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * gets the closest intersection to start of the line.
     * @param rect the inserted rectangle.
     * @return the point in the rectangle which is the closest to the start of line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double rectX = rect.getUpperLeft().getX();
        double rectY = rect.getUpperLeft().getY();
        Point intersect1 = null, intersect2 = null;
        if (this.isIntersecting(rect.getUpLine())) {
            intersect1 = this.intersectionWith(rect.getUpLine());
        }
        if (this.isIntersecting(rect.getDownLine())) {
            if (intersect1 == null) {
                intersect1 = this.intersectionWith(rect.getDownLine());
            } else {
                intersect2 = this.intersectionWith(rect.getDownLine());
            }
        }
        if (this.isIntersecting(rect.getLeftLine())) {
            if (intersect1 == null) {
                intersect1 = this.intersectionWith(rect.getLeftLine());
            } else {
                intersect2 = this.intersectionWith(rect.getLeftLine());
            }
        }
        if (this.isIntersecting(rect.getRightLine())) {
            if (intersect1 == null) {
                intersect1 = this.intersectionWith(rect.getRightLine());
            } else {
                intersect2 = this.intersectionWith(rect.getRightLine());
            }
        }
        if (intersect1 == null || intersect2 == null) {
            if (intersect1 == null) {
                return intersect2;
            } else {
                return intersect1;
            }
        } else if (this.getStart().distance(intersect1) < this.getStart().distance(intersect2)) {
            return intersect1;
        } else {
            return intersect2;
        }
    }
}
