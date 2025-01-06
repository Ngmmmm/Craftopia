package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe UsineFragile représente une version fragile de l'usine dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Usine et initialise une machine de type
 * UsineFragile.
 * Elle inclut des comportements spécifiques tels que la gestion de l'état de la
 * machine
 * et l'interaction avec le joueur.
 * </p>
 *
 * <p>
 * L'UsineFragile est une version plus fragile et moins durable de la machine de
 * récolte,
 * avec des coûts et des fonctionnalités spécifiques.
 * </p>
 *
 * @version 1.0
 * @autor Ouahid Houat
 */
public class UsineFragile extends Usine {

    /**
     * Constructeur par défaut pour initialiser l'UsineFragile.
     */
    public UsineFragile() {
        this.name = "UsineFragile"; // Nom de la machine
        this.produit = Produits.USINE_FRAGILE; // Type de produit
        this.prix = Argent.getPrice(this.produit); // Prix de la machine

        try {
            // Chargement de l'image de l'UsineFragile
            this.image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }

}
