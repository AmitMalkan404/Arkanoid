import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**
 * the level 2.
 */
public class Level2 implements LevelInformation {
    /**
     * the count of number of balls.
     *
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * the ball color.
     *
     * @return the ball color.
     */
    @Override
    public Color ballColor() {
        return Color.BLACK;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return tghe list of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int j = 320; j >= 220; j -= 10) {
            if (j == 270) {
                continue;
            }
            velocityList.add(Velocity.fromAngleAndSpeed(j, 5));
        }
        return velocityList;
    }
    /**
     * the speed of the paddle in the level.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * the paddle size.
     *
     * @return the paddle size.
     */
    @Override
    public int paddleWidth() {
        return 600;
    }

    /**
     * the level name that will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return new Level2Background();
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the blocks of the level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Point p = new Point(25, 350);
        blockList.add(new Block(25, 250, 50, 20, Color.RED, 1));
        blockList.add(new Block(75, 250, 50, 20, Color.RED, 1));
        blockList.add(new Block(125, 250, 50, 20, Color.ORANGE, 1));
        blockList.add(new Block(175, 250, 50, 20, Color.ORANGE, 1));
        blockList.add(new Block(225, 250, 50, 20, Color.YELLOW, 1));
        blockList.add(new Block(275, 250, 50, 20, Color.YELLOW, 1));
        blockList.add(new Block(325, 250, 50, 20, Color.GREEN, 1));
        blockList.add(new Block(375, 250, 50, 20, Color.GREEN, 1));
        blockList.add(new Block(425, 250, 50, 20, Color.GREEN, 1));
        blockList.add(new Block(475, 250, 50, 20, Color.BLUE, 1));
        blockList.add(new Block(525, 250, 50, 20, Color.BLUE, 1));
        blockList.add(new Block(575, 250, 50, 20, Color.PINK, 1));
        blockList.add(new Block(625, 250, 50, 20, Color.PINK, 1));
        blockList.add(new Block(675, 250, 50, 20, Color.CYAN, 1));
        blockList.add(new Block(725, 250, 50, 20, Color.CYAN, 1));
        return blockList;
    }

    /**
     * Number of levels that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
