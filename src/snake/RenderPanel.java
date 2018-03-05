package snake;

import java.awt.*;
import javax.swing.*;

public class RenderPanel extends JPanel 
{
    public static Color blue = new Color(166073);
   @Override
   protected void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       g.setColor(blue);
       g.fillRect(0, 0, 800, 700);
       Snake snake = Snake.snake;
       g.setColor(Color.orange);
       for(Point point: snake.snakeParts){
           g.fillRect(point.x * Snake.scale, point.y * Snake.scale, 
                   Snake.scale, Snake.scale);   
       }
       g.fillRect(snake.head.x * Snake.scale, snake.head.y * Snake.scale,
               Snake.scale, Snake.scale);
       g.setColor(Color.red);
       g.fillRect(snake.cherry.x * Snake.scale, snake.cherry.y * Snake.scale,
               Snake.scale, Snake.scale);
       String string = "Score: "+snake.score+" Lenght: "+snake.tailLenght+" Time: "+snake.time/ 2 / 20;
       g.setColor(Color.white);
       g.drawString(string, (int) getWidth() / 2 - string.length() * 2, 10);
       
       
      
     
   }
}
