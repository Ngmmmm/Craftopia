package item.recettes;

import item.bag.Inventaire;
import item.ressources.Arc;
import item.ressources.BlocPierre;
import item.ressources.Bois;
import item.ressources.BouleFeuille;
import item.ressources.Epee;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Hache;
import item.ressources.Pierre;
import item.ressources.Pioche;
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

    private static SuperObjet getRecette(Livre recette) {
        switch (recette) {

            case BLOC_PIERRE:
                return new BlocPierre();
            case PIOCHE:
                return new Pioche();
            case BOULE_FEUILLE:
                return new BouleFeuille();
            case ARC:
                return new Arc();
            case EPEE:
                return new Epee();
            case HACHE:
                return new Hache();
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

    public Ressource getRessource() {
        return this.ress;
    }

    public Ressource[] getTabRessource() {
        return this.ingredients;
    }
}
