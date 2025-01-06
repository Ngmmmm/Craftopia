package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe UsineXL représente une usine de grande capacité dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Usine et initialise une machine de type UsineXL.
 * Elle inclut des comportements spécifiques tels que la gestion de l'état de la machine
 * et l'interaction avec le joueur.
 * </p>
 *
 * <p>
 * L'UsineXL est une version améliorée et plus efficace de la machine de récolte, avec
 * des coûts et des fonctionnalités spécifiques qui la rendent capable de traiter de
 * grandes quantités de ressources.
 * </p>
 *
 * @version 1.0
 * @autor Ouahid Houat
 */
public class UsineXL extends Usine {

    /**
     * Constructeur par défaut pour initialiser l'UsineXL.
     */
    public UsineXL() {
        this.name = "UsineXL"; // Nom de la machine
        this.produit = Produits.USINE_XL; // Type de produit
        this.prix = Argent.getPrice(this.produit); // Prix de la machine
        this.capacite = 10; // Capacité maximale de la machine

        try {
            // Chargement de l'image de l'UsineXL
            this.image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
