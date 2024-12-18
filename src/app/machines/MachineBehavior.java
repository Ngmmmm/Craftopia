package machines;

import java.awt.Graphics2D;

import main.GamePanel;
import ressources.Ressource;
import sprites.Avatar;

public interface MachineBehavior {
    void backToPlayer(Avatar player, Ressource r);

    void draw(Graphics2D g2, GamePanel gp);
}
