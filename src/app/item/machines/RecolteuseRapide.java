
package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import java.io.File;
import javax.imageio.ImageIO;

public class RecolteuseRapide extends Recolteuse {

    public RecolteuseRapide() {
        this.name = "RecolteuseRapide";
        this.produit = Produits.RECOLTEUSE_RAPIDE;
        this.prix = Argent.getPrice(this.produit);
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
    public void backToPlayer(Ressource r, int quantity) {
        Inventaire.addToAvatarInv(r, quantity);
        stock.remove(r, quantity);
    }
}
