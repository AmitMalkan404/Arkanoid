import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private long startTime;
    private SpriteCollection gameScreen;
    private boolean stop;
    private boolean first;
    private int width;
    private int height;

    /**
     * constructor.
     * @param numOfSeconds the num of second.
     * @param countFrom where its count from.
     * @param gameScreen the sprites.
     * @param width the width.
     * @param height the height.
     */
    public CountdownAnimation(SpriteCollection gameScreen, int countFrom, double numOfSeconds, int width, int height) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.width = width / 2;
        this.height = height / 2;
        this.stop = false;
        this.first = true;
    }
    /**
     * in charge of the logic.
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        long curTime = System.currentTimeMillis();
        if (this.first) {
            this.startTime = System.currentTimeMillis();
            this.first = false;
        }
        this.gameScreen.drawAllOn(d);
        double singleCountLength = this.numOfSeconds / (double) this.countFrom;
        int countDown = (int) ((double) (1 + this.countFrom) - (double) (curTime - this.startTime)
                / (singleCountLength * 1000));
        if (countDown > 0) {
            d.setColor(Color.RED);
            d.drawText(this.width, this.height, "" + countDown, 72);
        }
        if (countDown == 0) {
            this.stop = true;
        }
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
