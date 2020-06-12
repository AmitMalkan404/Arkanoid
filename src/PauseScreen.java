import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * will display a screen with the message paused.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * @param k keyboard.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * the empty constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * in charge of the logic.
     *
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY.brighter());
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.GRAY);
        d.fillCircle(400, 310, 160);
        d.drawCircle(400, 310, 160);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(325, d.getHeight() / 4 + 50, 50, d.getHeight() * 1 / 2 - 80);
        d.fillRectangle(425, d.getHeight() / 4 + 50, 50, d.getHeight() * 1 / 2 - 80);
        d.drawRectangle(325, d.getHeight() / 4 + 50, 50, d.getHeight() * 1 / 2 - 80);
        d.drawRectangle(425, d.getHeight() / 4 + 50, 50, d.getHeight() * 1 / 2 - 80);
        d.setColor(Color.BLACK);
        d.drawText(325, d.getHeight() / 4 - 20, "paused", 50);
        d.drawText(175, d.getHeight() * 3 / 4 + 65, "press space to continue", 50);
    }

    /**
     * in charge of stopping condition.
     *
     * @return bolean value if true or false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

