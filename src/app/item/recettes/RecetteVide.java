package item.recettes;

import item.ressources.Ressource;
import item.ressources.RessourceVide;

/**
 * La classe RecetteVide représente une recette vide dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe abstraite Recette et initialise une recette
 * qui ne nécessite aucun ingrédient et ne produit aucune ressource. Elle est
 * utilisée comme modèle ou pour des cas particuliers dans le jeu.
 * </p>
 *
 * @version 1.0
 * @author Noa Gregoire-Mellier
 */
public class RecetteVide extends Recette {

    /**
     * Constructeur par défaut pour initialiser la recette vide.
     */
    public RecetteVide() {
        this.nom = "Vide"; // Nom de la recette

        // Initialisation des ingrédients nécessaires pour la recette
        this.ingredients = new Ressource[0]; // Aucun ingrédient nécessaire

        // Initialisation de la ressource résultante de la recette
        this.ress = new RessourceVide(); // Ressource résultante : RessourceVide
        this.ress.setQuantity(1); // Quantité de la ressource créée : 1

        this.time = 0; // Temps nécessaire pour créer la ressource : 0 secondes
    }
}
