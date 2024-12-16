package recettes;

import ressources.Ressource;
import sprites.Avatar;

public class Recette {
    private String nom;
    private Ressource[] ingredients;
    private int time;

    public boolean isPossible(Avatar player){
        int ok = ingredients.length;
        int act = 0;

        for (Ressource r : ingredients) {
            for(int i = 0; i< player.inv.inv.size()-1; i++){
                if (player.inv.inv.get(i).getItem().getName().equals(r.getName())){
                    if(player.inv.inv.get(i).getItem().getQuantity() == r.getQuantity()){
                        act++;
                    }
                }
            }
        }
        return ok == act;
    }
}
