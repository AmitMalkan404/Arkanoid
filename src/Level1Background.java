import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the background of level 1.
 */
public class Level1Background implements Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d draw the sprite to the screen.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawCircle(400, 150, 100);
        d.drawCircle(400, 150, 75);
        d.drawCircle(400, 150, 50);
        d.drawLine(250, 150, 350, 150); // westline.
        d.drawLine(450, 150, 550, 150); // eastline.
        d.drawLine(400, 0, 400, 100); // northline.
        d.drawLine(400, 200, 400, 300); // southline.

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
