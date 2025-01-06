package main;

import item.sprites.Avatar;
import item.sprites.SuperObjet;
import main.interfacegraphique.Ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension; // Importation pour définir les dimensions des composants
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList; // Importation pour dessiner des composants
import javax.swing.JLayeredPane; // Importation de JPanel pour créer le panneau de jeu
import javax.swing.JPanel;
import save.SaveEtatMap;
import save.SaveInvAvatar;
import tile.TileManager;

/**
 * La classe GamePanel hérite de JPanel et implémente Runnable pour le
 * fonctionnement via un thread.
 *
 * <p>
 * Cette classe gère le panneau de jeu principal, y compris le dessin des
 * éléments,
 * la gestion des entrées du clavier, la gestion des collisions, et l'interface
 * utilisateur.
 * Elle est responsable du cycle de vie du jeu et de la mise à jour continue de
 * l'état du jeu.
 * </p>
 *
 * <p>
 * Les méthodes incluent la configuration initiale du jeu, le démarrage du
 * thread du jeu,
 * la mise à jour de l'état du jeu, et le dessin des éléments à l'écran.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class GamePanel extends JPanel implements Runnable {

    final static int TAILLE_BLOC_ORIGINAL = 16; // Taille de base d'un bloc en pixels
    final static int FACTEUR_ECHELLE = 3; // Facteur d'agrandissement appliqué à chaque bloc
    public static final int TAILLE_BLOC = TAILLE_BLOC_ORIGINAL * FACTEUR_ECHELLE; // Taille finale d'un bloc (48px)
    public final int NB_COLONNE_MAX = 16; // Nombre maximal de colonnes visibles à l'écran
    public final int NB_LIGNE_MAX = 12; // Nombre maximal de lignes visibles à l'écran
    public final int LARGEUR_ECRAN = TAILLE_BLOC * NB_COLONNE_MAX; // Largeur totale de l'écran
    public final int HAUTEUR_ECRAN = TAILLE_BLOC * NB_LIGNE_MAX; // Hauteur totale de l'écran

    public final int NB_COLONNE_MAP_MAX = 52; // Nombre de colonnes maximal sur la carte
    public final int NB_LIGNE_MAP_MAX = 50; // Nombre de lignes maximal sur la carte
    public final int LARGEUR_MAP = TAILLE_BLOC * NB_COLONNE_MAP_MAX; // Largeur totale de la carte (2496px)
    public final int HAUTEUR_MAP = TAILLE_BLOC * NB_LIGNE_MAP_MAX; // Hauteur totale de la carte (2400px)

    int FPS = 60; // Images par seconde pour maintenir la fluidité du jeu

    public TileManager tileM = new TileManager(this); // Gestionnaire des blocs (tiles) pour dessiner la carte
    KeyHandler keyH = new KeyHandler(this); // Gestionnaire des touches clavier
    Thread gameThread; // Thread du jeu, pour exécuter le jeu en parallèle
    public CheckCollision collision = new CheckCollision(this); // Gestionnaire des collisions

    public AssetSetter aSetter = new AssetSetter(this); // Gestion des objets (ressources, ennemis...)

    public Ui ui = new Ui(this); // Gestionnaire de l'interface utilisateur

    public SaveInvAvatar saveInvAvatar = new SaveInvAvatar(this);
    public SaveEtatMap saveEtatMap = new SaveEtatMap(this);

    public Avatar avatar = new Avatar(this, keyH); // L'avatar contrôlé par le joueur

    public static ArrayList<SuperObjet> obj = new ArrayList<>(); // Tableau pour stocker les objets à afficher

    static boolean editMarche = true;
    public static boolean editInv = true;
    private Sound sound;

    public JLayeredPane layeredPane; // Panneau en couches pour superposer les éléments du jeu

    /**
     * Constructeur pour initialiser le panneau de jeu.
     */
    public GamePanel(JLayeredPane layeredPane) {
        super(); // Appel du constructeur de la classe parente
        this.layeredPane = layeredPane; // Initialisation du panneau en couches
        sound = new Sound();
        this.setPreferredSize(new Dimension(LARGEUR_ECRAN, HAUTEUR_ECRAN)); // Définir la taille du panneau
        this.setBackground(Color.BLACK); // Couleur de fond noire
        this.setDoubleBuffered(true); // Activer le double buffering pour un rendu fluide
        this.addKeyListener(keyH); // Ajouter le gestionnaire de clavier
        this.setFocusable(true); // Permettre au panneau de capturer les événements clavier
        this.setLayout(new BorderLayout());

        ui.initializeComponents(); // Initialise les composants après la configuration complète de GamePanel
    }

    /**
     * Configure les paramètres initiaux du jeu.
     */
    public void setupGame() {
        Ui.gameState = Ui.TITLE_STATE; // Définit l'état du jeu à l'écran de titre
        sound.loopMusic(1);
    }

    /**
     * Démarre le thread du jeu.
     */
    public void startGameThread() {
        gameThread = new Thread(this); // Crée un nouveau thread pour le jeu
        gameThread.start(); // Lance l'exécution du thread
    }

    /**
     * Boucle principale du thread du jeu.
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // Intervalle entre chaque image
        double delta = 0; // Stocke le temps accumulé
        long lastTime = System.nanoTime(); // Temps initial
        long currentTime; // Temps courant

        while (gameThread != null) { // Boucle tant que le thread est actif
            currentTime = System.nanoTime(); // Obtenir le temps actuel
            delta += (currentTime - lastTime) / drawInterval; // Calculer le delta de temps
            lastTime = currentTime; // Mettre à jour le temps précédent

            if (delta >= 1) { // Si une itération est prête
                update(Ui.gameState); // Met à jour les éléments du jeu
                repaint(); // Redessine l'écran
                delta--; // Réduit le delta
            }
        }
    }

    /**
     * Met à jour les éléments du jeu.
     */
    public void update(int gameState) {
        avatar.update(gameState); // Met à jour l'état de l'avatar (position, etc.)
    }

    /**
     * Dessine les éléments du jeu.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Ajoutez ici le code pour dessiner les éléments du jeu
        ui.draw(g);
    }

    /**
     * Obtient tous les points des objets présents dans le jeu.
     * 
     * @return un tableau de points correspondant aux positions des objets.
     */
    public Point[] getAllPoints() {
        Point[] points = new Point[obj.size()];
        for (int i = 0; i < obj.size(); i++) {
            points[i] = obj.get(i).pos;
        }
        return points;
    }

    /**
     * Active ou désactive la musique.
     */
    public void toggleMusic() {
        if (sound.isPlaying()) {
            sound.stopMusic();
        } else {
            sound.playMusic(1);
        }
    }
}
