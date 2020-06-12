import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * this is reading the file and create the blocks.
 */
public class BlocksDefinitionReader {

    /**
     * reading the blocks.
     * @param reader the file.
     * @return blocks from symbol factory.
     */
    public static BlocksFromSymbolsFactory fromReader(String reader) {
        Map<String, Integer> spaces = new HashMap();
        Map<String, BlockCreator> blocks = new HashMap();
        try {
            InputStream fis = new FileInputStream(reader);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            Map blockProperties;
            String symbol;
            BlockCreator blockCreator;
            Map spaceProperties;
            Map defValues = null;
            String line;
            do {
                line = br.readLine();
                if (line == null) {
                    continue;
                }
                if (line.startsWith("#") || line.equals("") || line.equals("\t")) {
                    continue;
                }
                if (line.startsWith("default ")) {
                    defValues = stringToMap(line.substring("default ".length()));
                    continue;
                }
                if (line.startsWith("bdef")) {
                    blockProperties = stringToMap(line.substring("bdef ".length()));
                    blockCreator = setBlockFromProperties(defValues, blockProperties);
                    symbol = (String) blockProperties.get("symbol");
                    blocks.put(symbol, blockCreator);
                    continue;
                }
                if (line.startsWith("sdef")) {
                    spaceProperties = stringToMap(line.substring("sdef ".length()));
                    spaces = makeSpacerMap(spaceProperties);
                } else {
                    throw new RuntimeException("Unsupported line format: " + line);
                }
            } while (line != null);
        } catch (Exception e) {
            System.err.println("Error: Target File Cannot Be Read");
        }
        return new BlocksFromSymbolsFactory(spaces, blocks);
    }

    /**
     * taking a string and return a map of it.
     * @param s string
     * @return map
     */
    public static Map stringToMap(String s) {
        String[] arr = s.split(" ");
        String[] mapNode;
        Map<String, String> properties = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            mapNode = arr[i].split(":");
            properties.put(mapNode[0], mapNode[1]);
        }
        return properties;
    }

    /**
     * the method return a map with all the fills for the blocks.
     * @param properties the properties map.
     * @return the new map.
     */
    public static Map fillMapForBlocks(Map<String, String> properties) {
        Map<String, String> fills = new HashMap<>();
        for (String s : properties.keySet()) {
            if (s.startsWith("fill") || s.startsWith("stroke")) {
                fills.put(s, properties.get(s));
            }
        }
        return fills;
    }

    /**
     * making a block creator from the maps.
     * @param def the default map.
     * @param prop the properties map.
     * @return the blockcreator type.
     */
    public static BlockCreator setBlockFromProperties(Map<String, String> def, Map<String, String> prop) {
        Integer width = null, height = null, hitCount = null;
        Map fills = fillMapForBlocks(prop);
        if (def != null) {
            fills.putAll(fillMapForBlocks(def));
            for (String s: def.keySet()) {
                if (s.contains("width")) {
                    width = Integer.parseInt(def.get(s));
                }
                if (s.contains("height")) {
                    height = Integer.parseInt(def.get(s));
                }
                if (s.contains("hit_points")) {
                    hitCount = Integer.parseInt(def.get(s));
                }
            }
        }
        for (String s: prop.keySet()) {
            if (s.contains("width")) {
                width = Integer.parseInt(prop.get(s));
            }
            if (s.contains("height")) {
                height = Integer.parseInt(prop.get(s));
            }
            if (s.contains("hit_points")) {
                hitCount = Integer.parseInt(prop.get(s));
            }
        }
        return new BasicBlockCreator(width, height, hitCount, fills);
    }

    /**
     * make spacemap.
     * @param spaces Map
     * @return new map.
     */
    public static Map<String, Integer> makeSpacerMap(Map<String, String> spaces) {
        Map<String, Integer> newMap = new HashMap<>();
        int width = 0;
        String symbol = "";
        for (String s: spaces.keySet()) {
            if (s.contains("width")) {
                width = Integer.parseInt(spaces.get(s));
            }
            if (s.contains("symbol")) {
                symbol = spaces.get(s);
            }
        }
        newMap.put(symbol, width);
        return newMap;
    }
}
