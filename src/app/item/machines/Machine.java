package item.machines;

import item.bag.Inventaire;
import item.ressources.Ressource;
import item.sprites.SuperObjet;

public abstract class Machine extends SuperObjet {
    public Inventaire stock;
    public Ressource res;
    public String etat;
    public int prix;
    public int time;

    public int getPrix() {
        return prix;
    }
    
}
