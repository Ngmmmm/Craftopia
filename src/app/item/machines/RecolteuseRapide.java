package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
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

    /**
     * Ajoute une ressource à la machine.
     */
    @Override
    public void addRessource() {
        this.res.increaseQuantity(2); // Augmente la quantité de la ressource de 2
    }

    /**
     * Retourne des ressources au joueur.
     * 
     * @param r        la ressource à retourner.
     * @param quantity la quantité de la ressource à retourner.
     */
    @Override
    public void backToPlayer(Ressource r, int quantity) {
        Inventaire.addToAvatarInv(r, quantity); // Ajoute les ressources à l'inventaire de l'avatar
        stock.remove(r, quantity); // Retire les ressources de l'inventaire de la machine
    }
}
