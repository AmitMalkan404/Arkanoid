/**
 * its tracking the scores of the player.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter the counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * implement the hitevent.
     * @param beingHit the block that was hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitCount() > 0) {
            this.currentScore.increase(5);
        } else {
            this.currentScore.increase(10);
        }
    }
}