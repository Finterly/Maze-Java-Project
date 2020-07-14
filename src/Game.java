import java.util.Scanner;

public class Game implements Theme {

    public int choosePlay(Scanner keyboard) {
        boolean choosing = true;
        while (choosing) {
            char prompt = Character.toUpperCase(keyboard.next().charAt(0));
            if (prompt == 'X') {
                System.out.println("Exiting game... Goodbye!");
                return 2;
            } else if (prompt == 'P')
                return 0;
            else if (prompt != 'P') {
                System.out.println("Please enter P to Play or X to Exit");
                continue;
            }
        }
        return 1;
    }

    public Cell initializePlay(Scanner keyboard, Player player, Maze maze, int mazeSize) {
        player.setPoints(mazeSize * 2);
        player.setPosition(maze.getStartPosition());
        IntPair pos = player.getPosition();
        Cell currentCell = maze.getCell(pos.xpos, pos.ypos);
        currentCell.setContent(Content.PLAYER);
        currentCell.incrVisit();
        System.out.println(
                "Welcome! " + player.getName() + " has entered the maze. \nEnter: N/S/E/W to Move or X to Exit");
        System.out.println(player);
        maze.printMaze();
        return currentCell;
    }
    
    public int play(Scanner keyboard, Player player, Maze maze, Cell currentCell, int mazeSize) {
        boolean playing = true;
        Cell nextCell = null;
        while (playing) {
            playing = validPoints(player);
            char direction = Character.toUpperCase(keyboard.next().charAt(0));
            if (direction == 'X') {
                playing = false;
                System.out.println("Exiting game... Goodbye!");
                return 2;
            } else if (!Character.toString(direction).matches("[NSWE]")) {
                System.out.println("Invalid input! Use N/S/E/W to Move, X to Exit");
                continue;
            }
            if (passWall(player, currentCell, maze, direction)) {
                nextCell = move(player, currentCell, maze, direction, false);
                player.incrMove();
                playing = updateCellContent(player, nextCell, maze, direction);
                currentCell = nextCell;
                currentCell.incrVisit();
                System.out.println(player);
                maze.printMaze();
            } else {
                player.incrFail();
                System.out.println(player);
            }
        }
        System.out.println("End of game. Goodbye!");
        return 0;
    }
    
    /* checks player has > 0 points to continue play */
    private boolean validPoints(Player player) {
        if (player.getPoints() > 0) {
            return true;
        } else {
            System.out.println("\u23F9 Sorry! You have run out of points!");
            return false;
        }
    }

    private boolean passWall(Player player, Cell currentCell, Maze maze, char direction) {
        Cell nextCell = move(player, currentCell, maze, direction, true);
        switch (getWall(currentCell, direction)) {
        case WALL:
            System.out.println("Fail. Wall, try again.");
            return false;
        case NO_WALL:
            return allowPass(currentCell, direction, nextCell, "Move successful.",
                    "Fail. Invalid move direction " + direction);
        case DOOR:
            if (player.hasPossession(Content.KEY)) {
                return allowPass(currentCell, direction, nextCell, "Opened door! Move successful.",
                        "Fail. Invalid move direction " + direction);
            } else
                System.out.println("Fail. Cannot open door without key, try later.");
            return false;
        case BREAKABLE:
            if (player.hasPossession(Content.HAMMER)) {
                return allowPass(currentCell, direction, nextCell, "Broke wall! Move successful.",
                        "Fail. Invalid move direction " + direction);
            } else
                System.out.println("Fail. Cannot break wall without hammer, try later.");
            return false;
        case FAKE:
            return allowPass(currentCell, direction, nextCell, "Fake wall! Move successful.",
                    "Fail. Invalid move direction " + direction);
        default:
            throw new RuntimeException("Unrecognized Wall Type" + getWall(currentCell, direction) + " . MazeInputFile "
                    + maze.getLineInfo(currentCell));
        }
    }

    private Wall getWall(Cell currentCell, char direction) {
        switch (direction) {
        case 'N':
            return currentCell.getNorthwall();
        case 'S':
            return currentCell.getSouthwall();
        case 'E':
            return currentCell.getEastwall();
        case 'W':
            return currentCell.getWestwall();
        default:
            throw new RuntimeException("Unrecognized input " + direction);
        }
    }

    /* checks if the next cell exists */
    private boolean allowPass(Cell currentCell, char direction, Cell nextCell, String sucMessage, String failMessage) {
        if (nextCell != null) {
            setWall(currentCell, direction);
            System.out.println(sucMessage);
            return true;
        } else {
            System.out.println(failMessage);
            return false;
        }
    }

    /* sets fake wall, breakable wall, or door to no wall after player crosses the wall */
    private void setWall(Cell currentCell, char direction) {
        switch (direction) {
        case 'N':
            currentCell.setNorthwall(Wall.NO_WALL);
            break;
        case 'S':
            currentCell.setSouthwall(Wall.NO_WALL);
            break;
        case 'E':
            currentCell.setEastwall(Wall.NO_WALL);
            break;
        case 'W':
            currentCell.setWestwall(Wall.NO_WALL);
            break;
        default:
            throw new RuntimeException("Unrecognized input " + direction);
        }
    }

    private Cell move(Player player, Cell currentCell, Maze maze, char direction, boolean checkOnly) {
        int x = player.getPosition().xpos;
        int y = player.getPosition().ypos;
        Cell nextCell = null;
        switch (direction) {
        case 'N':
            x -= 1;
            nextCell = maze.getCell(x, y);
            break;
        case 'S':
            x += 1;
            nextCell = maze.getCell(x, y);
            break;
        case 'E':
            y += 1;
            nextCell = maze.getCell(x, y);
            break;
        case 'W':
            y -= 1;
            nextCell = maze.getCell(x, y);
            break;
        default:
            throw new RuntimeException("Unrecognized input " + direction);
        }
        if (nextCell != null && !checkOnly) {
            player.getPosition().xpos = x;
            player.getPosition().ypos = y;
            currentCell.setContent(Content.VISIT);
            return nextCell;
        } else {
            return nextCell;
        }
    }

    private boolean updateCellContent(Player player, Cell cell, Maze maze, char direction) {
        switch (cell.getContent()) {
        case END:
            System.out.println(player.getName() + " is home! You win! " + Win);
            return false;
        case KEY:
            System.out.println("You found a key! " + Key);
            player.addPossession(Content.KEY);
            break;
        case HAMMER:
            System.out.println("Found a hammer! " + Key);
            player.addPossession(Content.HAMMER);
            break;
        case TROPHY:
            System.out.println("Found a trophy! " + Trophy + "Points doubled!");
            player.setPoints((player.getPoints() * 2));
            player.addPossession(Content.TROPHY);
            break;
        case VISIT:
            System.out.println("You've been here " + cell.getVisit() + " time(s) before! Minus 1 point.");
            player.setPoints(player.getPoints() - 1);
            break;
        case EMPTY:
            break;
        default:
            throw new RuntimeException(
                    "Unrecognized content " + cell.getContent() + ". Maze input file " + maze.getLineInfo(cell));
        }
        cell.setContent(Content.PLAYER);
        return true;
    }
}
