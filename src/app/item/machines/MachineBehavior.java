package item.machines;

import item.ressources.Ressource;

public interface MachineBehavior {
    void backToPlayer(Ressource r, int quantity);
    void createRessource();
}
