import biuoop.DrawSurface;

import java.awt.Color;

/**
 * add color background.
 */
public class ColorBackground implements Sprite {
    private Color color;

    /**
     * the constructor.
     * @param color the color.
     */
    public ColorBackground(Color color) {
        this.color = color;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d draw the sprite to the screen.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * adding the sprite object to the game.
     *
     * @param g the game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
