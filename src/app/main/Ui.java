package main;

import item.machines.Recolteuse;
import item.machines.Usine;
import item.sprites.SuperObjet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

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
    public static final int craftAvatarState = 9;

    public UiInteract itemManager;
    public UiTitleScreen titleScreen;
    public UiMenuBar menuPanel;
    public UiInventaire inventairePanel;
    public UiMarche marchePanel;
    public static UiPoseUsine poseUsinePanel;
    public static UiPoseRecolteuse poseRecolteusePanel;
    public UiCraftAvatar craftAvatarPanel;
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

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double scaleX = (double) gamePanel.getWidth() / gamePanel.LARGEUR_ECRAN;
        double scaleY = (double) gamePanel.getHeight() / gamePanel.HAUTEUR_ECRAN;

        // UI DRAW
        hideAllPanels();

        if (gameState == TITLE_STATE) {
            for (SuperObjet item :GamePanel.obj) {
                if (item instanceof Usine) {
                    ((Usine)item).uiUsine.setVisible(false);
                }
                if (item instanceof Recolteuse) {
                    ((Recolteuse)item).uiRecolteuse.setVisible(false);
                }
            }
            drawTitleScreen(g2, scaleX, scaleY);
        } else {
            titleScreen.setVisible(false);
            drawGameScreen(g2, scaleX, scaleY);
        }
    }

    public void initializeComponents() {
        this.itemManager = new UiInteract(gamePanel);
        this.titleScreen = new UiTitleScreen(gamePanel);
        this.menuPanel = new UiMenuBar(gamePanel);
        this.inventairePanel = new UiInventaire(gamePanel);
        this.marchePanel = new UiMarche(gamePanel);
        poseUsinePanel = new UiPoseUsine(gamePanel);
        poseRecolteusePanel = new UiPoseRecolteuse(gamePanel);
        this.craftAvatarPanel = new UiCraftAvatar(gamePanel);
    }

    protected void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(
                buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }

    private void hideAllPanels() {
        menuPanel.setVisible(false);
        menuPanel.actArgent();
        inventairePanel.setVisible(false);
        marchePanel.setVisible(false);
        poseUsinePanel.setVisible(false);
        craftAvatarPanel.setVisible(false);
    }

    private void drawTitleScreen(Graphics2D g2, double scaleX, double scaleY) {
        gamePanel.avatar.setValeurParDefaut();
        if (!initButton) {
            initButton = titleScreen.initButtons();
        }
        titleScreen.resizeButtons(scaleX, scaleY);
        scaleXAct = scaleX;
        scaleYAct = scaleY;

        titleScreen.drawTitleScreen(g2, scaleX, scaleY);
        titleScreen.backToMenu();
    }

    private void drawGameScreen(Graphics2D g2, double scaleX, double scaleY) {
        if (!initButtonGame) {
            menuPanel.initButton();
            menuPanel.resizeButtons();
            initButtonGame = true;
        }

        menuPanel.menuBar.setBounds(0, 0, gamePanel.getWidth(), 30);
        menuPanel.setVisible(true);

        gamePanel.tileM.draw(g2, scaleX, scaleY);
        drawGameObjects(g2, scaleX, scaleY);

        if (scaleXAct != scaleX || scaleYAct != scaleY) {
            itemManager.resetButton();
            itemManager.resizeButtons(scaleX, scaleY);
            scaleXAct = scaleX;
            scaleYAct = scaleY;
        }

        gamePanel.avatar.draw(g2, scaleX, scaleY);
        drawPanels(g2, scaleX, scaleY);
    }

    private void drawGameObjects(Graphics2D g2, double scaleX, double scaleY) {
        for (int i = 0; i < GamePanel.obj.size(); i++) {
            if (GamePanel.obj.get(i) != null) {
                GamePanel.obj.get(i).draw(g2, gamePanel, scaleX, scaleY);
            }
        }
    }

    private void drawPanels(Graphics2D g2, double scaleX, double scaleY) {
        int width;
        int height;

        int scrollWidth;
        int scrollHeight;
        int scrollX;
        int scrollY;
        switch (gameState) {
            case playState:
                break;
            case inventaireState:
                setPanelBounds(inventairePanel.mainPanel, 500, 300);
                inventairePanel.setVisible(true);
                break;
            case shopState:
                setPanelBounds(marchePanel.mainPanel, 580, 300);
                marchePanel.setVisible(true);
                break;
            case craftState:
                width = gamePanel.getWidth();
                height = gamePanel.getHeight();

                scrollWidth = 500;
                scrollHeight = 300;
                scrollX = (width - scrollWidth) / 2;
                scrollY = (height - scrollHeight) / 2;
                for (int i = 0; i < GamePanel.obj.size(); i++) {
                    if (GamePanel.obj.get(i) instanceof Usine) {
                        Usine usine = (Usine) GamePanel.obj.get(i);
                        usine.uiUsine.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);
                    }
                }
                break;
            case recolteuseState:
                width = gamePanel.getWidth();
                height = gamePanel.getHeight();

                scrollWidth = 500;
                scrollHeight = 300;
                scrollX = (width - scrollWidth) / 2;
                scrollY = (height - scrollHeight) / 2;

                for (int i = 0; i < GamePanel.obj.size(); i++) {
                    if (GamePanel.obj.get(i) instanceof Recolteuse) {
                        Recolteuse recolteuse = (Recolteuse) GamePanel.obj.get(i);
                        recolteuse.uiRecolteuse.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);
                    }
                }
                break;
            case poseUsineState:
                setPanelBounds(poseUsinePanel.mainPanel, gamePanel.getWidth() / 2, gamePanel.getHeight() / 2);
                poseUsinePanel.setVisible(true);
                break;

            case poseRecolteuseState:
                setPanelBounds(poseRecolteusePanel.mainPanel, gamePanel.getWidth() / 2, gamePanel.getHeight() / 2);
                poseRecolteusePanel.setVisible(true);
                break;
            case craftAvatarState:
                setPanelBounds(craftAvatarPanel.mainPanel, 500, 300);
                craftAvatarPanel.setVisible(true);
                break;
        }
    }

    protected void setPanelBounds(JPanel panel, int width, int height) {
        int scrollX = (gamePanel.getWidth() - width) / 2;
        int scrollY = (gamePanel.getHeight() - height + menuPanel.menuBar.getHeight()) / 2;
        panel.setBounds(scrollX, scrollY, width, height);
    }
}
