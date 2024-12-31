package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import item.sprites.Avatar;
import java.io.File;
import javax.imageio.ImageIO;

public class RecolteuseFragile extends Recolteuse {

    public RecolteuseFragile() {
        this.name = "RecolteuseFragile";
        this.stock = new Inventaire();
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
    public void backToPlayer(Avatar player, Ressource r) {
        player.inv.add(r, 1);
        stock.remove(r, 1);
    }

}
