import biuoop.DrawSurface;

import java.awt.*;

/**
 * the class of image in the background.
 */
public class ImageBackground implements Sprite {
    private Image image;

    /**
     * the constructor.
     * @param image the image.
     */
    public ImageBackground (Image image) {
        this.image = image;
    }
    /**
     * draw the sprite to the screen.
     * @param d draw the sprite to the screen.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
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
