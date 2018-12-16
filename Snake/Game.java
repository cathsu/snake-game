import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
/**
 * Write a description of class Root here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game
{
    public static void main(String[] args)
    {
        Game game = new Game();
        //Board board = new Board();
    }
    
    
    /**
     * Constructor for objects of class Root
     */
    
    public Game()
    {
        Board board = new Board();
        JFrame frame = new JFrame("Snake Game"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new BorderLayout()); 
        frame.add(board, BorderLayout.CENTER);
        
        frame.add(new Button(), BorderLayout.PAGE_END);
        frame.pack();
        frame.setVisible(true);
        
        board.startGame();
    }
}
