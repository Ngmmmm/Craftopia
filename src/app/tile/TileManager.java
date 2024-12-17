package tile;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

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

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("./img/tiles/water.png"));
            tile[1].collision = true; // Bloque les sprites
            tile[1].isOccupied = true; // Empêche de pouvoir poser un objet sur le bloc

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("./img/tiles/brickwall.png"));
            tile[2].collision = true;
            tile[2].isOccupied = true; // Empêche de pouvoir poser un objet sur le bloc

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("./img/tiles/Cloture.png"));
            tile[3].collision = true;
            tile[3].isOccupied = true; // Empêche de pouvoir poser un objet sur le bloc

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("./img/tiles/tree.png"));
            tile[4].collision = true;
            tile[4].isOccupied = true; // Empêche de pouvoir poser un objet sur le bloc

            // Chargement des blocs supplémentaires
            

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
    public void draw(Graphics2D g2) {
        int mapCol = 0;
        int mapLigne = 0;

        while (mapCol < gp.NB_COLONNE_MAP_MAX && mapLigne < gp.NB_LIGNE_MAP_MAX) {
            int tileNum = mapTileNum[mapCol][mapLigne];

            // Coordonnées de la bloc sur la carte
            int mapX = mapCol * gp.TAILLE_BLOC;
            int mapY = mapLigne * gp.TAILLE_BLOC;

            // Coordonnées de la bloc à l'écran
            int screenX = mapX - gp.avatar.mapPoint.x + gp.avatar.screenX;
            int screenY = mapY - gp.avatar.mapPoint.y + gp.avatar.screenY;

            // Vérifie si les coordonnées sont dans la zone visible
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
