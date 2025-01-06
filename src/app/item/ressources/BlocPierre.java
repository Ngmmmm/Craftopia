package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe BlocPierre représente un bloc de pierre dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource spécifique de type bloc de pierre.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs du bloc de pierre ainsi que le chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Noa Gregoire-Mellier
 */
public class BlocPierre extends Ressource {

    /**
     * Constructeur par défaut pour initialiser le bloc de pierre.
     */
    public BlocPierre() {
        name = "BlocPierre"; // Nom de la ressource

        produit = Produits.BLOC_PIERRE; // Type de produit
        prix = Argent.getPrice(Produits.BLOC_PIERRE); // Prix du bloc de pierre
        time = 3; // Temps nécessaire pour créer le bloc de pierre en secondes

        try {
            // Chargement de l'image du bloc de pierre
            File f1 = new File("./img/ressources/blocPierre.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
