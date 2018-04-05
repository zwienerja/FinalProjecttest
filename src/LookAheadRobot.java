import java.util.Random;

public class LookAheadRobot extends FacingRobot {
    /**
     * Right hand robot constructor
     * @param maze
     */
    public LookAheadRobot(Maze maze) { super(maze); }

    /**
     * Chooses the direction to move randomly, keeps track of direction robot is facing
     * @return MoveDirection
     */
    public int chooseMoveDirection(){
        int x;
        Random rannum = new Random();
        x = rannum.nextInt(4);
        setFaceDirection(x);
        return getFaceDirection();
    }

    /**
     * Method to move robot in the maze based on the current direction
     * makes sure the cell to move to is open
     * sets the new current cell the robot occupies
     * fills dead ends with '#' characters
     * @param direction
     * @return inMaze - boolean, whether cell to move to is open or not
     */
    public boolean move(int direction){
        boolean inMaze = false;
        boolean done = false;
        switch (direction){
            case 0:
                if (mazeCopy.openCell(getCurrentRow()+1, getCurrentCol())){
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), ' ');
                    setCurrentRow(getCurrentRow() + 1);
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    inMaze = true;
                }
                else
                    done = true;
                break;
            case 1:
                if (mazeCopy.openCell(getCurrentRow(), getCurrentCol()-1)){
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), ' ');
                    setCurrentCol(getCurrentCol() - 1);
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    inMaze = true;
                }
                else
                    done = true;
                break;
            case 2:
                if (mazeCopy.openCell(getCurrentRow()-1, getCurrentCol())){
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), ' ');
                    setCurrentRow(getCurrentRow() - 1);
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    inMaze = true;
                }
                else
                    done = true;
                break;
            case 3:
                if (mazeCopy.openCell(getCurrentRow(), getCurrentCol() + 1)){
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), ' ');
                    setCurrentCol(getCurrentCol() + 1);
                    mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    inMaze = true;
                }
                else
                    done = true;
                break;
        }
        if (!done)
            move(direction);
        if (done){
            switch (direction){
                case 0:
                    if (!mazeCopy.openCell(getCurrentRow()+1, getCurrentCol()) && !mazeCopy.openCell(getCurrentRow(), getCurrentCol()-1)
                            && !mazeCopy.openCell(getCurrentRow(), getCurrentCol()+1) && !super.solved()){
                        mazeCopy.setCell(getCurrentRow(), getCurrentCol(), '#');
                        setCurrentRow(getCurrentRow()-1);
                        mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    }
                case 1:
                    if (!mazeCopy.openCell(getCurrentRow(), getCurrentCol()-1) && !mazeCopy.openCell(getCurrentRow()-1, getCurrentCol())
                            && !mazeCopy.openCell(getCurrentRow()+1, getCurrentCol()) && !super.solved()){
                        mazeCopy.setCell(getCurrentRow(), getCurrentCol(), '#');
                        setCurrentCol(getCurrentCol()+1);
                        mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    }
                case 2:
                    if (!mazeCopy.openCell(getCurrentRow()-1, getCurrentCol()) && !mazeCopy.openCell(getCurrentRow(), getCurrentCol()+1)
                            && !mazeCopy.openCell(getCurrentRow(), getCurrentCol()-1) && !super.solved()){
                        mazeCopy.setCell(getCurrentRow(), getCurrentCol(), '#');
                        setCurrentRow(getCurrentRow()+1);
                        mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    }
                case 3:
                    if (!mazeCopy.openCell(getCurrentRow(), getCurrentCol()+1) && !mazeCopy.openCell(getCurrentRow()+1, getCurrentCol())
                            && !mazeCopy.openCell(getCurrentRow()-1, getCurrentCol()) && !super.solved()){
                        mazeCopy.setCell(getCurrentRow(), getCurrentCol(), '#');
                        setCurrentCol(getCurrentCol()-1);
                        mazeCopy.setCell(getCurrentRow(), getCurrentCol(), getCurrentName());
                    }
            }
        }
        return inMaze;
    }
}
