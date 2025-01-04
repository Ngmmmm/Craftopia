package main;

import item.bag.Inventaire;
import item.machines.Recolteuse;
import item.machines.Usine;
import item.marche.Marche;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class UiInteract extends Ui {
    private JButton itemButton;
    private final ImageIcon ItemButtonIcon;
    private static final String INTERACT_BUTTON_IMAGE_PATH = "./img/boutons/InteractButton.png";

    /**
     * Constructeur de la classe `UiGame`.
     * 
     * @param gamePanel Le panneau de jeu principal, utilisé pour afficher le menu
     *                  et le jeu
     */
    public UiInteract(GamePanel gamePanel) {
        super(gamePanel);
        this.ItemButtonIcon = new ImageIcon(INTERACT_BUTTON_IMAGE_PATH);
        this.itemButton = new JButton(ItemButtonIcon);
        setupButton();
    }

    private void setupButton() {
        gamePanel.add(itemButton);
        gamePanel.setLayout(null);
        itemButton.setVisible(false);
    }

    public void initButton(int posX, int posY, SuperObjet item, int index) {
        setPositionAndResize(posX, posY);
        configureButtonAction(item, index);
        itemButton.setVisible(true);
    }

    private void setPositionAndResize(int posX, int posY) {
        itemButton.setBounds(
                (int) (posX * gamePanel.ui.scaleXAct),
                (int) (posY * gamePanel.ui.scaleYAct),
                (int) (100 * gamePanel.ui.scaleXAct),
                (int) (21 * gamePanel.ui.scaleYAct));
        resizeButtonImages(itemButton, ItemButtonIcon);
    }

    private void configureButtonAction(SuperObjet item, int index) {

        if (!hasActionListener()) {
            switch (item instanceof Usine ? "Usine"
                    : item instanceof Recolteuse ? "Recolteuse" : item instanceof Marche ? "Marche" : "default") {
                case "Recolteuse":
                    setRecolteuseAction(item, index);
                    break;
                case "Usine":
                    setUsineAction(item, index);
                    break;
                case "Marche":
                    setMarcheAction();
                    break;
                default:
                    setDefaultAction(item, index);
                    break;
            }
        }
    }

    private void setMarcheAction() {
        itemButton.addActionListener(l -> {
            gameState = shopState;
            itemButton.setVisible(false);
            gamePanel.ui.marchePanel.rebuildMarketPanel();
        });
    }

    private boolean hasActionListener() {
        return itemButton.getActionListeners().length > 0;
    }

    private void setRecolteuseAction(SuperObjet item, int index) {

        itemButton.addActionListener(l -> {
            gameState = recolteuseState;
            itemButton.setVisible(false);
            Recolteuse recolteuse = (Recolteuse) item;
            int width = gamePanel.getWidth();
            int height = gamePanel.getHeight();

            int scrollWidth = (int) (width * 0.9); // Increase width to cover almost the entire screen
            int scrollHeight = height / 2; // Increase height to cover almost the entire screen
            int scrollX = (width - scrollWidth) / 2; // Center horizontally
            int scrollY = (height - scrollHeight) / 2; // Center vertically

            recolteuse.uiRecolteuse.updateInventory();
            recolteuse.uiRecolteuse.setVisible(true);
            recolteuse.uiRecolteuse.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);

        });

    }

    private void setUsineAction(SuperObjet item, int index) {
        itemButton.addActionListener(l -> {
            gameState = craftState;
            itemButton.setVisible(false);

            Usine usine = (Usine) item;
            int width = gamePanel.getWidth();
            int height = gamePanel.getHeight();

            int scrollWidth = 500; // Fixed width
            int scrollHeight = 300; // Fixed height
            int scrollX = (width - scrollWidth) / 2; // Center horizontally
            int scrollY = (height - scrollHeight) / 2; // Center vertically

            usine.uiUsine.updateInventory();
            usine.uiUsine.setVisible(true);
            usine.uiUsine.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);

        });
    }

    private void setDefaultAction(SuperObjet item, int index) {
        itemButton.addActionListener(l -> {
            Ressource ressource = (Ressource) item;
            if (ressource.time > 0) {
                JProgressBar progressBar = new JProgressBar(0, ressource.time);
                // Définir les bounds pour positionner la progressBar
                int progressBarWidth = 400;
                int progressBarHeight = 30;
                int progressBarX = (gamePanel.getWidth() - progressBarWidth) / 2;
                int progressBarY = gamePanel.ui.menuPanel.menuBar.getHeight() + 10;
                progressBar.setBounds(progressBarX, progressBarY, progressBarWidth, progressBarHeight);
                progressBar.setValue(0);
                progressBar.setStringPainted(true);
                gamePanel.add(progressBar);
                gamePanel.repaint();

                new Thread(() -> {
                    int previousGameState = gameState;
                    gamePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
                        public void componentResized(java.awt.event.ComponentEvent evt) {
                            int progressBarX = (gamePanel.getWidth() - progressBarWidth) / 2;
                            int progressBarY = gamePanel.ui.menuPanel.menuBar.getHeight() + 10;
                            progressBar.setBounds(progressBarX, progressBarY, progressBarWidth, progressBarHeight);
                        }
                    });
                    try {
                        for (int i = 0; i <= ressource.time; i++) {
                            if (gameState != previousGameState) {
                                gamePanel.remove(progressBar);
                                gamePanel.repaint();
                                return;
                            }
                            progressBar.setValue(i);
                            Thread.sleep(1000); // Sleep for 1 second
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (gameState == previousGameState) {
                            gamePanel.remove(progressBar);
                            Inventaire.addToAvatarInv(item, 1);
                            itemButton.setVisible(false);
                            resetButton();
                            gamePanel.avatar.resetDeplacement(); // Reset avatar movement
                        }
                    }
                }).start();
            } else {
                Inventaire.addToAvatarInv(item, 1);
                GamePanel.obj.get(index).setInvisible(gamePanel); // Permet de faire disparaître l'objet de la carte
                itemButton.setVisible(false);
                resetButton();
                gamePanel.avatar.resetDeplacement(); // Reset avatar movement
            }
        });
    }

    public void resetButton() {
        gamePanel.remove(itemButton);
        itemButton = new JButton(ItemButtonIcon);
        setupButton();
    }

    public void resizeButtons(double scaleX, double scaleY) {
        itemButton.setBounds(
                (int) (itemButton.getX() * scaleX),
                (int) (itemButton.getY() * scaleY),
                (int) (160 * scaleX),
                (int) (65 * scaleY));
        resizeButtonImages(itemButton, ItemButtonIcon);
    }
}
