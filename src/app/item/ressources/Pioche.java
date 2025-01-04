package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class Pioche extends Ressource{

    public Pioche(){
        name = "Pioche";
        prix = Argent.getPrice(Produits.PIOCHE); 
        produit = Produits.PIOCHE;
        time = 5;
        try {
            File f1 = new File("./img/ressources/pickaxe.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
