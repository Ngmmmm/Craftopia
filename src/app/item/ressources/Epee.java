package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe Epee représente une épée dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * spécifique de type épée.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps
 * nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs de l'épée ainsi que le
 * chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class Epee extends Ressource {

    /**
     * Constructeur par défaut pour initialiser l'épée.
     */
    public Epee() {
        name = "Epee"; // Nom de la ressource

        prix = Argent.getPrice(Produits.EPEE); // Prix de l'épée
        produit = Produits.EPEE; // Type de produit
        time = 5; // Temps nécessaire pour créer l'épée en secondes

        try {
            // Chargement de l'image de l'épée
            File f1 = new File("./img/ressources/sword.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
