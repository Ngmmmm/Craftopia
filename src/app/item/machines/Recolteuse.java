package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.ressources.Feuille;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import main.UiRecolteuse;

import java.io.File;
import javax.imageio.ImageIO;

public class Recolteuse extends Machine {

    public UiRecolteuse uiRecolteuse;

    public Recolteuse() {
        name = "Recolteuse";
        this.stock = new InventaireMachine(this);
        this.res = new Feuille(); // TODO : changer pour une ressource de base
        this.stock.add(res, 1);
        prix = Argent.getPrice(Produits.RECOLTEUSE);
        produit = Produits.RECOLTEUSE;
        uiRecolteuse = new UiRecolteuse(this);
        try {
            File f1 = new File("./img/machines/recolteuse.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addRessource() {
        this.res.increaseQuantity(1);
    }

    @Override
    public void backToPlayer(Ressource r, int quantity) {
        Ressource ressource = (Ressource) SuperObjet.getSuperObjet(r.getName());
        Inventaire.addToAvatarInv(ressource, quantity);
        this.stock.remove(r, quantity);
    }

    @Override
    public InventaireMachine getStock() {
        return stock;
    }

}
