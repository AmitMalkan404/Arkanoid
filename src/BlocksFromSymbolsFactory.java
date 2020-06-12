import java.util.HashMap;
import java.util.Map;

/**
 * define a mapping from symbols to spaces and blocks.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths = new HashMap();
    private Map<String, BlockCreator> blockCreators = new HashMap();

    /**
     * Constructor.
     * @param spacerWidths the first map.
     * @param blockCreators the second map.
     */
    public BlocksFromSymbolsFactory(Map spacerWidths, Map blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * returns true if 's' is a valid space symbol.
     * @param s the received string.
     * @return true if it space and false if not
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * returns true if 's' is a valid block symbol.
     * @param s the received string.
     * @return true if it is block and false if not.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }
    /**
     * Return a block according to the definitions associated with symbol s.
     * @param s the Symbol.
     * @param xpos the x position.
     * @param ypos the y position.
     * @return the created block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s the symbol.
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * setter for the hashmap.
     * @param symbol the key
     * @param creator the value.
     */
    private void addBlockCreator(String symbol, BlockCreator creator) {
        this.blockCreators.put(symbol, creator);
    }

    /**
     * setter for the hashmap.
     * @param symbol the key.
     * @param width the value.
     */
    private void addSpacer(String symbol, int width) {
        this.spacerWidths.put(symbol, width);
    }
}