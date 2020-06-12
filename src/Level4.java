import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the level 4.
 */
public class Level4 implements LevelInformation {
    /**
     * the count of number of balls.
     *
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 3;
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
        List<Velocity> velocityList = new ArrayList<Velocity>();
        velocityList.add(Velocity.fromAngleAndSpeed(240, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(270, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(300, 5));
        return velocityList;
    }

    /**
     * the speed of the paddle in the level.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return 8;
    }

    /**
     * the paddle size.
     *
     * @return the paddle size.
     */
    @Override
    public int paddleWidth() {
        return 180;
    }

    /**
     * the level name that will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return new Level4Background();
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the blocks of the level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        int[] hitPoint = {2, 1, 1, 1, 1, 1, 1};
        int y = 150;
        for (int i = 0; i < 7; ++i) {
            Color color = new Color((int) (Math.random() * 0x1000000));
            for (int j = 0; j < 15; ++j) {
                Block block = new Block(j * 50 + 25, y, 50, 20, color, hitPoint[i]);
                blockList.add(block);
            }
            y = y + 20;
        }
        return blockList;
    }

    /**
     * Number of levels that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
