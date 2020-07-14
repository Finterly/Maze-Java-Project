import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Content {
    START("S"), END("E"), EMPTY("no"), KEY, HAMMER, TROPHY, PLAYER, VISIT;

    static final private Map<String, Content> C_MAP = new HashMap<String, Content>();
    private List<String> aliases;

    private Content(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    static {
        for (Content content : Content.values()) {
            C_MAP.put(content.name().toUpperCase(), content);
            for (String alias : content.aliases)
                C_MAP.put(alias.toUpperCase(), content);
        }
    }

    /* returns enum constant if String value matches */
    static public Content fromString(String value, int lineNum, String line) {
        value = value.toUpperCase();
        if (value == null)
            throw new NullPointerException(
                    "Invalid content option \"" + value + "\". Error detected at line=" + lineNum + "(" + line + ")");
        Content content = C_MAP.get(value);
        if (content == null)
            throw new IllegalArgumentException(
                    "Invalid content option \"" + value + "\". Error detected at line=" + lineNum + "(" + line + ")");
        return content;
    }

}