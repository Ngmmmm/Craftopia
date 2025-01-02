package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;
import item.bag.Argent;
import item.marche.Marche.Produits;

public class BouleFeuille extends Ressource {
    public BouleFeuille() {
        name = "Boule de Feuille";
        prix = Argent.getPrice(Produits.BOULE_FEUILLE);
        produit = Produits.BOULE_FEUILLE;
        try {
            File f1 = new File("./img/ressources/leafBall.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
