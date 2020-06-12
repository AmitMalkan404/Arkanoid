import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * malkana1.
 * 313232084.
 * block is the class that holds all the rectangles.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<Image> images = new ArrayList<>();
    private List<Color> colors = new ArrayList<>();
    private Color stroke;
    private int hitCount;
    private int curHits = 0;
    private boolean hasStroke = false;
    private boolean isFillImage;
    private boolean simpleBlock = true;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     * @param x the x.
     * @param y the y.
     * @param width width.
     * @param height height.
     * @param hitCount counter of hit.
     * @param fills list of fills.
     */
    public Block(int x, int y, int width, int height, int hitCount, Map<String, String> fills) {
        this.rectangle = new Rectangle(x, y, width, height);
        int fillCounter = 0;
        for (String s: fills.keySet()) {
            if (s.contains("fill")) {
                fillCounter++;
            }
        }
        if (fillCounter == 1) {
            for (String s: fills.keySet()) {
                if (s.contains("fill") && fills.get(s).contains("color")) {
                    String strCol = fills.get(s).substring("color(".length(), fills.get(s).length() - 1);
                    Color tempCol = stringToColor(strCol);
                    this.color = tempCol;
                    this.simpleBlock = true;
                }
            }
        } else {
            this.simpleBlock = false;
        }
        for (String s: fills.keySet()) {
            if (s.startsWith("stroke")) {
                String ststroke = fills.get(s).substring("color(".length(), fills.get(s).length() - 1);
                this.stroke = stringToColor(ststroke);
                this.hasStroke = true;
            } else if (fills.get(s).startsWith("image")) {
                this.images.add(stringToImage(fills.get(s).substring("image(".length(), fills.get(s).length() - 1)));
                this.isFillImage = true;
            } else if (fills.get(s).startsWith("color")) {
                String strCol = fills.get(s).substring("color(".length(), fills.get(s).length() - 1);
                Color tempCol = stringToColor(strCol);
                this.colors.add(tempCol);
                this.isFillImage = false;
            }

        }
        this.hitListeners = new ArrayList<HitListener>();
        this.hitCount = hitCount;
    }
    /**
     * the constractor.
     * @param rectangle the block's rectangle.
     * @param color color of the block.
     * @param hitCount counts the num of hits by the ball.
     * @param hitListeners the list of the hit listeners.
     */
    public Block(Rectangle rectangle, java.awt.Color color, int hitCount, List<HitListener> hitListeners) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitCount = hitCount;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * the constructor.
     * @param rectangle the block's rectangle .
     * @param hitCount counts the num of hits by the ball.
     * @param color color of the block.
     */
    public Block(Rectangle rectangle, java.awt.Color color, int hitCount) {
        this.rectangle = rectangle;
        this.hitCount = hitCount;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * the constructor.
     * @param rectangle the block's rectangle .
     * @param color color of the block.
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * the constructor.
     * @param x x value.
     * @param y y value.
     * @param width the width of the block.
     * @param height the height of the block.
     * @param color the color of the block.
     * @param hitCount the hit counter.
     */
    public Block(int x, int y, int width, int height, java.awt.Color color, int hitCount) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.hitCount = hitCount;
    }

    /**
     * constructor for sidewalls.
     * @param x x value.
     * @param y y value.
     * @param width the width of the block.
     * @param height the height of the block.
     * @param color the color of the block.
     */
    public Block(int x, int y, int width, int height, java.awt.Color color) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = color;
    }

    /**
     * is the method that adds the block to the game.
     * @param g the game in which the block added to.
     */
        public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * the collision rectangle.
     * @return this.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * the block color.
     * @return the block color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * getHitCount of the block.
     * @return count of hits of the block.
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * the color1 of the block.
     * @param color1 the color1 of the block.
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * do nothing.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint the collision point inside collidable.
     * @param currentVelocity the velocity of the ball.
     * @param hitter the ball which hit the collidable.
     * @return the new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        int flag = 0;
        if (this.rectangle.getUpLine().onLine(collisionPoint) || this.rectangle.getDownLine().onLine(collisionPoint)) {
            newVelocity = new Velocity(newVelocity.getDx(), -1 * newVelocity.getDy());
            if (this.hitCount != 'X') {
                this.hitCount--;
                flag = 1;
            }
        }
        if (this.rectangle.getRightLine().onLine(collisionPoint)
                 || this.rectangle.getLeftLine().onLine(collisionPoint)) {
            newVelocity = new Velocity(-1 * newVelocity.getDx(), newVelocity.getDy());
            if (flag == 0 && this.hitCount != 'X') {
                this.hitCount--;
            }
        }
        this.curHits++;
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * draws the block on surface.
     * @param surface is the surface that the block gets draw on.
     */
    public void drawOn(DrawSurface surface) {
        int recX = (int) this.rectangle.getUpperLeft().getX();
        int recY = (int) this.rectangle.getUpperLeft().getY();
        if (simpleBlock && isFillImage == false) {
            surface.setColor(this.color);
            surface.fillRectangle(recX, recY, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            surface.setColor(Color.BLACK);
            surface.drawRectangle(recX, recY, (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        } else {
            if (isFillImage) {
                surface.drawImage(recX, recY, this.images.get(this.hitCount - 1));
            } else {
                if (this.hitCount > 0) {
                    surface.setColor(this.colors.get(this.hitCount - 1));
                    surface.fillRectangle(recX, recY, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
                }
            }
            if (hasStroke) {
                surface.setColor(this.stroke);
                surface.drawRectangle(recX, recY, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            }
        }
    }

    /**
     * removing the block from the game.
     * @param game the game that we have.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl the listener to the hits when we add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listener to the hits when we remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Make a copy of the hitListeners before iterating over them.
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Notify all listeners about a hit event:
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * making a string to image.
     * @param s the string.
     * @return image.
     */
    public static Image stringToImage(String s) {
        Image img = null;
        try {
            img = ImageIO.read(new File(s));
        } catch (IOException e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return img;
    }

    /**
     * making a string to color.
     * @param s the string.
     * @return color.
     */
    public static Color stringToColor(String s) {
        Color color = null;
        if (s.contains("RGB")) {
            int a, b, c;
            String s1 = s.substring("RGB(".length(), s.length() - 1);
            String[] arr = s1.split(",");
            a = Integer.parseInt(arr[0]);
            b = Integer.parseInt(arr[1]);
            c = Integer.parseInt(arr[2]);
            color = new Color(a, b, c);
            return color;
        } else {
            try {
                color = (Color) Color.class.getField(s).get(null);
                return color;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            //color = Color.getColor(s);
        }
        return color;
    }

    /**
     * returning this rectangles width.
     * @return this rectangles width.
     */
    public double getWidth() {
        return this.rectangle.getWidth();
    }
}
