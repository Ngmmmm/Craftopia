package main;

import item.machines.Usine;
import item.marche.Marche;
import item.recettes.RecetteHache;
import item.ressources.Bois;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Pierre;
import javax.swing.JLayeredPane;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setAllItems() {
        setRessource();
        setMachine();
        setMarche();
        // gp.saveEtatMap.loadEtatMap();
        gp.saveInvAvatar.loadInvAvatar();
    }

    public void setRessource() {

        GamePanel.obj.add(new Feuille());
        GamePanel.obj.get(0).pos.x = 23 * gp.TAILLE_BLOC;
        GamePanel.obj.get(0).pos.y = 25 * gp.TAILLE_BLOC;

        GamePanel.obj.add(new Bois());
        GamePanel.obj.get(1).pos.x = 24 * gp.TAILLE_BLOC;
        GamePanel.obj.get(1).pos.y = 25 * gp.TAILLE_BLOC;

        GamePanel.obj.add(new Pierre());
        GamePanel.obj.get(2).pos.x = 25 * gp.TAILLE_BLOC;
        GamePanel.obj.get(2).pos.y = 25 * gp.TAILLE_BLOC;

        GamePanel.obj.add(new Fer());
        GamePanel.obj.get(3).pos.x = 26 * gp.TAILLE_BLOC;
        GamePanel.obj.get(3).pos.y = 25 * gp.TAILLE_BLOC;

    }

    public void setMachine() {
        GamePanel.obj.add(new Usine());
        Usine usine = (Usine) GamePanel.obj.get(4);
        usine.setRecetteAct(new RecetteHache());
        gp.layeredPane.add(usine.uiUsine.getMainPanel(),JLayeredPane.PALETTE_LAYER);
        
        GamePanel.obj.get(4).pos.x = 27 * gp.TAILLE_BLOC;
        GamePanel.obj.get(4).pos.y = 25 * gp.TAILLE_BLOC;

    }

    public void setMarche() {
        GamePanel.obj.add(new Marche());
        GamePanel.obj.get(5).pos.x = 28 * gp.TAILLE_BLOC;
        GamePanel.obj.get(5).pos.y = 25 * gp.TAILLE_BLOC;
    }

}
