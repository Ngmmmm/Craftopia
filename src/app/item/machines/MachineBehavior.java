package item.machines;

import item.ressources.Ressource;

/**
 * L'interface MachineBehavior définit les comportements de base que doit implémenter une machine.
 *
 * <p>
 * Cette interface déclare deux méthodes que toute machine doit implémenter : 
 * `backToPlayer` pour retourner des ressources au joueur et `createRessource` 
 * pour créer des ressources. Cela permet de standardiser les interactions avec les machines.
 * </p>
 *
 * <p>
 * Les classes qui implémentent cette interface doivent fournir des définitions concrètes pour ces méthodes, 
 * permettant ainsi différentes implémentations spécifiques des comportements des machines.
 * </p>
 *
 * @author Ouahid Houat
 * @version 1.0
 */
public interface MachineBehavior {

    /**
     * Retourne des ressources au joueur.
     * 
     * @param r la ressource à retourner.
     * @param quantity la quantité de la ressource à retourner.
     */
    void backToPlayer(Ressource r, int quantity); // Méthode pour retourner des ressources au joueur

    /**
     * Crée des ressources.
     */
    void createRessource(); // Méthode pour créer des ressources
}
