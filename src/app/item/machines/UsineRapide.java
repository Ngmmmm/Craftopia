package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class UsineRapide extends Usine {

    public UsineRapide() {
        name = "UsineRapide";
        produit = Produits.USINE_RAPIDE;
        prix = Argent.getPrice(produit);
        try {
            image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}