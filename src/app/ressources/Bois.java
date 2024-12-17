package ressources;

import bag.Argent;
import java.io.File;
import javax.imageio.ImageIO;

public class Bois extends Ressource {
    
    public Bois(){
        name = "bois";
        prix = Argent.getVALBOIS(); 
        try {
            File f1 = new File("./img/ressources/bois.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        time = 300;
=======
>>>>>>> 59943d1b5555aee25e5e07e909ba2e72b7f0b067
        collision = true;
    }
    
}
