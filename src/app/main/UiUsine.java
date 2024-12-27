package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import item.bag.Inventaire;
import item.sprites.SuperObjet;

public class UiUsine {

    private static JScrollPane scrollPane;
    private static ArrayList<SuperObjet> inventoryItems;

    public UiUsine() {

        inventoryItems = Inventaire.getInv();
        // Panneau principal pour le JScrollPane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        for (SuperObjet item : inventoryItems) {
            mainPanel.add(createRow(item));
        }
        JButton exit = new JButton("X");
        exit.addActionListener(l -> {
            GamePanel.gameState = GamePanel.playState;
        });

        // Configure le JScrollPane
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Taille du JScrollPane
        scrollPane.add(exit);
        // scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, exit);
    }

    // Méthode pour créer une ligne avec des composants
    private static JPanel createRow(SuperObjet item) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        ImageIcon image = new ImageIcon(item.getImage());
        ImageIcon btnBuild = new ImageIcon("./img/boutons/BuildButton.png");

        // Ajouter une image (placeholder ici)
        JLabel imageLabel = new JLabel();

        imageLabel.setIcon(image); // Remplacer par une image
        imageLabel.setPreferredSize(new Dimension(50, 50));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        rowPanel.add(imageLabel);

        // Ajouter le nom du produit
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setPreferredSize(new Dimension(150, 50));
        rowPanel.add(nameLabel);

        // Ajouter le bouton "BUILD"
        JButton buildButton = new JButton();
        buildButton.setIcon(btnBuild);
        buildButton.setBounds(50, 50, 90, 40);
        // Redimensionner le bouton "BUILD"
        buildButton.setPreferredSize(new Dimension(90, 40));
        resizeButtonImages(buildButton, btnBuild);
        rowPanel.add(buildButton);

        return rowPanel;
    }

    // Getter pour récupérer le JScrollPane configuré
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Redimensionne l'image d'un bouton pour qu'elle corresponde à sa taille
     * actuelle.
     * 
     * @param button Le bouton à redimensionner
     * @param icon   L'icône à redimensionner
     */
    private static void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(buttonWidth, buttonHeight,
                java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }

    public void resizeContent() {
        // TODO : Réajuster la position des composants par rapport à la taille de la
        // fenêtre
    }

    public static void updateInventory() {
        inventoryItems = Inventaire.getInv();
        rebuildUsinePanel();
    }

    private static void rebuildUsinePanel() {
        JPanel mainPanel = (JPanel) scrollPane.getViewport().getView();
        mainPanel.removeAll();
        for (SuperObjet item : inventoryItems) {
            mainPanel.add(createRow(item));
        }
        JButton test = new JButton("exit");
        test.addActionListener(l -> {
            GamePanel.gameState = GamePanel.playState;
        });
        mainPanel.add(test);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
