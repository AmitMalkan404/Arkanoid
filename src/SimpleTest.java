import java.util.List;

/**
 * tester.
 */
public class SimpleTest {
    /**
     * test.
     * @param args no args.
     */
    public static void main(String[] args) {
        ScoreInfo a1 = new ScoreInfo("amit", 250);
        ScoreInfo a2 = new ScoreInfo("nana", 350);
        ScoreInfo a3 = new ScoreInfo("kaka", 150);
        HighScoresTable highScoresTable = new HighScoresTable(3);
        highScoresTable.add(a1);
        highScoresTable.add(a2);
        highScoresTable.add(a3);
        List<ScoreInfo> infoList = highScoresTable.getHighScores();
        for (int i = 0; i < infoList.size(); i++) {
            System.out.println(infoList.get(i).getName());
            System.out.println(infoList.get(i).getScore());
        }
        System.out.println(highScoresTable.getRank(250));
        System.out.println(highScoresTable.getRank(251));
        System.out.println(highScoresTable.size());
        highScoresTable.clear();
        System.out.println(highScoresTable.size());
    }
}