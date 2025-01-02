package item.ressources;

import java.io.File;

import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class Hache extends Ressource {

    public Hache() {
        name = "Hache";
        prix = Argent.getPrice(Produits.HACHE);
        produit = Produits.HACHE;
        try {
            File f1 = new File("./img/ressources/axe.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
