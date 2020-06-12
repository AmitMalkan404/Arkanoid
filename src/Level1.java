import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**
 * the level 1.
 */
public class Level1 implements LevelInformation {
    /**
     * the count of number of balls.
     *
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * the ball color.
     *
     * @return the ball color.
     */
    @Override
    public Color ballColor() {
        return Color.WHITE;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return tghe list of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(90, 5);
        velocityList.add(v1);
        return velocityList;
    }
    /**
     * the speed of the paddle in the level.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return 4;
    }

    /**
     * the paddle size.
     *
     * @return the paddle size.
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * the level name that will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return new Level1Background();
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the blocks of the level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Rectangle rectangle = new Rectangle(375, 125, 50, 50);
        Block b1 = new Block(rectangle, Color.RED, 1);
        blockList.add(b1);
        return blockList;
    }

    /**
     * Number of levels that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
