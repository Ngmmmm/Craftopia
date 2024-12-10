package tile;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[30];
        this.mapTileNum = new int[gp.NB_COLONNE_MAP_MAX][gp.NB_LIGNE_MAP_MAX];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("./img/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("./img/tiles/water.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("./img/tiles/brickwall.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("./img/tiles/Cloture.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("./img/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("./img/tiles/ClotureDroiteVerticale.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("./img/tiles/ClotureGaucheVerticale.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("./img/tiles/ClotureInfDroite.png"));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new File("./img/tiles/ClotureInfGauche.png"));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new File("./img/tiles/ClotureSupDroite.png"));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new File("./img/tiles/ClotureSupGauche.png"));
            tile[10].solidArea = new Rectangle();
            tile[10].collision = true;

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
        try {
            BufferedReader reader = new BufferedReader(new FileReader("maps/bigmap.txt"));
            int col = 0;
            int ligne = 0;

            while (col < gp.NB_COLONNE_MAP_MAX && ligne < gp.NB_LIGNE_MAP_MAX) {
                String line = reader.readLine();

                while (col < gp.NB_COLONNE_MAP_MAX) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][ligne] = num;
                    col++;
                }
                if (col == gp.NB_COLONNE_MAP_MAX) {
                    col = 0;
                    ligne++;
                }
            }
            reader.close();

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la carte : " + e.getMessage());
        }
    }

    public void draw(Graphics2D g2) {
        int mapCol = 0;
        int mapLigne = 0;
        while (mapCol < gp.NB_COLONNE_MAP_MAX && mapLigne < gp.NB_LIGNE_MAP_MAX) {

            int tileNum = mapTileNum[mapCol][mapLigne];

            int mapX = mapCol * gp.TAILLE_BLOC;
            int mapY = mapLigne * gp.TAILLE_BLOC;
            int screenX = mapX - gp.avatar.mapPoint.x + gp.avatar.screenX;
            int screenY = mapY - gp.avatar.mapPoint.y + gp.avatar.screenY;

            // Vérifiez si les coordonnées sont dans la zone visible
            if (mapX + gp.TAILLE_BLOC > gp.avatar.mapPoint.x - gp.avatar.screenX
                    && mapX - gp.TAILLE_BLOC < gp.avatar.mapPoint.x + gp.avatar.screenX
                    && mapY + gp.TAILLE_BLOC > gp.avatar.mapPoint.y - gp.avatar.screenY
                    && mapY - gp.TAILLE_BLOC < gp.avatar.mapPoint.y + gp.avatar.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TAILLE_BLOC, gp.TAILLE_BLOC, null);
            }

            mapCol++;

            if (mapCol == gp.NB_COLONNE_MAP_MAX) {
                mapCol = 0;
                mapLigne++;
            }
        }
    }
}
