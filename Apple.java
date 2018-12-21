import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.Arrays; 
import java.util.ArrayList; 
import java.lang.Object; 
import java.util.Random; 

public class Apple extends JPanel 
{ 
    private static ArrayList<Point> appleCoordinates = new ArrayList<Point>();
    private static int x = 0; 
    private static int y = 0; 
    private static int WIDTH = 30; 
    private static int HEIGHT = 30; 
    private static Random randGen = new Random(); 
    private static boolean newApple = false;
    public Apple()
    {
        appleCoordinates.add(new Point(randGen.nextInt(870/30)*30, randGen.nextInt(870/30)*30)); 
    }

    public void drawMe(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g; 
        
        //if an apple has not been eaten, draw the apple in the location it was before
        if (!newApple)
        {
            g2.setColor(Color.red); 
            g2.fillRect((int)appleCoordinates.get(0).getX(),(int)appleCoordinates.get(0).getY(),WIDTH,HEIGHT);  
        }
        
        //if the apple has been eaten, draw a new apple in a random location 
        else 
        {
            appleCoordinates.add(new Point(randGen.nextInt(870/30)*30, randGen.nextInt(870/30)*30)); //take into account that the 'apple' is 30 x 30
            appleCoordinates.remove(0);   
            newApple = false; 
        }
    }
    
    public void vanishApple()
    {
        newApple = true; 
    }
    
    //setters
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
 
    //getters
    public int getX() {return x;}
    public int getY() {return y;}
    public Point getPoint() {return appleCoordinates.get(0);}
    
}
