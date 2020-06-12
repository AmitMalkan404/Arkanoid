import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * malkana1.
 * 313232084.
 * the class of the spritecollection.
 */

public class SpriteCollection {
    private java.util.List<Sprite> spriteList = new ArrayList<>();

    /**
     * the constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * returns the spriteslist.
     *
     * @return the spriteslist.
     */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    /**
     * the adder of the sprite object to the spritecollection.
     *
     * @param s the sprite object.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d drawsurface object.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s1 : spriteList) {
            s1.drawOn(d);
        }
    }
}