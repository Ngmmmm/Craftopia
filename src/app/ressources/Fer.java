package ressources;

import bag.Argent;
import java.io.File;
import javax.imageio.ImageIO;

public class Fer extends Ressource {
    
    public Fer(){
        name = "fer";
        prix = Argent.getVALFER();
        try {
            File f1 = new File("./img/ressources/feuille.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
