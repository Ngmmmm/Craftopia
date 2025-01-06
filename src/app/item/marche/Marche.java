package item.marche;

import item.bag.Argent;
import item.bag.Inventaire;
import item.machines.Recolteuse;
import item.machines.RecolteuseFragile;
import item.machines.RecolteusePolyvalente;
import item.machines.RecolteuseRapide;
import item.machines.RecolteuseXL;
import item.machines.Usine;
import item.machines.UsineFragile;
import item.machines.UsinePolyvalente;
import item.machines.UsineRapide;
import item.machines.UsineXL;
import item.ressources.Arc;
import item.ressources.BlocPierre;
import item.ressources.Bois;
import item.ressources.BouleFeuille;
import item.ressources.Epee;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Hache;
import item.ressources.Pierre;
import item.ressources.Pioche;
import item.sprites.SuperObjet;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * La classe Marche représente le marché dans le jeu.
 *
 * <p>
 * Cette classe gère l'achat et la vente de produits par le joueur. Elle inclut
 * une liste de produits
 * disponibles à l'achat et à la vente, ainsi que des méthodes pour effectuer
 * des transactions.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'achat, la vente, et la récupération des produits
 * disponibles sur le marché.
 * Cette classe permet de simuler le fonctionnement d'un marché dans le jeu.
 * </p>
 *
 * @author Ouahid Houat
 * @version 1.0
 */
public class Marche extends SuperObjet {

    /**
     * Constructeur par défaut pour initialiser le marché.
     */
    public Marche() {
        name = "Marche"; // Nom du marché
        @SuppressWarnings("unused")
        final int quantity = 1; // Dans la version de base du jeu, il n'y a qu'un seul marché

        try {
            // Chargement de l'image du marché
            File f1 = new File("./img/marche/shop.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }

    /**
     * Enumération des produits disponibles sur le marché.
     */
    public static enum Produits {
        FEUILLE,
        BOULE_FEUILLE,
        BOIS,
        PIERRE,
        FER,
        BLOC_PIERRE,
        PIOCHE,
        ARC,
        EPEE,
        HACHE,
        USINE_FRAGILE,
        USINE,
        USINE_RAPIDE,
        USINE_XL,
        USINE_POLYVALENTE,
        RECOLTEUSE_FRAGILE,
        RECOLTEUSE,
        RECOLTEUSE_RAPIDE,
        RECOLTEUSE_XL,
        RECOLTEUSE_POLYVALENTE,
    }

    /**
     * Achetez un produit sur le marché.
     * 
     * @param produit  le produit à acheter.
     * @param quantity la quantité à acheter.
     */
    public static void buy(Produits produit, int quantity) {
        int prix = Argent.getPrice(produit); // Définir le coût du produit

        if (Argent.getMonnaiePlayer() >= prix) { // Vérifier si le joueur a suffisamment d'argent
            Argent.decreaseMoney(prix * quantity); // Réduire l'argent du joueur
            Inventaire.addToAvatarInv(getProduit(produit), quantity); // Ajouter le produit à l'inventaire du joueur
        }
    }

    /**
     * Vendez un produit sur le marché.
     * 
     * @param produit  le produit à vendre.
     * @param quantity la quantité à vendre.
     */
    public static void sell(Produits produit, int quantity) {
        int prix = Argent.getPrice(produit); // Définir le coût du produit

        Argent.increaseMoney(prix * quantity); // Augmenter l'argent du joueur
        Inventaire.removeFromAvatarInv(getProduit(produit), quantity); // Retirer le produit de l'inventaire du joueur
    }

    /**
     * Obtient le produit correspondant à l'énumération donnée.
     * 
     * @param produit le produit à obtenir.
     * @return l'objet correspondant au produit.
     */
    private static SuperObjet getProduit(Produits produit) {
        switch (produit) {
            case FEUILLE:
                return new Feuille();
            case BOIS:
                return new Bois();
            case PIERRE:
                return new Pierre();
            case FER:
                return new Fer();
            case BLOC_PIERRE:
                return new BlocPierre();
            case PIOCHE:
                return new Pioche();
            case USINE_FRAGILE:
                return new UsineFragile();
            case USINE:
                return new Usine();
            case USINE_RAPIDE:
                return new UsineRapide();
            case USINE_XL:
                return new UsineXL();
            case USINE_POLYVALENTE:
                return new UsinePolyvalente();
            case RECOLTEUSE_FRAGILE:
                return new RecolteuseFragile();
            case RECOLTEUSE:
                return new Recolteuse();
            case RECOLTEUSE_RAPIDE:
                return new RecolteuseRapide();
            case RECOLTEUSE_XL:
                return new RecolteuseXL();
            case RECOLTEUSE_POLYVALENTE:
                return new RecolteusePolyvalente();
            case BOULE_FEUILLE:
                return new BouleFeuille();
            case ARC:
                return new Arc();
            case EPEE:
                return new Epee();
            case HACHE:
                return new Hache();
            default:
                throw new IllegalArgumentException("Produit inconnu : " + produit); // Erreur pour produit inconnu
        }
    }

    /**
     * Obtient la liste des produits disponibles sur le marché.
     * 
     * @return une liste des produits disponibles.
     */
    public static ArrayList<SuperObjet> getList() {
        ArrayList<SuperObjet> produits = new ArrayList<>();
        for (Produits produit : Produits.values()) {
            produits.add(getProduit(produit)); // Ajoute chaque produit à la liste
        }
        return produits; // Retourne la liste des produits
    }
}
