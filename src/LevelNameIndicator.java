import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this implement the screen of the level name.
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;
    private Rectangle screen;

    /**
     * the constructor.
     * @param levelName the level name.
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
        this.screen = new Rectangle(600, 0, 200, 20);
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d draw the sprite to the screen.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GRAY);
        int recX = (int) this.screen.getUpperLeft().getX();
        int recY = (int) this.screen.getUpperLeft().getY();
        int width = (int) this.screen.getWidth();
        int height = (int) this.screen.getHeight();
        d.fillRectangle(recX, recY, width, height);
        d.setColor(Color.BLACK);
        d.drawText((recX + 5), (int) (5 + (height / 2)), "Level Name: " + this.levelName, 15);
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
