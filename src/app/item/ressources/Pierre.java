package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class Pierre extends Ressource {
    public Pierre() {
        name = "Pierre";
        prix = Argent.getPrice(Produits.PIERRE);
        produit = Produits.PIERRE;
        try {
            File f1 = new File("./img/ressources/pierre.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        time = 3000;
    }
}
