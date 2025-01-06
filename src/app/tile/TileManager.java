package tile;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Point;

/**
 * La classe TileManager gère les blocs du jeu, y compris leur chargement,
 * leurs propriétés,
 * la lecture de la carte depuis un fichier, et leur affichage à l'écran.
 * <p>
 * Elle utilise un tableau de blocs pour définir les différentes textures et un
 * tableau 2D
 * pour représenter la disposition des blocs sur la carte du jeu.
 * </p>
 * 
 * @author Ouahid Houat
 * @version 1.0
 */
public class TileManager {
    GamePanel gp; // Référence au panneau de jeu principal
    public Tile[] tile; // Tableau contenant les différents blocs
    public int[][] mapTileNum; // Tableau 2D représentant la carte des blocs
    public static Point spawnPoint = new Point();
    /**
     * Constructeur de la classe TileManager.
     * Initialise le tableau des blocs et charge la disposition des blocs depuis
     * un fichier.
     * 
     * @param gp Le panneau de jeu principal, utilisé pour accéder aux paramètres du
     *           jeu.
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[30]; // Initialisation du tableau de blocs (30 types maximum)
        this.mapTileNum = new int[gp.NB_COLONNE_MAP_MAX][gp.NB_LIGNE_MAP_MAX]; // Carte de blocs

        getTileImage(); // Chargement des images des blocs
        loadMap(); // Chargement de la disposition des blocs depuis un fichier
    }

    /**
     * Charge les images des blocs et configure leurs propriétés (exemple :
     * collision).
     * <p>
     * Chaque bloc est définie avec une image spécifique, et certaines blocs sont
     * configurées
     * comme "collisantes", ce qui signifie qu'elles bloquent les sprites.
     * </p>
     */
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("./img/tiles/grass.png"));
            tile[0].isGrass = true;
            

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("./img/tiles/water.png"));
            tile[1].collision = true; // Bloque les sprites
             // Empêche de pouvoir poser un objet sur le bloc

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("./img/tiles/brickwall.png"));
            tile[2].collision = true;
            ; // Empêche de pouvoir poser un objet sur le bloc

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("./img/tiles/Cloture.png"));
            tile[3].collision = true;
             // Empêche de pouvoir poser un objet sur le bloc

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("./img/tiles/tree.png"));
            tile[4].collision = true;
             // Empêche de pouvoir poser un objet sur le bloc

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("./img/tiles/grass.png"));
            

            // Chargement des blocs supplémentaires
            // tile[5] = new Tile();
            // tile[5].image = ImageIO.read(new File("./img//.png"));
            // tile[5].collision = true;

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
            tile[10].solidArea = new Rectangle(); // Définit une zone solide pour ce bloc
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(new File("./img/tiles/grass.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(new File("./img/tiles/Flowers.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(new File("./img/tiles/MaturedPlant.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(new File("./img/tiles/GrowingTile.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(new File("./img/tiles/Seeds.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge la carte de la disposition des blocs depuis un fichier texte.
     * <p>
     * Chaque ligne du fichier représente une ligne de la carte, où les nombres
     * correspondent aux indices des blocs dans le tableau {@code tile}.
     * </p>
     */
    public void loadMap() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("maps/bigmap.txt"));
            int col = 0;
            int ligne = 0;

            while (col < gp.NB_COLONNE_MAP_MAX && ligne < gp.NB_LIGNE_MAP_MAX) {
                String line = reader.readLine();
                

                while (col < gp.NB_COLONNE_MAP_MAX) {
                    String[] numbers = line.split(" "); // Découpe la ligne en nombres
                    int num = Integer.parseInt(numbers[col]); // Convertit en entier
                    if(num == 5){
                        spawnPoint.x = (col * GamePanel.TAILLE_BLOC);
                        spawnPoint.y =(ligne * GamePanel.TAILLE_BLOC);
                    }
                    mapTileNum[col][ligne] = num; // Affecte à la carte
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

    /**
     * Dessine les blocs visibles à l'écran en fonction de la position de l'avatar.
     * <p>
     * Cette méthode ne dessine que les blocs dans la zone visible pour optimiser
     * les performances.
     * </p>
     * 
     * @param g2 L'objet Graphics2D utilisé pour dessiner les blocs.
     */
    public void draw(Graphics2D g2, double scaleX, double scaleY) {
        int mapCol = 0;
        int mapLigne = 0;

        while (mapCol < gp.NB_COLONNE_MAP_MAX && mapLigne < gp.NB_LIGNE_MAP_MAX) {
            int tileNum = mapTileNum[mapCol][mapLigne];

            // Coordonnées de la bloc sur la carte
            int mapX = mapCol * GamePanel.TAILLE_BLOC;
            int mapY = mapLigne * GamePanel.TAILLE_BLOC;

            // Coordonnées de la bloc à l'écran
            int screenX = mapX - gp.avatar.mapPoint.x + gp.avatar.screenX;
            int screenY = mapY - gp.avatar.mapPoint.y + gp.avatar.screenY;

            // Vérifie si les coordonnées sont dans la zone visible
            if (mapX + GamePanel.TAILLE_BLOC > gp.avatar.mapPoint.x - gp.avatar.screenX
                    && mapX - GamePanel.TAILLE_BLOC < gp.avatar.mapPoint.x + gp.avatar.screenX
                    && mapY + GamePanel.TAILLE_BLOC > gp.avatar.mapPoint.y - gp.avatar.screenY
                    && mapY - GamePanel.TAILLE_BLOC < gp.avatar.mapPoint.y + gp.avatar.screenY) {
                //+1 pour résoudre un bug due a l'arrondi double/int
                g2.drawImage(tile[tileNum].image,(int)(screenX*scaleX), (int)(screenY*scaleY), (int)(GamePanel.TAILLE_BLOC*scaleX)+1, (int)(GamePanel.TAILLE_BLOC*scaleY)+1, null);
            
            }

            mapCol++;

            if (mapCol == gp.NB_COLONNE_MAP_MAX) {
                mapCol = 0;
                mapLigne++;
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tile[mapTileNum[x/GamePanel.TAILLE_BLOC][y/GamePanel.TAILLE_BLOC]];
    }


}
