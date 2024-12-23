package item.machines;

import java.io.File;

import javax.imageio.ImageIO;

public class UsineFragile extends Usine {

    public UsineFragile() {
        name = "Usine fragile";
        try {
            image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
