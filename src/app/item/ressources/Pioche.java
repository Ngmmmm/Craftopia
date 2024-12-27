package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class Pioche extends Ressource{

    public Pioche(){
        name = "Pioche";
        prix = Argent.getPrice(Produits.PIOCHE); 
        try {
            File f1 = new File("./img/ressources/pickaxe.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
