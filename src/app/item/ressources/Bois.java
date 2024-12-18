package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Argent;

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
        time = 300;
        collision = true;
    }
    
}
