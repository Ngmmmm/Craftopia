package item.machines;

import java.io.File;

import javax.imageio.ImageIO;
import item.bag.Inventaire;
import item.bag.Argent;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import item.sprites.Avatar;

public class RecolteuseFragile extends Recolteuse {

    public RecolteuseFragile() {
        this.name = "Récolteuse fragile";
        this.stock = new Inventaire();
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
