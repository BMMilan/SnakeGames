package snake;

import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Snake implements ActionListener, KeyListener{

    public JFrame jframe;
    public RenderPanel renderPanel;
    public static Snake snake;
    public Timer timer = new Timer(20, this);
    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    public static final int up = 0, down = 1, left = 2, right = 3, scale = 10;
    public int ticks = 0, direction  = down, score, tailLenght = 10, time;
    public Point head, cherry;
    public Random random;
    public boolean over = false, paused;
    public Dimension dim;

    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(805, 700);
        jframe.setResizable(false);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height 
                / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel = new RenderPanel());        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
        
    }
    public void startGame()
    {
        over = false;
        paused = false;
        direction = down;
        score = 0;
        time = 0;
        ticks = 0;
        tailLenght = 5;
        head = new Point(0, 0);
        snakeParts.clear();
        random = new Random();
        cherry = new Point(random.nextInt(79), random.nextInt(66));
        timer.start();   
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        renderPanel.repaint();
        ticks++;
        
        if(ticks %5 == 0 && head != null && !over && !paused){
            time++;
            snakeParts.add(new Point(head.x, head.y));            
            if(direction == up){
                if(head.y - 1 >= 0 && noTailAt(head.x,head.y - 1)){
                    head = new Point(head.x, head.y - 1);
                }
                else 
                    over = true;   
            }
            if(direction == down){
                if(head.y +1 < 67 && noTailAt(head.x,head.y + 1)){
               head = new Point(head.x, head.y + 1);
                }
                else 
                    over = true;
            }
            if(direction == left){
                if(head.x -1 >= 0 && noTailAt(head.x - 1,head.y)){
               head = new Point(head.x - 1, head.y);
                }
                else 
                    over = true;
            }
            if(direction == right){
                if(head.x +1 < 80 && noTailAt(head.x + 1,head.y)){
               head = new Point(head.x + 1, head.y);
                }
                else 
                    over = true;
            }
            if(snakeParts.size() > tailLenght)
            {
                snakeParts.remove(0);     
            } 
            if(cherry != null){
                if(head.equals(cherry)){
                    score+= 10;
                    tailLenght++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                }
                
            }
        }
    }

    public static void main(String[] args) {
        snake = new Snake();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(i == KeyEvent.VK_A && direction != right){
            direction = left;
        }
        if(i == KeyEvent.VK_D && direction != left){
            direction = right;
        }
        if(i == KeyEvent.VK_W && direction != down){
            direction = up;
        }
        if(i == KeyEvent.VK_S && direction != up){
            direction = down;
        }
        if(i == KeyEvent.VK_SPACE){
            if(over)
                startGame();
            else 
                paused = !paused;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    public boolean noTailAt(int x, int y) {
        for(Point point : snakeParts)
        {
            if(point.equals(new Point(x, y)))
                return false;
        }
        return true;
    }

}

