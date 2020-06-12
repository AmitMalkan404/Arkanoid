import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the screen which shows the end of the game.
 */
public class EndScreen implements Animation {
    private Counter scoreCounter;
    private boolean winIndicator;

    /**
     * the constructor.
     * @param scoreCounter shows the score.
     * @param indicator telling if has won.
     */
    public EndScreen(Counter scoreCounter, boolean indicator) {
        this.winIndicator = indicator;
        this.scoreCounter = scoreCounter;
    }
    /**
     * in charge of the logic.
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        String result;
        if (!this.winIndicator) {
            result = "You Lost";
        } else {
            result = "YOU WON!";
        }

        if (this.winIndicator) {
            d.setColor(Color.BLUE);
        } else {
            d.setColor(Color.RED);
        }

        d.drawText(250, 151, result, 58);
        d.setColor(Color.BLACK);
        d.drawText(280, 350, "Final score:" + this.scoreCounter.getValue(), 40);
        String cont = "Press space to continue...";
        d.setColor(Color.BLACK);
        d.drawText(150, 500, cont, 50);
    }

    /**
     * in charge of stopping condition.
     *
     * @return bolean value if true or false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
