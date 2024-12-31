package main;

import item.bag.Argent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class UiGame {

    private GamePanel gp;

    private final String INV_BUTTON_IMAGE_PATH = "./img/boutons/inventory.png";
    private final String BACKTOMENU_BUTTON_IMAGE_PATH = "./img/boutons/ExitButton.png";
    private final String SETTINGS_BUTTON_IMAGE_PATH = "./img/boutons/SettingsButton.png";
    private ImageIcon invButtonIcon;
    private ImageIcon backToMenuIcon;
    private ImageIcon setButtonIcon;
    private JButton invButton;
    private JButton backToMenuButton;
    private JButton setButton;
    private static JLabel argent;

    public UiGame(GamePanel gp) {
        this.gp = gp;
        invButtonIcon = new ImageIcon(INV_BUTTON_IMAGE_PATH);
        backToMenuIcon = new ImageIcon(BACKTOMENU_BUTTON_IMAGE_PATH);
        setButtonIcon = new ImageIcon(SETTINGS_BUTTON_IMAGE_PATH);
        invButton = new JButton(invButtonIcon);

        // ! Ce bloc permet de supprimer les contours, bordures et fond du bouton
        invButton.setOpaque(false);
        invButton.setContentAreaFilled(false);
        invButton.setBorderPainted(false);
        invButton.setFocusPainted(false);

        backToMenuButton = new JButton(backToMenuIcon);
        setButton = new JButton(setButtonIcon);
        invButton.setBounds(gp.menuBar.getWidth() / 2 - 500 / 2, gp.menuBar.getHeight() / 2 - 30 / 2, 70, 30);
        backToMenuButton.setBounds(gp.menuBar.getWidth() / 2 - 100 / 2, gp.menuBar.getHeight() / 2 - 30 / 2, 70, 30);
        setButton.setBounds(gp.menuBar.getWidth() / 2 + 350 / 2, gp.menuBar.getHeight() / 2 - 30 / 2, 70, 30);

        argent = new JLabel(Integer.toString(Argent.getMonnaiePlayer()));
        argent.setBounds(gp.menuBar.getWidth() / 2 + 600 / 2, gp.menuBar.getHeight() -30 , 70, 30);
    }

    public void initButton() {
        gp.menuBar.add(invButton);
        gp.menuBar.add(backToMenuButton);
        gp.menuBar.add(setButton);
        gp.menuBar.add(argent);
        resizeButtonImages(setButton, setButtonIcon);
        resizeButtonImages(backToMenuButton, backToMenuIcon);
        resizeButtonImages(invButton, invButtonIcon);
        invButton.addActionListener(l -> {
            gp.menuBar.setVisible(false);
            gp.gameState = gp.inventaireState;
        });
        backToMenuButton.addActionListener(l -> {
            gp.gameState = gp.TITLE_STATE;
            gp.ui.backToMenu();
            gp.initButtonGame = false;
            gp.menuBar.setVisible(false);
            gp.avatar.setValeurParDefaut();
        });
        setButton.addActionListener(l -> {
            gp.gameState = gp.settingsState;
            gp.menuBar.setVisible(false);
        });
        invButton.setVisible(true);
        backToMenuButton.setVisible(true);
        setButton.setVisible(true);


    }

    public void resizeButtons(double scaleX, double scaleY) {
        invButton.setBounds(gp.menuBar.getWidth() / 2 - 500 / 2,
        gp.menuBar.getHeight() / 2 - 30 / 2, 70, 30);
        backToMenuButton.setBounds(gp.menuBar.getWidth() / 2 - 100 / 2,
        gp.menuBar.getHeight() / 2 - 30 / 2, 70, 30);
        setButton.setBounds(gp.menuBar.getWidth() / 2 + 350 / 2,
        gp.menuBar.getHeight() / 2 - 30 / 2, 70, 30);
        resizeButtonImages(setButton, setButtonIcon);
        resizeButtonImages(backToMenuButton, backToMenuIcon);
        resizeButtonImages(invButton, invButtonIcon);
        argent.setBounds(gp.menuBar.getWidth() / 2 + 600 / 2, gp.menuBar.getHeight() -30 , 70, 30);
    }

    private void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(buttonWidth, buttonHeight,
                java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }

    public static void actArgent(){
        argent.setText(Integer.toString(Argent.getMonnaiePlayer()));
    }

}
