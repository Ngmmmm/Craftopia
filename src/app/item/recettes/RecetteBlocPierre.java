package item.recettes;

import item.ressources.BlocPierre;
import item.ressources.Pierre;
import item.sprites.SuperObjet;

public class RecetteBlocPierre {

    private String nom;
    private SuperObjet[] ingredients;
    private SuperObjet ress;

    public RecetteBlocPierre(){
        this.nom = "BlocPierre";
        this.ingredients = new SuperObjet[1];
        this.ingredients[0] = new Pierre();
        this.ingredients[0].setQuantity(4);
        this.ress = new BlocPierre();
        
    }
    

    
}
