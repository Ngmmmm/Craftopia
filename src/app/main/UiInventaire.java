package main;

import item.bag.Inventaire;
import item.sprites.SuperObjet;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class UiInventaire {

    private static JScrollPane scrollPane;
    private static List<SuperObjet> inventoryItems;
    private static JPanel mainPanel;

    public UiInventaire() {
        inventoryItems = Inventaire.getInv();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2)); // Divise en 2 colonnes

        // Panneau principal pour le JScrollPane
        JPanel invPanel = new JPanel();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom)
        for (SuperObjet item : inventoryItems) {
            invPanel.add(createRow(item));
        }

        JButton test = new JButton("exit");
        test.addActionListener(l -> {
            GamePanel.gameState = GamePanel.playState;
        });
        invPanel.add(test);

        scrollPane = new JScrollPane(invPanel);

        mainPanel.add(scrollPane);
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

    public JPanel getJPanel() {
        return mainPanel;
    }

    public static void updateInventory() {
        inventoryItems = Inventaire.getInv();
        rebuildInventoryPanel();
    }

    private static void rebuildInventoryPanel() {
        JPanel invPanel = (JPanel) scrollPane.getViewport().getView();
        invPanel.removeAll();
        for (SuperObjet item : inventoryItems) {
            invPanel.add(createRow(item));
        }
        JButton test = new JButton("exit");
        test.addActionListener(l -> {
            GamePanel.gameState = GamePanel.playState;
        });
        invPanel.add(test);
        invPanel.revalidate();
        invPanel.repaint();
    }
}
