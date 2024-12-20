package item.marche;

import java.io.File;
import javax.imageio.ImageIO;

import item.sprites.Avatar;
import item.sprites.SuperObjet;

public class Marche extends SuperObjet {

    public Marche() {
        name = "marché";
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
        int cost = getCost(produit);

        // Vérifier si le joueur a suffisamment de ressources
        if (avatar.getResources() >= cost) {
            // Réduire les ressources du joueur
            avatar.decreaseResources(cost);

            // Ajouter le produit à l'inventaire du joueur
            avatar.addToInventory(produit);

            System.out.println("Vous avez acheté : " + produit);
        } else {
            System.out.println("Vous n'avez pas assez de ressources pour acheter : " + produit);
        }
    }

    private int getCost(Produits produit) {
        // Définir les coûts pour chaque produit
        switch (produit) {
            case FEUILLE:
                return 10;
            case BOIS:
                return 20;
            case PIERRE:
                return 30;
            case FER:
                return 40;
            case BLOC_PIERRE:
                return 50;
            case PIOCHE:
                return 60;
            case USINE_FRAGILE:
                return 70;
            case USINE:
                return 80;
            case USINE_RAPIDE:
                return 90;
            case USINE_XL:
                return 100;
            case USINE_POLYVALENTE:
                return 110;
            case RECOLTEUSE_FRAGILE:
                return 120;
            case RECOLTEUSE:
                return 130;
            case RECOLTEUSE_RAPIDE:
                return 140;
            case RECOLTEUSE_XL:
                return 150;
            case RECOLTEUSE_POLYVALENTE:
                return 160;
            default:
                return 0;
        }
    }
}
