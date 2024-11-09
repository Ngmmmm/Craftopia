
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

        // Démarrage du thread de jeu
        ecranDuJeu.startGameThread();
    }
}