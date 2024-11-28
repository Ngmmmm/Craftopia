package tile;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        try (BufferedReader reader = new BufferedReader(new FileReader("maps/map.txt"))) {
            int ligne = 0;
            String line;
    
            while ((line = reader.readLine()) != null && ligne < gp.NB_LIGNE_MAX) {
                String[] numbers = line.trim().split(" ");
                for (int col = 0; col < gp.NB_COLONNE_MAX; col++) {
                    try {
                        mapTileNum[col][ligne] = Integer.parseInt(numbers[col]);
                    } catch (NumberFormatException e) {
                        System.err.println("Valeur invalide détectée à la ligne " + ligne + ", colonne " + col);
                        mapTileNum[col][ligne] = 0; // Valeur par défaut
                    }
                }
                ligne++;
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte : " + e.getMessage());
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
