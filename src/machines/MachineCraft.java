package machines;

import bag.Inventaire;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import recettes.Recette;
import ressources.Ressource;
import sprites.Avatar;

public class MachineCraft {

    public Inventaire inv;
    public Inventaire stock;
    public Recette recetteAct;
    public String etat;
    public BufferedImage img;

    public MachineCraft() {
        inv = new Inventaire();
        stock = new Inventaire();
        recetteAct = null;
        try {
            File f1 = new File("./img/machines/manualMachine.png");
            img = ImageIO.read(f1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFromPlayer(Avatar player,Ressource r){
        inv.add(r, 1);
        player.inv.remove(r, 1);
    }
    public void backToPlayer(Avatar player, Ressource r){
        player.inv.add(r, 1);
        inv.remove(r, 1);
    }
    public void setRecetteAct(Recette act){
        this.recetteAct = act;
    }
    public boolean isPossible(){
        return recetteAct.isPossible(inv);
    }
    public void createRessource(){
        if (isPossible()){
            stock.add(recetteAct.getRessource(), 1);
            for(int i = 0; i<recetteAct.getTabRessource().length; i++){
                inv.remove(recetteAct.getTabRessource()[i], 1);
            }
        }
    }





    
}
