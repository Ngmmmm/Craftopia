package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe Bois représente du bois dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * spécifique de type bois.
 * Elle définit le nom de la ressource, le prix, le type de produit, et le temps
 * nécessaire pour créer la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'initialisation des attributs du bois ainsi que le
 * chargement de l'image correspondante.
 * </p>
 *
 * @version 1.0
 * @author Noa Gregoire-Mellier
 */
public class Bois extends Ressource {

    /**
     * Constructeur par défaut pour initialiser le bois.
     */
    public Bois() {
        name = "Bois"; // Nom de la ressource

        prix = Argent.getPrice(Produits.BOIS); // Prix du bois
        produit = Produits.BOIS; // Type de produit
        waitIme = true; // Drapeau indiquant si un délai d'attente est nécessaire
        time = 0; // Temps nécessaire pour créer le bois en secondes

        try {
            // Chargement de l'image du bois
            File f1 = new File("./img/ressources/bois.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
