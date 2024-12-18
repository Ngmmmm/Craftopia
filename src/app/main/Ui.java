package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * La classe `Ui` gère l'affichage et la gestion des boutons du Menu du jeu.
 * Elle est responsable de la création, l'affichage et l'ajustement des boutons
 * pour l'écran principal et l'écran de jeu, ainsi que de l'affichage du titre
 * et du fond du menu.
 * 
 * <p>
 * Cette classe utilise les propriétés des composants Swing comme `JButton` et
 * `ImageIcon` pour afficher
 * les boutons du menu avec des images, et ajuste leur taille dynamiquement en
 * fonction de la taille du panneau.
 * </p>
 * 
 * @author Noa Gregoire--Mellier
 * @version 1.1
 */
public class Ui {

    // Instance du panneau de jeu (GamePanel)
    private GamePanel gamePanel;

    // Instance de l'objet Graphics2D pour dessiner
    private Graphics2D graphics2D;

    // Déclaration des icônes des boutons (start et exit)
    private Icon startButtonIcon;
    private Icon exitButtonIcon;

    // Déclaration des boutons start et exit
    private JButton startButton;
    private JButton exitButton;

    // Chemins des images pour les boutons et le fond
    private static final String START_BUTTON_IMAGE_PATH = "./img/newGameButton.png";
    private static final String EXIT_BUTTON_IMAGE_PATH = "./img/QuitButton.png";
    private static final String BACKGROUND_IMAGE_PATH = "./img/MenuBackground.png";

    /**
     * Constructeur de la classe `Ui`.
     * 
     * @param gamePanel Le panneau de jeu principal, utilisé pour afficher le menu
     *                  et le jeu
     */
    public Ui(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.startButtonIcon = new ImageIcon(START_BUTTON_IMAGE_PATH);
        this.exitButtonIcon = new ImageIcon(EXIT_BUTTON_IMAGE_PATH);
        this.startButton = new JButton(startButtonIcon);
        this.exitButton = new JButton(exitButtonIcon);
    }

    /**
     * Méthode permettant de dessiner le menu principal (titre du jeu et fond
     * d'écran).
     * Cette méthode est appelée à chaque frame pour mettre à jour l'écran de titre.
     * 
     * @param g2 Le Graphics2D utilisé pour dessiner sur l'écran
     */
    public void draw(Graphics2D g2) {
        this.graphics2D = g2;

        // Vérifie si le jeu est dans l'état du titre
        if (gamePanel.gameState == gamePanel.TITLE_STATE) {
            drawTitleScreen(g2);
        } else {
            // Si ce n'est pas l'état titre, on affiche une simple info (exemple ici "Oui")
            g2.setFont(new Font("Arial", Font.PLAIN, 40));
            g2.setColor(Color.WHITE);
            g2.drawString("Oui", 330, 200);
        }
    }

    /**
     * Méthode pour initialiser les boutons et leur affecter des actions (Play,
     * Quitter).
     * 
     * @return `true` si l'initialisation des boutons a réussi.
     */
    public boolean initButtons() {
        // Initialisation des boutons avec des images
        startButton.setBounds(300, 250, 160, 65);
        exitButton.setBounds(300, 350, 160, 65);

        // Redimensionner les images des boutons
        resizeButtonImages(startButton, startButtonIcon);
        resizeButtonImages(exitButton, exitButtonIcon);

        // Action pour le bouton "Start"
        startButton.addActionListener(l -> {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.remove(startButton);
        });

        // Action pour le bouton "Exit"
        exitButton.addActionListener(l -> {
            System.exit(0);
            gamePanel.remove(exitButton);
        });

        // Empêcher les boutons d'être sélectionnables (pour une meilleure apparence)
        startButton.setFocusable(false);
        exitButton.setFocusable(false);

        // Ajout des boutons au panneau de jeu
        gamePanel.add(startButton);
        gamePanel.add(exitButton);

        // Définition de la mise en page pour ne pas utiliser de gestionnaire de layout
        gamePanel.setLayout(null);
        gamePanel.setVisible(true);

        return true;
    }

    /**
     * Redimensionne l'image d'un bouton pour qu'elle corresponde à sa taille actuelle.
     * 
     * @param button Le bouton à redimensionner
     * @param icon L'icône à redimensionner
     */
    private void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }

    /**
     * Redimensionne les boutons en fonction des nouveaux facteurs de mise à
     * l'échelle.
     * Utilisé lorsque la fenêtre du jeu est redimensionnée.
     * 
     * @param scaleX Facteur de mise à l'échelle sur l'axe X
     * @param scaleY Facteur de mise à l'échelle sur l'axe Y
     */
    public void resizeButtons(double scaleX, double scaleY) {
        // Retirer les boutons avant de les redimensionner
        gamePanel.remove(startButton);
        gamePanel.remove(exitButton);

        // Calculer les nouvelles positions pour centrer les boutons
        int centerX = gamePanel.LARGEUR_ECRAN / 2;
        int centerY = gamePanel.HAUTEUR_ECRAN / 2;

        int newButtonWidth = (int) (160 * scaleX);
        int newButtonHeight = (int) (65 * scaleY);

        int newButtonX = centerX - newButtonWidth / 2; // Centrer horizontalement
        int newStartY = centerY - newButtonHeight - 10; // 10px d'écart entre les boutons
        int newExitY = centerY + 10;                    // Bouton Exit sous Start

        // Appliquer les nouvelles dimensions et positions
        startButton.setBounds(newButtonX, newStartY, newButtonWidth, newButtonHeight);
        exitButton.setBounds(newButtonX, newExitY, newButtonWidth, newButtonHeight);

        // Redimensionner les images des boutons
        resizeButtonImages(startButton, startButtonIcon);
        resizeButtonImages(exitButton, exitButtonIcon);

        // Réajouter les boutons au panneau de jeu
        gamePanel.add(startButton);
        gamePanel.add(exitButton);
    }

    /**
     * Dessine l'écran de titre du jeu avec un fond et le nom du jeu.
     * 
     * @param g2 Le Graphics2D utilisé pour dessiner sur l'écran
     */
    public void drawTitleScreen(Graphics2D g2) {
        // Charger et afficher l'image de fond
        ImageIcon backgroundIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
        g2.drawImage(backgroundIcon.getImage(), 0, 0, gamePanel.LARGEUR_ECRAN, gamePanel.HAUTEUR_ECRAN, null);

        // Affichage du texte du titre du jeu (Craftopia)
        g2.setFont(new Font("Arial", Font.BOLD, 80));
        String titleText = "CRAFTOPIA";
        Point titlePosition = new Point(160, 200);
        g2.setColor(Color.WHITE);
        g2.drawString(titleText, titlePosition.x, titlePosition.y);
    }
}
