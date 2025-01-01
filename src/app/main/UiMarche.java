package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import item.marche.Marche;
import item.sprites.SuperObjet;

public class UiMarche extends Ui {
    JPanel mainPanel;
    private JScrollPane scrollPane;
    private ArrayList<SuperObjet> obj = Marche.getList();

    public UiMarche(GamePanel gamePanel) {
        super(gamePanel);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement
        // Panneau principal pour le JScrollPane
        JPanel invPanel = new JPanel();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        System.out.println(obj.size());
        for (int i = 0; i < obj.size(); i++) {
            System.out.println(obj.get(i).getName());
            invPanel.add(createRow(obj.get(i).getName(), i));
        }

        // Configure le JScrollPane
        scrollPane = new JScrollPane(invPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Taille du JScrollPane

        mainPanel.add(scrollPane);
        gamePanel.layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        mainPanel.setVisible(false);
        
    }

    // Méthode pour créer une ligne avec des composants
    private JPanel createRow(String productName, int index) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Layout horizontal
        rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ImageIcon image = new ImageIcon(obj.get(index).getImage());
        ImageIcon btnSell = new ImageIcon("./img/boutons/SellButton.png");
        ImageIcon btnBuy = new ImageIcon("./img/boutons/BuyButton.png");
        // Ajouter une image (placeholder ici)
        JLabel imageLabel = new JLabel();

        imageLabel.setIcon(image); // Remplacer par une image
        imageLabel.setPreferredSize(new Dimension(50, 50));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        rowPanel.add(imageLabel);

        // Ajouter le nom du produit
        JLabel nameLabel = new JLabel(productName);
        nameLabel.setPreferredSize(new Dimension(150, 50));
        rowPanel.add(nameLabel);

        // Ajouter le bouton "SELL"
        JButton sellButton = new JButton();
        sellButton.setIcon(btnSell);
        sellButton.setBounds(50, 50, 90, 40);
        // Redimensionner le bouton "SELL"
        sellButton.setPreferredSize(new Dimension(90, 40));
        resizeButtonImages(sellButton, btnSell);
        rowPanel.add(sellButton);

        // Ajouter le bouton "BUY"
        JButton buyButton = new JButton();
        buyButton.setIcon(btnBuy);
        // Redimensionner le bouton "BUY"
        buyButton.setPreferredSize(new Dimension(90, 40));
        buyButton.setBounds(50, 50, 90, 40);
        resizeButtonImages(buyButton, btnBuy);
        rowPanel.add(buyButton);

        return rowPanel;
    }

    // Getter pour récupérer le JScrollPane configuré
    public JPanel getJPanel() {
        return mainPanel;
    }

    /**
     * Redimensionne l'image d'un bouton pour qu'elle corresponde à sa taille
     * actuelle.
     * 
     * @param button Le bouton à redimensionner
     * @param icon   L'icône à redimensionner
     */
    // private void resizeButtonImages(JButton button, Icon icon) {
    //     int buttonWidth = button.getWidth();
    //     int buttonHeight = button.getHeight();

    //     // Redimensionner l'icône en fonction de la taille du bouton
    //     ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(buttonWidth, buttonHeight,
    //             java.awt.Image.SCALE_SMOOTH));
    //     button.setIcon(resizedIcon);
    // }

    public void resizeContent() {
        // TODO : Réajuster la position des composants par rapport à la taille de la
        // fenêtre
    }

    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }
}
