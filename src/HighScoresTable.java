import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * the high-score table.
 */
class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> highScoreList;
    private String fileName = "highscores";
    /**
     * Create an empty high-scores table with the specified size.
     * @param size means that the table holds up to size top scores.
     */
    HighScoresTable(int size) {
        this.size = size;
        this.highScoreList = new ArrayList<>();
    }

    /**
     * Create an empty high-scores table with the specified size.
     * @param size means that the table holds up to size top scores.
     * @param list the list to insert.
     */
    HighScoresTable(int size, List list) {
        this.size = size;
        this.highScoreList = list;
    }

    /**
     * Add a high-score.
     * @param score the highscore to add.
     */
    public void add(ScoreInfo score) {
        this.highScoreList.add(score);
    }

    /**
     * Return table size.
     * @return table size.
     */
    public int size() {
        return getHighScores().size();
    }

    /**
     * check if the given score can be belong to the top 5 score.
     * @param score the given score.
     * @return true or false.
     */
    public boolean isOnTop5(int score) {
        if (this.highScoreList.size() < 5) {
            return true;
        } else {
            return (score > this.highScoreList.get(4).getScore());
        }
    }

    /**
     * Return the current high scores.
     * @return list sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        Collections.sort(this.highScoreList);
        return this.highScoreList;
    }

    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.

    /**
     * return the rank of the current score: where will it be on the list if added.
     * @param score the score of the player.
     * @return the rank of the score which placed, if there's no such score - it'll return -1 value.
     */
    public int getRank(int score) {
        int i = 1;
        for (ScoreInfo player: this.getHighScores()) {
            if (player.getScore() == score) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.highScoreList.clear();
    }


    /**public void load(File filename) throws IOException {
    }*/

    /**
     * Save table data to the specified file.
     * @param filename the filename.
     * @throws IOException the Exception.
     */
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        try {
            os = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(this.fileName)));
            for (ScoreInfo member : highScoreList) {
                os.printf(member.getName() + "," + member.getScore() + "\n");
            }
        } catch (IOException e) {
            System.out.println("fail while opening file in writing");
        } finally {
            if (os != null) {
                if (os != null) {
                    os.close();
                }
            }
        }
    }

    /**
     * Read a table from file and return it.
     * @param filename the file which we read from.
     * @throws IOException the Exception.
     * @return the new highscore table.
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        ArrayList<ScoreInfo> tableTemp = new ArrayList<>();

        BufferedReader is = null;
        try {
            is = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename)));
            String line;
            while ((line = is.readLine()) != null) {
                insertData(line, tableTemp);
            }
        } catch (IOException e) {
            System.out.println("fail while opening file in reading");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("fail while closing file in reading");
                }
            }
        }

        return new HighScoresTable(tableTemp.size(), tableTemp);
    }

        /*ObjectInputStream inputStream = null;
        HighScoresTable other;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("highscores"));
            HighScoresTable other2 = (HighScoresTable) inputStream.readObject();
            return other2;
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException("CLASS NOT FOUND", cnfe);
        } catch (FileNotFoundException fnfe) {
            other = new HighScoresTable(5);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return other;

    }*/

    /**
     * inserting the data to a list.
     * @param line the data.
     * @param tableCell the list.
     */
    public static void insertData(String line, ArrayList<ScoreInfo> tableCell) {
        String[] values = line.split(",");
        ScoreInfo member = new ScoreInfo(values[0], Integer.valueOf(values[1]));
        tableCell.add(member);
    }
}
