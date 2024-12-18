package machines;

import bag.Inventaire;
import main.GamePanel;

import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;
import ressources.Ressource;
import sprites.Avatar;

public class Recolteuse extends Machine implements MachineBehavior {

    public Recolteuse(Ressource res) {
        this.res = res;
        this.stock = new Inventaire();
        this.stock.add(res, 0);
        try {
            File f1 = new File("./img/machines/recolteuse.png");
            img = ImageIO.read(f1);
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

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

}
