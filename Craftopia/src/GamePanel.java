
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

// La classe GamePanel hérite de JPanel et implémente l'interface Runnable (nécessaire pour le fonctionnement)
public class GamePanel extends JPanel implements Runnable {

    // * PARAMÈTRES DE L'ÉCRAN

    final int tailleOriginalBloc = 16; // Taille originale d'un bloc (en pixels)
    final int facteurEchelle = 3; // Facteur d'agrandissement des blocs
    final int tailleBloc = tailleOriginalBloc * facteurEchelle; // Taille d'un bloc en pixels (tailleOriginaleBloc *
                                                                // facteurEchelle)
    final int nbColonneMax = 16; // Nombre de colonnes maximum sur l'écran
    final int nbLigneMax = 12;// Nombre de lignes maximum sur l'écran
    final int largeurEcran = tailleBloc * nbColonneMax; // Largeur de l'écran (tailleBloc * nbColonneMax)
    final int hauteurEcran = tailleBloc * nbLigneMax; // Hauteur de l'écran (tailleBloc * nbLigneMax)

    int FPS = 60; // FPS (Frames Par Seconde)

    KeyHandler keyH = new KeyHandler(); // Instance de KeyHandler pour gérer les événements clavier

    Thread gameThread;// Thread pour le jeu

    // Constructeur de GamePanel
    public GamePanel() {
        // Définir la taille préférée du panneau
        this.setPreferredSize(new Dimension(largeurEcran, hauteurEcran));
        // Définir la couleur de fond du panneau
        this.setBackground(Color.BLACK);
        // Activer le double buffering pour améliorer les performances
        this.setDoubleBuffered(true);
        // Ajouter le gestionnaire de clavier au panneau
        this.addKeyListener(keyH);
        // Rendre le panneau focusable pour recevoir les événements clavier
        this.setFocusable(true);
    }

    // Méthode pour démarrer le thread du jeu
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Méthode pour exécuter le thread du jeu
    @Override
    public void run() {

    }

}