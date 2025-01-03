package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;


public class RessourceVide extends Ressource {
    public RessourceVide() {
        name = "Vide";
        prix = 0;
        produit = null;
        try {
            File f1 = new File("./img/ressources/vide.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
