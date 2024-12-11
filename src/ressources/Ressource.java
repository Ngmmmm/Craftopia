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

    /**
     * Augmente la quantité possédée d'un item
     * 
     * @param item     L'item auquel on applique la méthode
     * @param quantity La valeur par laquelle augmenter la quantité de l'item
     */
    public void increaseQuantity(Ressource ress, int quantity) {
        ress.setQuantity(ress.getQuantity() + quantity);// Augmente la quantité de l'item par la valeur de quantity
    }

    /**
     * Réduit la quantité possédée d'un item
     * 
     * @param item     L'item auquel on applique la méthode
     * @param quantity La valeur par laquelle réduire la quantité de l'item
     */
    public void reduceQuantity(Ressource ress, int quantity) {
        ress.setQuantity(ress.getQuantity() - quantity);// Réduit la quantité de l'item par la valeur de quantity
    }

    
    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, pos.x, pos.y, null);

    }
}
