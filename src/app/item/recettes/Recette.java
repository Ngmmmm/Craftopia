package item.recettes;

import item.bag.Inventaire;
import item.ressources.Ressource;

public class Recette {
    private String nom;
    private Ressource[] ingredients;
    private Ressource ress;
    private int time;

    public boolean isPossible(Inventaire inv){
        int sizeRecette = ingredients.length;
        int act = 0;

        for (Ressource r : ingredients) {
            for(int i = 0; i< inv.inv.size()-1; i++){
                if (inv.inv.get(i).getItem().getName().equals(r.getName())){
                    if(inv.inv.get(i).getItem().getQuantity() == r.getQuantity()){
                        act++;
                    }
                }
            }
        }
        return sizeRecette == act;
    }

    public Ressource getRessource(){
        return this.ress;
    }

    public Ressource[] getTabRessource(){
        return this.ingredients;
    }
}
