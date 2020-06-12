import java.util.ArrayList;
import java.util.List;

/**
 * malkana1.
 * 313232084.
 * the class of the GameEnvironment.
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables = new ArrayList<>();

    /**
     * the constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * list of the collidables.
     * @return the collidables list.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * add the given collidable to the environment.
     * @param c the addtion collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     * @param trajectory is the trajectory of the ball.
     * @return the collision info that gices the closest collision point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo min = null, temp = null;
        for (Collidable c : collidables) {
            temp = new CollisionInfo(trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()),
                    c);
            if (temp != null && min != null) {
                if (trajectory.getStart().distance(min.getCollisionPoint()) > trajectory.getStart().distance(
                        temp.getCollisionPoint())) {
                    min = temp;
                }
            } else if (temp != null) {
                min = temp;
            }
        }
        return min;
    }

}
