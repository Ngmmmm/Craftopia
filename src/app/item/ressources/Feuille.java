package item.ressources;

import item.bag.Argent;
import item.marche.Marche.Produits;
import java.io.File;
import javax.imageio.ImageIO;

public class Feuille extends Ressource {
    
    public Feuille(){
        name = "Feuille";
        prix = Argent.getPrice(Produits.FEUILLE); 
        produit = Produits.FEUILLE;
        time = 0;
        try {
            File f1 = new File("./img/ressources/feuille.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
