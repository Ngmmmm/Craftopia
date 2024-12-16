package machines;

import bag.Inventaire;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import ressources.Ressource;

public class Recolteuse {
    
    public Inventaire inv;
    public Ressource res;
    public String etat;
    public BufferedImage img;

    public Recolteuse(Ressource res){
        this.res = res;
        this.inv = new Inventaire();
        this.inv.add(res, 0);
        try {
            File f1 = new File("./img/object/bois.png");
            img = ImageIO.read(f1);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addRessource(){
        this.res.increaseQuantity(res, 1);
    }
    
}
