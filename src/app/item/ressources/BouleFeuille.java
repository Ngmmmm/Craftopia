package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe BouleFeuille représente une boule de feuille dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * spécifique de type boule de feuille.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps
 * nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs de la boule de feuille
 * ainsi que le chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class BouleFeuille extends Ressource {

    /**
     * Constructeur par défaut pour initialiser la boule de feuille.
     */
    public BouleFeuille() {
        name = "BouleFeuille"; // Nom de la ressource

        prix = Argent.getPrice(Produits.BOULE_FEUILLE); // Prix de la boule de feuille
        produit = Produits.BOULE_FEUILLE; // Type de produit
        time = 0; // Temps nécessaire pour créer la boule de feuille en secondes

        try {
            // Chargement de l'image de la boule de feuille
            File f1 = new File("./img/ressources/leafBall.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
