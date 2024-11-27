package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame; // Importation de la classe JFrame pour créer une fenêtre

public class Main { // Définition de la classe principale

    public static void main(String[] args) { // Méthode main, point d'entrée du programme

        JFrame fenetre = new JFrame(); // Création d'une nouvelle instance de JFrame

        // Définition de la fermeture de la fenêtre (quitter le programme lorsque la
        // fenêtre est fermée
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définition de la taille de la fenêtre (non redimensionnable)
        fenetre.setResizable(false);
        // Définition du titre de la fenêtre
        fenetre.setTitle("Craftopia");
        ImageIcon logo = new ImageIcon("img/CraftopiaLogo.jpeg");
        // ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("resources/img/CraftopiaLogo.jpeg"));
        fenetre.setIconImage(logo.getImage());

        // Création d'une nouvelle instance de GamePanel
        GamePanel ecranDuJeu = new GamePanel();

        // Ajout du panneau de jeu à la fenêtre
        fenetre.add(ecranDuJeu);
        // Ajustement de la taille de la fenêtre pour qu'elle contienne tous les
        // éléments
        fenetre.pack();
        // Placement de la fenêtre au centre de l'écran
        fenetre.setLocationRelativeTo(null);
        // Affichage de la fenêtre
        fenetre.setVisible(true);

        ecranDuJeu.setupGame();
        // Démarrage du thread de jeu
        ecranDuJeu.startGameThread();
    }
}