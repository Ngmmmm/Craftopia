package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class BlocPierre extends Ressource {

    public BlocPierre() {
        name = "Bloc Pierre";
        produit = Produits.BLOC_PIERRE;
        prix = Argent.getPrice(Produits.BLOC_PIERRE);
        try {
            File f1 = new File("./img/ressources/pierre.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
