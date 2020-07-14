import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Maze implements Theme {

    private List<List<Cell>> cellList = new ArrayList<List<Cell>>();
    private HashMap<Cell, String> lineInfoMap = new HashMap<Cell, String>();
    private int mazeSize;
    private int rowLength;
    private int columnLength;

    public Maze() {
    }

    public void loadMaze(File file) {
        int countRow = 0;
        int countColumn = 0;
        int lineNum = 0;
        final String FIELD_SEP = ",";
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.readLine();
            String line = in.readLine();
            while (line != null) {
                lineNum++;
                line = line.trim();
                String[] column = line.split(FIELD_SEP);
                int x = isValidXY(column[0], lineNum, line);
                int y = isValidXY(column[1], lineNum, line);
                Wall northwall = Wall.fromString(column[2], lineNum, line);
                Wall southwall = Wall.fromString(column[3], lineNum, line);
                Wall eastwall = Wall.fromString(column[4], lineNum, line);
                Wall westwall = Wall.fromString(column[5], lineNum, line);
                Content content = Content.fromString(column[6], lineNum, line);
                for (int i = cellList.size(); i <= x; i++) {
                    cellList.add(new ArrayList<Cell>());
                    if (cellList.size() > countRow) {
                        countRow++;
                    } // else continue loop
                }
                List<Cell> cellRow = cellList.get(x);
                for (int j = cellRow.size(); j <= y; j++) {
                    cellRow.add(null);
                    if (cellRow.size() > countColumn) {
                        countColumn++;
                    } // else continue loop
                }
                Cell cell = new Cell(northwall, southwall, eastwall, westwall, content);
                cellRow.set(y, cell);
                String lineInfo = "line=" + lineNum + "(" + line + ")";
                lineInfoMap.put(cell, lineInfo);
                line = in.readLine();
            }
            rowLength = countColumn;
            columnLength = countRow;
            mazeSize = rowLength * columnLength;
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        /* reverses order of rows (x) as intended by maze input file */
        Collections.reverse(cellList);
    }

    /*
     * XYcoordinates cannot be negative integers. Mazes larger than 40 x 40 are
     * not suitable for Console view
     */
    private int isValidXY(String input, int lineNum, String line) {
        int i = 0;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(ex.getMessage()
                    + ". Invalid maze input file. XYcoordinate must be an integer 0 to 40. Error detected at line="
                    + lineNum + "(" + line + ")");
        }
        if (i >= 0 && i <= 40)
            return i;
        else
            throw new IllegalArgumentException(
                    "Invalid maze input file. XYcoordinate must be an integer 0 to 40. Error detected at line="
                            + lineNum + "(" + line + ")");
    }

    public void validateMaze() {
        checkForNull();
        isRegularRectangle();
        validateWall();
        validateStartEnd(Content.START);
        validateStartEnd(Content.END);
    }

    /* Maze cannot have null cells */
    private void checkForNull() {
        for (int x = 0; x < cellList.size(); x++) {
            List<Cell> cellRow = cellList.get(x);
            for (int y = 0; y < cellRow.size(); y++) {
                Cell cell = cellRow.get(y);
                if (cell == null) {
                    throw new IllegalArgumentException(
                            "Invalid maze input file, missing lines/cells. Error detected at "
                                    + (cellList.size() - x - 1) + "," + y);
                }
            }
        }
    }
    
    /* Maze cannot have null cells */
    private void isRegularRectangle() {
        int y = 0;
        for (int x = 0; x < cellList.size(); x++) {
            int count = 0;
            for (y = 0; y < cellList.get(x).size(); y++) {
                count++;
            }
            if (count != rowLength)
                throw new IllegalArgumentException(
                        "Invalid maze input file. Must be a regular rectangle. Error detected for "
                                + (cellList.size() - x - 1) + "," + y);
        }
    }
    /* Cell wall type must match neighboring cell wall */
    private void validateWall() {
        for (int x = 0; x < cellList.size(); x++) {
            for (int y = 0; y < cellList.get(x).size(); y++) {
                Cell cell = getCell(x, y);
                if (cell != null) {
                    Cell nb = getCell(x - 1, y);
                    if (nb != null && cell.getNorthwall() != nb.getSouthwall()) {
                        throw new IllegalArgumentException("Invalid mazefile" + getLineInfo(cell)
                                + " Northwall must match Southwall of " + getLineInfo(nb));
                    }
                    nb = getCell(x + 1, y);
                    if (nb != null && cell.getSouthwall() != nb.getNorthwall()) {
                        throw new IllegalArgumentException("Invalid mazefile " + getLineInfo(cell)
                                + " Southwall must match Northwall of " + getLineInfo(nb));
                    }
                    nb = getCell(x, y + 1);
                    if (nb != null && cell.getEastwall() != nb.getWestwall()) {
                        throw new IllegalArgumentException("Invalid mazefile " + getLineInfo(cell)
                                + " Eastwall must match Westwall of " + getLineInfo(nb));
                    }
                    nb = getCell(x, y - 1);
                    if (nb != null && cell.getWestwall() != nb.getEastwall()) {
                        throw new IllegalArgumentException("Invalid mazefile " + getLineInfo(cell)
                                + " Westwall must match Eastwall of " + getLineInfo(nb));
                    }
                }
            }
        }
    }

    /* Maze must have exactly one start and one end */
    private void validateStartEnd(Content SE) {
        List<Cell> listSE = new ArrayList<Cell>();
        Cell cellSE = null;
        for (int x = 0; x < cellList.size(); x++) {
            List<Cell> cellRow = cellList.get(x);
            for (int y = 0; y < cellRow.size(); y++) {
                Cell cell = cellRow.get(y);
                if (cell.getContent().equals(SE)) {
                    listSE.add(cell);
                }
            }
        }
        if (listSE.size() == 0)
            throw new IllegalArgumentException(getLineInfo(cellSE) + "Maze input file has no " + SE.toString());
        else if (listSE.size() > 1)
            throw new IllegalArgumentException("Maze input file must have exactly one " + SE.toString()
                    + ". Error detected at " + getLineInfo(listSE.get(0)) + "; " + getLineInfo(listSE.get(1)) + "...");
    }

    public List<List<Cell>> getCellList() {
        return cellList;
    }

    public Cell getCell(int x, int y) {
        try {
            Cell cell = cellList.get(x).get(y);
            return cell;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public IntPair getStartPosition() {
        IntPair p = new IntPair();
        for (int x = 0; x < cellList.size(); x++) {
            List<Cell> cellRow = cellList.get(x);
            for (int y = 0; y < cellRow.size(); y++) {
                Cell cell = cellRow.get(y);
                if (cell.getContent().equals(Content.START)) {
                    p.xpos = x;
                    p.ypos = y;
                    break;
                }
            }
        }
        return p;
    }

    public int getMazeSize() {
        return mazeSize;
    }

    public void printMaze() {
        for (int x = 0; x < cellList.size(); x++) {
            List<Cell> cellRow = cellList.get(x);
            if (x == 0) {
                for (int y = 0; y < cellRow.size(); y++) {
                    Cell cell = cellRow.get(y);
                    printNSwall(cell.getNorthwall());
                }
                System.out.println("+");
            }
            for (int y = 0; y < cellRow.size(); y++) {
                Cell cell = cellRow.get(y);
                if (y == 0)
                    printEWwall(cell.getWestwall());
                printContent(cell.getContent());
                printEWwall(cell.getEastwall());
            }
            System.out.println();
            for (int y = 0; y < cellRow.size(); y++) {
                Cell cell = cellRow.get(y);
                printNSwall(cell.getSouthwall());
            }
            System.out.println("+");
        }
    }

    private void printNSwall(Wall wall) {
        switch (wall) {
        case WALL:
            System.out.print("+---");
            break;
        case NO_WALL:
            System.out.print("+   ");
            break;
        case DOOR:
            System.out.print("+-d-");
            break;
        case BREAKABLE:
            System.out.print("+-b-");
            break;
        case FAKE:
            System.out.print("+---");
            break;
        default:
            throw new RuntimeException("Unrecognized Wall type " + wall);
        }
    }

    private void printEWwall(Wall wall) {
        switch (wall) {
        case WALL:
            System.out.print("|");
            break;
        case NO_WALL:
            System.out.print(" ");
            break;
        case DOOR:
            System.out.print("d");
            break;
        case BREAKABLE:
            System.out.print("b");
            break;
        case FAKE:
            System.out.print("|");
            break;
        default:
            throw new RuntimeException("Unrecognized Wall type " + wall);
        }
    }

    private void printContent(Content content) {
        switch (content) {
        case START:
            System.out.print(Start + " ");
            break;
        case END:
            System.out.print(" " + End);
            break;
        case EMPTY:
            System.out.print("   ");
            break;
        case KEY:
            System.out.print("   ");
            break;
        case HAMMER:
            System.out.print("   ");
            break;
        case TROPHY:
            System.out.print("   ");
            break;
        case PLAYER:
            System.out.print(Player + " ");
            break;
        case VISIT:
            System.out.print(Visit + " ");
            break;
        default:
            throw new RuntimeException("Unrecognized Content type " + content);
        }
    }

    /* Line information of original input file in case of error detected */
    public String getLineInfo(Cell cell) {
        String lineInfo = lineInfoMap.get(cell);
        return lineInfo;
    }
}
