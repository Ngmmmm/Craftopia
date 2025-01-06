package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;
import item.bag.Argent;
import item.marche.Marche.Produits;

/**
 * La classe Arc représente un arc dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * spécifique de type arc.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps
 * nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs de l'arc ainsi que le
 * chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class Arc extends Ressource {

    /**
     * Constructeur par défaut pour initialiser l'arc.
     */
    public Arc() {
        name = "Arc"; // Nom de la ressource

        prix = Argent.getPrice(Produits.ARC); // Prix de l'arc
        produit = Produits.ARC; // Type de produit
        time = 5; // Temps nécessaire pour créer l'arc en secondes

        try {
            // Chargement de l'image de l'arc
            File f1 = new File("./img/ressources/bow.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
