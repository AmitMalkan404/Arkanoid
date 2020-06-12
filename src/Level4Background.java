import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the background of level 4.
 */
public class Level4Background implements Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d draw the sprite to the screen.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0x4394E6));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(150 + 10 * i, 300, 110 + 10 * i, 600);
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(600 + 10 * i, 450, 560 + 10 * i, 600);
        }
        d.setColor(Color.DARK_GRAY.brighter());

        d.fillCircle(175, 330, 30);
        d.fillCircle(160, 300, 25);

        d.fillCircle(625, 480, 30);
        d.fillCircle(610, 450, 25);
        d.setColor(Color.lightGray.darker());
        d.fillCircle(200, 295, 25);

        d.fillCircle(650, 445, 25);
        d.setColor(Color.GRAY.brighter());
        d.fillCircle(230, 300, 30);
        d.fillCircle(215, 325, 20);

        d.fillCircle(680, 450, 30);
        d.fillCircle(665, 475, 20);

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
