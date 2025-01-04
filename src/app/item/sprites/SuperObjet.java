
package item.sprites;

import item.machines.Recolteuse;
import item.machines.RecolteuseFragile;
import item.machines.RecolteusePolyvalente;
import item.machines.RecolteuseRapide;
import item.machines.RecolteuseXL;
import item.machines.Usine;
import item.machines.UsineFragile;
import item.machines.UsinePolyvalente;
import item.machines.UsineRapide;
import item.machines.UsineXL;
import item.marche.Marche;
import item.marche.Marche.Produits;
import item.ressources.Arc;
import item.ressources.BlocPierre;
import item.ressources.Bois;
import item.ressources.BouleFeuille;
import item.ressources.Epee;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Hache;
import item.ressources.Pierre;
import item.ressources.Pioche;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
    public Produits produit;
    public boolean waitIme = false;
    public static int idCounter = 1;
    public final int id;
    public int time = 0;

    public SuperObjet() {
        id = idCounter++;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }

    public Produits getProduit() {
        return this.produit;
    }

    public static SuperObjet getSuperObjet(String name) {
        if (name.equals("Fer")) {
            return new Fer();
        } else if (name.equals("Bois")) {
            return new Bois();
        } else if (name.equals("Feuille")) {
            return new Feuille();
        } else if (name.equals("Pierre")) {
            return new Pierre();
        } else if (name.equals("Pioche")) {
            return new Pioche();
        } else if (name.equals("BlocPierre")) {
            return new BlocPierre();
        } else if (name.equals("Recolteuse")) {
            return new Recolteuse();
        } else if (name.equals("RecolteuseFragile")) {
            return new RecolteuseFragile();
        } else if (name.equals("RecolteusePolyvalente")) {
            return new RecolteusePolyvalente();
        } else if (name.equals("RecolteuseRapide")) {
            return new RecolteuseRapide();
        } else if (name.equals("RecolteuseXL")) {
            return new RecolteuseXL();
        } else if (name.equals("Usine")) {
            return new Usine();
        } else if (name.equals("UsineFragile")) {
            return new UsineFragile();
        } else if (name.equals("UsinePolyvalente")) {
            return new UsinePolyvalente();
        } else if (name.equals("UsineRapide")) {
            return new UsineRapide();
        } else if (name.equals("UsineXL")) {
            return new UsineXL();
        } else if (name.equals("Marche")) {
            return new Marche();
        }else if (name.equals("BouleFeuille")) {
            return new BouleFeuille();
        }else if (name.equals("Hache")) {
            return new Hache();
        }else if (name.equals("Epee")){
            return new Epee();
        }else if(name.equals("Arc")){
            return new Arc();
        }

        return null;
    }

}