package item.ressources;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Argent;

public class Fer extends Ressource {

    public Fer() {
        name = "fer";
        prix = Argent.getVALFER();
        try {
            File f1 = new File("./img/ressources/fer.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
