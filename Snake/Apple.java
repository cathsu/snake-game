import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.Arrays; 
import java.util.ArrayList; 
import java.lang.Object; 
import java.util.Random; 
/**
 * Write a description of class Apple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends JPanel 
{ 
    private static ArrayList<Point> appleCoordinates = new ArrayList<Point>();
    private static int x = 0; 
    private static int y = 0; 
    private static int WIDTH = 30; 
    private static int HEIGHT = 30; 
    private static Random randGen = new Random(); 
    private static boolean drawApple = true;
    //Board board = new Board(); 
    public Apple()
    {
        appleCoordinates.add(new Point(randGen.nextInt(870/30)*30, randGen.nextInt(870/30)*30)); 
        //appleCoordinates.add(new Point(board.getCell(randGen.nextInt(870), randGen.nextInt(870))));
    }

    public void drawMe(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g; 
        if (drawApple)
        {
            g2.setColor(Color.red); 
            g2.fillRect((int)appleCoordinates.get(0).getX(),(int)appleCoordinates.get(0).getY(),WIDTH,HEIGHT);  
            //g2.setColor(Color.black); 
            //g2.drawRect((int)appleCoordinates.get(0).getX(),(int)appleCoordinates.get(0).getY(),WIDTH,HEIGHT); 
        }
        
        else 
        {
            appleCoordinates.add(new Point(randGen.nextInt(870/30)*30, randGen.nextInt(870/30)*30)); //take into account that the 'apple' is 30 x 30
            appleCoordinates.remove(0);   
            drawApple = true; 
        }
    }
    
    public void vanishApple()
    {
        drawApple = false; 
    }
    
    
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
 
    public int getX() {return x;}
    public int getY() {return y;}
    
    public Point getPoint() {return appleCoordinates.get(0);}
    
}
