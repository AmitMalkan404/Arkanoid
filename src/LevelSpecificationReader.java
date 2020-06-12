import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * reading the levels from the text file.
 */
public class LevelSpecificationReader implements LevelInformation {
    private List<Block> blockList = new LinkedList();
    private List<Velocity> ballsVelocities = new LinkedList();
    private int blocksInLevel;
    private Sprite backgroundSprite;
    private int paddleSpeed;
    private int paddleSize;
    private String name;
    private int upperLeftx;
    private int upperLefty;
    private BlocksFromSymbolsFactory blocksFactory;


    /**
     * That method that will get a file name and returns a list of LevelInformation objects.
     * @param reader file name.
     * @return a list of LevelInformation objects.
     * @throws IOException the exception.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {
        Integer rowHeight = null;
        Integer rowIndex;
        List<LevelInformation> levels = new LinkedList<>();
        try {
            //InputStream fis = new FileInputStream("####level_definition.txt");
            BufferedReader br = new BufferedReader(reader);
            String line;
            String[] arrayText;
            do {
                newStart();
                LevelSpecificationReader level = new LevelSpecificationReader();
                do {
                    line = br.readLine();
                    while (line.equals("") || line.startsWith("#")) {
                        line = br.readLine();
                    }
                    if (line.equals("START_LEVEL")) {
                        line = br.readLine();
                    }
                    if (line.contains("START_BLOCKS")) {
                        rowIndex = 0;
                        while (!line.contains("END_BLOCKS")) {
                            line = br.readLine();
                            if (line.contains("END_BLOCKS")) {
                                continue;
                            }
                            int curX = this.upperLeftx;
                            for (int i = 0; i < line.length(); i++) {
                                char cSymbol = line.charAt(i);
                                String symbol = String.valueOf(cSymbol);
                                int curY = rowIndex * rowHeight + this.upperLefty;
                                if (blocksFactory.isSpaceSymbol(symbol)) {
                                    curX = curX + blocksFactory.getSpaceWidth(symbol);
                                } else {
                                    Block block = blocksFactory.getBlock(symbol, curX, curY);
                                    if (block == null) {
                                        throw new RuntimeException("Failed to create block of type:" + symbol);
                                    }
                                    curX = curX + (int) block.getWidth();
                                    this.blockList.add(block);
                                }
                            }
                            rowIndex++;
                        }
                    }
                    if (line.equals("END_LEVEL")) {
                        continue;
                    }
                    if (line.equals("END_BLOCKS")) {
                        continue;
                    }
                    arrayText = line.split(":");
                    String key, value;
                    key = arrayText[0];
                    value = arrayText[1];
                    if (key.equals("level_name")) {
                        this.name = value;
                    }
                    if (key.equals("ball_velocities")) {
                        String[] longTextVelocity = value.split(" ");
                        for (int i = 0; i < longTextVelocity.length; i++) {
                            String[] textVel = longTextVelocity[i].split(",");
                            double angle, speed;
                            angle = Double.parseDouble(textVel[0]);
                            speed = Double.parseDouble(textVel[1]);
                            Velocity tempVel = Velocity.fromAngleAndSpeed(angle, speed);
                                this.ballsVelocities.add(tempVel);
                        }
                    }
                    if (key.equals("background")) {
                        if (value.startsWith("color(RGB(") && value.endsWith("))")) {
                            String temp = value.substring(10, value.length() - 2);
                            String[] colorRgb = temp.split(",");
                            int a = Integer.parseInt(colorRgb[0].trim());
                            int b = Integer.parseInt(colorRgb[1].trim());
                            int c = Integer.parseInt(colorRgb[2].trim());
                            Color color = new Color(a, b, c);
                            this.backgroundSprite = new ColorBackground(color);
                        } else if (value.startsWith("color(") && value.endsWith(")")) {
                            String temp = value.substring(6, value.length() - 1);
                            try {
                                Field field = Color.class.getField(temp);
                                Color color = (Color) field.get((Object) null);
                                this.backgroundSprite = new ColorBackground(color);
                            } catch (NoSuchFieldException e5) {
                                throw new RuntimeException("Unsupported color name: " + temp);
                            } catch (IllegalAccessException e6) {
                                throw new RuntimeException("Unsupported color name: " + temp);
                            }
                        } else if (value.startsWith("image(") && value.endsWith(")")) {
                            String temp = value.substring(6, value.length() - 1);
                            //temp = "*/" + temp;
                            InputStream is = null;
                            try {
                                BufferedImage image = ImageIO.read(new File(temp));
                                this.backgroundSprite = new ImageBackground(image);
                            } catch (IOException e1) {
                                throw new RuntimeException("Failed loading image: " + temp, e1);
                            } finally {
                                if (is != null) {
                                    try {
                                        is.close();
                                    } catch (IOException e2) {
                                        throw new RuntimeException("Failed loading image: " + temp, e2);
                                    }
                                }
                            }
                        }
                    }
                    if (key.equals("paddle_speed")) {
                        this.paddleSpeed = Integer.parseInt(value.trim());
                    }
                    if (key.equals("paddle_width")) {
                        this.paddleSize = Integer.parseInt(value.trim());
                    }
                    if (key.equals("block_definitions")) {
                        this.blocksFactory = BlocksDefinitionReader.fromReader(value);
                    }
                    if (key.equals("blocks_start_x")) {
                        this.upperLeftx = Integer.parseInt(value.trim());
                    }
                    if (key.equals("blocks_start_y")) {
                        this.upperLefty = Integer.parseInt(value.trim());
                    }
                    if (key.equals("row_height")) {
                        rowHeight = Integer.parseInt(value.trim());
                    }
                    if (key.equals("num_blocks")) {
                        this.blocksInLevel = Integer.parseInt(value.trim());
                    }
                } while (!(line.contains("END_LEVEL")));
                level.copy(this);
                levels.add(level);
                String nullCheckLine = br.readLine();
                if (nullCheckLine == null) {
                    line = br.readLine();
                }
            } while (line != null);
            br.close();
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return levels;
    }
/**
    /**
     * the count of number of balls.
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.ballsVelocities.size();
    }

    /**
     * the ball color.
     *
     * @return the ball color.
     */
    @Override
    public Color ballColor() {
        return Color.WHITE;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return tghe list of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return ballsVelocities;
    }

    /**
     * the speed of the paddle in the level.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * the paddle size.
     *
     * @return the paddle size.
     */
    @Override
    public int paddleWidth() {
        return this.paddleSize;
    }

    /**
     * the level name that will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    @Override
    public String levelName() {
        return this.name;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return this.backgroundSprite;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the blocks of the level.
     */
    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * Number of levels that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksInLevel;
    }

    /**
     * adding a block to the list.
     * @param block the block to add.
     */
    public void addBlock(Block block) {
        this.blockList.add(block);
    }

    /**
     * copy levels.
     * @param other level.
     */
    public void copy(LevelSpecificationReader other) {
        this.name = other.name;
        this.blockList = other.blockList;
        this.blocksFactory = other.blocksFactory;
        this.blocksInLevel = other.blocksInLevel;
        this.paddleSize = other.paddleSize;
        this.paddleSpeed = other.paddleSpeed;
        this.ballsVelocities = other.ballsVelocities;
        this.backgroundSprite = other.backgroundSprite;
        this.upperLeftx = other.upperLeftx;
        this.upperLefty = other.upperLefty;
    }

    /**
     * initialize everything.
     */
    public void newStart() {
        this.upperLefty = 0;
        this.upperLeftx = 0;
        this.backgroundSprite = null;
        this.ballsVelocities = new LinkedList<>();
        this.paddleSpeed = 0;
        this.paddleSize = 0;
        this.blocksInLevel = 0;
        this.name = null;
        this.blockList = new LinkedList<>();
        this.blocksFactory = null;
    }
}
