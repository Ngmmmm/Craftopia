package main;

import item.sprites.SuperObjet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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
            switch (item.name) {
                case "Récolteuse":
                    setRecolteuseAction();
                    break;
                case "Usine":
                    setUsineAction();
                    break;

                case "Marché":
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
        });
    }

    private boolean hasActionListener() {
        return itemButton.getActionListeners().length > 0;
    }

    private void setRecolteuseAction() {
        itemButton.addActionListener(l -> {
            gameState = recolteuseState;
            itemButton.setVisible(false);
        });
    }

    private void setUsineAction() {
        itemButton.addActionListener(l -> {
            gameState = craftState;
            itemButton.setVisible(false);
        });
    }

    private void setDefaultAction(SuperObjet item, int index) {
        itemButton.addActionListener(l -> {
            gamePanel.avatar.inv.add(item, 1);
            GamePanel.obj.remove(index); // Permet de faire disparaître l'objet de la carte
            itemButton.setVisible(false);
            resetButton();
            gamePanel.ui.inventairePanel.updateInventory();

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

    private void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(
                buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }
}
