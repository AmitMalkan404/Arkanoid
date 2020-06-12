
import biuoop.DrawSurface;

import java.awt.*;

/**
 * malkana1.
 * 313232084.
 * Ball is the class that defines the ball which consists of radius, midpoint, color and velocity.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity veloc;
    private GameEnvironment gameEn;
    // constructor.

    /**
     * returns the constructor.
     * @param center center point of ball.
     * @param r radius of the ball.
     * @param color color of the ball.
     * @param environment enviorment of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEn = environment;
        this.veloc = new Velocity(3, 3);
    }

    /**
     * returns the constructor.
     * @param x x point of ball.
     * @param y y point of ball.
     * @param r radius of the ball.
     * @param color color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.veloc = new Velocity(3, 3);
    }
    /**
     * this method check if the ball is inside the rectangle and let it out.
     * @param rec is the rectangle variable.
     * @param centre the point of the centre of the ball.
     * @return the false or true value.
     */
    private boolean insideRec(Rectangle rec, Point centre) {
        double x = centre.getX();
        double y = centre.getY();
        double minY = rec.getUpperLeft().getY();
        double maxY = rec.getUpperLeft().getY() + rec.getHeight();
        double minX = rec.getUpperLeft().getX();
        double maxX = rec.getUpperLeft().getX() + rec.getWidth();
        if ((x > minX) && (x < maxX)) {
            if ((y > minY) && (y < maxY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * calculate the trajectory line of the ball.
     * @return  the trajectory line of the ball.
     */
    public Line getTrajectory() {
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.veloc.getDx() * 999, this.center.getY() + this.veloc.getDy() * 999);
        return trajectory;
    }

    /**
     * returning the gameenviorment of the ball.
     * @return the gameenviorment of the ball.
     */
    public GameEnvironment getGameEn() {
        return this.gameEn;
    }
    /**
     * defines the ball with radius point (x,y), radius = radius, and color.
     * @param x the x value of the point.
     * @param y the y value of the point.
     * @param radius the radius of the ball.
     * @param color the color of the ball.
     * @param environment is the game enviroment of the ball.
     */
    public Ball(int x, int y, int radius, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.gameEn = environment;
    }

    // accessors

    /**
     * setting the game environment.
     * @param environment the game environment.
     */
    public void setGameEn(GameEnvironment environment) {
        this.gameEn = gameEn;
    }

    /**
     *
     * @return the x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     *
     * @return radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     *
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface with color.
     * @param surface the surface of the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * set the velocity with velocity type.
     * @param v the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.veloc = v;
        this.veloc.applyToPoint(this.center);
    }

    /**
     * set the velocity with velocity dx dy.
     * @param dx the delta of x.
     * @param dy the delta of y.
     */
    public void setVelocity(double dx, double dy) {
        this.veloc = new Velocity(dx, dy);
        this.veloc.applyToPoint(this.center);
    }

    /**
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.veloc;
    }

    /**
     *
     * @param g the game which the ball is inside of.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * moving the ball and check if it get close enough to object and change velocity in demand.
     */
    public void moveOneStep() {
        Point intersectionP;
        Line shortLine = new Line(this.center.getX(), this.center.getY(), this.center.getX() + this.veloc.getDx(),
                this.center.getY() + this.veloc.getDy());
        Rectangle rectangle;
        Velocity newVelocity;
        for (Collidable c : this.gameEn.getCollidables()) {
            rectangle = c.getCollisionRectangle();
            Line traj = this.getTrajectory();
            if (traj.closestIntersectionToStartOfLine(rectangle) != null) {
                intersectionP = traj.closestIntersectionToStartOfLine(rectangle);
                if (shortLine.onLine(intersectionP)) {
                    if (!(this.insideRec(rectangle, this.center))) {
                        java.awt.Color color1 = new java.awt.Color((int) (Math.random() * 0x1000000));
                        newVelocity = c.hit(this, intersectionP, this.getVelocity());
                        this.setVelocity(newVelocity);
                        return;
                    }
                }
            }
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * making the time passing by.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * removing the ball from the game.
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}