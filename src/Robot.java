/**
 * Robot Class - Super
 */
public abstract class Robot {

    private char name = 'R';
    private int currentRow, currentCol;
    protected Maze mazeCopy;

    /**
     * default Robot constructor
     * @param maze
     */
    public Robot(Maze maze){
        setMaze(maze);
    }

    /**
     * sets the maze that the robot will traverse
     * @param maze
     */
    public void setMaze (Maze maze){
        mazeCopy = maze;
        mazeCopy.setCell(maze.getStartRow(), maze.getStartCol(), name);
        currentRow = maze.getStartRow();
        currentCol = maze.getStartCol();
    }

    /**
     * returns a copy of the maze the robot is going to traverse
     * @return mazeCopy
     */
    public Maze getMazeCopy(){ return mazeCopy; }

    /**
     * sets the new current row of the robot
     * @param row
     */
    protected void setCurrentRow(int row){
        currentRow = row;
    }

    /**
     * sets the new current column of the robot
     * @param col
     */
    protected void setCurrentCol(int col){
        currentCol = col;
    }

    /**
     * sets the robot name as a character - example: 'R'
     * @param newName
     */
    public void setRobotName(char newName){
        name = newName;
    }

    /**
     * returns the current row the robot is in
     * @return currentRow
     */
    protected int getCurrentRow(){ return currentRow; }

    /**
     * returns the current column the robot is in
     * @return currentCol
     */
    protected int getCurrentCol(){ return currentCol; }

    /**
     * returns the current character name of the robot
     * @return name
     */
    protected char getCurrentName(){ return name; }

    /**
     * default method for choose the move direction,
     * is defined further in inheritance classes
     * @return direction to move - "0,1,2, or 3" - "South, West, North, or East" respectively
     */
    public abstract int chooseMoveDirection();

    /**
     * default method for moving the robot in the maze,
     * is defined further in inheritance classes
     * @param direction
     * @return boolean - true if the robot moved/can move, false if the robot couldn't move in the specified direction
     */
    public abstract boolean move(int direction);

    /**
     * checks if robot has made it to the finish
     * @return boolean - true if robot made it out of maze
     */
    public boolean solved(){
        boolean done = false;
        if (currentRow == mazeCopy.getEndRow() && currentCol == mazeCopy.getEndCol()){
            done = true;
        }
        return done;
    }
}
