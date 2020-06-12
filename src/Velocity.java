/**
 * malkana1.
 * 313232084.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    // constructor
    private double dx;
    private double dy;

    /**
     *
     * @param angle gets angle.
     * @param speed gets speed.
     * @return return velocity out of it.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.cos(angle) * (speed);
        double dy = Math.sin(angle) * (speed);
        return new Velocity(dx, dy);
    }

    /**
     * the dx and dy are the movement of the ball.
     * @param dx the x movement
     * @param dy the y movement
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     *
     * @param p the point
     * @return Take a point with position (x,y) and return a new pointwith position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        Point newP = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newP;
    }

    /**
     *
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     *
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * making velocity and angle to speed.
     * @param angle the angle.
     * @return speed.
     */
    public double getSpeed(double angle) {
        angle = Math.toRadians(angle);
        double speed = getDx() / Math.cos(angle);
        return speed;
    }
}