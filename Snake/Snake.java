import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.Arrays; 
import java.util.ArrayList; 
import javax.swing.JOptionPane; 
import java.util.concurrent.TimeUnit; 
/*
 * Write a description of class Rectangle here.
 * 
 * @author (your name) 
 */

public class Snake extends JPanel //implements KeyListener
{
    //int x = 100, y = 100, width = 20, height = 20;

    private static ArrayList<Point> snakeCoordinates = new ArrayList<Point>(); 
    public static final int UP = 0; 
    public static final int DOWN = 1; 
    public static final int RIGHT = 2; 
    public static final int LEFT = 3; 
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30; 
    private static int x; //= 0; 
    private static int y; //= 0; 
    private static int dir = 3; 
    private static int preDir = -1; 
    private static int length = 3;
    private static boolean update = false; 
    boolean alive = true; 
    private static int timeDelay = 200; 
    private javax.swing.Timer timer = new Timer(timeDelay, new SnakeListener()); 
    private static int speedFactor = 1; 
    

    public Snake()
    {
        //timer.start(); 
        //snakeCoordinates.add(new Point(450, 450)); 
        //snakeCoordinates.add(new Point(480, 450)); 
        //snakeCoordinates.add(new Point(510, 450)); 
    }  

    public void newSnake() 
    {
        timer.start(); 
        snakeCoordinates.add(new Point(450, 450)); 
        snakeCoordinates.add(new Point(480, 450)); 
        snakeCoordinates.add(new Point(510, 450)); 
    }

    public void drawMe (Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g; //cast Graphics g as a Graphics2D
        setTimeDelay(getTimeDelay()); 
        int i = 0; 

        for (Point location: snakeCoordinates)
        {
            Color darkGreen = new Color(0,102,0); 
            g2.setColor(darkGreen); 
            g2.fillRect((int)snakeCoordinates.get(i).getX(), (int)snakeCoordinates.get(i).getY(), WIDTH, HEIGHT);
            //g2.setColor(Color.black); 
            //g2.drawRect((int)snakeCoordinates.get(i).getX(), (int)snakeCoordinates.get(i).getY(), WIDTH, HEIGHT);
            //System.out.println(" x = " + (int) snakeCoordinates.get(i).getX() + " y = " + (int) snakeCoordinates.get(i).getY()); 
            i++; 
        }
    }

    //setter
    public void setDirection(int dir) {this.dir = dir;}

    public void setPreviousDirection(int preDir) {this.preDir = preDir;}

    public void setUpdate(boolean update) {this.update = update;}

    public void setTimeDelay(int timeDelay)
    {
        this.timeDelay = timeDelay; 
        timer.setDelay(timeDelay); 
    }

    //getter
    public int getDirection() {return dir;}

    public int getPreviousDirection() {return preDir;}

    public int getTimeDelay() {return timeDelay;}

    public int getLength() {return length;}

    public int getHeadX() {return (int)snakeCoordinates.get(0).getX();}

    public int getHeadY() {return (int)snakeCoordinates.get(0).getY();}

    public ArrayList<Point>getPoints() {return snakeCoordinates;}

    public Point getPoint(int index) {return snakeCoordinates.get(index);}

    public boolean isAlive() {return alive;}

    
    //public Dimension size() {return snakeCoordinates.size;}
    private class SnakeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Point headSnake = snakeCoordinates.get(0); 

            x = (int) headSnake.getX();  y = (int) headSnake.getY();

            /*
            if (update)
            {
            snakeCoordinates.add(snakeCoordinates.get(snakeCoordinates.size()-1)); 
            } 
             */
            

            //shifts all but the snake's head by one 
            for (int i = (snakeCoordinates.size()-1); i>0;  i -= 1)
            {
                snakeCoordinates.set(i, snakeCoordinates.get(i-1));   
            }
            if (update)
            {
                grow(); 
            } 
            //System.out.println("dir = " + dir + " preDir = " + preDir); 

            if (dir == 0 ) 
                y -= HEIGHT;
            else if (dir == 1)
                y += HEIGHT; 
            else if (dir == 2) 
                x += WIDTH;
            else if (dir == 3) 
                x -= WIDTH; 

            snakeCoordinates.set(0, new Point(x,y));

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
     * This method adds one block to the snake's body. It temporarily houses the new block in the same 
     * location as the snake's 'tail'. During the next iteration of actionPerformed(), the for loop will 
     * shift all locations by one, and the new block (now the 'tail') will be in the location as  the snake's 
     * previous tail. 
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
