import biuoop.DrawSurface;

/**
 * malkana1.
 * 313232084.
 * this is the interface of the collidable classes.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint the collision point inside collidable.
     * @param currentVelocity the velocity of the ball.
     * @param hitter the ball which hit the collidable.
     * @return the new velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * the surface of the collidable.
     * @param surface of the collidable.
     */
    void drawOn(DrawSurface surface);

}