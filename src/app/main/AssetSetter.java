package main;

import ressources.Fer;
import tile.TileManager;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    } 

    public void setRessource(){
        gp.ressource[0] = new Fer();
        gp.ressource[0].pos.x = 9 * gp.TAILLE_BLOC;
        gp.ressource[0].pos.y = 8 * gp.TAILLE_BLOC;
        

    }




    
}
