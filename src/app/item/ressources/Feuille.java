package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Argent;
import item.marche.Marche.Produits;

public class Feuille extends Ressource {
    
    public Feuille(){
        name = "Feuille";
        prix = Argent.getPrice(Produits.FEUILLE); 
        try {
            File f1 = new File("./img/ressources/feuille.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
