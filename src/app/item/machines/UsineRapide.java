package item.machines;

import java.io.File;

import javax.imageio.ImageIO;

public class UsineRapide extends Usine {

    public UsineRapide() {
        name = "Usine rapide";
        try {
            image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}