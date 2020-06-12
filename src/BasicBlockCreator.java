import java.util.Map;

/**
 * THE CREATOR OF THE BLOCK.
 */
public class BasicBlockCreator implements BlockCreator {
    private int width;
    private int height;
    private int hitpoints;
    private Map fills;

    /**
     * constructor.
     * @param width the width.
     * @param height the height.
     * @param hitpoints the hitpoints.
     * @param fills the fills.
     */
    public BasicBlockCreator(int width, int height, int hitpoints, Map fills) {
        this.width = width;
        this.height = height;
        this.hitpoints = hitpoints;
        this.fills = fills;
    }

    /**
     * Create a block at the specified location.
     *
     * @param xpos the x location.
     * @param ypos the y location.
     * @return the block.
     */
    @Override
    public Block create(int xpos, int ypos) {
        return new Block(xpos, ypos, this.width, this.height, this.hitpoints, this.fills);
    }

    /**
     * setter for fills.
     * @param fills1 the map.
     */
    public void setFills(Map fills1) {
        this.fills = fills1;
    }

    /**
     * setter for height.
     * @param height1 the height.
     */
    public void setHeight(int height1) {
        this.height = height1;
    }

    /**
     * setter for hitpoints.
     * @param hitpoints1
     */
    public void setHitpoints(int hitpoints1) {
        this.hitpoints = hitpoints1;
    }

    /**
     * setter for width.
     * @param width1 the width.
     */
    public void setWidth(int width1) {
        this.width = width1;
    }
}
