public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos the x location.
     * @param ypos the y location.
     * @return the block.
     */
    Block create(int xpos, int ypos);
}
