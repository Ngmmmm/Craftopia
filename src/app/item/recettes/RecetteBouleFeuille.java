package item.recettes;

import item.ressources.BouleFeuille;
import item.ressources.Feuille;
import item.ressources.Ressource;

/**
 * La classe RecetteBouleFeuille représente la recette pour créer une boule de feuille dans le jeu.
 *
 * <p>
 * Cette classe hérite de la classe abstraite Recette et initialise une recette spécifique 
 * pour créer une boule de feuille. Elle définit les ingrédients nécessaires ainsi que la 
 * ressource résultante.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class RecetteBouleFeuille extends Recette {

    /**
     * Constructeur par défaut pour initialiser la recette d'une boule de feuille.
     */
    public RecetteBouleFeuille() {
        this.nom = "BouleFeuille"; // Nom de la recette

        // Initialisation des ingrédients nécessaires pour la recette
        this.ingredients = new Ressource[1]; // Tableau d'un ingrédient
        this.ingredients[0] = new Feuille(); // Ingrédient : Feuille
        this.ingredients[0].setQuantity(4); // Quantité de Feuille nécessaire : 4

        // Initialisation de la ressource résultante de la recette
        this.ress = new BouleFeuille(); // Ressource résultante : BouleFeuille
        this.ress.setQuantity(1); // Quantité de la boule de feuille créée : 1

        this.time = 1; // Temps nécessaire pour créer la ressource : 1 seconde
    }
}
