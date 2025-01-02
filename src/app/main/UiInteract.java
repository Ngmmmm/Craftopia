package main;

import item.bag.Inventaire;
import item.machines.Recolteuse;
import item.machines.Usine;
import item.sprites.SuperObjet;
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
        Recolteuse recolteuse = (Recolteuse) item;
        itemButton.addActionListener(l -> {
            gameState = recolteuseState;
            itemButton.setVisible(false);
        });
    }

    private void setUsineAction(SuperObjet item, int index) {
        itemButton.addActionListener(l -> {
            gameState = craftState;
            itemButton.setVisible(false);

            Usine usine = (Usine) item;
            int width = gamePanel.getWidth();
            int height = gamePanel.getHeight();

            int scrollWidth = (int) (width * 0.9); // Increase width to cover almost the entire screen
            int scrollHeight = height / 2; // Increase height to cover almost the entire screen
            int scrollX = (width - scrollWidth) / 2; // Center horizontally
            int scrollY = (height - scrollHeight) / 2; // Center vertically

            usine.uiUsine.setVisible(true);
            usine.uiUsine.setBoundsInside(scrollX, scrollY, scrollWidth, scrollHeight);
            usine.uiUsine.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);

            gamePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent evt) {
                    int newWidth = gamePanel.getWidth();
                    int newHeight = gamePanel.getHeight();

                    int newScrollWidth = (int) (newWidth * 0.9); // Increase width to cover almost the entire screen
                    int newScrollHeight = newHeight / 2; // Increase height to cover almost the entire screen
                    int newScrollX = (newWidth - newScrollWidth) / 2; // Center horizontally
                    int newScrollY = (newHeight - newScrollHeight) / 2; // Center vertically

                    usine.uiUsine.setBounds(newScrollX, newScrollY, scrollWidth, scrollHeight);
                    usine.uiUsine.setBoundsInside(newScrollX, newScrollY, scrollWidth, scrollHeight);
                }
            });

        });
    }

    private void setDefaultAction(SuperObjet item, int index) {
        itemButton.addActionListener(l -> {
            Inventaire.addToAvatarInv(item, 1);
            GamePanel.obj.get(index).setInvisible(gamePanel); // Permet de faire disparaître l'objet de la carte
            itemButton.setVisible(false);
            resetButton();

            // ! Pour le timer de l'ajout de l'objet à l'inventaire
            // if(item.waitIme){
            //     ActionListener taskPerformer = new ActionListener() {
            //         public void actionPerformed(ActionEvent evt) {
            //             gamePanel.avatar.inv.add(item, 1);
            //             GamePanel.obj.remove(index); // Permet de faire disparaître l'objet de la carte
            //             itemButton.setVisible(false);
            //             resetButton();
            //             UiInventaire.updateInventory();
            //             GamePanel.editMarche = true;
            //         }
            //     };
            //     Timer timer = new Timer(2000, taskPerformer);
            //     timer.setRepeats(false);
            //     timer.start();
            // }else{
            //     gamePanel.avatar.inv.add(item, 1);
            //     GamePanel.obj.remove(index); // Permet de faire disparaître l'objet de la carte
            //     itemButton.setVisible(false);
            //     resetButton();
            //     UiInventaire.updateInventory();
            //     GamePanel.editMarche = true;
                
            // }


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
