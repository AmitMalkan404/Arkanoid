/**
 * malkana1.
 * 313232084.
 * the class of the paddle.
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * paddle class implement sprite and collidable.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    private int speed;

    /**
     * constructors.
     *
     * @param keyboard  keyboard sensor of the game.
     * @param rectangle the rectangle of the paddle.
     * @param color     the color of the paddle.
     * @param speed     the speed of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle, java.awt.Color color, int speed) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
        this.speed = speed;
    }

    /**
     * return the color.
     *
     * @return the color of the paddle.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * moving the paddle to left by the keyboard sensor method.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() > 25) {
            this.rectangle = new Rectangle(this.rectangle.getUpperLeft().getX() - speed,
                    this.rectangle.getUpperLeft().getY(), this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * moving the paddle to right by the keyboard sensor method.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() < 775 - this.rectangle.getWidth()) {
            this.rectangle = new Rectangle(this.rectangle.getUpperLeft().getX() + speed,
                    this.rectangle.getUpperLeft().getY(), this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * time passed is sprite method.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draws the paddle on the surface.
     *
     * @param d the parameter of the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        int recX = (int) this.rectangle.getUpperLeft().getX();
        int recY = (int) this.rectangle.getUpperLeft().getY();
        d.fillRectangle(recX, recY, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(recX, recY, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * gets the collision rectangle.
     *
     * @return collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * apply the hit method to the paddle.
     *
     * @param collisionPoint  the collision point inside collidable.
     * @param hitter  the ball which hits the paddle.
     * @param currentVelocity the velocity of the ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        double speed1;
        double coliX = collisionPoint.getX();
        double curX = this.rectangle.getUpperLeft().getX();
        double width = this.rectangle.getWidth();
        if (this.rectangle.getUpLine().onLine(collisionPoint) || this.rectangle.getDownLine().onLine(collisionPoint)) {
            if (coliX >= curX && coliX < curX + (width / 5)) {
                speed1 = newVelocity.getSpeed(300 - 90);
                newVelocity = currentVelocity.fromAngleAndSpeed(300 - 90, 6);
            } else if (coliX >= curX + (width / 5) && coliX < curX + ((width * 2) / 5)) {
                speed1 = newVelocity.getSpeed(330 - 90);
                newVelocity = currentVelocity.fromAngleAndSpeed(330 - 90, 6);
            } else if (coliX >= curX + ((width * 2) / 5) && coliX < curX + ((width * 3) / 5)) {
                newVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            } else if (coliX >= curX + ((width * 3) / 5) && coliX < curX + ((width * 4) / 5)) {
                speed1 = newVelocity.getSpeed(30 - 90);
                newVelocity = currentVelocity.fromAngleAndSpeed(30 - 90, 6);
            } else if (coliX >= curX + ((width * 4) / 5) && coliX <= curX + width) {
                speed1 = newVelocity.getSpeed(60 - 90);
                newVelocity = currentVelocity.fromAngleAndSpeed(60 - 90, 6);
            }
        }
        if (this.rectangle.getRightLine().onLine(collisionPoint)
                || this.rectangle.getLeftLine().onLine(collisionPoint)) {
            newVelocity = new Velocity(-1 * newVelocity.getDx(), newVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);

    }


}