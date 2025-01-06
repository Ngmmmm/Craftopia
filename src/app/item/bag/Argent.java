package item.bag;

import item.marche.Marche.Produits;

/**
 * La classe Argent gère la monnaie du joueur dans le jeu.
 *
 * <p>
 * Cette classe est utilisée pour obtenir la quantité de monnaie du joueur, déterminer les prix des produits,
 * et gérer les augmentations et diminutions de la monnaie. Elle inclut des méthodes statiques pour interagir 
 * avec la monnaie du joueur et définir les prix des différents produits disponibles dans le jeu.
 * </p>
 *
 * <p>
 * Les produits sont classés avec différents prix en fonction de leur rareté et de leur utilité. Les méthodes 
 * permettent d'ajuster la monnaie du joueur en fonction des transactions effectuées.
 * </p>
 *
 * @author Noa Gregoire-Mellier
 * @version 1.0
 */
public class Argent {

    private static int monnaiePlayer = 0;

    /**
     * Obtient la quantité actuelle de monnaie du joueur.
     * 
     * @return la quantité de monnaie du joueur.
     */
    public static int getMonnaiePlayer() {
        return monnaiePlayer;
    }

    /**
     * Obtient le prix d'un produit donné.
     * 
     * @param produit le produit dont le prix est demandé.
     * @return le prix du produit.
     */
    public static int getPrice(Produits produit) {
        switch (produit) {
            case FEUILLE:
                return 1; // Produit très commun
            case BOULE_FEUILLE:
                return 2; // Assemblage de feuilles, légèrement plus cher
            case BOIS:
                return 3; // Un peu plus utile que la feuille
            case PIERRE:
                return 4; // Plus solide, donc un peu plus cher
            case FER:
                return 6; // Plus rare et utilisé pour des objets avancés
            case BLOC_PIERRE:
                return 8; // Assemblage de pierres, donc légèrement plus cher
            case PIOCHE:
                return 10; // Outil utile, mais abordable
            case ARC:
                return 12; // Arme à distance, prix moyen
            case EPEE:
                return 15; // Arme de mêlée, prix moyen
            case HACHE:
                return 10; // Outil de coupe, prix abordable

            // Usines
            case USINE_FRAGILE:
                return 20; // Produit basique mais utile
            case USINE:
                return 50; // Usine standard, assez efficace
            case USINE_RAPIDE:
                return 80; // Plus efficace, donc plus coûteuse
            case USINE_XL:
                return 120; // Capacité très élevée, prix en conséquence
            case USINE_POLYVALENTE:
                return 150; // Très efficace et polyvalente

            // Récolteuses
            case RECOLTEUSE_FRAGILE:
                return 15; // Produit fragile mais fonctionnel
            case RECOLTEUSE:
                return 40; // Récolteuse standard
            case RECOLTEUSE_RAPIDE:
                return 70; // Plus rapide et performante
            case RECOLTEUSE_XL:
                return 100; // Très grande capacité
            case RECOLTEUSE_POLYVALENTE:
                return 130; // Polyvalente et très efficace

            default:
                throw new IllegalArgumentException("Produit inconnu : " + produit);
        }
    }

    /**
     * Augmente la monnaie du joueur d'une valeur donnée.
     * 
     * @param val la valeur de l'augmentation.
     */
    public static void increaseMoney(int val) {
        monnaiePlayer += Math.abs(val);
    }

    /**
     * Diminue la monnaie du joueur d'une valeur donnée.
     * 
     * @param val la valeur de la diminution.
     */
    public static void decreaseMoney(int val) {
        monnaiePlayer -= Math.abs(val);
    }
}
