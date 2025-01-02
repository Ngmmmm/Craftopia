package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class RecolteuseXL extends Recolteuse {

    public RecolteuseXL() {
        this.name = "RecolteuseXL";
        this.stock.add(res, 0);
        produit = Produits.RECOLTEUSE_XL;
        prix = Argent.getPrice(produit);
        try {
            this.image = ImageIO.read(new File("./img/machines/recolteuse.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
