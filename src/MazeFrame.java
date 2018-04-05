import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Assignment: Homework4
 * Author: Jared Zwiener
 * Worked with: Devon Favinger and Hunter Smith
 *
 * Homework Questions
 * 1. A
 * 2. B
 * 3. B
 * 4. A
 * 5. B
 * 6. B
 * 7. D
 * 8. B
 * 9. A
 * 10. B
 * 11. fix the error - added the list to the scrollPane wrong -->
 *
 *      //Create a JList and add it to a scroll pane.
        //Assume the array already exists.
        JList list = new JList(array);
        JScrollPane scrollPane = new JScrollPane(list);
 *
 * class which makes a JFrame Window
 * displays robot traversing a maze
 */
public class MazeFrame extends JFrame {
    private File infile;
    private Maze maze;
    private Robot bot;
    private Boolean stillWorking;
    private Boolean inMaze;
    private MazePanel mazePanel;
    int frameWidth = 850, frameLength = 700;
    int botWidth = 15, botHeight = 15;

    /**
     * creates a JFrame with a menu bar with options to select a maze file, select the type of robot, and
     * solve the maze
     * @param inbot
     * @param inMaze
     * @param file
     * @param stillRunning
     */
    public MazeFrame(Robot inbot, Maze inMaze, File file, Boolean stillRunning){
        this.stillWorking = stillRunning;
        this.infile = file;
        this.maze = inMaze;
        this.bot = inbot;
        this.mazePanel = new MazePanel(bot, maze);
        this.add(mazePanel, BorderLayout.CENTER);

        this.setTitle("Maze Window");
        this.setSize(frameWidth,frameLength);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu mazeMenu = new JMenu("Maze");
        JMenu robotMenu = new JMenu("Robot");
        menuBar.add(fileMenu);
        menuBar.add(mazeMenu);
        menuBar.add(robotMenu);

        JMenuItem solveAction = new JMenuItem("Solve");
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem loadFileAction = new JMenuItem("Load File");
        JMenuItem rightHandRobotAction = new JMenuItem("Right Hand Robot");
        JMenuItem lookAheadRobotAction = new JMenuItem("Look Ahead Robot");
        JMenuItem randomRobotAction = new JMenuItem("Random Robot");

        fileMenu.add(solveAction);
        fileMenu.add(exitAction);
        mazeMenu.add(loadFileAction);
        robotMenu.add(rightHandRobotAction);
        robotMenu.add(lookAheadRobotAction);
        robotMenu.add(randomRobotAction);

        //solveAction.setEnabled(false);
        //rightHandRobotAction.setEnabled(false);
        //randomRobotAction.setEnabled(false);
        //lookAheadRobotAction.setEnabled(false);


        solveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stillWorking = true;
                run();
                stillWorking = false;
            }
        });
        exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        loadFileAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infile = MazeFrame.getFile();
                maze = new Maze(infile);
                mazePanel.setMaze(maze);
                mazePanel.setSize();
                mazePanel.repaint();
                frameWidth = maze.getCols()*45;
                frameLength = maze.getRows()*50;
                setSize(frameWidth, frameLength);
                rightHandRobotAction.setEnabled(true);
                lookAheadRobotAction.setEnabled(true);
                randomRobotAction.setEnabled(true);

            }
        });
        rightHandRobotAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bot = new RightHandRobot(maze);
                mazePanel.setBot(bot);
                mazePanel.repaint();
                solveAction.setEnabled(true);
            }
        });
        lookAheadRobotAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bot = new LookAheadRobot(maze);
                mazePanel.setBot(bot);
                mazePanel.repaint();
                solveAction.setEnabled(true);
            }
        });
        randomRobotAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bot = new RandomRobot(maze);
                mazePanel.setBot(bot);
                mazePanel.repaint();
                solveAction.setEnabled(true);
            }
        });
    }

    /**
     * main method
     * establishes an initial maze file, maze, and robot
     * creates a new MazeFrame with the initialized variables
     * @param args
     */
    public static void main(String[] args) {

        File inputFile = new File("biggermaze.txt");  //sample: testmaze.txt
        Maze maze = new Maze(inputFile);
        Robot bot = new RightHandRobot(maze);

        MazeFrame ew = new MazeFrame(bot, maze, inputFile, false);
    }

    /**
     * resets the maze to the current maze file
     * repaints the maze on the GUI
     */
    public void resetMaze(){
        if(stillWorking == true && infile != null){
            maze = new Maze(infile);
            mazePanel.setMaze(maze);
            mazePanel.setSize();
            mazePanel.repaint();
        }

    }

    /**
     * Get the file that has the maze specifications.
     * @return File chosen by user.
     */
    public static File getFile()
    {
        JFileChooser chooser;
        try{

            // Get the filename.
            chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status != JFileChooser.APPROVE_OPTION)
            {
                System.out.println("No File Chosen");
                System.exit(0);
            }
            return chooser.getSelectedFile();
        } catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
            System.exit(0);

        }
        return null; //should never get here, but makes compiler happy
    }

    /**
     *  method that moves the robot through the maze
     *  paints the JFrame accordingly as the robot moves
     */
    public void run() {
        stillWorking = true;
        try {
            for (int k = 0; k < 1000000 && !bot.solved(); k++)
            //this limits the robot's moves, in case it takes too long to find the exit.
            {
                Thread.sleep(500);
                int direction = bot.chooseMoveDirection();
                if (direction >= 0)  //invalid direction is -1
                {
                    bot.move(direction);
                    mazePanel.paintImmediately(bot.getCurrentCol(), bot.getCurrentRow(), frameWidth, frameLength);
                }
                Thread.sleep(100);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("This shouldn't happen.");
        }
    }
}
