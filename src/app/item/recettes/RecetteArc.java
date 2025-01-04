package item.recettes;

import item.ressources.Arc;
import item.ressources.Bois;
import item.ressources.BouleFeuille;
import item.ressources.Ressource;

public class RecetteArc extends Recette {

    public RecetteArc() {
        this.nom = "Arc";
        this.ingredients = new Ressource[2];
        this.ingredients[0] = new Bois();
        this.ingredients[0].setQuantity(2);
        this.ingredients[1] = new BouleFeuille();
        this.ingredients[1].setQuantity(1);
        this.ress = new Arc();
        this.ress.setQuantity(1);
    }
}
