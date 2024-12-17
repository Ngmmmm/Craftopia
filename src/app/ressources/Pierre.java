package ressources;

import java.io.File;

import javax.imageio.ImageIO;

import bag.Argent;

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
<<<<<<< HEAD
        time = 0;
    }
    
=======
>>>>>>> 59943d1b5555aee25e5e07e909ba2e72b7f0b067
        collision = true;
    }
}
