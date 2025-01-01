package main;

import item.sprites.Avatar;
import item.sprites.SuperObjet;
import java.awt.BorderLayout; // Importation pour définir les dimensions des composants
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; // Importation pour dessiner des composants
import java.util.ArrayList; // Importation de JPanel pour créer le panneau de jeu

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import tile.TileManager;

// La classe GamePanel hérite de JPanel et implémente Runnable pour le fonctionnement via un thread
public class GamePanel extends JPanel implements Runnable {

    final int TAILLE_BLOC_ORIGINAL = 16; // Taille de base d'un bloc en pixels
    final int FACTEUR_ECHELLE = 3; // Facteur d'agrandissement appliqué à chaque bloc
    public final int TAILLE_BLOC = TAILLE_BLOC_ORIGINAL * FACTEUR_ECHELLE; // Taille finale d'un bloc (48px)
    public final int NB_COLONNE_MAX = 16; // Nombre maximal de colonnes visibles à l'écran
    public final int NB_LIGNE_MAX = 12; // Nombre maximal de lignes visibles à l'écran
    public final int LARGEUR_ECRAN = TAILLE_BLOC * NB_COLONNE_MAX; // Largeur totale de l'écran
    public final int HAUTEUR_ECRAN = TAILLE_BLOC * NB_LIGNE_MAX; // Hauteur totale de l'écran

    public final int NB_COLONNE_MAP_MAX = 52; // Nombre de colonnes maximal sur la carte
    public final int NB_LIGNE_MAP_MAX = 50; // Nombre de lignes maximal sur la carte
    public final int LARGEUR_MAP = TAILLE_BLOC * NB_COLONNE_MAP_MAX; // Largeur totale de la carte (2496px)
    public final int HAUTEUR_MAP = TAILLE_BLOC * NB_LIGNE_MAP_MAX; // Hauteur totale de la carte (2400px)

    int FPS = 60; // Images par seconde pour maintenir la fluidité du jeu

    TileManager tileM = new TileManager(this); // Gestionnaire des blocs (tiles) pour dessiner la carte
    KeyHandler keyH = new KeyHandler(this); // Gestionnaire des touches clavier
    Thread gameThread; // Thread du jeu, pour exécuter le jeu en parallèle
    public CheckCollision collision = new CheckCollision(this); // Gestionnaire des collisions

    public AssetSetter aSetter = new AssetSetter(this); // Gestion des objets (ressources, ennemis...)

    public Ui ui = new Ui(this); // Gestionnaire de l'interface utilisateur

    public Avatar avatar = new Avatar(this, keyH); // L'avatar contrôlé par le joueur
    public static ArrayList<SuperObjet> obj = new ArrayList<>(); // Tableau pour stocker les objets à afficher

    public JLayeredPane layeredPane; // Panneau en couches pour superposer les éléments du jeu

    /**
     * Constructeur pour initialiser le panneau de jeu.
     */
    public GamePanel(JLayeredPane layeredPane) {
        super(); // Appel du constructeur de la classe parente
        this.layeredPane = layeredPane; // Initialisation du panneau en couches
        this.setPreferredSize(new Dimension(LARGEUR_ECRAN, HAUTEUR_ECRAN)); // Définir la taille du panneau
        this.setBackground(Color.BLACK); // Couleur de fond noire
        this.setDoubleBuffered(true); // Activer le double buffering pour un rendu fluide
        this.addKeyListener(keyH); // Ajouter le gestionnaire de clavier
        this.setFocusable(true); // Permettre au panneau de capturer les événements clavier
        this.setLayout(new BorderLayout());

        ui.initializeComponents(); // Initialize components after GamePanel is fully configured
    }

    /**
     * Configure les paramètres initiaux du jeu.
     */
    public void setupGame() {
        aSetter.setAllItems();
        Ui.gameState = Ui.TITLE_STATE; // Définit l'état du jeu à l'écran de titre
        
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

    
}
