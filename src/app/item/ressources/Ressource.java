package item.ressources;

import item.sprites.SuperObjet;

public abstract class Ressource extends SuperObjet {

    private int quantity = 1;
    public int prix;
    public int time;
    public boolean isItem = true;

    public String getName() {
        return name;
    }

    @Override
    public int getPrix() {
        return prix;
    }
}
