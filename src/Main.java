import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;

public class Main implements Theme {

    private static String usage = "usage: playerName mazePath";

    public static void main(String[] args) {
        int status = 0;
        if (args.length < 2) {
            System.out.println(usage);
            System.exit(1);
        }
        try {
            Player player = new Player(args[0]);
            Game game = new Game();
            String mazePath = args[1];
//            String mazePath = "/Users/Finterly/Dev/maze/Hu_Finterly_Maze_R0688968/mazes_for_testing/MidiMaze.txt";
            File mazeFile = new File(mazePath);     
            Maze maze = initializeMaze(mazeFile);

            printIntro(player);
            maze.printMaze();
            Scanner keyboard = new Scanner(System.in);
            if (game.choosePlay(keyboard) == 0) {
                Cell currentCell = game.initializePlay(keyboard, player, maze, maze.getMazeSize());
                status = game.play(keyboard, player, maze, currentCell, maze.getMazeSize());
                if (status == 0)
                    processScore(player, maze, mazeFile);
            }
            keyboard.close(); 
            System.exit(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Maze initializeMaze(File mazeFile) {
        Maze maze = new Maze();
        maze.loadMaze(mazeFile);
        maze.validateMaze();
        return maze;
    }

    private static void printIntro(Player player) {
        System.out.println("Introduction: " + player.getName() + ", the dog " + Player
                + ", chased a car and is now far away from home. Help " + player.getName() + " get home."
                + "\nWalls: | or --- walls, -d- doors, -b- breakable walls, | or --- fake walls."
                + "\nHidden items: key to open doors, hammer to break walls, trophy for points."
                + "\nRules: fail minus 1 point, retrace step minus 1 point, trophy doubles points. Reach home before you run out of points!"
                + "\n\nEnter: P to Play or X to Exit:");
    }

    private static void processScore(Player player, Maze maze, File mazeFile) {
        Score newScore = new Score(player.getName(), FilenameUtils.getBaseName(mazeFile.getName()), player.getMoves(), player.getFails(),
                player.getPoints());
        String scoreFile = "high_scores.txt";
        File f = new File(scoreFile);
        if (f.exists()) {
            newScore.loadScore(f);
        }
        writeScoreToFile(newScore, scoreFile);
    }

    private static void writeScoreToFile(Score newScore, String scoreFile) {
        PrintWriter out = null;
        try {
            newScore.getScoreList().add(newScore);
            newScore.sortScoreList();
            out = new PrintWriter(new BufferedWriter(new FileWriter(scoreFile, false)));
            out.println("PLAYERNAME,MAZENAME,NUMBER_OF_MOVES_SOLVED,NUMBER_OF_FAILS,POINTS");
            for (Score score : newScore.getScoreList()) {
                out.println(score);
            }
            System.out.println(
                    "\nYour score has been written to highscore.txt\nResults:" + newScore + "\n" + getDateTime());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }

    private static String getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
