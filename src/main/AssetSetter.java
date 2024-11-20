package main;

import ressources.Bois;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjet(){
        gp.objet[0] = new Bois();
        gp.objet[0].posX = 3 * gp.tailleBloc;
        gp.objet[0].posY = 2 * gp.tailleBloc;

    }




    
}
