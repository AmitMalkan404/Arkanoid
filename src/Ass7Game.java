import biuoop.GUI;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  malkana1.
 *  313232084.
 * this is the main class that runs the game.
 */
public class Ass7Game {
    /**
     * main method.
     * @param args gets nothing and runs the game.
     */
    public static void main(String[] args) {
        String levelSetsPath = "./level_sets.txt";
        String levelDefs = "./definitions/level_definitions.txt";
        List<LevelInformation> levelList = new ArrayList<>();
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        HighScoresTable h1 = new HighScoresTable(0);
        try {
            File fr = new File("highscores");
            h1 = HighScoresTable.loadFromFile(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final HighScoresTable highScoresTable = h1;
        GameFlow game = new GameFlow(ar, gui, highScoresTable);
        Menu<Task<Void>> mainMenu = new MenuManager<>("Arkanoid", ar, gui.getKeyboardSensor());
        LevelSpecificationReader levels = new LevelSpecificationReader();
//READING THE LEVEL SETS.
        LevelSets levelSets = null;
        try {
            FileReader is = new FileReader(levelSetsPath);
            levelSets = levelSets.fromReader(is);
        } catch (IOException var13) {
            throw new RuntimeException("Failed loading level defs");
        }
//CREATING THE LEVELSETS PATHS.
        String simplePath = levelSets.getLevelSetList().get(0).getLevelDefinitionPath();
        String comPath = levelSets.getLevelSetList().get(1).getLevelDefinitionPath();
//CREATING THE LEVELS MENU.
        Menu<Task<Void>> levelSetsMenu = new MenuManager<>("Level Sets", ar, gui.getKeyboardSensor());
//MAKING THE FIRST SET.
        levelSetsMenu.addSelection("k", "Simple", new Task<Void>() {
            @Override
            public Void run() {
                List<LevelInformation> levelList = new ArrayList<>();
                GameFlow game = new GameFlow(ar, gui, highScoresTable);
                //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(simplePath);
                try {
                    FileReader fr = new FileReader(simplePath);
                    levelList = levels.fromReader(fr);
                    game.runLevels(levelList);
                    levelSetsMenu.reset();
                    highScoresTable.save(new File("highscores"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
//MAKING THE SECOND SET.
        levelSetsMenu.addSelection("c", "Creative", new Task<Void>() {
            @Override
            public Void run() {
                List<LevelInformation> levelList = new ArrayList<>();
                GameFlow game = new GameFlow(ar, gui, highScoresTable);
                try {
                    FileReader fr = new FileReader(comPath);
                    levelList = levels.fromReader(fr);
                    //levelList.remove(0);
                    game.runLevels(levelList);
                    levelSetsMenu.reset();
                    highScoresTable.save(new File("highscores"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
//DEFINING THE MAIN MENU.
        mainMenu.addSubMenu("s", "Start a Game", levelSetsMenu);
        mainMenu.addSelection("h", "High Scores", new Task<Void>() {
            public Void run() {
                ar.run(new KeyPressStoppableAnimation(new HighScoresAnimation(highScoresTable),
                        gui.getKeyboardSensor(), "space"));
                return null;
            }
        });
        mainMenu.addSelection("e", "Exit", new Task<Void>() {
            public Void run() {
                System.exit(0);
                return null;
            }
        });
//RUN!
        //game.runLevels(levelList);
        while (true) {
            ar.run(mainMenu);
            Task<Void> task = (Task) mainMenu.getStatus();
            task.run();
            mainMenu.reset();
        }
    }

}
