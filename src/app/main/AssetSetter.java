package main;

import item.machines.Usine;
import item.ressources.Bois;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Pierre;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setAllItems(){
        setRessource();
        setUsine();
    }
    public void setRessource() {

        gp.ressource[0] = new Feuille();
        gp.ressource[0].pos.x = 23 * gp.TAILLE_BLOC;
        gp.ressource[0].pos.y = 25 * gp.TAILLE_BLOC;

        gp.ressource[1] = new Bois();
        gp.ressource[1].pos.x = 24 * gp.TAILLE_BLOC;
        gp.ressource[1].pos.y = 25 * gp.TAILLE_BLOC;

        gp.ressource[2] = new Pierre();
        gp.ressource[2].pos.x = 25 * gp.TAILLE_BLOC;
        gp.ressource[2].pos.y = 25 * gp.TAILLE_BLOC;

        gp.ressource[3] = new Fer();
        gp.ressource[3].pos.x = 26 * gp.TAILLE_BLOC;
        gp.ressource[3].pos.y = 25 * gp.TAILLE_BLOC;

    }

    public void setUsine(){
        gp.usine[0] = new Usine();
        gp.usine[0].pos.x = 27 * gp.TAILLE_BLOC;
        gp.usine[0].pos.y = 25 * gp.TAILLE_BLOC;

    }

}
