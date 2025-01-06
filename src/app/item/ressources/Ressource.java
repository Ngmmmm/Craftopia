package item.ressources;

import item.sprites.SuperObjet;

/**
 * La classe abstraite Ressource représente une ressource dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe SuperObjet et sert de base pour toutes les ressources du jeu.
 * Elle définit les propriétés de base telles que le prix, le nom et la quantité de la ressource.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'obtention du nom, du prix et de la quantité de la ressource.
 * </p>
 *
 * @version 1.0
 * @autor Noa Gregoire-Mellier
 */
public abstract class Ressource extends SuperObjet {

    // Prix de la ressource
    public int prix;

    // Indicateur si l'objet est un item
    public boolean isItem = true;

    /**
     * Obtient le nom de la ressource.
     * 
     * @return le nom de la ressource.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtient le prix de la ressource.
     * 
     * @return le prix de la ressource.
     */
    @Override
    public int getPrix() {
        return prix;
    }

    /**
     * Obtient la quantité de la ressource.
     * 
     * @return la quantité de la ressource.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }
}
