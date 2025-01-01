package main;

import item.sprites.SuperObjet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;


public class UiInteract {

    private final GamePanel gamePanel;

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
        this.gamePanel = gamePanel;
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
                (int) (posX * gamePanel.scaleXAct),
                (int) (posY * gamePanel.scaleYAct),
                (int) (100 * gamePanel.scaleXAct),
                (int) (21 * gamePanel.scaleYAct));
        resizeButtonImages(itemButton, ItemButtonIcon);
    }

    private void configureButtonAction(SuperObjet item, int index) {
        if (!hasActionListener()) {
            switch (item.getName()) {
                case "Recolteuse":
                    setRecolteuseAction();
                    break;
                case "Usine":
                    setUsineAction();
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
            gamePanel.gameState = gamePanel.shopState;
            itemButton.setVisible(false);
        });
    }

    private boolean hasActionListener() {
        return itemButton.getActionListeners().length > 0;
    }

    private void setRecolteuseAction() {
        itemButton.addActionListener(l -> {
            gamePanel.gameState = gamePanel.recolteuseState;
            itemButton.setVisible(false);
        });
    }

    private void setUsineAction() {
        itemButton.addActionListener(l -> {
            gamePanel.gameState = gamePanel.craftState;
            itemButton.setVisible(false);
        });
    }

    private void setDefaultAction(SuperObjet item, int index) {
        itemButton.addActionListener(l -> {
            if(item.waitIme){
                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gamePanel.avatar.inv.add(item, 1);
                        GamePanel.obj.remove(index); // Permet de faire disparaître l'objet de la carte
                        itemButton.setVisible(false);
                        resetButton();
                        UiInventaire.updateInventory();
                        GamePanel.editMarche = true;
                    }
                };
                Timer timer = new Timer(2000, taskPerformer);
                timer.setRepeats(false);
                timer.start();
            }else{
                gamePanel.avatar.inv.add(item, 1);
                GamePanel.obj.remove(index); // Permet de faire disparaître l'objet de la carte
                itemButton.setVisible(false);
                resetButton();
                UiInventaire.updateInventory();
                GamePanel.editMarche = true;
                
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

    private void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(
                buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }
}
