package item.recettes;

import item.ressources.Ressource;
import item.ressources.RessourceVide;

public class RecetteVide extends Recette{
    public RecetteVide() {
        this.nom = "Vide";
        this.ingredients = new Ressource[0];
        this.ress = new RessourceVide();
        this.time = 0;
    }
    
}
