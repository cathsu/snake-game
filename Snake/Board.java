import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.Arrays; 
import java.lang.Object; 
import java.util.concurrent.TimeUnit; 
import java.util.ArrayList; 
import java.util.Random; 

public class Board extends JPanel 
{
    Snake snake = new Snake(); 
    Apple apple = new Apple(); 
    //Button buttons = new Button();
    int width; 
    int height; 
    boolean runInto = false; 
    JFrame frame; 
    public Board() //default constructor 
    {

    }

    public void paintComponent(Graphics g) //will be called automatically by JComponent
    {
        super.paintComponent(g); //call parent class- call when you are not painting entire component  
        Graphics2D g2 = (Graphics2D)g; 
        

        //draw the checkered board 
        for (int row = 0; row<width; row += 30)
            for (int col = 0; col<height; col += 30)
            {
                g2.setColor(Color.black); 
                g2.fillRect(row, col, 30, 30); 
                //g2.fill(grid[row][col]); 
                g2.setColor(Color.white); 
                g2.drawRect(row,col,30,30); 
            } 
        snake.drawMe(g); 
        apple.drawMe(g); 
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(1200,900);
    }
    
    /*
     * getWidth() determines the width of the board based on the window's size
     */
    public int getWidth()
    {
        width = getBounds().width; 
        return width;
    }

    /*
     * getHeight() determines the height of the board based on the window's size
     */
    public int getHeight() 
    {
        height = getBounds().height; 
        return height;
    }

    public void startGame() //start the game 
    {  
        //myTimer = new Timer(TIMER_DELAY, gameTimer);
        //Timer timer = new Timer(3000, new ActionListener()
        snake.newSnake(); 
        this.findKeyEvents(); 
        boolean endGame = false; 
        while (!endGame)
        {

            if (didSnakeEatApple(apple)) //NullPointerException
            {
                apple.vanishApple();                  
            }

            //System.out.println(snake.getPoints());
            repaint(); 

            didSnakeRunIntoWall();
            if (runInto || !snake.isAlive())
            {
                snake.stopTimer(); 
                endGame = true; 

            }    
        }
    }

    public void didSnakeRunIntoWall()
    {
        if (snake.getHeadX() < 0 || snake.getHeadY() < 0 || snake.getHeadX() > width
        || snake.getHeadY() > height)
        {
            runInto = true; 
        }
    }

    //nullPointerException when it was in Snake class 
    public boolean didSnakeEatApple(Apple apple)
    {
        for (int i = 0; i<snake.getLength(); i++)
            if (snake.getPoint(i).equals(apple.getPoint()))
            {
                snake.setUpdate(true); 
                return true;
            }
        return false; 
    }

    /*
     * This method listens for key presses across all components in Swing. 
     * 
     * Note: The snake cannot go UP when it's DOWN, LEFT when it's going RIGHT, etc. 
     */
    public void findKeyEvents() //grab all key events in my application 
    {
        //Listens to all keyboard events
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(new KeyEventDispatcher() { 

                public boolean dispatchKeyEvent(final KeyEvent e) { //dispatches the key events 
                    snake.setPreviousDirection(snake.getDirection()); 
                    if (e.getKeyCode() == KeyEvent.VK_UP && snake.getPreviousDirection() != 1) 
                    {    
                        snake.setDirection(0); 
                        //snake.move(0); 
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getPreviousDirection() != 0)
                    {
                        snake.setDirection(1); 
                        //snake.move(1);   
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getPreviousDirection() != 3)
                    {
                        snake.setDirection(2); 
                        //snake.move(2); 
                    }
                    else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getPreviousDirection() != 2)
                    {
                        snake.setDirection(3); 
                        //snake.move(3); 
                        
                    }
                    
                    
                    return false; //allows the keyboard focus manager to resume normal operations
                }
            });
    }


   
}
