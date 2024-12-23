package item.machines;

import java.io.File;

import javax.imageio.ImageIO;

public class UsineXL extends Usine {

    public UsineXL() {
        name = "Usine XL";
        try {
            image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
