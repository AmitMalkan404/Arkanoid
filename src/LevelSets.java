import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * the class which supply the levels sets.
 */
public class LevelSets {
    private List<LevelSet> levelSetList = new ArrayList();

    /**
     * reading the file.
     * @param reader the file.
     * @return the level sets.
     * @throws IOException if there exception.
     */
    public static LevelSets fromReader(Reader reader) throws IOException {
        LevelSets result = new LevelSets();
        LevelSets.LevelSet currentSet = null;
        LineNumberReader lineReader = null;

        try {
            lineReader = new LineNumberReader(reader);
            String line = null;

            while ((line = lineReader.readLine()) != null) {
                if (lineReader.getLineNumber() % 2 == 0) {
                    currentSet.setLevelDefinitionPath(line.trim());
                    result.addLevelSet(currentSet);
                    currentSet = null;
                } else {
                    currentSet = new LevelSets.LevelSet();
                    String[] lineParts = line.trim().split(":");
                    currentSet.setKey(lineParts[0]);
                    currentSet.setMessage(lineParts[1]);
                }
            }
        } finally {
            if (lineReader != null) {
                lineReader.close();
            }

        }

        return result;
    }

    /**
     * adding the levelset to the levels.
     * @param levelSet the set.
     */
    public void addLevelSet(LevelSets.LevelSet levelSet) {
        this.levelSetList.add(levelSet);
    }

    /**
     * getter for the list.
     * @return the list.
     */
    public List<LevelSets.LevelSet> getLevelSetList() {
        return this.levelSetList;
    }


    /**
     * inner class.
     */
    public static class LevelSet {
        private String message;
        private String key;
        private String levelDefinitionPath;

        /**
         * constructor - empty.
         */
        public LevelSet() {
        }

        /**
         * setter.
         * @param msg the val.
         */
        public void setMessage(String msg) {
            this.message = msg;
        }

        /**
         * setter.
         * @param button the val.
         */
        public void setKey(String button) {
            this.key = button;
        }

        /**
         * setter.
         * @param levelPath the val.
         */
        public void setLevelDefinitionPath(String levelPath) {
            this.levelDefinitionPath = levelPath;
        }

        /**
         * getter.
         * @return the val.
         */
        public String getMessage() {
            return this.message;
        }

        /**
         * getter.
         * @return the val.
         */
        public String getKey() {
            return this.key;
        }

        /**
         * getter.
         * @return the val.
         */
        public String getLevelDefinitionPath() {
            return this.levelDefinitionPath;
        }
    }
}
