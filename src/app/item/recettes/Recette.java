package item.recettes;

import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.util.ArrayList;

/**
 * La classe abstraite Recette représente une recette dans le jeu.
 *
 * <p>
 * Cette classe gère les ingrédients nécessaires à la création de ressources,
 * la vérification de la possibilité de créer une ressource en fonction des
 * ingrédients disponibles,
 * et l'interaction avec le joueur. Elle définit les propriétés de base et les
 * comportements communs
 * des différentes recettes.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'obtention des ingrédients, la vérification de la
 * possibilité de créer une ressource,
 * et la gestion des noms et du temps de création des recettes.
 * </p>
 *
 * @author Noa Gregoire-Mellier
 * @version 1.0
 */
public abstract class Recette {
    // Nom de la recette
    protected String nom;

    // Tableau des ingrédients nécessaires pour la recette
    protected Ressource[] ingredients;

    // Ressource résultante de la recette
    protected Ressource ress;

    // Temps nécessaire pour créer la ressource
    protected int time;

    /**
     * Enumération des différentes recettes disponibles dans le livre de recettes.
     */
    public static enum Livre {
        BOULE_FEUILLE,
        BLOC_PIERRE,
        PIOCHE,
        ARC,
        EPEE,
        HACHE,
    }

    /**
     * Enumération des différents ingrédients possibles pour les recettes.
     */
    public static enum Ingredient {
        FEUILLE,
        BOIS,
        PIERRE,
        FER,
    }

    /**
     * Obtient une recette spécifique en fonction de l'énumération donnée.
     * 
     * @param recette l'énumération de la recette à obtenir.
     * @return l'objet Recette correspondant.
     */
    public static Recette getRecette(Livre recette) {
        switch (recette) {
            case BLOC_PIERRE:
                return new RecetteBlocPierre();
            case PIOCHE:
                return new RecettePioche();
            case BOULE_FEUILLE:
                return new RecetteBouleFeuille();
            case ARC:
                return new RecetteArc();
            case EPEE:
                return new RecetteEpee();
            case HACHE:
                return new RecetteHache();
            default:
                throw new IllegalArgumentException("Recette inconnue : " + recette); // Exception pour recette inconnue
        }
    }

    /**
     * Vérifie si la recette est possible avec les ingrédients disponibles dans
     * l'inventaire.
     * 
     * @param inv l'inventaire contenant les ingrédients.
     * @return `true` si la recette est possible, `false` sinon.
     */
    public boolean isPossible(ArrayList<SuperObjet> inv) {
        for (Ressource ingredient : ingredients) { // Parcourt tous les ingrédients de la recette
            boolean found = false;
            for (SuperObjet obj : inv) { // Parcourt tous les objets de l'inventaire
                if (obj.getClass().equals(ingredient.getClass()) && obj.quantity >= ingredient.getQuantity()) {
                    found = true;
                    break; // Si l'ingrédient est trouvé en quantité suffisante, passe au suivant
                }
            }
            if (!found) {
                return false; // Retourne false si un ingrédient est manquant ou en quantité insuffisante
            }
        }
        return true; // Retourne true si tous les ingrédients sont disponibles en quantité suffisante
    }

    /**
     * Obtient la liste de toutes les recettes disponibles.
     * 
     * @return une liste de toutes les recettes disponibles.
     */
    public static ArrayList<Recette> getRecettes() {
        ArrayList<Recette> recettes = new ArrayList<>();
        recettes.add(getRecette(Livre.BLOC_PIERRE));
        recettes.add(getRecette(Livre.PIOCHE));
        recettes.add(getRecette(Livre.BOULE_FEUILLE));
        recettes.add(getRecette(Livre.ARC));
        recettes.add(getRecette(Livre.EPEE));
        recettes.add(getRecette(Livre.HACHE));
        return recettes; // Retourne la liste des recettes disponibles
    }

    /**
     * Obtient la ressource résultante de la recette.
     * 
     * @return la ressource résultante.
     */
    public Ressource getRessource() {
        return this.ress; // Retourne la ressource résultante de la recette
    }

    /**
     * Obtient les ingrédients nécessaires pour la recette.
     * 
     * @return un tableau des ingrédients nécessaires.
     */
    public Ressource[] getIngredients() {
        return this.ingredients; // Retourne le tableau des ingrédients nécessaires pour la recette
    }

    /**
     * Obtient le nom de la recette.
     * 
     * @return le nom de la recette.
     */
    public String getName() {
        return this.nom; // Retourne le nom de la recette
    }

    /**
     * Obtient le temps nécessaire pour créer la ressource.
     * 
     * @return le temps de création en secondes.
     */
    public int getTime() {
        return this.time; // Retourne le temps de création de la recette en secondes
    }
}
