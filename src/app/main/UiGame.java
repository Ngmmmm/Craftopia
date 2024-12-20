package main;

import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UiGame {

    private GamePanel gp;

    private final String INV_BUTTON_IMAGE_PATH = "./img/ContinueButton.png";
    private final String BACKTOMENU_BUTTON_IMAGE_PATH = "./img/ExitButton.png";
    private final String SETTINGS_BUTTON_IMAGE_PATH = "./img/SettingButton.png";
    private ImageIcon invButtonIcon;
    private ImageIcon backToMenuIcon;
    private ImageIcon setButtonIcon;
    private JButton invButton;
    private JButton backToMenuButton;
    private JButton setButton;

    public UiGame(GamePanel gp){
        this.gp = gp;
        invButtonIcon = new ImageIcon(INV_BUTTON_IMAGE_PATH);
        backToMenuIcon = new ImageIcon(BACKTOMENU_BUTTON_IMAGE_PATH);
        setButtonIcon = new ImageIcon(SETTINGS_BUTTON_IMAGE_PATH);
        invButton = new JButton(invButtonIcon);
        backToMenuButton = new JButton(backToMenuIcon);
        setButton = new JButton(setButtonIcon);
        invButton.setSize(160,65);
        backToMenuButton.setSize(160,65);
        setButton.setSize(160,65);
        gp.setLayout(new BorderLayout());
        gp.add(invButton, BorderLayout.NORTH);
        gp.add(backToMenuButton, BorderLayout.SOUTH);
        gp.add(setButton, BorderLayout.EAST);
        invButton.setVisible(false);
        backToMenuButton.setVisible(false);
        setButton.setVisible(false);
    }


    public void initButton(){
        resizeButtonImages(setButton, setButtonIcon);
        resizeButtonImages(backToMenuButton, backToMenuIcon);
        resizeButtonImages(invButton, invButtonIcon);
        invButton.addActionListener(l -> {
            gp.gameState = gp.inventaireState;
            gp.buttonGame.hideButton();
        });
        backToMenuButton.addActionListener(l -> {
            gp.gameState = gp.TITLE_STATE;
            gp.buttonGame.hideButton();
            gp.ui.backToMenu();
            gp.initButtonGame = false;
        });
        setButton.addActionListener(l -> {
            gp.gameState = gp.settingsState;
            gp.buttonGame.hideButton();
        });
        invButton.setVisible(true);
        backToMenuButton.setVisible(true);
        setButton.setVisible(true);
    }


    public void hideButton(){
        invButton.setVisible(false);
        backToMenuButton.setVisible(false);
        setButton.setVisible(false);

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
