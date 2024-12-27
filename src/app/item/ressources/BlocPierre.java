package item.ressources;

import java.io.File;

import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class BlocPierre extends Ressource {

    public BlocPierre() {
        name = "Bloc de Pierre";
        prix = Argent.getPrice(Produits.BLOC_PIERRE);
        try {
            File f1 = new File("./img/ressources/blocPierre.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
