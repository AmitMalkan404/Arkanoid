import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the Animation of the highscores.
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable highScoresTable;

    /**
     * the Constructor.
     * @param hst the highscoretable.
     */
    public HighScoresAnimation(HighScoresTable hst) {
        this.highScoresTable = hst;
        this.stop = false;
    }

    /**
     * in charge of the logic.
     *
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(51, 50, "High Scores", 32);
        d.drawText(49, 50, "High Scores", 32);
        d.drawText(50, 51, "High Scores", 32);
        d.drawText(50, 49, "High Scores", 32);
        d.setColor(Color.YELLOW);
        d.drawText(50, 50, "High Scores", 32);
        d.setColor(Color.BLACK);
        d.drawText(101, 120, "Player Name", 24);
        d.drawText(99, 120, "Player Name", 24);
        d.drawText(100, 121, "Player Name", 24);
        d.drawText(100, 119, "Player Name", 24);
        d.setColor(Color.GREEN);
        d.drawText(100, 120, "Player Name", 24);
        d.setColor(Color.BLACK);
        d.drawText(451, 120, "Score", 24);
        d.drawText(449, 120, "Score", 24);
        d.drawText(450, 121, "Score", 24);
        d.drawText(450, 119, "Score", 24);
        d.setColor(Color.GREEN);
        d.drawText(450, 120, "Score", 24);
        d.setColor(Color.BLACK);
        d.drawLine(100, 130, 700, 130);
        d.setColor(Color.GREEN);
        d.drawLine(100, 131, 700, 131);
        d.setColor(Color.BLACK);
        d.drawLine(100, 132, 700, 132);
        d.setColor(Color.BLACK);

        for (int i = 0; i < 5; ++i) {
            ScoreInfo scoreInfo = this.highScoresTable.getHighScores().get(i);
            int nameX = 100;
            int scoreX = 450;
            int entryY = 170 + i * 40;
            d.setColor(Color.BLACK);
            d.drawText(nameX + 1, entryY, scoreInfo.getName(), 24);
            d.drawText(nameX - 1, entryY, scoreInfo.getName(), 24);
            d.drawText(nameX, entryY + 1, scoreInfo.getName(), 24);
            d.drawText(nameX, entryY - 1, scoreInfo.getName(), 24);
            d.setColor(Color.ORANGE);
            d.drawText(nameX, entryY, scoreInfo.getName(), 24);
            d.setColor(Color.BLACK);
            d.drawText(scoreX + 1, entryY, "" + scoreInfo.getScore(), 24);
            d.drawText(scoreX - 1, entryY, "" + scoreInfo.getScore(), 24);
            d.drawText(scoreX, entryY + 1, "" + scoreInfo.getScore(), 24);
            d.drawText(scoreX, entryY - 1, "" + scoreInfo.getScore(), 24);
            d.setColor(Color.ORANGE);
            d.drawText(scoreX, entryY, "" + scoreInfo.getScore(), 24);
        }

        String msg = "Press space to continue";
        d.setColor(Color.BLACK);
        d.drawText(199, 500, msg, 32);
        d.setColor(Color.decode("#1788d0"));
        d.drawText(200, 501, msg, 32);
        d.setColor(Color.BLACK);
        d.drawText(202, 502, msg, 32);
    }

    /**
     * in charge of stopping condition.
     *
     * @return bolean value if true or false.
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }
}
