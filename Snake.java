import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList; 

public class Snake extends JPanel 
{
    private static ArrayList<Point> snakeCoordinates = new ArrayList<Point>(); 
    public static final int UP = 0; 
    public static final int DOWN = 1; 
    public static final int RIGHT = 2; 
    public static final int LEFT = 3; 
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30; 
    private static int x; 
    private static int y; 
    private static int dir = 3; 
    private static int preDir = -1; 
    private static int length = 3;
    private static boolean update = false; 
    boolean alive = true; 
    private static int timeDelay = 200; 
    private javax.swing.Timer timer = new Timer(timeDelay, new SnakeListener()); 
    
    public Snake(){}  

    public void newSnake() 
    {
        timer.start(); 
        snakeCoordinates.add(new Point(450, 450)); 
        snakeCoordinates.add(new Point(480, 450)); 
        snakeCoordinates.add(new Point(510, 450)); 
    }

    public void drawMe (Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g; 
        setTimeDelay(getTimeDelay()); 
        int i = 0; 
        for (Point location: snakeCoordinates)
        {
            Color darkGreen = new Color(0,102,0); 
            g2.setColor(darkGreen); 
            g2.fillRect((int)snakeCoordinates.get(i).getX(), (int)snakeCoordinates.get(i).getY(), WIDTH, HEIGHT);
            i++; 
        }
    }

    //setters
    public void setDirection(int dir) {this.dir = dir;}

    public void setPreviousDirection(int preDir) {this.preDir = preDir;}

    public void setUpdate(boolean update) {this.update = update;}

    public void setTimeDelay(int timeDelay)
    {
        this.timeDelay = timeDelay; 
        timer.setDelay(timeDelay); 
    }

    //getters
    public int getDirection() {return dir;}

    public int getPreviousDirection() {return preDir;}

    public int getTimeDelay() {return timeDelay;}

    public int getLength() {return length;}

    public int getHeadX() {return (int)snakeCoordinates.get(0).getX();}

    public int getHeadY() {return (int)snakeCoordinates.get(0).getY();}

    public ArrayList<Point>getPoints() {return snakeCoordinates;}

    public Point getPoint(int index) {return snakeCoordinates.get(index);}

    public boolean isAlive() {return alive;}

    
    
    private class SnakeListener implements ActionListener
    {
        /**
         * actionPerformed enables the snake to move properly and grow 
         */
        public void actionPerformed(ActionEvent e)
        {
            Point headSnake = snakeCoordinates.get(0); 

            x = (int) headSnake.getX();  y = (int) headSnake.getY();

            //excepting the first block -- the snake's head -- all of the blocks have the locations
            //  shifted to the location of the block in front of them 
            for (int i = (snakeCoordinates.size()-1); i>0;  i -= 1)
            {
                snakeCoordinates.set(i, snakeCoordinates.get(i-1));   
            }
            
            //if the snake has eaten an apple, make the snake grow 
            if (update)
            {
                grow(); 
            } 
            
            //shifts the location of the snake head, depending on which direction 
            //  the snake is now in 
            if (dir == 0 ) 
                y -= HEIGHT;
            else if (dir == 1)
                y += HEIGHT; 
            else if (dir == 2) 
                x += WIDTH;
            else if (dir == 3) 
                x -= WIDTH; 

            snakeCoordinates.set(0, new Point(x,y));
            
            //checks to make sure the snake has not yet died
            didDie(); 
        }
    }

    public boolean didDie()
    {
        for (int i = 2; i<snakeCoordinates.size(); i++)
        {
            if (getPoint(0).equals(getPoint(i)))
                alive = false; 

        }
        return false; 
    }

    /**
     * This method adds one more block to the snake's body. It temporarily houses the new block in the same 
     * location as the snake's previous 'tail', now the second-to-last block. During the next iteration of actionPerformed(), the for loop will 
     * shift all of the blocks (except for its head) to the location of the block right before them. As the location of the second-to-last block is 
     * equal to the location of the last block, the last block's location will not change. 
     */
    public void grow()
    {
        snakeCoordinates.add(snakeCoordinates.get(snakeCoordinates.size()-1));    
        //System.out.println("NewTail = " + getPoint(snakeCoordinates.size()-1));      
        update = false;  
    }

    public void stopTimer()
    {
        timer.stop(); 
    }
}
