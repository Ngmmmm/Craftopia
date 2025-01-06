package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe Fer représente du fer dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * spécifique de type fer.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps
 * nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs du fer ainsi que le
 * chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Noa Gregoire-Mellier
 */
public class Fer extends Ressource {

    /**
     * Constructeur par défaut pour initialiser le fer.
     */
    public Fer() {
        name = "Fer"; // Nom de la ressource

        prix = Argent.getPrice(Produits.FER); // Prix du fer
        produit = Produits.FER; // Type de produit
        time = 5; // Temps nécessaire pour créer le fer en secondes

        try {
            // Chargement de l'image du fer
            File f1 = new File("./img/ressources/fer.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
