package machines;

import bag.Inventaire;
import java.io.File;
import javax.imageio.ImageIO;
import recettes.Recette;
import ressources.Ressource;
import sprites.Avatar;

public class Usine extends Machine implements MachineBehavior{

    public Inventaire inventaireAvatar;
    public Recette recetteAct;

    public Usine() {
        inventaireAvatar = new Inventaire();
        stock = new Inventaire();
        recetteAct = null;
        try {
            File f1 = new File("./img/machines/manualMachine.png");
            img = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFromPlayer(Avatar player, Ressource r) {
        inventaireAvatar.add(r, 1);
        player.inv.remove(r, 1);
    }
    @Override
    public void backToPlayer(Avatar player, Ressource r) {
        player.inv.add(r, 1);
        inventaireAvatar.remove(r, 1);
    }

    public void setRecetteAct(Recette act) {
        this.recetteAct = act;
    }

    public boolean isPossible() {
        return recetteAct.isPossible(inventaireAvatar);
    }

    public void createRessource() {
        if (isPossible()) {
            stock.add(recetteAct.getRessource(), 1);
            for (int i = 0; i < recetteAct.getTabRessource().length; i++) {
                inventaireAvatar.remove(recetteAct.getTabRessource()[i], 1);
            }
        }
    }

}
