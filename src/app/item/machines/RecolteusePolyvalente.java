package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe RecolteusePolyvalente représente une machine de récolte polyvalente
 * dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Recolteuse et initialise une machine de type
 * RecolteusePolyvalente.
 * Elle inclut des comportements spécifiques tels que la gestion de l'état de la
 * machine et l'interaction avec le joueur.
 * </p>
 *
 * <p>
 * La RecolteusePolyvalente est une version améliorée et plus efficace de la
 * machine de récolte, avec des coûts
 * et des fonctionnalités spécifiques qui la rendent polyvalente.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class RecolteusePolyvalente extends Recolteuse {

    /**
     * Constructeur par défaut pour initialiser la RecolteusePolyvalente.
     */
    public RecolteusePolyvalente() {
        this.name = "RecolteusePolyvalente"; // Nom de la machine
        this.produit = Produits.RECOLTEUSE_POLYVALENTE; // Type de produit
        this.prix = Argent.getPrice(produit); // Prix de la machine
        this.prix = Argent.getPrice(Produits.RECOLTEUSE_POLYVALENTE); // Prix de la machine (duplicata par sécurité)

        try {
            // Chargement de l'image de la RecolteusePolyvalente
            this.image = ImageIO.read(new File("./img/machines/recolteuse.png"));
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }

}
