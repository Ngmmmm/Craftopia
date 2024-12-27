package item.recettes;

import item.ressources.BlocPierre;
import item.ressources.Pierre;
import item.ressources.Ressource;

public class RecetteBlocPierre {

    private String nom;
    private Ressource[] ingredients;
    private Ressource ress;

    public RecetteBlocPierre(){
        this.nom = "Bloc de Pierre";
        this.ingredients = new Ressource[1];
        this.ingredients[0] = new Pierre();
        this.ingredients[0].setQuantity(4);
        this.ress = new BlocPierre();
        
    }
    

    
}
