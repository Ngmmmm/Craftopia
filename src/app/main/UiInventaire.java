package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import item.bag.Inventaire;
import item.sprites.SuperObjet;

public class UiInventaire {

    private static JScrollPane scrollPane;
    private static List<SuperObjet> inventoryItems;

    public UiInventaire() {
        inventoryItems = Inventaire.getInv();

        // Panneau principal pour le JScrollPane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom)
        for (SuperObjet item : inventoryItems) {
            mainPanel.add(createRow(item));
        }

        scrollPane = new JScrollPane(mainPanel);
    }

    private static JPanel createRow(SuperObjet item) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        ImageIcon image = new ImageIcon(item.getImage());
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setPreferredSize(new Dimension(50, 50));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rowPanel.add(imageLabel);

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setPreferredSize(new Dimension(150, 50));
        rowPanel.add(nameLabel);

        return rowPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public static void updateInventory() {
        inventoryItems = Inventaire.getInv();
        rebuildInventoryPanel();
    }

    private static void rebuildInventoryPanel() {
        JPanel mainPanel = (JPanel) scrollPane.getViewport().getView();
        mainPanel.removeAll();
        for (SuperObjet item : inventoryItems) {
            mainPanel.add(createRow(item));
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
