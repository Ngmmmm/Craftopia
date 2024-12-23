package item.machines;

import java.io.File;

import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class RecolteusePolyvalente extends Recolteuse {

    public RecolteusePolyvalente() {
        this.name = "Récolteuse polyvalente";
        this.stock.add(res, 0);
        this.prix = Argent.getPrice(Produits.RECOLTEUSE_POLYVALENTE);
        try {
            this.image = ImageIO.read(new File("./img/machines/recolteuse_polyvalente.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
