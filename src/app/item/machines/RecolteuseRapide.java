package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe RecolteuseRapide représente une machine de récolte rapide dans le
 * jeu.
 *
 * <p>
 * Cette classe hérite de la classe Recolteuse et initialise une machine de type
 * RecolteuseRapide.
 * Elle inclut des comportements spécifiques tels que l'ajout rapide de
 * ressources et le retour des ressources au joueur.
 * </p>
 *
 * <p>
 * La RecolteuseRapide est une version améliorée et plus efficace de la machine
 * de récolte, avec des coûts
 * et des fonctionnalités spécifiques qui la rendent plus rapide.
 * </p>
 *
 * @version 1.0
 * @autor Ouahid Houat
 */
public class RecolteuseRapide extends Recolteuse {

    /**
     * Constructeur par défaut pour initialiser la RecolteuseRapide.
     */
    public RecolteuseRapide() {
        this.name = "RecolteuseRapide"; // Nom de la machine
        this.produit = Produits.RECOLTEUSE_RAPIDE; // Type de produit
        this.prix = Argent.getPrice(this.produit); // Prix de la machine

        try {
            // Chargement de l'image de la RecolteuseRapide
            File f1 = new File("./img/machines/recolteuse.png");
            this.image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }

}
