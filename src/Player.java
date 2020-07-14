import java.util.ArrayList;
import java.util.List;

public class Player implements Theme {
    private final String name;
    private int moves = 0;
    private int fails = 0;
    private int points;
    private IntPair position;
    private List<Content> possession = new ArrayList<Content>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMoves() {
        return moves;
    }

    public void incrMove() {
        this.moves += 1;
    }

    public int getFails() {
        return fails;
    }

    public void incrFail() {
        this.fails += 1;
        setPoints(getPoints() - 1);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public IntPair getPosition() {
        return position;
    }

    public void setPosition(IntPair position) {
        this.position = position;
    }

    public void addPossession(Content content) {
        possession.add(content);
    }

    public boolean hasPossession(Content content) {
        if (possession.contains(content))
            return true;
        return false;
    }
    /* allows possession to be printed as corresponding unicode character */
    private List<String> possessionTheme() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < possession.size(); i++) {
            switch (possession.get(i)) {
            case KEY:
                list.add(" " + Key);
                break;
            case TROPHY:
                list.add(" " + Trophy);
                break;
            case HAMMER:
                list.add(" " + Hammer);
                break;
            default:
                list.add(possession.get(i).toString());
                break;
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "NAME: " + name + ", POSSESSIONS: " + possessionTheme() + "\nMOVES: " + moves + ", FAILS: " + fails
                + ", POINTS: " + points;
    }
}
