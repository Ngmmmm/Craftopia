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
        this.tile = new Tile[30];
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

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("./img/tiles/Cloture.png"));

            //tile[4] = new Tile();
            //tile[4].image = ImageIO.read(new File("./img/tiles/.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("./img/tiles/ClotureDroiteVerticale.png"));
            
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("./img/tiles/ClotureGaucheVerticale.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("./img/tiles/ClotureInfDroite.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new File("./img/tiles/ClotureInfGauche.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new File("./img/tiles/ClotureSupDroite.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new File("./img/tiles/ClotureSupGauche.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(new File("./img/tiles/EmptyTile.png"));
            
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(new File("./img/tiles/Flowers.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(new File("./img/tiles/MaturedPlant.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(new File("./img/tiles/GrowingTile.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(new File("./img/tiles/Seeds.png"));

            // tile[16] = new Tile();
            // tile[16].image = ImageIO.read(new File("./img/tiles/.png"));

            // tile[17] = new Tile();
            // tile[17].image = ImageIO.read(new File("./img/tiles/.png"));

            // tile[18] = new Tile();
            // tile[0].image = ImageIO.read(new File("./img/tiles/.png"));

            // tile[19] = new Tile();
            // tile[19].image = ImageIO.read(new File("./img/tiles/.png"));

            // tile[20] = new Tile();
            // tile[20].image = ImageIO.read(new File("./img/tiles/.png"));
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
