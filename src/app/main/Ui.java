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
 * @version 2.0
 */
public class Ui {

    // Instance du panneau de jeu (GamePanel)
    private GamePanel gamePanel;

    // Déclaration des icônes des boutons (start et exit)
    private Icon startButtonIcon;
    private Icon continueButtonIcon;
    private Icon settingsButtonIcon;
    private Icon exitButtonIcon;

    // Déclaration des boutons start et exit
    private JButton startButton;
    private JButton continueButton;
    private JButton settingsButton;
    private JButton exitButton;

    // Chemins des images pour les boutons et le fond
    private static final String START_BUTTON_IMAGE_PATH = "./img/newGameButton.png";
    private static final String CONTINUE_BUTTON_IMAGE_PATH = "./img/ContinueButton.png";
    private static final String SETTINGS_BUTTON_IMAGE_PATH = "./img/SettingsButton.png";
    private static final String EXIT_BUTTON_IMAGE_PATH = "./img/ExitButton.png";
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
        this.continueButtonIcon = new ImageIcon(CONTINUE_BUTTON_IMAGE_PATH);
        this.settingsButtonIcon = new ImageIcon(SETTINGS_BUTTON_IMAGE_PATH);
        this.exitButtonIcon = new ImageIcon(EXIT_BUTTON_IMAGE_PATH);

        this.startButton = new JButton(startButtonIcon);
        this.continueButton = new JButton(continueButtonIcon);
        this.settingsButton = new JButton(settingsButtonIcon);
        this.exitButton = new JButton(exitButtonIcon);
    }

    /**
     * Méthode pour initialiser les boutons et leur affecter des actions (Play,
     * Quitter).
     * 
     * @return `true` si l'initialisation des boutons a réussi.
     */
    public boolean initButtons() {
        // Initialisation des boutons avec des images
        startButton.setBounds(gamePanel.LARGEUR_ECRAN / 2 - 130 / 2, gamePanel.HAUTEUR_ECRAN / 2 -80, 160, 65);
        continueButton.setBounds(gamePanel.LARGEUR_ECRAN / 2 - 130 / 2, gamePanel.HAUTEUR_ECRAN / 2 -80, 160, 65);
        settingsButton.setBounds(gamePanel.LARGEUR_ECRAN / 2 - 130 / 2, gamePanel.HAUTEUR_ECRAN / 2 -80 + 65 * 2, 160, 65);
        exitButton.setBounds(gamePanel.LARGEUR_ECRAN / 2 - 130 / 2, gamePanel.HAUTEUR_ECRAN / 2 -80 + 65 * 3, 160, 65);

        // Redimensionner les images des boutons
        resizeButtonImages(startButton, startButtonIcon);
        resizeButtonImages(continueButton, continueButtonIcon);
        resizeButtonImages(settingsButton, settingsButtonIcon);
        resizeButtonImages(exitButton, exitButtonIcon);

        // Action pour le bouton "Start"
        startButton.addActionListener(l -> {
            gamePanel.gameState = gamePanel.playState;
            startButton.setVisible(false);
            continueButton.setVisible(false);
            settingsButton.setVisible(false);
            exitButton.setVisible(false);

        });

        // Action pour le bouton "Continue"
        continueButton.addActionListener(l -> {
            gamePanel.gameState = gamePanel.playState;
            startButton.setVisible(false);
            continueButton.setVisible(false);
            settingsButton.setVisible(false);
            exitButton.setVisible(false);

        });

        // Action pour le bouton "Settings"
        settingsButton.addActionListener(l -> {
            gamePanel.gameState = gamePanel.playState;
            startButton.setVisible(false);
            continueButton.setVisible(false);
            settingsButton.setVisible(false);
            exitButton.setVisible(false);

        });

        // Action pour le bouton "Exit"
        exitButton.addActionListener(l -> {
            System.exit(0);
            startButton.setVisible(false);
            continueButton.setVisible(false);
            settingsButton.setVisible(false);
            exitButton.setVisible(false);
        });

        // Empêcher les boutons d'être sélectionnables (pour une meilleure apparence)
        startButton.setFocusable(false);
        continueButton.setFocusable(false);
        settingsButton.setFocusable(false);
        exitButton.setFocusable(false);

        // Ajout des boutons au panneau de jeu
        gamePanel.add(startButton);
        gamePanel.add(continueButton);
        gamePanel.add(settingsButton);
        gamePanel.add(exitButton);

        // Définition de la mise en page pour ne pas utiliser de gestionnaire de layout
        gamePanel.setLayout(null);
        gamePanel.setVisible(true);

        return true;
    }

    /**
     * Redimensionne l'image d'un bouton pour qu'elle corresponde à sa taille
     * actuelle.
     * 
     * @param button Le bouton à redimensionner
     * @param icon   L'icône à redimensionner
     */
    private void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(buttonWidth, buttonHeight,
                java.awt.Image.SCALE_SMOOTH));
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

        startButton.setBounds((int) ((gamePanel.LARGEUR_ECRAN / 2 - 130 / 2) * scaleX),
                (int) ((gamePanel.HAUTEUR_ECRAN / 2 -80) * scaleY), (int) (160 * scaleX), (int) (65 * scaleY));
        continueButton.setBounds((int) ((gamePanel.LARGEUR_ECRAN / 2 - 130 / 2) * scaleX),
                (int) ((gamePanel.HAUTEUR_ECRAN / 2 -80 + 65) * scaleY), (int) (160 * scaleX), (int) (65 * scaleY));
        settingsButton.setBounds((int) ((gamePanel.LARGEUR_ECRAN / 2 - 130 / 2) * scaleX),
                (int) ((gamePanel.HAUTEUR_ECRAN / 2 -80 + 65 * 2) * scaleY), (int) (160 * scaleX), (int) (65 * scaleY));
        exitButton.setBounds((int) ((gamePanel.LARGEUR_ECRAN / 2 - 130 / 2) * scaleX),
                (int) ((gamePanel.HAUTEUR_ECRAN / 2 -80 + 65 * 3) * scaleY), (int) (160 * scaleX), (int) (65 * scaleY));

        // Redimensionner les images des boutons
        resizeButtonImages(startButton, startButtonIcon);
        resizeButtonImages(continueButton, continueButtonIcon);
        resizeButtonImages(settingsButton, settingsButtonIcon);
        resizeButtonImages(exitButton, exitButtonIcon);
    }

    /**
     * Dessine l'écran de titre du jeu avec un fond et le nom du jeu.
     * 
     * @param g2 Le Graphics2D utilisé pour dessiner sur l'écran
     */
    public void drawTitleScreen(Graphics2D g2, double scaleX, double scaleY) {
        // Charger et afficher l'image de fond
        ImageIcon backgroundIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
        g2.drawImage(backgroundIcon.getImage(), 0, 0, (int) (gamePanel.LARGEUR_ECRAN * scaleX),
                (int) (gamePanel.HAUTEUR_ECRAN * scaleY), null);

    }
}
