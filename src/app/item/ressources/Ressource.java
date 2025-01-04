package item.ressources;

import item.sprites.SuperObjet;

public abstract class Ressource extends SuperObjet {


    public int prix;
    public boolean isItem = true;

    public String getName() {
        return name;
    }

    @Override
    public int getPrix() {
        return prix;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
