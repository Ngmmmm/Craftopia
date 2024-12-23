package item.machines;

import java.io.File;

import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class RecolteuseXL extends Recolteuse {

    public RecolteuseXL() {
        this.name = "Récolteuse XL";
        this.stock.add(res, 0);
        this.prix = Argent.getPrice(Produits.RECOLTEUSE_XL);
        try {
            this.image = ImageIO.read(new File("./img/machines/recolteuse_xl.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
