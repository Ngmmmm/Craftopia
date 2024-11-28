package tile;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[10];
        this.mapTileNum = new int[gp.NB_COLONNE_MAX][gp.NB_LIGNE_MAX];
        
        getTileImage();
        loadMap();
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

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("./src/maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int ligne = 0;
            while (col < gp.NB_COLONNE_MAX && ligne < gp.NB_LIGNE_MAX) {
                
                String line = br.readLine();
                while (col < gp.NB_COLONNE_MAX) {
                    
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][ligne] = num;
                    col++;
                }
                if (col == gp.NB_COLONNE_MAX) {
                    col = 0;
                    ligne++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Impossible de charger la carte: " + e);
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int ligne = 0;
        Point pos = new Point(0, 0);

        while (col < gp.NB_COLONNE_MAX && ligne < gp.NB_LIGNE_MAX) {

            int tileNum = mapTileNum[col][ligne];

            g2.drawImage(tile[tileNum].image, pos.x, pos.y, gp.TAILLE_BLOC, gp.TAILLE_BLOC, null);
            col++;
            pos.x += gp.TAILLE_BLOC;
            if (col == gp.NB_COLONNE_MAX) {
                col = 0;
                pos.x = 0;
                ligne++;
                pos.y += gp.TAILLE_BLOC;
            }
        }
    }

}
