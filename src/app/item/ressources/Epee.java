package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class Epee extends Ressource {

    public Epee() {
        name = "Epee";
        prix = Argent.getPrice(Produits.EPEE);
        produit = Produits.EPEE;
        try {
            File f1 = new File("./img/ressources/sword.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
