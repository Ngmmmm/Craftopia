package main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class UiMenuBar extends Ui {

    private static final String INV_BUTTON_IMAGE_PATH = "./img/boutons/inventory.png";
    private static final String BACKTOMENU_BUTTON_IMAGE_PATH = "./img/boutons/ExitButton.png";
    private static final String SETTINGS_BUTTON_IMAGE_PATH = "./img/boutons/SettingsButton.png";

    private ImageIcon invButtonIcon;
    private ImageIcon backToMenuIcon;
    private ImageIcon setButtonIcon;

    private JButton invButton;
    private JButton backToMenuButton;
    private JButton setButton;

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

        // Initialize buttons
        invButton = createButton(invButtonIcon);
        backToMenuButton = createButton(backToMenuIcon);
        setButton = createButton(setButtonIcon);

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
        menuBar.add(backToMenuButton);
        menuBar.add(setButton);
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
        invButton.setBounds(panelWidth / 2 - 500 / 2, panelHeight / 2 - buttonHeight / 2, buttonWidth, buttonHeight);
        backToMenuButton.setBounds(panelWidth / 2 - 100 / 2, panelHeight / 2 - buttonHeight / 2, buttonWidth,
                buttonHeight);
        setButton.setBounds(panelWidth / 2 + 350 / 2, panelHeight / 2 - buttonHeight / 2, buttonWidth, buttonHeight);

        // Resize icons for the buttons
        resizeButtonImages(invButton, invButtonIcon);
        resizeButtonImages(backToMenuButton, backToMenuIcon);
        resizeButtonImages(setButton, setButtonIcon);
    }

    private void addActionListeners() {
        invButton.addActionListener(l -> {
            menuBar.setVisible(false);
            gameState = inventaireState;
            gamePanel.ui.inventairePanel.updateInventory();

        });

        backToMenuButton.addActionListener(l -> {
            gameState = TITLE_STATE;
            menuBar.setVisible(false);

        });

        setButton.addActionListener(l -> {
            gameState = settingsState;
            menuBar.setVisible(false);

        });
    }

    public void resizeButtons() {
        configureButtonBounds();
    }

    public void setVisible(boolean visible) {
        menuBar.setVisible(visible);
    }

}
