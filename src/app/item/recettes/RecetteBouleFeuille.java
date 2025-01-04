package item.recettes;

import item.ressources.BouleFeuille;
import item.ressources.Feuille;
import item.ressources.Ressource;

public class RecetteBouleFeuille extends Recette {

    public RecetteBouleFeuille() {
        this.nom = "BouleFeuille";
        this.ingredients = new Ressource[1];
        this.ingredients[0] = new Feuille();
        this.ingredients[0].setQuantity(4);
        this.ress = new BouleFeuille();
        this.ress.setQuantity(1);
        this.time = 0;
    }
}
