package main;

import ressources.Fer;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjet(){
        gp.objet[0] = new Fer();
        gp.objet[0].pos.x = 3 * gp.TAILLE_BLOC;
        gp.objet[0].pos.y = 2 * gp.TAILLE_BLOC;

    }




    
}
