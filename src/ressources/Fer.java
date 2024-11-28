package ressources;

import java.io.File;
import javax.imageio.ImageIO;

public class Fer extends Objet {
    
    public Fer(){
        name = "fer";
        prix = Argent.VALFER;
        try {
            File f1 = new File("./img/object/fer.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
