package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class UsineXL extends Usine {

    public UsineXL() {
        name = "Usine XL";
        produit = Produits.USINE_XL;
        prix = Argent.getPrice(produit);
        try {
            image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
