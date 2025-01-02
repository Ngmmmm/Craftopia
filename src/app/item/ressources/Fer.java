package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class Fer extends Ressource {

    public Fer() {
        name = "Fer";
        prix = Argent.getPrice(Produits.FER);
        produit = Produits.FER;
        try {
            File f1 = new File("./img/ressources/fer.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
