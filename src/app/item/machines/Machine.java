package item.machines;

import item.bag.InventaireMachine;
import item.sprites.SuperObjet;

/**
 * La classe abstraite Machine représente une machine dans le jeu.
 *
 * <p>
 * Cette classe est utilisée pour définir les propriétés de base et les comportements communs
 * des différentes machines. Chaque machine a un stock, un état, un prix, une capacité,
 * une durée de fonctionnement, et un indicateur d'activité.
 * </p>
 *
 * <p>
 * Les machines peuvent avoir différentes implémentations spécifiques, chacune avec son propre inventaire
 * et ses propres comportements. Les méthodes abstraites et concrètes fournissent une interface standardisée
 * pour interagir avec les machines dans le jeu.
 * </p>
 *
 * @author Noa Gregoire-Mellier
 * @version 1.0
 */
public abstract class Machine extends SuperObjet implements MachineBehavior {

    // Inventaire spécifique de la machine
    public InventaireMachine stock;

    // État actuel de la machine (par exemple, "en marche", "arrêtée")
    public String etat;

    // Prix de la machine
    public int prix;

    // Temps de fonctionnement de la machine
    public int time;

    // Capacité maximale de la machine
    public int capacite;

    // Indicateur d'activité de la machine (true si active, false sinon)
    public boolean isActive;

    /**
     * Obtient le prix de la machine.
     * 
     * @return le prix de la machine.
     */
    public int getPrix() {
        return prix; // Retourne le prix de la machine.
    }

    /**
     * Méthode abstraite pour obtenir l'inventaire de la machine.
     * 
     * @return l'inventaire de la machine.
     */
    public abstract InventaireMachine getStock();
}
