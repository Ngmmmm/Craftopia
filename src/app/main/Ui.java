package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
    protected GamePanel gamePanel;

    protected boolean initButton = false;
    protected boolean initButtonGame = false;

    public static int gameState; // État actuel du jeu (titre, en cours, etc.)

    public static final int TITLE_STATE = 0; // État correspondant à l'écran de titre
    public static final int playState = 1; // État correspondant au jeu en cours
    public static final int recolteuseState = 2;
    public static final int craftState = 3;
    public static final int inventaireState = 4;
    public static final int shopState = 5;
    public static final int settingsState = 6;
    public static final int poseUsineState = 7;
    public static final int poseRecolteuseState = 8;

    public UiInteract itemManager;
    public UiTitleScreen titleScreen;
    public UiMenuBar menuPanel;
    public UiInventaire inventairePanel;
    public UiMarche marchePanel;
    public static UiPoseUsine poseUsinePanel;
    public static UiPoseRecolteuse poseRecolteusePanel;
    public double scaleXAct;
    public double scaleYAct;

    /**
     * Constructeur de la classe `Ui`.
     * 
     * @param gamePanel Le panneau de jeu principal, utilisé pour afficher le menu
     *                  et le jeu
     */
    public Ui(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.scaleXAct = gamePanel.getWidth() / gamePanel.LARGEUR_ECRAN;
        this.scaleYAct = gamePanel.getHeight() / gamePanel.HAUTEUR_ECRAN;
    }

    public void initializeComponents() {
        this.itemManager = new UiInteract(gamePanel);
        this.titleScreen = new UiTitleScreen(gamePanel);
        this.menuPanel = new UiMenuBar(gamePanel);
        this.inventairePanel = new UiInventaire(gamePanel);
        this.marchePanel = new UiMarche(gamePanel);
        this.poseUsinePanel = new UiPoseUsine(gamePanel);
        this.poseRecolteusePanel = new UiPoseRecolteuse(gamePanel);


    }

    protected void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(
                buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }

    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        double scaleX = (double) gamePanel.getWidth() / gamePanel.LARGEUR_ECRAN;
        double scaleY = (double) gamePanel.getHeight() / gamePanel.HAUTEUR_ECRAN;
        // Appliquer les transformations pour mettre à l'échelle
        // g2.scale(scaleX, scaleY);

        // UI DRAW

        menuPanel.setVisible(false);
        menuPanel.actArgent();
        // inventairePanel.setVisible(false);
        marchePanel.setVisible(false);

        // TITLESCREEN
        if (gameState == TITLE_STATE) {
            
            gamePanel.avatar.setValeurParDefaut();
            if (!initButton) {
                initButton = titleScreen.initButtons();
            }
            titleScreen.resizeButtons(scaleX, scaleY);
            scaleXAct = scaleX;
            scaleYAct = scaleY;

            titleScreen.drawTitleScreen(g2, scaleX, scaleY);
            titleScreen.backToMenu();

        } else {
            // MENU
            if (!initButtonGame) {
                menuPanel.initButton();
                menuPanel.resizeButtons();
                initButtonGame = true;

            }

            menuPanel.menuBar.setBounds(0, 0, gamePanel.getWidth(), 30); // Adjust width to gamePanel's width
            menuPanel.setVisible(true);
            // MAP
            gamePanel.tileM.draw(g2, scaleX, scaleY);
            // Ressource
            for (int i = 0; i < GamePanel.obj.size(); i++) {
                if (GamePanel.obj.get(i) != null) {
                    GamePanel.obj.get(i).draw(g2, gamePanel, scaleX, scaleY);
                    
                }
            }
            if (scaleXAct != scaleX || scaleYAct != scaleY) {
                itemManager.resizeButtons(scaleX, scaleY);
                scaleXAct = scaleX;
                scaleYAct = scaleY;
            }

            // PLAYER
            gamePanel.avatar.draw(g2, scaleX, scaleY);
            // g2.dispose();

            // UI
            if (gameState == playState) {
                

            } else if (gameState == inventaireState) {

                int width = gamePanel.getWidth();
                int height = gamePanel.getHeight();

                int scrollWidth = width / 2;
                int scrollHeight = height / 2;
                int scrollX = (width - scrollWidth) / 2;
                int scrollY = (height - scrollHeight) / 2;

                inventairePanel.mainPanel.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);
                inventairePanel.setVisible(true);

            } else if (gameState == shopState) {
                marchePanel.mainPanel.setBounds(60, 60, gamePanel.getWidth() / 2, gamePanel.getHeight() / 2);
                marchePanel.setVisible(true);
               

            } else if (gameState == craftState) {
               

            } else if (gameState == poseUsineState) {
                int width = gamePanel.getWidth();
                int height = gamePanel.getHeight();

                int scrollWidth = width / 2;
                int scrollHeight = height / 2;
                int scrollX = (width - scrollWidth) / 2;
                int scrollY = (height - scrollHeight) / 2;

                poseUsinePanel.mainPanel.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);
                poseUsinePanel.setVisible(true);

            }else if (gameState == poseRecolteuseState) {
                int width = gamePanel.getWidth();
                int height = gamePanel.getHeight();

                int scrollWidth = width / 2;
                int scrollHeight = height / 2;
                int scrollX = (width - scrollWidth) / 2;
                int scrollY = (height - scrollHeight) / 2;

                poseRecolteusePanel.mainPanel.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);
                poseRecolteusePanel.setVisible(true);
            }
        }


    }


}
