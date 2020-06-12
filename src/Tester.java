import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

/**
 * This class creates a window with two frames: a large grey rectangle and a small yellow one,
 * with balls bouncing inside.
 * The balls' sizes are received by main args.
 * The rest of the balls' parameters are random.
 * Assignment 2
 *
 * @author Amit Malkan 313232084 malkana1
 * @version 04/04/2019
 *
 */
public class Tester {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int numOfBalls = args.length;

        GUI gui = new GUI("title", 300, 300); // Create a GUI screen sized 200*200 px
        //int[] ballSizesArray = new int[numOfBalls];

        //Random rand = new Random(); // create a random-number generator
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball[] ballsArray = new Ball[3];
        Velocity v = Velocity.fromAngleAndSpeed(120,10);
        Point p1 = new Point(60, 60);
        Rectangle rec1 = new Rectangle(p1, 60, 60);
        Block bl1 = new Block(rec1, Color.GREEN);
        Point p2 = new Point(0, 0);
        Rectangle rec2 = new Rectangle(p2, 10, 300);
        Block bl2 = new Block(rec2, Color.GRAY);
        Point p3 = new Point(290, 0);
        Rectangle rec3 = new Rectangle(p3, 10, 300);
        Block bl3 = new Block(rec3, Color.GRAY);
        Point p4 = new Point(10, 0);
        Rectangle rec4 = new Rectangle(p4, 280, 10);
        Block bl4 = new Block(rec4, Color.GRAY);
        Point p5 = new Point(10, 290);
        Rectangle rec5 = new Rectangle(p5, 280, 10);
        Block bl5 = new Block(rec5, Color.GRAY);
        Point p6 = new Point(160, 100);
        Rectangle rec6 = new Rectangle(p6, 30, 40);
        Block bl6 = new Block(rec6, Color.RED);
        //Point p2 = new Point(150, 150);
        //Rectangle rec2 = new Rectangle(p2, 50, 50, Color.RED);
        //Block bl2 = new Block(rec2);
        GameEnvironment game = new GameEnvironment();
        //game.addCollidable(bl2);
        game.addCollidable(bl1);
        game.addCollidable(bl2);
        game.addCollidable(bl3);
        game.addCollidable(bl4);
        game.addCollidable(bl5);
        game.addCollidable(bl6);
        ballsArray[0] = new Ball(150,151,10, java.awt.Color.BLACK, game);
        ballsArray[0].setVelocity(v);
        //ballsArray[1] = new Ball(15,15,15, java.awt.Color.BLACK, game);
        //ballsArray[2] = new Ball(20,20,20, java.awt.Color.BLACK, game);
        //ballsArray[0].setGame(game);
        //Paddle paddle = new Paddle(new Rectangle(new Point(100,260),80,20), null);
        //game.addCollidable(paddle);
        //paddle.drawOn(ballsSurface);


        while (true) {
            DrawSurface ballsSurface = gui.getDrawSurface();
            //paddle.drawOn(ballsSurface);
        /*
            ballsSurface.setColor(Color.GRAY);
            ballsSurface.fillRectangle(60, 60, 60, 60);
            ballsSurface.setColor(Color.YELLOW);
            ballsSurface.fillRectangle(150, 150, 50, 50);
            ballsSurface.setColor(Color.RED);
            ballsSurface.fillRectangle(160, 290, 60, 20);
            */
        for(Collidable c : game.getCollidables())
        {
            c.drawOn(ballsSurface);
        }
            ballsArray[0].moveOneStep();
            ballsArray[0].drawOn(ballsSurface);
            gui.show(ballsSurface);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}