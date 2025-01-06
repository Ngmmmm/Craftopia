package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe UsineRapide représente une machine de type usine rapide dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Usine et initialise une machine de type UsineRapide.
 * Elle inclut des comportements spécifiques tels que la gestion de l'état de la machine
 * et l'interaction avec le joueur.
 * </p>
 *
 * <p>
 * L'UsineRapide est une version améliorée de la machine de récolte avec un temps de fonctionnement plus court,
 * avec des coûts et des fonctionnalités spécifiques qui la rendent plus efficace.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class UsineRapide extends Usine {

    /**
     * Constructeur par défaut pour initialiser l'UsineRapide.
     */
    public UsineRapide() {
        this.name = "UsineRapide"; // Nom de la machine
        this.produit = Produits.USINE_RAPIDE; // Type de produit
        this.prix = Argent.getPrice(this.produit); // Prix de la machine
        this.time = 5; // Temps de fonctionnement plus court

        try {
            // Chargement de l'image de l'UsineRapide
            this.image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
