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

public class Marche extends SuperObjet {

    public Marche() {
        name = "Marche";
        @SuppressWarnings("unused")
        final int quantity = 1; // Dans la version de base du jeu, il n'y a qu'un seul marché
        try {
            File f1 = new File("./img/marche/shop.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static void buy(Produits produit, int quantity) {
        // Définir les coûts des produits
        int prix = Argent.getPrice(produit);

        // Vérifier si le joueur a suffisamment d'argent
        if (Argent.getMonnaiePlayer() >= prix) {
            // Réduire l'argent du joueur
            Argent.decreaseMoney(prix * quantity);

            // Ajouter le produit à l'inventaire du joueur
            Inventaire.addToAvatarInv(getProduit(produit), quantity);

            System.out.println("Vous avez acheté : " + produit);
        } else {
            System.out.println("Vous n'avez pas assez d'argent pour acheter : " + produit);
        }
    }

    public static void sell(Produits produit, int quantity) {
        // Définir les coûts des produits
        int prix = Argent.getPrice(produit);

        // Augmenter l'argent du joueur
        Argent.increaseMoney(prix*quantity);

        // Retirer le produit de l'inventaire du joueur
        Inventaire.removeFromAvatarInv(getProduit(produit), quantity);

        System.out.println("Vous avez vendu : " + produit);
    }

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
                throw new IllegalArgumentException("Produit inconnu : " + produit);
        }
    }

    public static ArrayList<SuperObjet> getList() {
        ArrayList<SuperObjet> produits = new ArrayList<>();
        for (Produits produit : Produits.values()) {
            produits.add(getProduit(produit));
        }
        return produits;
    }

}
