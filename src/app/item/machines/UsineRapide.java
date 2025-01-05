package item.machines;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class UsineRapide extends Usine {

    public UsineRapide() {
        this.name = "UsineRapide";
        this.produit = Produits.USINE_RAPIDE;
        this.prix = Argent.getPrice(this.produit);
        this.time = 5;
        try {
            this.image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}