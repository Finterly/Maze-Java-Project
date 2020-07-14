import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Wall {
    WALL, NO_WALL("no"), DOOR, BREAKABLE, FAKE("hidden");

    static final private Map<String, Wall> W_MAP = new HashMap<String, Wall>();
    private List<String> aliases;

    private Wall(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    static {
        for (Wall wall : Wall.values()) {
            W_MAP.put(wall.name().toUpperCase(), wall);
            for (String alias : wall.aliases)
                W_MAP.put(alias.toUpperCase(), wall);
        }
    }
    
    /* returns enum constant if String value matches */
    static public Wall fromString(String value, int lineNum, String line) {
        value = value.toUpperCase();
        if (value == null)
            throw new NullPointerException(
                    "Invalid content option \"" + value + "\". Error detected at line=" + lineNum + "(" + line + ")");
        Wall wall = W_MAP.get(value);
        if (wall == null)
            throw new IllegalArgumentException(
                    "Invalid content option \"" + value + "\". Error detected at line=" + lineNum + "(" + line + ")");
        return wall;
    }
    
}
