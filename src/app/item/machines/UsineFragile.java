package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class UsineFragile extends Usine {

    public UsineFragile() {
        name = "UsineFragile";
        produit = Produits.USINE_FRAGILE;
        prix = Argent.getPrice(produit);
        try {
            image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
