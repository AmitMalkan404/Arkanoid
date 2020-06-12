
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * this is the indicator of the score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle screen;

    /**
     * the constructor.
     * @param score the score of the player.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.screen = new Rectangle(400, 0, 200, 20);
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
        String string = "Score: " + Integer.toString(this.score.getValue());
        d.drawText((int) (recX + (width / 3)), (int) (5 + (height / 2)), string, 15);
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
