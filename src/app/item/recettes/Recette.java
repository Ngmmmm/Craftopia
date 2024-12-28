package item.recettes;

import java.util.ArrayList;

import item.bag.Inventaire;
import item.ressources.Bois;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Pierre;
import item.ressources.Ressource;
import item.sprites.SuperObjet;

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

    private static Recette getRecette(Livre recette) {
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

    private static SuperObjet getIngredients(Ingredient ingredient) {
        switch (ingredient) {
            case FEUILLE:
                return new Feuille();
            case BOIS:
                return new Bois();
            case PIERRE:
                return new Pierre();
            case FER:
                return new Fer();
            default:
                throw new IllegalArgumentException("Ingrédient inconnu : " + ingredient);
        }
    }

    public boolean isPossible(Inventaire inv) {
        int sizeRecette = ingredients.length;
        int act = 0;

        for (SuperObjet r : ingredients) {
            for (int i = 0; i < Inventaire.inv.size() - 1; i++) {
                if (Inventaire.inv.get(i).name.equals(r.name)) {
                    if (Inventaire.inv.get(i).quantity == r.quantity) {
                        act++;
                    }
                }
            }
        }
        return sizeRecette == act;
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
}
