package machines;

import bag.Inventaire;
import ressources.Ressource;
import java.awt.image.BufferedImage;

public abstract class Machine {
    public Inventaire stock;
    public Ressource res;
    public String etat;
    public BufferedImage img;
}
