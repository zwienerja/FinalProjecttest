/**
 * abstract class facing robot, inherits from robot
 * keeps track of the direction the robot is facing
 */
public abstract class FacingRobot extends Robot {

    private int faceDirection = 0;

    /**
     * facingRobot constructor
     * @param maze
     */
    public FacingRobot(Maze maze){
        super(maze);
        faceDirection = 0;
    }

    /**
     * returns the direction the robot is facing
     * @return faceDirection
     */
    protected int getFaceDirection() {return faceDirection;}

    /**
     * sets the direction the robot is facing
     * @param direction
     */
    protected void setFaceDirection(int direction) {
        if (direction <= 3 && direction >= 0){
            faceDirection = direction;
        }
    }
}
