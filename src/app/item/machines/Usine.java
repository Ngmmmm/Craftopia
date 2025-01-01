package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.marche.Marche.Produits;
import item.recettes.Recette;
import item.recettes.RecetteBlocPierre;
import item.ressources.Ressource;
import item.sprites.Avatar;
import main.UiUsine;

import java.io.File;
import javax.imageio.ImageIO;

public class Usine extends Machine implements MachineBehavior {

    public Recette recetteAct;
    public UiUsine ui;
    private int idCounter = 1;
    public final int id;
    // TODO : public UiConfigUsine uiConfig;

    public Usine() {
        name = "Usine";
        stock = new Inventaire();
        recetteAct = new RecetteBlocPierre();// TODO : UI pour définir la recette
        prix = Argent.getPrice(Produits.USINE);
        ui = new UiUsine(this);
        id = idCounter++;
        try {
            File f1 = new File("./img/machines/usine.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            System.out.println("Impossible d'afficher l'usine " + e);

        }
    }

    public void addFromPlayer(Avatar player, Ressource r) {
        Inventaire.addToAvatarInv(r, 1);
        player.inv.remove(r, 1);
    }

    @Override
    public void backToPlayer(Avatar player, Ressource r) {
        player.inv.add(r, 1);
        Inventaire.removeFromAvatarInv(r, 1);
    }

    public void setRecetteAct(Recette act) {
        this.recetteAct = act;
    }

    public boolean isPossible() {
        return recetteAct.isPossible(Inventaire.getInvAvatar());
    }

    public void createRessource() {
        if (isPossible()) {
            System.out.println("Création de la ressource");
            stock.add(recetteAct.getRessource(), 1);
            for (int i = 0; i < recetteAct.getIngredients().length; i++) {
                Inventaire.removeFromAvatarInv(recetteAct.getIngredients()[i], 1);
            }
        } else {
            System.out.println("Recette impossible");
        }
        System.out.println("Réssource créée");
    }

}
