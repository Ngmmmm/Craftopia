package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.marche.Marche.Produits;
import item.recettes.Recette;
import item.ressources.Ressource;
import item.sprites.Avatar;
import java.io.File;
import javax.imageio.ImageIO;

public class Usine extends Machine implements MachineBehavior {

    public Inventaire inventaireAvatar;
    public Recette recetteAct;

    public Usine() {
        name = "Usine";
        inventaireAvatar = new Inventaire();
        stock = new Inventaire();
        recetteAct = null;
        prix = Argent.getPrice(Produits.USINE);
        try {
            File f1 = new File("./img/machines/usine.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            System.out.println("Impossible d'afficher l'usine " + e);

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
            for (int i = 0; i < recetteAct.getIngredients().length; i++) {
                inventaireAvatar.remove(recetteAct.getIngredients()[i], 1);
            }
        }
    }

}
