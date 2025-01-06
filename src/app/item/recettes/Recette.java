package item.recettes;

import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.util.ArrayList;

public abstract class Recette {
    protected String nom;
    protected Ressource[] ingredients;
    protected Ressource ress;
    protected int time;

    public static enum Livre {
        BOULE_FEUILLE,
        BLOC_PIERRE,
        PIOCHE,
        ARC,
        EPEE,
        HACHE,
    }

    public static enum Ingredient {
        FEUILLE,
        BOIS,
        PIERRE,
        FER,
    }

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
                throw new IllegalArgumentException("Recette inconnu : " + recette);
        }
    }

    public boolean isPossible(ArrayList<SuperObjet> inv) {
        for (Ressource ingredient : ingredients) {
            boolean found = false;
            for (SuperObjet obj : inv) {
                if (obj.getClass().equals(ingredient.getClass()) && obj.quantity >= ingredient.getQuantity()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Recette> getRecettes() {
        ArrayList<Recette> recettes = new ArrayList<>();
        recettes.add(getRecette(Livre.BLOC_PIERRE));
        recettes.add(getRecette(Livre.PIOCHE));
        recettes.add(getRecette(Livre.BOULE_FEUILLE));
        recettes.add(getRecette(Livre.ARC));
        recettes.add(getRecette(Livre.EPEE));
        recettes.add(getRecette(Livre.HACHE));
        return recettes;
    }

    public Ressource getRessource() {
        return this.ress;
    }

    public Ressource[] getIngredients() {
        return this.ingredients;
    }

    public String getName() {
        return this.nom;
    }

    public int getTime() {
        return this.time;
    }
}
