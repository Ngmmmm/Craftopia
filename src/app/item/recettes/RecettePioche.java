package item.recettes;

import item.ressources.Bois;
import item.ressources.Fer;
import item.ressources.Pioche;
import item.ressources.Ressource;

/**
 * La classe RecettePioche représente la recette pour créer une pioche dans le
 * jeu.
 *
 * <p>
 * Cette classe hérite de la classe abstraite Recette et initialise une recette
 * spécifique
 * pour créer une pioche. Elle définit les ingrédients nécessaires ainsi que la
 * ressource résultante.
 * </p>
 *
 * @version 1.0
 * @author Noa Gregoire-Mellier
 */
public class RecettePioche extends Recette {

    /**
     * Constructeur par défaut pour initialiser la recette d'une pioche.
     */
    public RecettePioche() {
        this.nom = "Pioche"; // Nom de la recette

        // Initialisation des ingrédients nécessaires pour la recette
        this.ingredients = new Ressource[2]; // Tableau de deux ingrédients
        this.ingredients[0] = new Bois(); // Premier ingrédient : Bois
        this.ingredients[0].setQuantity(2); // Quantité de Bois nécessaire : 2
        this.ingredients[1] = new Fer(); // Deuxième ingrédient : Fer
        this.ingredients[1].setQuantity(3); // Quantité de Fer nécessaire : 3

        // Initialisation de la ressource résultante de la recette
        this.ress = new Pioche(); // Ressource résultante : Pioche
        this.ress.setQuantity(1); // Quantité de la pioche créée : 1
    }
}
