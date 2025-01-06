package main;

import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UiTitleScreen extends Ui {
    private Icon startButtonIcon;
    private Icon continueButtonIcon;
    private Icon settingsButtonIcon;
    private Icon exitButtonIcon;

    private JButton startButton;
    private JButton continueButton;
    private JButton settingsButton;
    private JButton exitButton;

    private JPanel settingsMenuPanel;
    private JButton muteButton;
    public boolean inSettings;

    private static final String START_BUTTON_IMAGE_PATH = "./img/boutons/newGameButton.png";
    private static final String CONTINUE_BUTTON_IMAGE_PATH = "./img/boutons/ContinueButton.png";
    private static final String SETTINGS_BUTTON_IMAGE_PATH = "./img/boutons/SettingsButton.png";
    private static final String EXIT_BUTTON_IMAGE_PATH = "./img/boutons/ExitButton.png";
    private static final String BACKGROUND_IMAGE_PATH = "./img/MenuBackground.png";

    public UiTitleScreen(GamePanel gamePanel) {
        super(gamePanel);
        this.startButtonIcon = new ImageIcon(START_BUTTON_IMAGE_PATH);
        this.continueButtonIcon = new ImageIcon(CONTINUE_BUTTON_IMAGE_PATH);
        this.settingsButtonIcon = new ImageIcon(SETTINGS_BUTTON_IMAGE_PATH);
        this.exitButtonIcon = new ImageIcon(EXIT_BUTTON_IMAGE_PATH);

        this.startButton = new JButton(startButtonIcon);
        this.continueButton = new JButton(continueButtonIcon);
        this.settingsButton = new JButton(settingsButtonIcon);
        this.exitButton = new JButton(exitButtonIcon);

        // Initialisation du panneau des paramètres
        this.settingsMenuPanel = new JPanel();
        this.muteButton = new JButton("Mute Music");

        gamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repositionSettingsMenu();
            }
        });
    }

    public boolean initButtons() {
        // Configuration des boutons de l'écran principal
        startButton.setBounds(gamePanel.LARGEUR_ECRAN / 2 - 130 / 2, gamePanel.HAUTEUR_ECRAN / 2 - 80, 160, 65);
        continueButton.setBounds(gamePanel.LARGEUR_ECRAN / 2 - 130 / 2, gamePanel.HAUTEUR_ECRAN / 2 - 80 + 65, 160, 65);
        settingsButton.setBounds(gamePanel.LARGEUR_ECRAN / 2 - 130 / 2, gamePanel.HAUTEUR_ECRAN / 2 - 80 + 65 * 2, 160,
                65);
        exitButton.setBounds(gamePanel.LARGEUR_ECRAN / 2 - 130 / 2, gamePanel.HAUTEUR_ECRAN / 2 - 80 + 65 * 3, 160, 65);

        resizeButtonImages(startButton, startButtonIcon);
        resizeButtonImages(continueButton, continueButtonIcon);
        resizeButtonImages(settingsButton, settingsButtonIcon);
        resizeButtonImages(exitButton, exitButtonIcon);

        startButton.addActionListener(l -> {
            gamePanel.aSetter.setAllItemsByRandom();
            Ui.gameState = Ui.playState;
            setVisible(false);
        });

        continueButton.addActionListener(l -> {
            gamePanel.aSetter.setAllItemsBySave();
            Ui.gameState = Ui.playState;
            setVisible(false);
        });

        settingsButton.addActionListener(l -> {
            setVisible(false);
            inSettings = true;
            showSettingsMenu();
        });

        exitButton.addActionListener(l -> {
            gamePanel.saveEtatMap.saveEtatMap();
            gamePanel.saveInvAvatar.saveInvAvatar();
            System.exit(0);
        });

        startButton.setFocusable(false);
        continueButton.setFocusable(false);
        settingsButton.setFocusable(false);
        exitButton.setFocusable(false);

        gamePanel.add(startButton);
        gamePanel.add(continueButton);
        gamePanel.add(settingsButton);
        gamePanel.add(exitButton);

        initSettingsMenu();

        return true;
    }

    private void initSettingsMenu() {
        // Initialiser le panneau de menu des paramètres
        settingsMenuPanel.setLayout(null);
        settingsMenuPanel.setBounds((gamePanel.LARGEUR_ECRAN - 400) / 2, (gamePanel.HAUTEUR_ECRAN - 300) / 2, 400, 300);

        // Bouton pour couper/réactiver le son
        muteButton.setBounds((400 - 150) / 2, 60, 150, 60);
        muteButton.addActionListener(l -> {
            gamePanel.toggleMusic(); // Appelle une méthode existante ou à implémenter dans GamePanel
        });

        muteButton.setFocusable(false);
        settingsMenuPanel.add(muteButton);

        // Ajouter un bouton "Retour au menu principal"
        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds((400 - 150) / 2, 140, 150, 60);
        backButton.addActionListener(l -> {
            settingsMenuPanel.setVisible(false);
            inSettings = false;
            setVisible(true);
        });

        backButton.setFocusable(false);
        settingsMenuPanel.add(backButton);

        settingsMenuPanel.setVisible(false);
        gamePanel.add(settingsMenuPanel);

        repositionSettingsMenu(); // Positionner initialement le panneau des paramètres
    }

    private void showSettingsMenu() {
        settingsMenuPanel.setVisible(true);
    }

    private void repositionSettingsMenu() {
        int screenWidth = gamePanel.getWidth();
        int screenHeight = gamePanel.getHeight();
        settingsMenuPanel.setBounds((screenWidth - 400) / 2, (screenHeight - 300) / 2, 400, 300);
    }

    public void drawTitleScreen(Graphics2D g2, double scaleX, double scaleY) {
        ImageIcon backgroundIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
        g2.drawImage(backgroundIcon.getImage(), 0, 0, (int) (gamePanel.LARGEUR_ECRAN * scaleX),
                (int) (gamePanel.HAUTEUR_ECRAN * scaleY), null);
    }

    public void setVisible(boolean visible) {
        startButton.setVisible(visible);
        continueButton.setVisible(visible);
        settingsButton.setVisible(visible);
        exitButton.setVisible(visible);
    }

    public void resizeButtons(double scaleX, double scaleY) {

        startButton.setBounds((int) ((gamePanel.LARGEUR_ECRAN / 2 - 130 / 2) * scaleX),
                (int) ((gamePanel.HAUTEUR_ECRAN / 2 - 80) * scaleY), (int) (160 * scaleX), (int) (65 * scaleY));
        continueButton.setBounds((int) ((gamePanel.LARGEUR_ECRAN / 2 - 130 / 2) * scaleX),
                (int) ((gamePanel.HAUTEUR_ECRAN / 2 - 80 + 65) * scaleY), (int) (160 * scaleX), (int) (65 * scaleY));
        settingsButton.setBounds((int) ((gamePanel.LARGEUR_ECRAN / 2 - 130 / 2) * scaleX),
                (int) ((gamePanel.HAUTEUR_ECRAN / 2 - 80 + 65 * 2) * scaleY), (int) (160 * scaleX),
                (int) (65 * scaleY));
        exitButton.setBounds((int) ((gamePanel.LARGEUR_ECRAN / 2 - 130 / 2) * scaleX),
                (int) ((gamePanel.HAUTEUR_ECRAN / 2 - 80 + 65 * 3) * scaleY), (int) (160 * scaleX),
                (int) (65 * scaleY));

        // Redimensionner les images des boutons
        resizeButtonImages(startButton, startButtonIcon);
        resizeButtonImages(continueButton, continueButtonIcon);
        resizeButtonImages(settingsButton, settingsButtonIcon);
        resizeButtonImages(exitButton, exitButtonIcon);
    }
}
