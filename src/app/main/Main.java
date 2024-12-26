package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * La classe Main sert de point d'entrée au jeu Craftopia.
 * <p>
 * Elle initialise la fenêtre principale du jeu, configure ses propriétés,
 * ajoute le panneau de jeu, et démarre le thread principal du jeu.
 * </p>
 * 
 * <p>
 * Le jeu repose sur un panneau personnalisé {@link GamePanel}, qui contient
 * toute la logique et l'affichage du jeu.
 * </p>
 * 
 * @version 2.0
 */
public class Main extends JFrame { // Définition de la classe principale qui étend JFrame

    // Déclaration des panneaux
    private JPanel menuBar;
    JScrollPane invPanel, marchePanel;
    GamePanel ecranDuJeu;
    UiMarche uiMarche;

    /**
     * Constructeur de la classe Main.
     * Initialise la fenêtre du jeu et configure les composants.
     */
    public Main() {
        // Définition du titre de la fenêtre
        super("Craftopia");

        // Définition de la fermeture de la fenêtre (quitter le programme lorsque la
        // fenêtre est fermée)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définition de la taille de la fenêtre (redimensionnable)
        setResizable(true);
        setMinimumSize(new Dimension(768, 576));

        // Ajout du logo de la fenêtre
        ImageIcon logo = new ImageIcon("img/CraftopiaLogo.jpeg");
        setIconImage(logo.getImage());

        // Initialisation des panneaux
        menuBar = new JPanel();

        UiInventaire uiInventaire = new UiInventaire();
        invPanel = uiInventaire.getScrollPane();

        UiMarche uiMarche = new UiMarche();

        marchePanel = uiMarche.getScrollPane();

        // Création d'une nouvelle instance de GamePanel
        ecranDuJeu = new GamePanel(menuBar, invPanel, marchePanel);

        // Configuration du menuBar
        menuBar.setBackground(Color.GRAY);
        menuBar.setLayout(null);
        menuBar.setPreferredSize(new Dimension(30, 30));

        // Configuration du invPanel
        invPanel.setBackground(Color.BLUE);

        // Configuration du marchePanel

        // Création et configuration du JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); // Utilisation d'un layout null pour un positionnement absolu

        // Ajout des panneaux au JLayeredPane avec des priorités
        menuBar.setBounds(0, 0, 768, 30);
        invPanel.setBounds(60, 60, getWidth() / 2, getHeight() / 2);
        marchePanel.setBounds(60, 60, getWidth() / 2, getHeight() / 2);
        ecranDuJeu.setBounds(0, 0, 768, 576);

        layeredPane.add(ecranDuJeu, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(menuBar, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(invPanel, JLayeredPane.MODAL_LAYER);
        layeredPane.add(marchePanel, JLayeredPane.MODAL_LAYER);

        invPanel.setVisible(false);
        marchePanel.setVisible(false);

        // Ajout du JLayeredPane au conteneur principal
        Container pane = getContentPane();
        setContentPane(pane);
        pane.setLayout(new BorderLayout());
        pane.add(layeredPane, BorderLayout.CENTER);

        // Ajustement de la taille de la fenêtre pour qu'elle contienne tous les
        // éléments
        pack();
        // Placement de la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        // Affichage de la fenêtre
        setVisible(true);

        // Ajout d'un écouteur pour redimensionner les composants lorsque la fenêtre est
        // redimensionnée
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();

                menuBar.setBounds(0, 0, width, 30);

                int scrollWidth = width / 2;
                int scrollHeight = height / 2;
                int scrollX = (width - scrollWidth) / 2;
                int scrollY = (height - scrollHeight) / 2;

                invPanel.setBounds(scrollX,scrollY, scrollWidth,scrollHeight);
                marchePanel.setBounds(scrollX,scrollY, scrollWidth,scrollHeight);

                ecranDuJeu.setBounds(0, 0, width, height);
                
            }
        });

        // Initialisation des paramètres du jeu et démarrage du thread de jeu
        ecranDuJeu.setupGame();
        ecranDuJeu.startGameThread();
    }

    /**
     * Point d'entrée principal du programme.
     * Cette méthode crée une instance de la classe Main.
     * 
     * @param args les arguments de ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        new Main();
    }
}
