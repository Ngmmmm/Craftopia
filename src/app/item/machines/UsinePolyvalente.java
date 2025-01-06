package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe UsinePolyvalente représente une usine polyvalente dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Usine et initialise une machine de type
 * UsinePolyvalente.
 * Elle inclut des comportements spécifiques tels que la gestion de l'état de la
 * machine
 * et l'interaction avec le joueur.
 * </p>
 *
 * <p>
 * L'UsinePolyvalente est une version améliorée et plus durable de la machine de
 * récolte,
 * avec des coûts et des fonctionnalités spécifiques qui la rendent polyvalente.
 * </p>
 *
 * @version 1.0
 * @autor Ouahid Houat
 */
public class UsinePolyvalente extends Usine {

    /**
     * Constructeur par défaut pour initialiser l'UsinePolyvalente.
     */
    public UsinePolyvalente() {
        this.name = "UsinePolyvalente"; // Nom de la machine
        this.produit = Produits.USINE_POLYVALENTE; // Type de produit
        this.prix = Argent.getPrice(this.produit); // Prix de la machine

        try {
            // Chargement de l'image de l'UsinePolyvalente
            this.image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
