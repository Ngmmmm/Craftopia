package item.recettes;

import item.ressources.Bois;
import item.ressources.Epee;
import item.ressources.Fer;
import item.ressources.Ressource;

public class RecetteEpee extends Recette {

    public RecetteEpee() {
        this.ingredients = new Ressource[2];
        this.ingredients[0] = new Bois();
        this.ingredients[0].setQuantity(2);
        this.ingredients[1] = new Fer();
        this.ingredients[1].setQuantity(3);
        new Epee();
    }

}
