package item.recettes;

import item.bag.Inventaire;
import item.ressources.Ressource;
import item.sprites.SuperObjet;

public abstract class Recette {
    protected String nom;
    protected Ressource[] ingredients;
    protected Ressource ress;
    protected int time;

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
