
package item.machines;

import java.io.File;
import javax.imageio.ImageIO;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.bag.Argent;
import item.marche.Marche.Produits;
import item.ressources.Ressource;

public class RecolteuseRapide extends Recolteuse {

    public RecolteuseRapide() {
        this.name = "Récolteuse rapide";
        this.stock = new InventaireMachine(this);
        this.stock.add(res, 0);
        this.prix = Argent.getPrice(Produits.RECOLTEUSE_RAPIDE);
        try {
            File f1 = new File("./img/machines/recolteuse.png");
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
    public void backToPlayer(Ressource r) {
        Inventaire.addToAvatarInv(r, 1);
        stock.remove(r, 1);
    }
}
