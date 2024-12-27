package item.recettes;

import item.ressources.BouleFeuille;
import item.ressources.Feuille;
import item.ressources.Ressource;

public class RecetteBouleFeuille {
    private String nom;
    private Ressource[] ingredients;
    private Ressource ress;

    public RecetteBouleFeuille() {
        this.nom = "Boule de Feuille";
        this.ingredients = new Ressource[1];
        this.ingredients[0] = new Feuille();
        this.ingredients[0].setQuantity(4);
        this.ress = new BouleFeuille();
    }
}
