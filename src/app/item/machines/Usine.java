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

    public Usine() {
        this.name = "Usine";
        this.stock = new InventaireMachine(this);
        this.prix = Argent.getPrice(Produits.USINE);
        this.produit = Produits.USINE;
        this.uiUsine = new UiUsine(this);
        this.recetteAct = null;
        this.capacite = 5;
        this.time = 10;
        try {
            File f1 = new File("./img/machines/usine.png");
            this.image = ImageIO.read(f1);
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
        Ressource ressource = (Ressource) SuperObjet.getSuperObjet(r.getName());
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
        int compteur = 0;
        for (SuperObjet item : this.stock.getInv()) {
            Ressource ress = (Ressource) item;

            compteur += ress.getQuantity();

        }
        if (isPossible() && compteur < this.capacite) {

            Ressource ressource = (Ressource) SuperObjet.getSuperObjet(recetteAct.getRessource().getName());
            for (int i = 0; i < recetteAct.getIngredients().length; i++) {
                Inventaire.removeFromAvatarInv(recetteAct.getIngredients()[i],
                        recetteAct.getIngredients()[i].getQuantity());
            }
            stock.add(ressource, 1);

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
