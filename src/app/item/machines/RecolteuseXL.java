package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class RecolteuseXL extends Recolteuse {

    public RecolteuseXL() {
        this.name = "RecolteuseXL";
        this.produit = Produits.RECOLTEUSE_XL;
        this.prix = Argent.getPrice(this.produit);
        this.capacite = 10;
        try {
            this.image = ImageIO.read(new File("./img/machines/recolteuse.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
