import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * the level 3.
 */
public class Level3 implements LevelInformation {
    /**
     * the count of number of balls.
     *
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 2;
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
        velocityList.add(Velocity.fromAngleAndSpeed(225, 5));
        velocityList.add(Velocity.fromAngleAndSpeed(315, 5));
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
        return 200;
    }

    /**
     * the level name that will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return new Level3Background();
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the blocks of the level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        java.awt.Color[] colors = new Color[5];
        colors[0] = Color.decode("#c1b8b2");
        colors[1] = Color.decode("#ef1607");
        colors[2] = Color.decode("#ffff1e");
        colors[3] = Color.decode("#211ed8");
        colors[4] = Color.decode("#fffff6");
        int y = 150;
        int[] hitPoints = {2, 1, 1, 1, 1};
        for (int i = 0; i < colors.length; i++) {
            for (int j = i + 5; j < 15; j++) {
                blockList.add(new Block(j * 50 + 25, y, 50, 20, colors[i], hitPoints[i]));
            }
            y += 20;
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
