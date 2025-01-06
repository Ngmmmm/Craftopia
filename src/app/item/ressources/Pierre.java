package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe Pierre représente une pierre dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * spécifique de type pierre.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps
 * nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs de la pierre ainsi que
 * le chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Noa Gregoire-Mellia
 */
public class Pierre extends Ressource {

    /**
     * Constructeur par défaut pour initialiser la pierre.
     */
    public Pierre() {
        name = "Pierre"; // Nom de la ressource

        prix = Argent.getPrice(Produits.PIERRE); // Prix de la pierre
        produit = Produits.PIERRE; // Type de produit
        time = 3; // Temps nécessaire pour créer la pierre en secondes

        try {
            // Chargement de l'image de la pierre
            File f1 = new File("./img/ressources/pierre.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
