package item.recettes;

import item.ressources.BlocPierre;
import item.ressources.Pierre;
import item.ressources.Ressource;

public class RecetteBlocPierre extends Recette {

    public RecetteBlocPierre() {
        this.nom = "BlocPierre";
        this.ingredients = new Ressource[1];
        this.ingredients[0] = new Pierre();
        this.ingredients[0].setQuantity(4);
        this.ress = new BlocPierre();
        this.ress.setQuantity(1);
        this.time = 5;
    }

}
