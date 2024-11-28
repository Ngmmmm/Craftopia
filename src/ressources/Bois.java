package ressources;

import bag.Argent;
import java.io.File;
import javax.imageio.ImageIO;

public class Bois extends Objet {
    
    public Bois(){
        name = "bois";
        prix = Argent.getVALBOIS(); 
        try {
            File f1 = new File("./img/object/bois.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
