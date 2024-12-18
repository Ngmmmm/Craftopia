package item.machines;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Inventaire;
import item.recettes.Recette;
import item.ressources.Ressource;
import item.sprites.Avatar;

public class Usine extends Machine implements MachineBehavior {

    public Inventaire inventaireAvatar;
    public Recette recetteAct;

    public Usine() {
        inventaireAvatar = new Inventaire();
        stock = new Inventaire();
        recetteAct = null;
        try {
            File f1 = new File("./img/machines/usine.png");
            img = ImageIO.read(f1);
        } catch (Exception e) {
            System.out.println("Immpossible d'afficher l'usine " + e);

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

    public void draw(Graphics2D g2, GamePanel gp) {
        // Coordonnées de la bloc à l'écran
        int screenX = pos.x - gp.avatar.mapPoint.x + gp.avatar.screenX;
        int screenY = pos.y - gp.avatar.mapPoint.y + gp.avatar.screenY;

        // Vérifie si les coordonnées sont dans la zone visible
        if (pos.x + gp.TAILLE_BLOC > gp.avatar.mapPoint.x - gp.avatar.screenX
                && pos.x - gp.TAILLE_BLOC < gp.avatar.mapPoint.x + gp.avatar.screenX
                && pos.y + gp.TAILLE_BLOC > gp.avatar.mapPoint.y - gp.avatar.screenY
                && pos.y - gp.TAILLE_BLOC < gp.avatar.mapPoint.y + gp.avatar.screenY) {
            g2.drawImage(img, screenX, screenY, gp.TAILLE_BLOC, gp.TAILLE_BLOC, null);
        }
    }

}
