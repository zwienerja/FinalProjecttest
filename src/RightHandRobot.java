/**
 * Right hand robot class
 * chooses move direction by keeping 'right hand' on right wall
 */
public class RightHandRobot extends FacingRobot {

    /**
     * Right hand robot constructor
     * @param maze
     */
    public RightHandRobot(Maze maze) { super(maze); }

    /**
     * chooses direction to move
     * turns right when it can, then moves forward if it can, then turns left if it can, then turns around if all else fails
     * @return currentDirection to move
     */
    public int chooseMoveDirection(){
        switch (getFaceDirection()) {
            case 0:
                if (!mazeCopy.openCell(getCurrentRow(), getCurrentCol() - 1) && mazeCopy.openCell(getCurrentRow() + 1, getCurrentCol())) {
                        return 0;
                }
                else if (mazeCopy.openCell(getCurrentRow(), getCurrentCol() - 1)) {
                    setFaceDirection(1);
                    return 1;
                }
                else if (mazeCopy.openCell(getCurrentRow(), getCurrentCol() + 1)) {
                    setFaceDirection(3);
                    return 3;
                }
                else {
                    setFaceDirection(2);
                    return 2;
                }
            case 1:
                if (!mazeCopy.openCell(getCurrentRow()-1, getCurrentCol()) && mazeCopy.openCell(getCurrentRow(), getCurrentCol()-1)) {
                        return 1;
                }
                else if (mazeCopy.openCell(getCurrentRow()-1,getCurrentCol())){
                    setFaceDirection(2);
                    return  2;
                }
                else if (mazeCopy.openCell(getCurrentRow()+1,getCurrentCol())){
                    setFaceDirection(0);
                    return 0;
                }
                else{
                    setFaceDirection(3);
                    return 3;
                }
            case 2:
                if (!mazeCopy.openCell(getCurrentRow(), getCurrentCol()+1) && mazeCopy.openCell(getCurrentRow()-1, getCurrentCol())) {
                        return 2;
                }
                else if (mazeCopy.openCell(getCurrentRow(),getCurrentCol()+1)){
                    setFaceDirection(3);
                    return  3;
                }
                else if (mazeCopy.openCell(getCurrentRow(),getCurrentCol()-1)){
                    setFaceDirection(1);
                    return 1;
                }
                else{
                    setFaceDirection(0);
                    return 0;
                }
            case 3:
                if (!mazeCopy.openCell(getCurrentRow()+1, getCurrentCol()) && mazeCopy.openCell(getCurrentRow(), getCurrentCol()+1)) {
                        return 3;
                    }
                else if (mazeCopy.openCell(getCurrentRow()+1,getCurrentCol())){
                    setFaceDirection(0);
                    return  0;
                }
                else if (mazeCopy.openCell(getCurrentRow()-1,getCurrentCol())){
                    setFaceDirection(2);
                    return 2;
                }
                else{
                    setFaceDirection(1);
                    return 1;
                }
        }
        return getFaceDirection();
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
