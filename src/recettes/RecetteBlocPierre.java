package recettes;

import ressources.Pierre;
import ressources.Ressource;

public class RecetteBlocPierre {

    private String nom;
    private Ressource[] ingredients;

    public RecetteBlocPierre(){
        this.nom = "BlocPierre";
        this.ingredients = new Ressource[1];
        this.ingredients[0] = new Pierre();
        this.ingredients[0].setQuantity(4);
    }
    

    
}
