package machines;

import bag.Inventaire;
import recettes.Recette;
import ressources.Ressource;
import sprites.Avatar;

public class MachineCraft {

    public Inventaire inv;
    public Inventaire stock;
    public Recette recetteAct;

    public MachineCraft() {
        inv = new Inventaire();
        stock = new Inventaire();
        recetteAct = null;
    }

    public void addFromPlayer(Avatar player,Ressource r){
        inv.add(r, 1);
        player.inv.remove(r, 1);
    }

    public void backToPlayer(Avatar player, Ressource r){
        player.inv.add(r, 1);
        inv.remove(r, 1);
    }

    public void setRecetteAct(Recette act){
        this.recetteAct = act;
    }

    public boolean isPossible(){
        return recetteAct.isPossible(inv);
    }

    public void createRessource(){
        if (isPossible()){
            stock.add(recetteAct.getRessource(), 1);
            for(int i = 0; i<recetteAct.getTabRessource().length; i++){
                inv.remove(recetteAct.getTabRessource()[i], 1);
            }
        }
    }





    
}
