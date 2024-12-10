package ressources;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Ressource {

    public BufferedImage image;
    public String name;
    private int quantity;
    public boolean collision = false;
    public Point pos = new Point();
    public int prix;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, pos.x, pos.y, null);

    }
}
