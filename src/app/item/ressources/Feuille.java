package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe Feuille représente une feuille dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource spécifique de type feuille.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs de la feuille ainsi que le chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class Feuille extends Ressource {

    /**
     * Constructeur par défaut pour initialiser la feuille.
     */
    public Feuille() {
        name = "Feuille"; // Nom de la ressource

        prix = Argent.getPrice(Produits.FEUILLE); // Prix de la feuille
        produit = Produits.FEUILLE; // Type de produit
        time = 0; // Temps nécessaire pour créer la feuille en secondes

        try {
            // Chargement de l'image de la feuille
            File f1 = new File("./img/ressources/feuille.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
