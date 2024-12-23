package main;

import item.sprites.Avatar;
import item.sprites.SuperObjet;
import java.awt.BorderLayout; // Importation pour définir les dimensions des composants
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; // Importation pour dessiner des composants
import java.awt.Graphics2D; // Importation pour des dessins graphiques avancés
import java.awt.ScrollPane;
import java.util.ArrayList; // Importation de JPanel pour créer le panneau de jeu
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
    public double scaleXAct = getWidth() / LARGEUR_ECRAN;
    public double scaleYAct = getHeight() / HAUTEUR_ECRAN;

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

    public JPanel menuBar = new JPanel();
    public ScrollPane invPanel = new ScrollPane();
    public ScrollPane marchePanel = new ScrollPane();

    Ui ui = new Ui(this); // Gestionnaire de l'interface utilisateur
    public UiInteract itemManager = new UiInteract(this);
    public UiGame buttonGame = new UiGame(this);
    UiInventaire uiInv = new UiInventaire(this);

    public Avatar avatar = new Avatar(this, keyH); // L'avatar contrôlé par le joueur
    public ArrayList<SuperObjet> obj = new ArrayList<>(); // Tableau pour stocker les objets à afficher

    boolean initButton = false;
    boolean initButtonGame = false;
    boolean initInvPanel = false;
    public int gameState; // État actuel du jeu (titre, en cours, etc.)
    public final int TITLE_STATE = 0; // État correspondant à l'écran de titre
    public final int playState = 1; // État correspondant au jeu en cours
    public final int recolteuseState = 2;
    public final int craftState = 3;
    public final int inventaireState = 4;
    public final int shopState = 5;
    public final int settingsState = 6;

    /**
     * Constructeur pour initialiser le panneau de jeu.
     */
    public GamePanel(JPanel menuBar, ScrollPane invPanel, ScrollPane marchePanel) {
        this.setPreferredSize(new Dimension(LARGEUR_ECRAN, HAUTEUR_ECRAN)); // Définir la taille du panneau
        this.setBackground(Color.BLACK); // Couleur de fond noire
        this.setDoubleBuffered(true); // Activer le double buffering pour un rendu fluide
        this.addKeyListener(keyH); // Ajouter le gestionnaire de clavier
        this.setFocusable(true); // Permettre au panneau de capturer les événements clavier
        this.setLayout(new BorderLayout());
        this.menuBar = menuBar;
        this.invPanel = invPanel;
        this.marchePanel = marchePanel;

    }

    /**
     * Configure les paramètres initiaux du jeu.
     */
    public void setupGame() {
        aSetter.setAllItems();
        gameState = TITLE_STATE;
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
                update(gameState); // Met à jour les éléments du jeu
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
        Graphics2D g2 = (Graphics2D) g;
        double scaleX = (double) getWidth() / LARGEUR_ECRAN;
        double scaleY = (double) getHeight() / HAUTEUR_ECRAN;
        // Appliquer les transformations pour mettre à l'échelle
        // g2.scale(scaleX, scaleY);

        menuBar.setVisible(false);
        marchePanel.setVisible(false);
        // TITLESCREEN
        if (gameState == TITLE_STATE) {
            if (!initButton) {
                initButton = ui.initButtons();
            }
            if (scaleXAct != scaleX || scaleYAct != scaleY) {
                ui.resizeButtons(scaleX, scaleY);
                scaleXAct = scaleX;
                scaleYAct = scaleY;
            }
            ui.drawTitleScreen(g2, scaleX, scaleY);
        } else {
            menuBar.setVisible(true);
            // MAP
            tileM.draw(g2, scaleX, scaleY);
            // Ressource
            for (int i = 0; i < obj.size(); i++) {
                if (obj.get(i) != null) {
                    obj.get(i).draw(g2, this, scaleX, scaleY);
                }
            }
            if (scaleXAct != scaleX || scaleYAct != scaleY) {
                itemManager.resizeButtons(scaleX, scaleY);
                buttonGame.resizeButtons(scaleX, scaleY);
                scaleXAct = scaleX;
                scaleYAct = scaleY;
            }

            // PLAYER

            avatar.draw(g2, scaleX, scaleY);
            // g2.dispose();
            // UI
            if (gameState == playState) {
                if (!initButtonGame) {
                    buttonGame.initButton();
                    buttonGame.resizeButtons(scaleX, scaleY);
                    initButtonGame = true;

                }

            } else if (gameState == inventaireState) {
                // uiInv.inner();

            } else if (gameState == shopState) {
                marchePanel.setVisible(true);
            }
        }

    }
}
