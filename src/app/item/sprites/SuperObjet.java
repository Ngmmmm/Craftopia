
package item.sprites;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

import main.GamePanel;

public class SuperObjet {
    public BufferedImage image;
    public String name;
    public boolean collision = true;
    public Point pos = new Point();
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int quantity;
    public boolean isItem = false;

    public void draw(Graphics2D g2, GamePanel gp, double scaleX, double scaleY) {
        // Coordonnées de la bloc à l'écran
        int screenX = pos.x - gp.avatar.mapPoint.x + gp.avatar.screenX;
        int screenY = pos.y - gp.avatar.mapPoint.y + gp.avatar.screenY;

        // Vérifie si les coordonnées sont dans la zone visible
        if (pos.x + gp.TAILLE_BLOC > gp.avatar.mapPoint.x - gp.avatar.screenX
                && pos.x - gp.TAILLE_BLOC < gp.avatar.mapPoint.x + gp.avatar.screenX
                && pos.y + gp.TAILLE_BLOC > gp.avatar.mapPoint.y - gp.avatar.screenY
                && pos.y - gp.TAILLE_BLOC < gp.avatar.mapPoint.y + gp.avatar.screenY) {
            g2.drawImage(image, (int) (screenX * scaleX), (int) (screenY * scaleY), (int) (gp.TAILLE_BLOC * scaleX),
                    (int) (gp.TAILLE_BLOC * scaleY), null);
        }
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

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getPrix() {
        return 0;
    }

    public void setInvisible(GamePanel gp) {
        new Thread(() -> {
            try {
                Point originalPos = new Point(pos);
                pos.setLocation(Integer.MAX_VALUE, Integer.MAX_VALUE); // Déplacer l'objet hors de l'écran
                Thread.sleep(5000); // Attendre 5 secondes
    
                while (isAvatarNearby(gp.avatar, originalPos)) {
                    Thread.sleep(100); // Attendre 100 ms avant de vérifier à nouveau
                }
    
                pos.setLocation(originalPos); // Remettre l'objet à sa position d'origine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    private boolean isAvatarNearby(Avatar avatar, Point originalPos) {
        int proximityThreshold = 50; // Distance de proximité en pixels
        Point avatarPos = avatar.getPosition();
        double distance = originalPos.distance(avatarPos);
        return distance < proximityThreshold;
    }

}