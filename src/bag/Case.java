package bag;

import ressources.Ressource;

/**
 * La classe Case représente une case générique dans un inventaire ou un sac.
 * 
 * <p>
 * Cette classe permet de stocker un objet de type Ressource ainsi qu'un nom
 * associé à cette case. Elle inclut des méthodes pour gérer l'objet contenu,
 * son nom.
 * </p>
 * 
 * <p>
 * La classe est générique et peut accepter tout type d'objet qui hérite de la
 * classe Ressource, offrant ainsi une grande flexibilité pour la gestion des
 * différents types d'objets dans un inventaire.
 * </p>
 * 
 * @param <E> le type d'objet contenu dans la case, qui doit hériter de la
 *            classe Ressource.
 * 
 * @author Ouahid Houat
 * @version 1.0
 */
public class Case<E extends Ressource> {
    private E item; // Un objet quelconque héritant de Ressource
    private String text; // Le texte qu'affichera la case à l'écran lorsque l'on passe dessus

    /**
     * Constructeur
     */
    public Case() {
        this.item = null;
    }

    /**
     * Retourne l'objet contenu dans la case.
     * 
     * @return l'objet de type Ressource contenu dans la case.
     */
    public E getItem() {
        return item;
    }

    /**
     * Modifie l'item de la case
     * 
     * @param item
     */
    public void setItem(E item) {
        this.item = item;
    }

    /**
     * Retourne le texte affiché par la case.
     * 
     * @return le Texte associé à la case.
     */
    public String getText() {
        return text;
    }

    /**
     * Modifie le texte associé à la case
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

}
