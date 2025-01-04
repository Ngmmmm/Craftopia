package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.recettes.Recette;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.io.File;
import javax.imageio.ImageIO;
import main.UiUsine;

public class Usine extends Machine {

    public Recette recetteAct;

    public UiUsine uiUsine;

    // TODO : public UiConfigUsine uiConfig;

    public Usine() {
        name = "Usine";
        this.stock = new InventaireMachine(this);
        prix = Argent.getPrice(Produits.USINE);
        produit = Produits.USINE;
        uiUsine = new UiUsine(this);
        this.recetteAct = null;

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
    public void backToPlayer(Ressource r, int quantity) {
        Ressource ressource = (Ressource) SuperObjet.getSuperObjet(recetteAct.getRessource().getName());
        Inventaire.addToAvatarInv(ressource, quantity);
        stock.remove(r, quantity);
    }

    public void setRecetteAct(Recette recette) {
        this.recetteAct = recette;
    }

    public boolean isPossible() {
        return recetteAct.isPossible(Inventaire.getInvAvatar());
    }

    public void createRessource() {
        if (isPossible()) {
            Ressource ressource = (Ressource) SuperObjet.getSuperObjet(recetteAct.getRessource().getName());
            stock.add(ressource, 1);
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

    public Recette getRecette() {
        return recetteAct;
    }

    public void update() {
        uiUsine = new UiUsine(this);
    }

}
