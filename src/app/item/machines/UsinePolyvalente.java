package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class UsinePolyvalente extends Usine {

    public UsinePolyvalente() {
        name = "Usine polyvalente";
        produit = Produits.USINE_POLYVALENTE;
        prix = Argent.getPrice(produit);
        try {
            image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
