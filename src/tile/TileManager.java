package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("./img/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("./img/tiles/water.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("./img/tiles/brickwall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int lignes = 0;
        int x = 0;
        int y = 0;
        while (col < gp.nbColonneMax && lignes < gp.nbLigneMax) {
            g2.drawImage(tile[0].image, x, y, gp.tailleBloc, gp.tailleBloc, null);
            col++;
            x += gp.tailleBloc;
            if (col == gp.nbColonneMax) {
                col = 0;
                x = 0;
                lignes++;
                y += gp.tailleBloc;
            }
        }
    }

}
