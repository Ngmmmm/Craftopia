package item.machines;

import java.io.File;

import javax.imageio.ImageIO;

public class UsinePolyvalente extends Usine {

    public UsinePolyvalente() {
        name = "Usine polyvalente";
        try {
            image = ImageIO.read(new File("./img/machines/usine.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
