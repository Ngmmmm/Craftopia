package item.machines;

import java.io.File;
import javax.imageio.ImageIO;

import item.bag.Inventaire;
import item.ressources.Ressource;
import item.sprites.Avatar;

public class Recolteuse extends Machine implements MachineBehavior {

    public Recolteuse(Ressource res) {
        this.res = res;
        this.stock = new Inventaire();
        this.stock.add(res, 0);
        try {
            File f1 = new File("./img/machines/recolteuse.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addRessource() {
        this.res.increaseQuantity(1);
    }

    @Override
    public void backToPlayer(Avatar player, Ressource r) {
        player.inv.add(r, 1);
        stock.remove(r, 1);
    }

    
}
