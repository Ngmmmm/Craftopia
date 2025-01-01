package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class Bois extends Ressource {

    public Bois() {
        name = "Bois";
        prix = Argent.getPrice(Produits.BOIS);
        produit = Produits.BOIS;
        waitIme = true;
        try {
            File f1 = new File("./img/ressources/bois.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        time = 300;
    }

}
