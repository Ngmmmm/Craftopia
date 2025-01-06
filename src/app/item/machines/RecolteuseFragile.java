package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe RecolteuseFragile représente une version fragile de la machine de
 * récolte dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe Recolteuse et initialise une machine de type
 * RecolteuseFragile. Elle inclut des comportements spécifiques
 * tels que l'ajout de ressources et le retour des ressources au joueur.
 * </p>
 *
 * <p>
 * La RecolteuseFragile est une version plus fragile et moins durable de la
 * machine de récolte, avec des coûts et des fonctionnalités spécifiques.
 * </p>
 *
 * @author Ouahid Houat
 * @version 1.0
 */
public class RecolteuseFragile extends Recolteuse {

    /**
     * Constructeur par défaut pour initialiser la RecolteuseFragile.
     */
    public RecolteuseFragile() {
        this.name = "RecolteuseFragile"; // Nom de la machine
        this.produit = Produits.RECOLTEUSE_FRAGILE; // Type de produit
        this.prix = Argent.getPrice(produit); // Prix de la machine
        this.prix = Argent.getPrice(Produits.RECOLTEUSE_FRAGILE); // Prix de la machine (duplicata par sécurité)

        try {
            // Chargement de l'image de la RecolteuseFragile
            File f1 = new File("./img/machines/recolteuse.png");
            this.image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }
}
