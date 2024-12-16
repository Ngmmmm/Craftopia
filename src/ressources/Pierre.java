package ressources;

import java.io.File;

import javax.imageio.ImageIO;

import bag.Argent;

public class Pierre extends Ressource{

    public Pierre(){
        name = "pierre";
        prix = Argent.getVALPIERRE();
        try {
            File f1 = new File("./img/object/fer.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        time = 0;
    }
    
}
