import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Maze class, takes in a maze file, makes a 2D array out of it
 */
public class Maze {
    public int totalRow, totalCol, rowStart, colStart, rowEnd, colEnd;
    private char [][] maze;

    /**
     * Maze Constructor
     * Reads a maze file, sets maze variables and maze values in the array
     * @param filename
     */
    public Maze(File filename) {
        try {
            Scanner infile = new Scanner(filename);

            totalRow = infile.nextInt();
            totalCol = infile.nextInt();
            rowStart = infile.nextInt();
            colStart = infile.nextInt();
            rowEnd = infile.nextInt();
            colEnd = infile.nextInt();
            infile.nextLine();

            maze = new char[totalRow][totalCol];

            String thisLine = "";

            for (int rowCounter = 0; rowCounter < totalRow; rowCounter++) {
                if (infile.hasNextLine()) {
                    thisLine = infile.nextLine();
                }
                for (int colCounter = 0; colCounter < totalCol; colCounter++) {
                    maze[rowCounter][colCounter] = thisLine.charAt(colCounter);
                }
            }
        }
        catch (FileNotFoundException e){
        e.printStackTrace();
        }
    }

    /**
     * returns total number of rows in the maze array
     * @return totalRow
     */
    public int getRows(){return totalRow;}

    /**
     * returns total number of columns in the maze array
     * @return totalCol
     */
    public int getCols(){return totalCol;}

    /**
     * returns the row of the starting space in the maze array
     * @return rowStart
     */
    protected int getStartRow(){return rowStart;}

    /**
     * returns the column of the starting space in the maze array
     * @return colStart
     */
    protected int getStartCol(){return colStart;}

    /**
     * returns the row of the ending space in the maze array
     * @return rowEnd
     */
    protected int getEndRow(){return rowEnd;}

    /**
     * returns the column of the ending space in the maze array
     * @return colEnd
     */
    protected int getEndCol(){return colEnd;}


    /**
     * returns what value is in a specified cell in the maze array
     * @param row
     * @param col
     * @return cellValue
     */
    protected char getCell(int row, int col){
        char cellValue = maze[row][col];
        return cellValue;
    }

    /**
     * determines if the specified cell is an open space in the maze
     * @param row
     * @param col
     * @return boolean - open if true, closed if false
     */
    public boolean openCell(int row, int col){
        boolean open = false;
        if(0 <= row && row < totalRow && 0 <= col && col < totalCol){
            if(maze [row][col] == ' ' || maze [row][col] == 'S' || maze [row][col] == 'X' || maze [row][col] == 'R' ){
                open = true;
            }
        }
        return open;
    }

    /**
     * sets a new value to a specified cell in the maze array
     * @param row
     * @param col
     * @param inputChar
     */
    protected void setCell(int row, int col, char inputChar){
        maze[row][col] = inputChar;
    }

    /**
     * returns the maze as a string
     * @return mazeDisplay
     */
    public String toString(){
        String mazeDisplay = "";
        for (int rowCounter = 0; rowCounter < totalRow; rowCounter++) {
            mazeDisplay = (mazeDisplay + "\n");
            for (int colCounter = 0; colCounter < totalCol; colCounter++) {
                mazeDisplay += maze[rowCounter][colCounter];
            }
        }
        return mazeDisplay;
    }

}
