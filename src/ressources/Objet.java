package ressources;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Objet {

    public BufferedImage image;
    String name;
    public boolean collision = false;
    public Point pos = new Point();

    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, pos.x, pos.y,null);



    }
    
}
