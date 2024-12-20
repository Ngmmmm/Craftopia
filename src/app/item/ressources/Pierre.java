package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class Pierre extends Ressource {
    public Pierre() {
        name = "Pierre";
        prix = Argent.getPrice(Produits.PIERRE);
        try {
            File f1 = new File("./img/ressources/pierre.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        time = 0;
    }
}
