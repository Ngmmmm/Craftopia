package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class Bois extends Ressource {

    public Bois() {
        name = "Bois";
        prix = Argent.getPrice(Produits.BOIS);
        try {
            File f1 = new File("./img/ressources/bois.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        time = 300;
    }

}
