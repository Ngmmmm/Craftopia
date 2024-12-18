package machines;

import bag.Inventaire;
import ressources.Ressource;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Machine {
    public Inventaire stock;
    public Ressource res;
    public String etat;
    public boolean collision = true;
    public BufferedImage img;
    public Point pos = new Point();
    public int prix;
    public int time;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
}
