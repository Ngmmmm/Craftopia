package ressources;

import bag.Argent;
import java.io.File;
import javax.imageio.ImageIO;

public class Pioche extends Ressource{

    public Pioche(){
        name = "pioche";
        prix = Argent.getVALPIOCHE(); 
        try {
            File f1 = new File("./img/object/bois.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
