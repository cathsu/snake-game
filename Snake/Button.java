import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class Buttons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends JPanel
{
    // instance variables - replace the example below with your own
    private int x;
    Snake snake = new Snake(); 
    int delay; 
    /**
     * Constructor for objects of class Buttons
     */
    public Button()
    {
        
        this.setLayout(new GridBagLayout()); 
        GridBagConstraints c = new GridBagConstraints();
        
        //slow down button 
        JButton slow = new JButton("Slow Down");
        c.fill = GridBagConstraints.BOTH;
        
        //c.weighty = 0.5;
        c.ipady = 30;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5 ;
        c.insets = new Insets(10,10,10,10); 
        this.add(slow, c);
        
        //speed up button 
        JButton speed = new JButton("Speed Up");
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipady = 0;
        
        c.gridx = 1;
        c.gridy = 0;
        this.add(speed, c);
        slow.addActionListener(new slowDownListener()); 
        speed.addActionListener(new speedUpListener()); 
        
    }
    
    private class slowDownListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event)
        {
            delay = snake.getTimeDelay(); 
            delay += 50; 
            snake.setTimeDelay(delay); 
        }
    }
    
    private class speedUpListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event)
        {
            delay = snake.getTimeDelay(); 
            delay -= 50; 
            snake.setTimeDelay(delay); 
        }
    }
    
}
