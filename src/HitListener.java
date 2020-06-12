/**
 * the listener interface to the hits.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block that was hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}