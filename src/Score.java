import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {

    private List<Score> scoreList = new ArrayList<Score>();
    private String name;
    private String mazeName;
    private int moves;
    private int fails;
    private int points;

    public Score(String name, String maze, int move, int fail, int point) {
        this.name = name;
        this.mazeName = maze;
        this.moves = move;
        this.fails = fail;
        this.points = point;
    }

    public void loadScore(File file) {
        int lineNum = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.readLine();
            String line = in.readLine();
            final String FIELD_SEP = ",";
            while (line != null) {
                lineNum++;
                line = line.trim();
                String[] column = line.split(FIELD_SEP);
                String name = column[0];
                String maze = column[1];
                int move = Integer.parseInt(column[2]);
                int fail = Integer.parseInt(column[3]);
                int point = Integer.parseInt(column[4]);

                Score score = new Score(name, maze, move, fail, point);
                scoreList.add(score);
                line = in.readLine();
            }
            in.close();
        } catch (IOException e) {
            System.out
                    .println(e + " Failed to load high_scores.txt. Your score could not be written to high_scores.txt");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println(e + ". Error detected at line " + lineNum
                    + " of high_scores.txt. Your score could not be written.");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e + ". Error detected at line " + lineNum
                    + " of high_scores.txt. Your score could not be written.");
            System.exit(1);
        }
    }

    public List<Score> getScoreList() {
        return scoreList;
    }
    
    public int getPoints() {
        return points;
    }
    
    /* sorts scores by descending points */
    public void sortScoreList() {
        Collections.sort(scoreList, (s1, s2) -> s2.getPoints() - s1.getPoints());
    }

    @Override
    public String toString() {
        return name + "," + mazeName + "," + moves + "," + fails + "," + points;
    }
}
