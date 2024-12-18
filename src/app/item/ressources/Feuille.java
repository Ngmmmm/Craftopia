package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Argent;

public class Feuille extends Ressource {
    
    public Feuille(){
        name = "feuille";
        prix = Argent.getVALFEUILLE(); 
        try {
            File f1 = new File("./img/ressources/feuille.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
