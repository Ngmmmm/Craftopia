package item.recettes;

import item.ressources.BlocPierre;
import item.ressources.Pierre;
import item.ressources.Ressource;

/**
 * La classe RecetteBlocPierre représente la recette pour créer un bloc de
 * pierre dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe abstraite Recette et initialise une recette
 * spécifique
 * pour créer un bloc de pierre. Elle définit les ingrédients nécessaires ainsi
 * que la
 * ressource résultante.
 * </p>
 *
 * @version 1.0
 * @author Noa Gregoire-Mellier
 */
public class RecetteBlocPierre extends Recette {

    /**
     * Constructeur par défaut pour initialiser la recette d'un bloc de pierre.
     */
    public RecetteBlocPierre() {
        this.nom = "BlocPierre"; // Nom de la recette

        // Initialisation des ingrédients nécessaires pour la recette
        this.ingredients = new Ressource[1]; // Tableau d'un ingrédient
        this.ingredients[0] = new Pierre(); // Ingrédient : Pierre
        this.ingredients[0].setQuantity(4); // Quantité de Pierre nécessaire : 4

        // Initialisation de la ressource résultante de la recette
        this.ress = new BlocPierre(); // Ressource résultante : BlocPierre
        this.ress.setQuantity(1); // Quantité du bloc de pierre créé : 1

        this.time = 5; // Temps nécessaire pour créer la ressource : 5 secondes
    }
}
