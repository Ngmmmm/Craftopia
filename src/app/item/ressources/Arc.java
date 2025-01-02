package item.ressources;

import java.io.File;

import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class Arc extends Ressource {

    public Arc() {
        name = "Arc";
        prix = Argent.getPrice(Produits.ARC);
        produit = Produits.ARC;
        try {
            File f1 = new File("./img/ressources/bow.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
