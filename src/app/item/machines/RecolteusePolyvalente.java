package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class RecolteusePolyvalente extends Recolteuse {

    public RecolteusePolyvalente() {
        this.name = "RecolteusePolyvalente";
        this.produit = Produits.RECOLTEUSE_POLYVALENTE;
        this.prix = Argent.getPrice(produit);
        this.prix = Argent.getPrice(Produits.RECOLTEUSE_POLYVALENTE);
        try {
            this.image = ImageIO.read(new File("./img/machines/recolteuse.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
