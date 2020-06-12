import java.awt.Color;
import java.util.List;
/**
 * the interface which gives the information of the level.
 */
public interface LevelInformation {
    /**
     * the count of number of balls.
     * @return the number of balls.
     */
    int numberOfBalls();
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * the ball color.
     * @return the ball color.
     */
    Color ballColor();
    /**
     * The initial velocity of each ball.
     * @return tghe list of the balls.
     */
    List<Velocity> initialBallVelocities();
    /**
     * the speed of the paddle in the level.
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * the paddle size.
     * @return the paddle size.
     */
    int paddleWidth();

    /**
     * the level name that will be displayed at the top of the screen.
     * @return the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return the blocks of the level.
     */
    List<Block> blocks();

    /**
     *
     * Number of levels that should be removed before the level is considered to be "cleared".
     * @return the number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
