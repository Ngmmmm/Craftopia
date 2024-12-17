package recettes;

import ressources.Bois;
import ressources.Fer;
import ressources.Pioche;
import ressources.Ressource;

public class RecettePioche extends Recette{

    private String nom;
    private Ressource[] ingredients;
    private Ressource ress;

    public RecettePioche(){
        this.nom = "Pioche";
        this.ingredients = new Ressource[2];
        this.ingredients[0] = new Bois();
        this.ingredients[0].setQuantity(2);
        this.ingredients[1] = new Fer();
        this.ingredients[1].setQuantity(3);
        this.ress = new Pioche();
    }
    
}
