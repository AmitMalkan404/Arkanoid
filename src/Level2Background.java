import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the background of level 2.
 */
public class Level2Background implements Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d draw the sprite to the screen.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.ORANGE.brighter());
        for (int i = 0; i < 80; i++) {
            d.drawLine(120, 150, i * 10, 250);
        }
        d.setColor(Color.ORANGE);
        d.fillCircle(120, 150, 50);
        d.setColor(Color.YELLOW.darker());
        d.fillCircle(120, 150, 40);
        d.setColor(Color.YELLOW);
        d.fillCircle(120, 150, 30);
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
