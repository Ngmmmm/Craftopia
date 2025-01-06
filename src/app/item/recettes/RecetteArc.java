package item.recettes;

import item.ressources.Arc;
import item.ressources.Bois;
import item.ressources.BouleFeuille;
import item.ressources.Ressource;

/**
 * La classe RecetteArc représente la recette pour créer un arc dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe abstraite Recette et initialise une recette
 * spécifique
 * pour créer un arc. Elle définit les ingrédients nécessaires ainsi que la
 * ressource résultante.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class RecetteArc extends Recette {

    /**
     * Constructeur par défaut pour initialiser la recette d'un arc.
     */
    public RecetteArc() {
        this.nom = "Arc"; // Nom de la recette

        // Initialisation des ingrédients nécessaires pour la recette
        this.ingredients = new Ressource[2]; // Tableau de deux ingrédients
        this.ingredients[0] = new Bois(); // Premier ingrédient : Bois
        this.ingredients[0].setQuantity(2); // Quantité de Bois nécessaire : 2
        this.ingredients[1] = new BouleFeuille(); // Deuxième ingrédient : BouleFeuille
        this.ingredients[1].setQuantity(1); // Quantité de BouleFeuille nécessaire : 1

        // Initialisation de la ressource résultante de la recette
        this.ress = new Arc(); // Ressource résultante : Arc
        this.ress.setQuantity(1); // Quantité de l'arc créé : 1
    }
}
