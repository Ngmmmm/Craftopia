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

public class UiInventaire extends Ui {

    private JScrollPane scrollPane;
    private List<SuperObjet> inventoryItems;
    JPanel mainPanel;

    public UiInventaire(GamePanel gamePanel) {
        super(gamePanel);
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
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        invPanel.add(test);

        scrollPane = new JScrollPane(invPanel);

        mainPanel.add(scrollPane);
        gamePanel.add(mainPanel);

    }

    private JPanel createRow(SuperObjet item) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Dimensions dynamiques basées sur la taille du mainPanel
        int panelWidth = mainPanel.getWidth();
        int imageSize = panelWidth / 10; // Exemple : image = 10% de la largeur
        int labelWidth = panelWidth / 5; // Exemple : texte = 20% de la largeur

        // Quantité
        JLabel nbLabel = new JLabel("x" + Inventaire.getQuantity(item));
        nbLabel.setPreferredSize(new Dimension(labelWidth, imageSize));
        rowPanel.add(nbLabel);

        // Image de l'objet
        ImageIcon image = new ImageIcon(item.getImage());
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(
                new ImageIcon(image.getImage().getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH)));
        imageLabel.setPreferredSize(new Dimension(imageSize, imageSize));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rowPanel.add(imageLabel);

        // Nom de l'objet
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setPreferredSize(new Dimension(panelWidth - (labelWidth + imageSize + 50), imageSize));
        rowPanel.add(nameLabel);

        return rowPanel;
    }

    public JPanel getJPanel() {
        return mainPanel;
    }

    public void updateInventory() {
        inventoryItems = Inventaire.getInv();
        rebuildInventoryPanel();
    }

    private void rebuildInventoryPanel() {
        JPanel invPanel = (JPanel) scrollPane.getViewport().getView();
        invPanel.removeAll();
        for (SuperObjet item : inventoryItems) {
            invPanel.add(createRow(item));
        }
        JButton test = new JButton("exit");
        test.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        invPanel.add(test);
        invPanel.revalidate();
        invPanel.repaint();
    }

    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }
}
