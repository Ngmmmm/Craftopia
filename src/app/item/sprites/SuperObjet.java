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
import item.ressources.*;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

/**
 * La classe SuperObjet représente les objets interactifs dans le jeu.
 *
 * <p>
 * Chaque SuperObjet possède des attributs tels que son image, son nom, sa
 * position,
 * une zone de collision, sa quantité (pour les ressources), et d'autres
 * propriétés
 * spécifiques. Les SuperObjets peuvent être dessinés sur l'écran, manipulés par
 * l'avatar (récolte, fabrication, etc.), et contiennent des méthodes pour des
 * interactions variées.
 * </p>
 *
 * <p>
 * Cette classe sert également de base pour créer des objets spécifiques comme
 * des
 * ressources, des machines ou des produits de marché.
 * </p>
 *
 * @author Ouahid Houat
 * @version 1.0
 */
public class SuperObjet {

    public BufferedImage image; // Image représentant l'objet.
    public String name; // Nom de l'objet.
    public boolean collision = true; // Indique si l'objet peut provoquer une collision.
    public Point pos = new Point(); // Position de l'objet sur la carte.
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // Zone de collision de l'objet.
    public int solidAreaDefaultX = 0; // Position par défaut de la zone de collision sur l'axe X.
    public int solidAreaDefaultY = 0; // Position par défaut de la zone de collision sur l'axe Y.
    public int quantity; // Quantité possédée (pour les objets récoltables ou échangeables).
    public boolean isItem = false; // Indique si l'objet est un "item" spécifique.
    public Produits produit; // Produit lié à cet objet (s'il fait partie du marché).
    public boolean waitIme = false; // Indique si l'objet attend une action spécifique.
    public static int idCounter = 1; // Compteur global pour attribuer des ID uniques.
    public final int id; // Identifiant unique de l'objet.
    public int time = 0; // Temps restant (utilisé pour certaines interactions ou états).

    /**
     * Constructeur par défaut qui initialise un SuperObjet avec un ID unique.
     */
    public SuperObjet() {
        id = idCounter++; // Attribue un ID unique à chaque nouvel objet créé.
    }

    /**
     * Dessine l'objet sur l'écran si celui-ci est dans la zone visible.
     *
     * @param g2     l'objet {@link Graphics2D} utilisé pour dessiner.
     * @param gp     le panneau principal du jeu.
     * @param scaleX le facteur d'échelle horizontal.
     * @param scaleY le facteur d'échelle vertical.
     */
    public void draw(Graphics2D g2, GamePanel gp, double scaleX, double scaleY) {
        // Coordonnées de l'objet sur l'écran, calculées en fonction de sa position
        // relative à l'avatar.
        int screenX = pos.x - gp.avatar.mapPoint.x + gp.avatar.screenX;
        int screenY = pos.y - gp.avatar.mapPoint.y + gp.avatar.screenY;

        // Vérifie si l'objet est dans la zone visible avant de le dessiner.
        if (pos.x + GamePanel.TAILLE_BLOC > gp.avatar.mapPoint.x - gp.avatar.screenX
                && pos.x - GamePanel.TAILLE_BLOC < gp.avatar.mapPoint.x + gp.avatar.screenX
                && pos.y + GamePanel.TAILLE_BLOC > gp.avatar.mapPoint.y - gp.avatar.screenY
                && pos.y - GamePanel.TAILLE_BLOC < gp.avatar.mapPoint.y + gp.avatar.screenY) {
            g2.drawImage(image, (int) (screenX * scaleX), (int) (screenY * scaleY),
                    (int) (GamePanel.TAILLE_BLOC * scaleX), (int) (GamePanel.TAILLE_BLOC * scaleY), null);
        }
    }

    /**
     * Augmente la quantité possédée d'un item.
     *
     * @param quantity la valeur par laquelle augmenter la quantité de l'item.
     */
    public void increaseQuantity(int quantity) {
        this.quantity += quantity; // Augmente la quantité de l'objet.
    }

    /**
     * Réduit la quantité possédée d'un item.
     *
     * @param quantity la valeur par laquelle réduire la quantité de l'item.
     * @throws IllegalArgumentException si la quantité demandée est supérieure à la
     *                                  quantité possédée.
     */
    public void reduceQuantity(int quantity) {
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("Nombre de ressource insuffisant.");
        }
        this.quantity -= quantity; // Réduit la quantité de l'objet.
    }

    /**
     * Récupère le nom de l'objet.
     *
     * @return le nom de l'objet sous forme de chaîne de caractères.
     */
    public String getName() {
        return name;
    }

    /**
     * Récupère l'image de l'objet.
     *
     * @return une instance de {@link BufferedImage} représentant l'objet.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Récupère le temps associé à l'objet (s'il est utilisé pour des actions
     * temporisées).
     *
     * @return le temps en secondes.
     */
    public int getTime() {
        return time;
    }

    /**
     * Récupère le prix de l'objet. Cette méthode peut être surchargée par des
     * sous-classes.
     *
     * @return le prix de l'objet (par défaut, 0).
     */
    public int getPrix() {
        return 0;
    }

    /**
     * Rend l'objet temporairement invisible (retiré de la carte) pendant un délai
     * de 5 secondes,
     * puis le replace à sa position d'origine si l'avatar n'est plus à proximité.
     *
     * @param gp le panneau principal du jeu.
     */
    public void setInvisible(GamePanel gp) {
        new Thread(() -> {
            try {
                Point originalPos = new Point(pos); // Sauvegarde de la position d'origine.
                pos.setLocation(Integer.MAX_VALUE, Integer.MAX_VALUE); // Déplace l'objet hors de l'écran.
                Thread.sleep(5000); // Attend 5 secondes.

                while (isAvatarNearby(gp.avatar, originalPos)) {
                    Thread.sleep(100); // Vérifie régulièrement si l'avatar est toujours proche.
                }

                pos.setLocation(originalPos); // Replace l'objet à sa position d'origine.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Vérifie si l'avatar est proche d'une position donnée.
     *
     * @param avatar      l'avatar du joueur.
     * @param originalPos la position d'origine à vérifier.
     * @return {@code true} si l'avatar est dans un rayon de proximité défini.
     */
    private boolean isAvatarNearby(Avatar avatar, Point originalPos) {
        int proximityThreshold = 50; // Distance de proximité en pixels.
        Point avatarPos = avatar.getPosition();
        double distance = originalPos.distance(avatarPos);
        return distance < proximityThreshold;
    }

    /**
     * Récupère la quantité associée à l'objet.
     *
     * @return la quantité actuelle.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Définit la quantité associée à l'objet.
     *
     * @param quantity la nouvelle quantité à définir.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Récupère le produit associé à cet objet (s'il est lié au marché).
     *
     * @return un objet de type {@link Produits}.
     */
    public Produits getProduit() {
        return this.produit;
    }

    /**
     * Renvoie une instance spécifique d'un objet en fonction de son nom.
     *
     * @param name le nom de l'objet à récupérer.
     * @return une instance d'un sous-type de {@link SuperObjet} correspondant au
     *         nom.
     */
    public static SuperObjet getSuperObjet(String name) {
        // Vérifie le nom et retourne l'objet correspondant.
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
        } else if (name.equals("BouleFeuille")) {
            return new BouleFeuille();
        } else if (name.equals("Hache")) {
            return new Hache();
        } else if (name.equals("Epee")) {
            return new Epee();
        } else if (name.equals("Arc")) {
            return new Arc();
        }
        return null; // Si aucun objet ne correspond au nom donné.
    }
}
