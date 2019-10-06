import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

public class Board extends JPanel 
{
    Snake snake = new Snake(); 
    Apple apple = new Apple(); 
    int width; 
    int height; 
    boolean runInto = false; 
    JFrame frame; 
    public Board() {}

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
                g2.setColor(Color.white); 
                g2.drawRect(row,col,30,30); 
            } 
            
        //draw the snake and apple 
        snake.drawMe(g); 
        apple.drawMe(g); 
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(1200,900);
    }
    
    public int getWidth()
    {
        width = getBounds().width; 
        return width;
    }

    public int getHeight() 
    {
        height = getBounds().height; 
        return height;
    }
    
    /*
     * gameRun() is the function that keeps the game going. Only until the snake "dies" by running into itself or the wall 
     * does the function exit and the game come to an end. 
     */
    public void gameRun() 
    {  
        snake.newSnake(); 
        this.findKeyEvents(); 
        boolean endGame = false; 
        while (!endGame)
        {
            if (didSnakeEatApple(apple)) 
            {
                apple.vanishApple();                  
            }
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
     * This method listens for key presses across all components in Swing. It listens to all keyboard events and 
     * grabs all the key events in the application. 
     * Note: The snake cannot go UP when it's DOWN, LEFT when it's going RIGHT, etc. 
     */
    public void findKeyEvents() 
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(new KeyEventDispatcher() { 

                public boolean dispatchKeyEvent(final KeyEvent e) { //dispatches the key events 
                    snake.setPreviousDirection(snake.getDirection()); 
                    if (e.getKeyCode() == KeyEvent.VK_UP && snake.getPreviousDirection() != 1) 
                        snake.setDirection(0); 
                    else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getPreviousDirection() != 0)
                        snake.setDirection(1); 
                    else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getPreviousDirection() != 3)
                        snake.setDirection(2); 
                    else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getPreviousDirection() != 2)
                        snake.setDirection(3); 
                    return false; //allows the keyboard focus manager to resume normal operations
                }
            });
    }
}
