package main;

import item.bag.Argent;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class UiMenuBar extends Ui {

    private static final String INV_BUTTON_IMAGE_PATH = "./img/boutons/inventory.png";
    private static final String BACKTOMENU_BUTTON_IMAGE_PATH = "./img/boutons/ExitButton.png";
    private static final String SETTINGS_BUTTON_IMAGE_PATH = "./img/boutons/SettingsButton.png";
    private static final String CRAFTAVATAR_BUTTON_IMAGE_PATH = "./img/boutons/CraftAvatarButton.png";

    private ImageIcon invButtonIcon;
    private ImageIcon backToMenuIcon;
    private ImageIcon setButtonIcon;
    private ImageIcon crafAvatarIcon;

    private JButton invButton;
    private JButton backToMenuButton;
    private JButton setButton;
    private JButton crafAvatarButton;

    private JLabel argent;

    public JPanel menuBar;

    public UiMenuBar(GamePanel gp) {
        super(gp);
        initializeComponents();
        configureMenuBar();
        addComponentsToMenuBar();
        addMenuBarToLayeredPane();
        configureResizeListener();

        menuBar.setBounds(0, 0, gp.getWidth(), 100); // Assure des dimensions correctes pour le menu
        menuBar.revalidate();
        menuBar.repaint();

    }

    public void initializeComponents() {
        // Initialize icons
        invButtonIcon = new ImageIcon(INV_BUTTON_IMAGE_PATH);
        backToMenuIcon = new ImageIcon(BACKTOMENU_BUTTON_IMAGE_PATH);
        setButtonIcon = new ImageIcon(SETTINGS_BUTTON_IMAGE_PATH);
        crafAvatarIcon = new ImageIcon(CRAFTAVATAR_BUTTON_IMAGE_PATH);

        // Initialize buttons
        invButton = createButton(invButtonIcon);
        backToMenuButton = createButton(backToMenuIcon);
        setButton = createButton(setButtonIcon);
        crafAvatarButton = createButton(crafAvatarIcon);
        argent = new JLabel(Integer.toString(Argent.getMonnaiePlayer()) + " $");

        // Initialize menu bar
        menuBar = new JPanel();
        menuBar.setLayout(null); // Use null layout for absolute positioning
        menuBar.setBackground(Color.GRAY);
        menuBar.setFocusable(false);

    }

    private JButton createButton(ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFocusable(false); // Prevent the button from capturing keyboard focus
        return button;
    }

    private void configureMenuBar() {
        menuBar.setVisible(false); // Start hidden
    }

    private void addComponentsToMenuBar() {
        menuBar.add(invButton);
        menuBar.add(crafAvatarButton);
        menuBar.add(backToMenuButton);
        menuBar.add(setButton);
        menuBar.add(argent);
    }

    private void addMenuBarToLayeredPane() {
        gamePanel.layeredPane.add(menuBar, JLayeredPane.POPUP_LAYER);
    }

    private void configureResizeListener() {
        menuBar.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeButtons();
            }
        });
    }

    public void initButton() {
        configureButtonBounds();
        addActionListeners();
        menuBar.setVisible(true); // Show the menu bar
    }

    private void configureButtonBounds() {
        int panelWidth = menuBar.getWidth();
        int panelHeight = menuBar.getHeight();
        int buttonWidth = 70;
        int buttonHeight = 30;

        // Set bounds for buttons
        int spacing = 20; // Define the spacing between buttons
        int totalWidth = 4 * buttonWidth + 3 * spacing; // Total width occupied by buttons and spacing
        int startX = (panelWidth - totalWidth) / 2; // Starting X position for the first button

        invButton.setBounds(startX, panelHeight / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
        crafAvatarButton.setBounds(startX + buttonWidth + spacing, panelHeight / 2 - buttonHeight / 2, buttonWidth,
                buttonHeight);
        backToMenuButton.setBounds(startX + 2 * (buttonWidth + spacing), panelHeight / 2 - buttonHeight / 2,
                buttonWidth, buttonHeight);
        setButton.setBounds(startX + 3 * (buttonWidth + spacing), panelHeight / 2 - buttonHeight / 2, buttonWidth,
                buttonHeight);
        argent.setBounds(panelWidth - 100, panelHeight / 2 - buttonHeight / 2, 100, buttonHeight);  
        // Resize icons for the buttons
        resizeButtonImages(invButton, invButtonIcon);
        resizeButtonImages(backToMenuButton, backToMenuIcon);
        resizeButtonImages(setButton, setButtonIcon);
        resizeButtonImages(crafAvatarButton, crafAvatarIcon);
    }

    private void addActionListeners() {
        invButton.addActionListener(l -> {
            gamePanel.ui.inventairePanel.updateInventory();
            gameState = inventaireState;
        });

        backToMenuButton.addActionListener(l -> {
            int response = JOptionPane.showConfirmDialog(gamePanel, "Souhaitez-vous sauvegarder avant de quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                gamePanel.saveEtatMap.saveEtatMap();
                Ui.gameState = Ui.TITLE_STATE;
                menuBar.setVisible(false);
            }
            Ui.gameState = Ui.TITLE_STATE;
            menuBar.setVisible(false);
        });

        setButton.addActionListener(l -> {
            gameState = settingsState;
            menuBar.setVisible(false);

        });

        crafAvatarButton.addActionListener(l -> {
            gameState = craftAvatarState;
            menuBar.setVisible(false);
        });
    }

    public void resizeButtons() {
        configureButtonBounds();
    }

    public void setVisible(boolean visible) {
        menuBar.setVisible(visible);
    }

    public void actArgent() {
        argent.setText(Integer.toString(Argent.getMonnaiePlayer()) + " $");
    }
}
