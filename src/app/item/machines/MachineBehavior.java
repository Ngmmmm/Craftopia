package item.machines;

import java.awt.Graphics2D;

import item.ressources.Ressource;
import item.sprites.Avatar;
import main.GamePanel;

public interface MachineBehavior {
    void backToPlayer(Avatar player, Ressource r);

    void draw(Graphics2D g2, GamePanel gp);
}
