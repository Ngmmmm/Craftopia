package item.marche;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Argent;
import item.machines.Recolteuse;
import item.machines.Usine;
import item.ressources.BlocPierre;
import item.ressources.Bois;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Pierre;
import item.ressources.Pioche;
import item.sprites.Avatar;
import item.sprites.SuperObjet;

public class Marche extends SuperObjet {

    public Marche() {
        name = "Marché";
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
        BOIS,
        PIERRE,
        FER,
        BLOC_PIERRE,
        PIOCHE,
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

    public void buy(Avatar avatar, Produits produit) {
        // Définir les coûts des produits
        int prix = Argent.getPrice(produit);

        // Vérifier si le joueur a suffisamment d'argent
        if (Argent.getMonnaiePlayer() >= prix) {
            // Réduire l'argent du joueur
            Argent.decreaseMoney(prix);

            // Ajouter le produit à l'inventaire du joueur
            avatar.inv.add(getProduit(produit), 1);

            System.out.println("Vous avez acheté : " + produit);
        } else {
            System.out.println("Vous n'avez pas assez d'argent pour acheter : " + produit);
        }
    }

    public void sell(Avatar avatar, Produits produit) {
        // Définir les coûts des produits
        int prix = Argent.getPrice(produit);

        // Augmenter l'argent du joueur
        Argent.increaseMoney(prix);

        // Retirer le produit de l'inventaire du joueur
        avatar.inv.remove(getProduit(produit), 1);
        
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
                return null;
            case USINE:
                return new Usine();
            // TODO: Créer dans le package machine les autres types d'usines
            // case USINE_RAPIDE:
            // return null;
            // case USINE_XL:
            // return null;
            // case USINE_POLYVALENTE:
            // return null;
            // case RECOLTEUSE_FRAGILE:
            // return null;
            case RECOLTEUSE:
                return new Recolteuse();

            // TODO: Créer dans le package machine les autres types de récolteuses
            // case RECOLTEUSE_RAPIDE:
            // return null;
            // case RECOLTEUSE_XL:
            // return null;
            // case RECOLTEUSE_POLYVALENTE:
            // return null;
            default:
                throw new IllegalArgumentException("Produit inconnu : " + produit);
        }
    }

}
