package item.ressources;

import item.sprites.SuperObjet;

public abstract class Ressource extends SuperObjet {

    private int quantity;
    public int prix;
    public int time;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrix() {
        return prix;
    }
}
