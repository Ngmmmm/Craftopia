package main;

import item.sprites.SuperObjet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UiInteract {

    private final GamePanel gamePanel;

    private JButton itemButton;
    private final ImageIcon ItemButtonIcon;
    private static final String INTERACT_BUTTON_IMAGE_PATH = "./img/InteractButton.png";

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
        gamePanel.add(itemButton);
        gamePanel.setLayout(null);
        itemButton.setVisible(false);
    }

    public void initButton(int posX, int posY, SuperObjet item, int index){
        itemButton.setBounds((int)(posX*gamePanel.scaleXAct),(int) (posY*gamePanel.scaleYAct),(int) (100*gamePanel.scaleXAct),(int)(21*gamePanel.scaleYAct));
        resizeButtonImages(itemButton, ItemButtonIcon);
        System.out.println(item.name);
        int verif = 0;
            for(int i = 0; i<itemButton.getActionListeners().length;i++){
                if (itemButton.getActionListeners()[i] != null){
                    verif++;
                }
            }
        if (item.name.equals("recolteuse") && verif == 0){
            itemButton.addActionListener(l -> {
                gamePanel.gameState = gamePanel.recolteuseState;
                itemButton.setVisible(false);
            });
        }else if (item.name.equals("usine") && verif == 0){
            itemButton.addActionListener(l -> {
                gamePanel.gameState = gamePanel.craftState;
                itemButton.setVisible(false);
            });
        }else{
            if (verif == 0){
            itemButton.addActionListener(l -> {
                gamePanel.avatar.inv.add(item, 1);
                gamePanel.obj.remove(index); // Permet de faire disparaitre l'objet de la carte
                itemButton.setVisible(false);
                resetButton();
                
            });
            }
        }
        itemButton.setVisible(true);
    }

    public void resetButton(){
        gamePanel.remove(itemButton);
        itemButton = new JButton(ItemButtonIcon);
        gamePanel.add(itemButton);
        gamePanel.setLayout(null);
        itemButton.setVisible(false);
        
    }

    public void resizeButtons(double scaleX, double scaleY){
        itemButton.setBounds((int)(itemButton.getX()*scaleX), (int)(itemButton.getY()*scaleY),(int)(160*scaleX),(int)(65*scaleY));
        resizeButtonImages(itemButton, ItemButtonIcon);
    }

    private void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(buttonWidth, buttonHeight,
                java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }

    
}
