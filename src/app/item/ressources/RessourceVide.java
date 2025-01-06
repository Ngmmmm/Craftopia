package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe RessourceVide représente une ressource vide dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Ressource et initialise une ressource
 * qui ne nécessite aucun ingrédient et ne produit aucune valeur. Elle est
 * utilisée comme modèle ou pour des cas particuliers dans le jeu.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class RessourceVide extends Ressource {

    /**
     * Constructeur par défaut pour initialiser la ressource vide.
     */
    public RessourceVide() {
        name = "Vide"; // Nom de la ressource

        prix = 0; // Prix de la ressource
        produit = null; // Aucun produit associé

        try {
            // Chargement de l'image de la ressource vide
            File f1 = new File("./img/ressources/vide.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
