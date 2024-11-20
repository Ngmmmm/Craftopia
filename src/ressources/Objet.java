package ressources;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Objet {

    public BufferedImage image;
    String name;
    public boolean collision = false;
    public int posX, posY;

    public void draw(Graphics2D g2, GamePanel gp){
        
        g2.drawImage(image, posX, posY,null);



    }
    
}
