package main;

import item.machines.Usine;
import item.marche.Marche;
import item.ressources.Bois;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Pierre;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setAllItems() {
        setRessource();
        setMachine();
        setMarche();
    }

    public void setRessource() {

        gp.obj.add(new Feuille());
        gp.obj.get(0).pos.x = 23 * gp.TAILLE_BLOC;
        gp.obj.get(0).pos.y = 25 * gp.TAILLE_BLOC;

        gp.obj.add(new Bois());
        gp.obj.get(1).pos.x = 24 * gp.TAILLE_BLOC;
        gp.obj.get(1).pos.y = 25 * gp.TAILLE_BLOC;

        gp.obj.add(new Pierre());
        gp.obj.get(2).pos.x = 25 * gp.TAILLE_BLOC;
        gp.obj.get(2).pos.y = 25 * gp.TAILLE_BLOC;

        gp.obj.add(new Fer());
        gp.obj.get(3).pos.x = 26 * gp.TAILLE_BLOC;
        gp.obj.get(3).pos.y = 25 * gp.TAILLE_BLOC;

    }

    public void setMachine() {
        gp.obj.add(new Usine());
        gp.obj.get(4).pos.x = 27 * gp.TAILLE_BLOC;
        gp.obj.get(4).pos.y = 25 * gp.TAILLE_BLOC;

    }

    public void setMarche() {
        gp.obj.add(new Marche());
        gp.obj.get(5).pos.x = 28 * gp.TAILLE_BLOC;
        gp.obj.get(5).pos.y = 25 * gp.TAILLE_BLOC;
    }

}
