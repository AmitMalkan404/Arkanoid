import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * this class responsible for the game flowing.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private int gameWidth;
    private int gameHeight;
    private Counter score;
    private Counter lives;
    private GUI gui;
    private HighScoresTable highScoresTable;
    private DialogManager dialogManager;

    /**
     * the game flow class.
     * @param ar animation runner of the game.
     * @param gui the gui.
     * @param hst the high score table.
     */
    public GameFlow(AnimationRunner ar, GUI gui, HighScoresTable hst) {
        this.ar = ar;
        this.ks = gui.getKeyboardSensor();
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.gui = gui;
        this.highScoresTable = hst;
        this.dialogManager = gui.getDialogManager();
    }

    /**
     * running the given levels.
     * @param levels the list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.ar, this.ks, this.lives, this.score, this.gui);

            level.initialize();

            while (level.getLeftBlocks().getValue() > 0 && this.lives.getValue() > 0) {
                level.playOneTurn();
            }

            if (this.lives.getValue() == 0) {
                break;
            }
        }
        if (this.highScoresTable.isOnTop5(this.score.getValue())) {
            String name = this.dialogManager.showQuestionDialog("Enter Name", "What is your name?", "Anonymous");
            ScoreInfo user = new ScoreInfo(name, this.score.getValue());
            this.highScoresTable.add(user);

            try {
                this.highScoresTable.save(new File("highscores.txt"));
            } catch (IOException e) {
                System.err.println("failed to show the highscores table");
                e.printStackTrace(System.err);
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(new EndScreen(this.score, this.lives.getValue() > 0),
                this.gui.getKeyboardSensor(), "space"));
        this.ar.run(new KeyPressStoppableAnimation(new HighScoresAnimation(this.highScoresTable),
                this.gui.getKeyboardSensor(), "space"));
        this.score.decrease(this.score.getValue());
        this.lives.increase(7);
    }
}
