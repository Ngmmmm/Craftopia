package item.ressources;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

public abstract class Ressource {

    public BufferedImage image;
    public String name;
    private int quantity;
    public boolean collision = false;
    public Point pos = new Point();
    public int prix;
    public int time;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

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
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;// Augmente la quantité de l'item par la valeur de quantity
    }

    /**
     * Réduit la quantité possédée d'un item
     * 
     * @param item     L'item auquel on applique la méthode
     * @param quantity La valeur par laquelle réduire la quantité de l'item
     */
    public void reduceQuantity(int quantity) {
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("Nombre de ressource insufisant.");
        }
        this.quantity -= quantity;// Réduit la quantité de l'item par la valeur de quantity
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        // Coordonnées de la bloc à l'écran
        int screenX = pos.x - gp.avatar.mapPoint.x + gp.avatar.screenX;
        int screenY = pos.y - gp.avatar.mapPoint.y + gp.avatar.screenY;

        // Vérifie si les coordonnées sont dans la zone visible
        if (pos.x + gp.TAILLE_BLOC > gp.avatar.mapPoint.x - gp.avatar.screenX
                && pos.x - gp.TAILLE_BLOC < gp.avatar.mapPoint.x + gp.avatar.screenX
                && pos.y + gp.TAILLE_BLOC > gp.avatar.mapPoint.y - gp.avatar.screenY
                && pos.y - gp.TAILLE_BLOC < gp.avatar.mapPoint.y + gp.avatar.screenY) {
            g2.drawImage(image, screenX, screenY, gp.TAILLE_BLOC, gp.TAILLE_BLOC, null);
        }
    }
}
