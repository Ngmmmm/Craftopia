package main;

import item.sprites.SuperObjet;
import javax.swing.JButton;

public class UiGame {

    private GamePanel gamePanel;

    private JButton itemButton;

    /**
     * Constructeur de la classe `UiGame`.
     * 
     * @param gamePanel Le panneau de jeu principal, utilisé pour afficher le menu
     *                  et le jeu
     */
    public UiGame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.itemButton = new JButton("Interagir");
        gamePanel.add(itemButton);
        gamePanel.setLayout(null);
        itemButton.setVisible(false);
    }

    public void initButton(int posX, int posY, SuperObjet item, int index){
        itemButton.setBounds((int)(posX*gamePanel.scaleXAct),(int) (posY*gamePanel.scaleYAct),(int) (gamePanel.TAILLE_BLOC*gamePanel.scaleXAct),(int)(gamePanel.TAILLE_BLOC*gamePanel.scaleYAct));
        if (item.name.equals("recolteuse")){
            itemButton.addActionListener(l -> {
                gamePanel.gameState = gamePanel.recolteuseState;
                itemButton.setVisible(false);
            });
        }else if (item.name.equals("usine")){
            itemButton.addActionListener(l -> {
                gamePanel.gameState = gamePanel.craftState;
                itemButton.setVisible(false);
            });
        }else{
            itemButton.addActionListener(l -> {
                gamePanel.avatar.inv.add(item, 1);
                itemButton.setVisible(false);
                gamePanel.obj.remove(index); // Permet de faire disparaitre l'objet de la carte
                
            });
        }
        itemButton.setVisible(true);
    }

    public void hideAndResetButton(){
        for (int i =0; i<itemButton.getActionListeners().length;i++){
            itemButton.removeActionListener(itemButton.getActionListeners()[i]);
        }
        itemButton.setVisible(false);
        
    }

    public void resizeButtons(double scaleX, double scaleY){
        itemButton.setBounds((int)(itemButton.getX()*scaleX), (int)(itemButton.getY()*scaleY),(int)(gamePanel.TAILLE_BLOC*scaleX),(int)(gamePanel.TAILLE_BLOC*scaleY));
    }

    
}
