package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe Pioche représente une pioche dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * spécifique de type pioche.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps
 * nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs de la pioche ainsi que
 * le chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Noa Gregoire-Mellier
 */
public class Pioche extends Ressource {

    /**
     * Constructeur par défaut pour initialiser la pioche.
     */
    public Pioche() {
        name = "Pioche"; // Nom de la ressource

        prix = Argent.getPrice(Produits.PIOCHE); // Prix de la pioche
        produit = Produits.PIOCHE; // Type de produit
        time = 5; // Temps nécessaire pour créer la pioche en secondes

        try {
            // Chargement de l'image de la pioche
            File f1 = new File("./img/ressources/pickaxe.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
