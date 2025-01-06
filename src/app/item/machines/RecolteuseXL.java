package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe RecolteuseXL représente une machine de récolte de grande capacité
 * dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Recolteuse et initialise une machine de type
 * RecolteuseXL.
 * Elle inclut des comportements spécifiques tels que la gestion de l'état de la
 * machine
 * et l'interaction avec le joueur.
 * </p>
 *
 * <p>
 * La RecolteuseXL est une version améliorée avec une capacité plus grande de la
 * machine de récolte,
 * avec des coûts et des fonctionnalités spécifiques qui la rendent plus
 * efficace pour des récoltes de grande taille.
 * </p>
 *
 * @version 1.0
 * @autor Ouahid Houat
 */
public class RecolteuseXL extends Recolteuse {

    /**
     * Constructeur par défaut pour initialiser la RecolteuseXL.
     */
    public RecolteuseXL() {
        this.name = "RecolteuseXL"; // Nom de la machine
        this.produit = Produits.RECOLTEUSE_XL; // Type de produit
        this.prix = Argent.getPrice(this.produit); // Prix de la machine
        this.capacite = 10; // Capacité maximale de la machine

        try {
            // Chargement de l'image de la RecolteuseXL
            this.image = ImageIO.read(new File("./img/machines/recolteuse.png"));
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
