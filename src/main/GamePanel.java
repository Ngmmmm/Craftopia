package main;

import java.awt.Color; // Importation de la classe pour gérer les couleurs
import java.awt.Dimension; // Importation pour définir les dimensions des composants
import java.awt.Graphics; // Importation pour dessiner des composants
import java.awt.Graphics2D; // Importation pour des dessins graphiques avancés
import javax.swing.JPanel; // Importation de JPanel pour créer le panneau de jeu
import ressources.*; // Importation des ressources nécessaires
import sprites.Avatar; // Importation de la classe Avatar (le joueur)
import tile.TileManager; // Importation pour gérer les blocs du jeu

// La classe GamePanel hérite de JPanel et implémente Runnable pour le fonctionnement via un thread
public class GamePanel extends JPanel implements Runnable {

    final int TAILLE_BLOC_ORIGINAL = 16; // Taille de base d'un bloc en pixels
    final int FACTEUR_ECHELLE = 3; // Facteur d'agrandissement appliqué à chaque bloc
    public final int TAILLE_BLOC = TAILLE_BLOC_ORIGINAL * FACTEUR_ECHELLE; // Taille finale d'un bloc
    public final int NB_COLONNE_MAX = 16; // Nombre maximal de colonnes visibles à l'écran
    public final int NB_LIGNE_MAX = 12; // Nombre maximal de lignes visibles à l'écran
    public final int LARGEUR_ECRAN = TAILLE_BLOC * NB_COLONNE_MAX; // Largeur totale de l'écran
    public final int HAUTEUR_ECRAN = TAILLE_BLOC * NB_LIGNE_MAX; // Hauteur totale de l'écran

    public final int NB_COLONNE_MAP_MAX = 52; // Nombre de colonnes maximal sur la carte
    public final int NB_LIGNE_MAP_MAX = 50; // Nombre de lignes maximal sur la carte
    public final int LARGEUR_MAP = TAILLE_BLOC * NB_COLONNE_MAP_MAX; // Largeur totale de la carte
    public final int HAUTEUR_MAP = TAILLE_BLOC * NB_LIGNE_MAP_MAX; // Hauteur totale de la carte

    int FPS = 60; // Images par seconde pour maintenir la fluidité du jeu

    TileManager tileM = new TileManager(this); // Gestionnaire des blocs (tiles) pour dessiner la carte
    KeyHandler keyH = new KeyHandler(this); // Gestionnaire des touches clavier
    Thread gameThread; // Thread du jeu, pour exécuter le jeu en parallèle
    public CheckCollision collision = new CheckCollision(this); // Gestionnaire des collisions

    AssetSetter aSetter = new AssetSetter(this); // Gestion des objets (ressources, ennemis...)
    Ui ui = new Ui(this); // Gestionnaire de l'interface utilisateur
    public Avatar avatar = new Avatar(this, keyH); // L'avatar contrôlé par le joueur
    public Objet objet[] = new Objet[10]; // Tableau pour stocker les objets à afficher

    public int gameState; // État actuel du jeu (titre, en cours, etc.)
    public final int TITLE_STATE = 0; // État correspondant à l'écran de titre
    public final int playState = 1; // État correspondant au jeu en cours

    int avatarSpeed = 1; // Vitesse par défaut de l'avatar

    /**
     * Constructeur pour initialiser le panneau de jeu.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(LARGEUR_ECRAN, HAUTEUR_ECRAN)); // Définir la taille du panneau
        this.setBackground(Color.BLACK); // Couleur de fond noire
        this.setDoubleBuffered(true); // Activer le double buffering pour un rendu fluide
        this.addKeyListener(keyH); // Ajouter le gestionnaire de clavier
        this.setFocusable(true); // Permettre au panneau de capturer les événements clavier
    }

    /**
     * Configure les paramètres initiaux du jeu.
     */
    public void setupGame() {
        aSetter.setObjet(); // Initialise les objets du jeu
        gameState = TITLE_STATE; // Définit l'état initial à l'écran de titre
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
                update(); // Met à jour les éléments du jeu
                repaint(); // Redessine l'écran
                delta--; // Réduit le delta
            }
        }
    }

    /**
     * Met à jour les éléments du jeu.
     */
    public void update() {
        avatar.update(); // Met à jour l'état de l'avatar (position, etc.)
    }

    /**
     * Dessine les éléments du jeu.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Appelle la méthode de dessin de la classe parent
        Graphics2D g2 = (Graphics2D) g; // Conversion en Graphics2D pour un dessin avancé
        double scaleX = (double) getWidth() / LARGEUR_ECRAN;
        double scaleY = (double) getHeight() / HAUTEUR_ECRAN;
        // Appliquer les transformations pour mettre à l'échelle
        g2.scale(scaleX, scaleY);
        if (gameState == TITLE_STATE) { // Si l'état est celui de l'écran de titre
            ui.draw(g2); // Dessine l'interface utilisateur de l'écran de titre
        } else { // Sinon, état de jeu en cours
            tileM.draw(g2); // Dessine les blocs (carte)
            for (int i = 0; i < objet.length; i++) { // Parcourt les objets
                if (objet[i] != null) { // Si l'objet existe
                    objet[i].draw(g2, this); // Dessine l'objet
                }
            }
            avatar.draw(g2); // Dessine l'avatar
            g2.dispose(); // Libère les ressources graphiques
        }
    }
}
