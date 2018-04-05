import java.util.Random;

/**
 * RandomRobot class which extends the Robot class
 */
public class RandomRobot extends FacingRobot {

    /**
     * Random robot constructor
     * @param maze
     */
    public RandomRobot(Maze maze) { super(maze); }

    /**
     * Chooses the direction to move randomly
     * @return MoveDirection
     */
    public int chooseMoveDirection(){
        int x;
        Random rannum = new Random();
        x = rannum.nextInt(4);
        return x;
    }

    /**
     * Method to move robot in the maze based on the current direction
     * makes sure the cell to move to is open
     * sets the new current cell the robot occupies
     * @param direction
     * @return inMaze - boolean, whether cell to move to is open or not
     */
    public boolean move(int direction){
        boolean inMaze = false;
        switch (direction){
            case 0:
                if (mazeCopy.openCell(getCurrentRow()+1, getCurrentCol())){
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), ' ');
                    setCurrentRow(getCurrentRow() + 1);
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    inMaze = true;
                }
                break;
            case 1:
                if (mazeCopy.openCell(getCurrentRow(), getCurrentCol()-1)){
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), ' ');
                    setCurrentCol(getCurrentCol() - 1);
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    inMaze = true;
                }
                break;
            case 2:
                if (mazeCopy.openCell(getCurrentRow()-1, getCurrentCol())){
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), ' ');
                    setCurrentRow(getCurrentRow() - 1);
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    inMaze = true;
                }
                break;
            case 3:
                if (mazeCopy.openCell(getCurrentRow(), getCurrentCol() + 1)){
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), ' ');
                    setCurrentCol(getCurrentCol() + 1);
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    inMaze = true;
                }
                break;
        }
        return inMaze;
    }
}
