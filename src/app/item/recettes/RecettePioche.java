package item.recettes;

import item.ressources.Bois;
import item.ressources.Fer;
import item.ressources.Pioche;
import item.ressources.Ressource;

public class RecettePioche extends Recette {

    public RecettePioche() {
        this.nom = "Pioche";
        this.ingredients = new Ressource[2];
        this.ingredients[0] = new Bois();
        this.ingredients[0].setQuantity(2);
        this.ingredients[1] = new Fer();
        this.ingredients[1].setQuantity(3);
        this.ress = new Pioche();
    }

}
