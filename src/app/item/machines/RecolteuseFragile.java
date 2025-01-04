package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import java.io.File;
import javax.imageio.ImageIO;

public class RecolteuseFragile extends Recolteuse {

    public RecolteuseFragile() {
        this.name = "RecolteuseFragile";
        this.stock = new InventaireMachine(this);
        produit = Produits.RECOLTEUSE_FRAGILE;
        prix = Argent.getPrice(produit);
        this.stock.add(res, 0);
        this.prix = Argent.getPrice(Produits.RECOLTEUSE_FRAGILE);
        try {
            File f1 = new File("./img/machines/recolteuse.png");
            this.image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRessource() {
        this.res.increaseQuantity(1);
    }

    @Override
    public void backToPlayer(Ressource r,int quantity) {
        Inventaire.addToAvatarInv(r, quantity);
        stock.remove(r, quantity);
    }

}
