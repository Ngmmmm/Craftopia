package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe Hache représente une hache dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * spécifique de type hache.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps
 * nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs de la hache ainsi que le
 * chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class Hache extends Ressource {

    /**
     * Constructeur par défaut pour initialiser la hache.
     */
    public Hache() {
        name = "Hache"; // Nom de la ressource

        prix = Argent.getPrice(Produits.HACHE); // Prix de la hache
        produit = Produits.HACHE; // Type de produit
        time = 5; // Temps nécessaire pour créer la hache en secondes

        try {
            // Chargement de l'image de la hache
            File f1 = new File("./img/ressources/axe.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
