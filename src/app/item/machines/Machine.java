package item.machines;

import item.bag.InventaireMachine;
import item.ressources.Ressource;
import item.sprites.SuperObjet;

public abstract class Machine extends SuperObjet implements MachineBehavior {
    public InventaireMachine stock;
    public Ressource res;
    public String etat;
    public int prix;
    public int time;
    
    public int getPrix() {
        return prix;
    }

    public abstract InventaireMachine getStock();
}
