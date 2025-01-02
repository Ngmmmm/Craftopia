package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import java.io.File;
import javax.imageio.ImageIO;

public class Recolteuse extends Machine {

    public Recolteuse() {
        name = "Recolteuse";
        this.stock = new InventaireMachine(this);
        this.stock.add(res, 0);
        prix = Argent.getPrice(Produits.RECOLTEUSE);
        produit = Produits.RECOLTEUSE;
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
    public void backToPlayer(Ressource r) {
        Inventaire.addToAvatarInv(r, 1);
        stock.remove(r, 1);
    }

    @Override
    public InventaireMachine getStock() {
        return stock;
    }

}
