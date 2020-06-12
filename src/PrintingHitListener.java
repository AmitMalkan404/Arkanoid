/**
 * the class test the hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * creating the hit event.
     * @param beingHit the block that was hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + " points was hit.");
    }
}
