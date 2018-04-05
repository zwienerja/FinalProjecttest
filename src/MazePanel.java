import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

/**
 * creates the MazePanel Class which displays a maze and robot
 */
public class MazePanel extends JPanel {
    private Maze maze;
    private Robot bot;
    int cellWidth = 40, cellLength = 40;
    Random r = new Random();

    /**
     * mazePanel constructor
     * @param inbot
     * @param inMaze
     */
    public MazePanel(Robot inbot, Maze inMaze){
        this.bot = inbot;
        this.maze = inMaze;
        this.setSize(maze.getRows(), maze.getCols());

    }

    /**
     * set the size of the panel depending on the size of the maze
     */
    public void setSize(){
        this.setSize(maze.getCols()*40, maze.getRows()*40);
    }

    /**
     * sets which maze will be travelled thru
     * @param inMaze
     */
    public void setMaze (Maze inMaze){
        maze = inMaze;
    }

    /**
     * sets the robot to traverse the maze
     * @param robot
     */
    public void setBot (Robot robot){
        bot = robot;
    }


    /**
     * paints the maze graphics on the display
     * @param g
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int row = 0; row < maze.totalRow; row++) {
            for (int col = 0; col < maze.totalCol; col++) {
                g.setColor(Color.black);
                g.drawRect((cellWidth*col), (cellLength*row), cellWidth, cellLength);//outline
                if (!maze.openCell(row, col)) {
                    g.setColor(Color.BLUE);
                    g.fillRect((cellWidth*col), (cellLength* row), cellWidth, cellLength);//draw oval
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect((cellWidth*col), (cellLength*row), cellWidth, cellLength);//draw oval
                }
            }
        }
        g.setColor(Color.RED);
        g.fillOval(bot.getCurrentCol()*cellWidth, bot.getCurrentRow()*cellLength, cellWidth, cellLength);
    }
}
