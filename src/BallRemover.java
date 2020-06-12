/**
 * this class is removing all the balls.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter lostBalls;

    /**
     * the constructor.
     * @param game the game.
     * @param balls the lost balls.
     */
    public BallRemover(GameLevel game, Counter balls) {
        this.game = game;
        this.lostBalls = balls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block that was hit.
     * @param hitter   is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.lostBalls.decrease(1);
    }
}
