
package item.machines;

import java.io.File;
import javax.imageio.ImageIO;
import item.bag.Inventaire;
import item.bag.Argent;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import item.sprites.Avatar;

public class RecolteuseRapide extends Recolteuse {

    public RecolteuseRapide() {
        this.name = "Récolteuse rapide";
        this.stock = new Inventaire();
        this.stock.add(res, 0);
        this.prix = Argent.getPrice(Produits.RECOLTEUSE_RAPIDE);
        try {
            File f1 = new File("./img/machines/recolteuse_rapide.png");
            this.image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRessource() {
        this.res.increaseQuantity(2);
    }

    @Override
    public void backToPlayer(Avatar player, Ressource r) {
        player.inv.add(r, 1);
        stock.remove(r, 1);
    }
}
