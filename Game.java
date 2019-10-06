import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

public class Game
{
    public static void main(String[] args)
    {
        Game game = new Game();
    }
    
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
        
        board.gameRun();
    }
}
