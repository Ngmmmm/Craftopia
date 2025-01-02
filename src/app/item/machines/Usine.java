package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.recettes.Recette;
import item.recettes.RecetteArc;
import item.recettes.RecetteBlocPierre;
import item.ressources.Ressource;
import main.UiUsine;

import java.io.File;
import javax.imageio.ImageIO;

public class Usine extends Machine {

    public Recette recetteAct;
    // public UiRecette uiRecette;
    // public UiStock uiStock;
    public UiUsine uiUsine;
    private int idCounter = 1;
    public final int id;
    // TODO : public UiConfigUsine uiConfig;

    public Usine() {
        name = "Usine";
        this.stock = new InventaireMachine(this);
        
        recetteAct = new RecetteArc();// TODO : UI pour définir la recette
        prix = Argent.getPrice(Produits.USINE);
        produit = Produits.USINE;
        uiUsine = new UiUsine(this);
        id = idCounter++;

        try {
            File f1 = new File("./img/machines/usine.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            System.out.println("Impossible d'afficher l'usine " + e);

        }
    }

    public void addFromPlayer(Ressource r) {
        stock.add(r, 1);
        Inventaire.removeFromAvatarInv(r, 1);

    }

    @Override
    public void backToPlayer(Ressource r) {
        Inventaire.addToAvatarInv(r, 1);
        stock.remove(r, 1);
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
                Inventaire.removeFromAvatarInv(recetteAct.getIngredients()[i],
                        recetteAct.getIngredients()[i].getQuantity());
            }

        } else {
            System.out.println("Recette impossible");
        }
    }

    @Override
    public InventaireMachine getStock() {
        return stock;
    }

}
