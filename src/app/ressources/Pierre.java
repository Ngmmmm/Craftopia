package ressources;

import bag.Argent;
import java.io.File;
import javax.imageio.ImageIO;

public class Pierre extends Ressource {
    public Pierre(){
        name = "Pierre";
        prix = Argent.getVALPIERRE(); 
        try {
            File f1 = new File("./img/ressources/pierre.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        time = 0;
    
    
        collision = true;
    }
}
