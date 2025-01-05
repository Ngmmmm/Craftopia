package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class UsineXL extends Usine {

    public UsineXL() {
        this.name = "UsineXL";
        this.produit = Produits.USINE_XL;
        this.prix = Argent.getPrice(this.produit);
        this.capacite = 10;
        try {
            this.image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
