package item.recettes;

import item.bag.Inventaire;
import item.sprites.SuperObjet;

public class Recette {
    private String nom;
    private SuperObjet[] ingredients;
    private SuperObjet ress;
    private int time;

    public boolean isPossible(Inventaire inv){
        int sizeRecette = ingredients.length;
        int act = 0;

        for (SuperObjet r : ingredients) {
            for(int i = 0; i< inv.inv.size()-1; i++){
                if (inv.inv.get(i).name.equals(r.name)){
                    if(inv.inv.get(i).quantity == r.quantity){
                        act++;
                    }
                }
            }
        }
        return sizeRecette == act;
    }

    public SuperObjet getRessource(){
        return this.ress;
    }

    public SuperObjet[] getTabRessource(){
        return this.ingredients;
    }
}
