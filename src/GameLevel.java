import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * malkana1.
 * 313232084.
 * the class of the game of the balls blocks and paddle.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter leftBalls;
    private Counter leftBlocks;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private int width = 800;
    private int height = 600;
    private int blockWidth = 50;
    private int blockHeight = 25;
    private GUI gui;
    // Create a GUI screen
    private LevelInformation levelInformation;

    /**
     * the constructor of the class.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.gui = new GUI("Arkanoid 2019", this.width, this.height);
        this.runner = new AnimationRunner(gui, 60);
        this.keyboard = gui.getKeyboardSensor();
        this.levelInformation = new Level4();
        this.leftBalls = new Counter(this.levelInformation.numberOfBalls());
        this.leftBlocks = new Counter(this.levelInformation.numberOfBlocksToRemove());
    }

    /**
     * the constructor of the class.
     * @param levelInfo the level in which we play.
     * @param ar the animation runner.
     * @param ks the keyboard sensor.
     * @param livesCount the lives Counter.
     * @param score the score.
     * @param gui the gui.
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, KeyboardSensor ks, Counter livesCount,
            Counter score, GUI gui) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.score = score;
        this.lives = livesCount;
        this.runner = ar;
        this.keyboard = ks;
        this.levelInformation = levelInfo;
        this.leftBalls = new Counter(this.levelInformation.numberOfBalls());
        this.leftBlocks = new Counter(this.levelInformation.numberOfBlocksToRemove());
    }

    /**
     * adding the collidable to a list array.
     *
     * @param c the collidable object which added to the array.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adding the Sprite to a list array.
     *
     * @param s the Sprite object which added to the array.
     */
    public void addSprite(Sprite s) {
        sprites.getSpriteList().add(s);
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        //creating the background.
        this.levelInformation.getBackground().addToGame(this);
        //listeners and indicators.
        HitListener blockRemover = new BlockRemover(this, this.leftBlocks);
        HitListener ballRemover = new BallRemover(this, this.leftBalls);
        HitListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreScreen = new ScoreIndicator(this.score);
        LivesIndicator livesindicator = new LivesIndicator(this.lives);
        LevelNameIndicator levelName = new LevelNameIndicator(this.levelInformation.levelName());
        levelName.addToGame(this);
        scoreScreen.addToGame(this);
        livesindicator.addToGame(this);
        // variables.
        int max = 12, startX = width - blockHeight - blockWidth, startY = 5 * blockHeight;
        //creating the sidewalls.
        Point p2 = new Point(0, 20);
        Rectangle leftRect = new Rectangle(p2, this.blockHeight, this.height - 20);
        Block left = new Block(leftRect, Color.GRAY);
        Point p3 = new Point(this.width - this.blockHeight, 20);
        Rectangle rightRect = new Rectangle(p3, this.blockHeight, this.height - 20);
        Block right = new Block(rightRect, Color.GRAY);
        Point p4 = new Point(this.blockHeight, 20);
        Rectangle upRect = new Rectangle(p4, this.width - (this.blockHeight * 2), this.blockHeight);
        Block up = new Block(upRect, Color.GRAY);
        Point p5 = new Point(this.blockHeight, this.height);
        Rectangle bottumRect = new Rectangle(p5, this.width - (this.blockHeight * 2), this.blockHeight);
        Block bottum = new Block(bottumRect, Color.WHITE);
        left.addToGame(this);
        right.addToGame(this);
        up.addToGame(this);
        bottum.addToGame(this);
        bottum.addHitListener(ballRemover);
        //creating the paddle.
        Point p7 = new Point(400 - (this.levelInformation.paddleWidth() / 2), 560);
        Rectangle rec7 = new Rectangle(p7, this.levelInformation.paddleWidth(), this.blockHeight - 10);
        Paddle gamePaddle = new Paddle(this.keyboard, rec7, Color.yellow, this.levelInformation.paddleSpeed());
        gamePaddle.addToGame(this);
        //creating the mainBlocks that will be removed.
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(sprites, 3, 2, this.width, this.height));
        this.running = true;
        while (!this.shouldStop()) {
            this.runner.run(this);
        }
    }

    /**
     * running the game.
     */
    public void run() {
        while (this.lives.getValue() >= 0) {
            playOneTurn();
            if (leftBlocks.getValue() == 0) {
                gui.close();
                break;
            }
        }
        gui.close();
    }

    /**
     * Removing a collidable from the list.
     *
     * @param c the collidable that we want to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }

    /**
     * Removing sprite from the sprites collection that we have.
     * @param s the sprite which we want to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.getSpriteList().remove(s);
    }

    /**
     * in charge of the logic.
     *
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //checking if there is no more blocks.
        if (leftBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
            return;
        }
        //checking if there is no more balls.
        if (leftBalls.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
            this.leftBalls.increase(this.levelInformation.numberOfBalls());
            return;
        }
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(new PauseScreen(), this.keyboard, "space"));
            this.runner.run(new CountdownAnimation(sprites, 3, 2, this.width, this.height));
        }
    }

    /**
     * in charge of stopping condition.
     *
     * @return bolean value if true or false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * create the Balls On Top Of the Paddle.
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 550, 5, this.levelInformation.ballColor(), this.environment);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }

    /**
     * returning the left blocks.
     * @return the blocks.
     */
    public Counter getLeftBlocks() {
        return this.leftBlocks;
    }

    /**
     * returning the lives counter.
     * @return the lives.
     */
    public Counter getLives() {
        return this.lives;
    }

    /**
     * returning the score counter.
     * @return the score.
     */
    public Counter getScore() {
        return score;
    }
}