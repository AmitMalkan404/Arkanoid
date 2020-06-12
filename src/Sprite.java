import biuoop.DrawSurface;

/**
 * malkana1.
 * 313232084.
 * the interface of the sprite.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d draw the sprite to the screen.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * adding the sprite object to the game.
     * @param g the game.
     */
    void addToGame(GameLevel g);
}