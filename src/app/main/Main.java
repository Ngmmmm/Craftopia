package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel; // Importation de la classe JFrame pour créer une fenêtre

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
 * @author Ouahid Houat
 * @version 1.0
 */
public class Main { // Définition de la classe principale

    /**
     * Point d'entrée principal du programme. Cette méthode configure la fenêtre du
     * jeu
     * et lance les mécanismes nécessaires à son fonctionnement.
     * 
     * @param args les arguments de ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) { // Méthode main, point d'entrée du programme

        // Création d'une nouvelle instance de JFrame
        JFrame fenetre = new JFrame();

        // Définition de la fermeture de la fenêtre (quitter le programme lorsque la
        // fenêtre est fermée)
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définition de la taille de la fenêtre (redimensionnable)
        fenetre.setResizable(true);
        fenetre.setMinimumSize(new Dimension(768,576));
        // Définition du titre de la fenêtre
        fenetre.setTitle("Craftopia");
        ImageIcon logo = new ImageIcon("img/CraftopiaLogo.jpeg");
        fenetre.setIconImage(logo.getImage()); // Ajout du logo de la fenêtre
        fenetre.setLayout(new BorderLayout());

        // Création d'une nouvelle instance de GamePanel
        JPanel taskBar = new JPanel();
        JPanel invPanel = new JPanel();

        taskBar.setBackground(Color.GRAY);
        taskBar.setLayout(null);
        taskBar.setPreferredSize(new Dimension(30,30));
        // Ajout du panneau de jeu à la fenêtre
        GamePanel ecranDuJeu = new GamePanel(taskBar);
        fenetre.add(taskBar,BorderLayout.NORTH);
        taskBar.setVisible(false);
        fenetre.add(ecranDuJeu, BorderLayout.CENTER);
        // Ajustement de la taille de la fenêtre pour qu'elle contienne tous les
        // éléments
        fenetre.pack();
        // Placement de la fenêtre au centre de l'écran
        fenetre.setLocationRelativeTo(null);
        // Affichage de la fenêtre
        fenetre.setVisible(true);

        ecranDuJeu.setupGame(); // Initialisation des paramètres du jeu Démarrage du thread de jeu
        ecranDuJeu.startGameThread();
    }
}
