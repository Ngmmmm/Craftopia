package item.ressources;

import java.io.File;

import javax.imageio.ImageIO;

import item.bag.Argent;

public class BlocPierre extends Ressource {

    public BlocPierre(){
        name = "blocpierre";
        prix = Argent.getVALBLOCPIERRE(); 
        try {
            File f1 = new File("./img/ressources/pierre.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
