/**
 * the interface to notify the hits.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl the listener to the hits when we add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listener to the hits when we remove.
     */
    void removeHitListener(HitListener hl);
}
