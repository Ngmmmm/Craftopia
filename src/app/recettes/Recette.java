package recettes;

<<<<<<< HEAD
import bag.Inventaire;
import ressources.Ressource;

public class Recette {
    private String nom;
    private Ressource[] ingredients;
    private Ressource ress;
    private int time;

    public boolean isPossible(Inventaire inv){
        int ok = ingredients.length;
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
        return ok == act;
    }

    public Ressource getRessource(){
        return this.ress;
    }

    public Ressource[] getTabRessource(){
        return this.ingredients;
    }
=======
public class Recette {
    private String nom;
    private String[] ingredients;
    private int time;

>>>>>>> 59943d1b5555aee25e5e07e909ba2e72b7f0b067
}
