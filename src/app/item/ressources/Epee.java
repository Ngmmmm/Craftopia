package item.ressources;

import java.io.File;

import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class Epee extends Ressource {

    public Epee() {
        name = "Épée";
        prix = Argent.getPrice(Produits.EPEE);
        try {
            File f1 = new File("./img/ressources/sword.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
