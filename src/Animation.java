import biuoop.DrawSurface;
/**
 * the interface of the animation of the game.
 */
public interface Animation {
    /**
     * in charge of the logic.
     * @param d the surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * in charge of stopping condition.
     * @return bolean value if true or false.
     */
    boolean shouldStop();
}
