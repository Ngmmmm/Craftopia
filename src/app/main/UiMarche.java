package main;

import javax.swing.*;
import java.awt.*;

public class UiMarche {

    private JScrollPane scrollPane;

    public UiMarche() {
        // Panneau principal pour le JScrollPane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        for (int i = 0; i < 10; i++) {
            mainPanel.add(createRow("Produit " + (i + 1)));
        }

        // Configure le JScrollPane
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Taille du JScrollPane
    }

    // Méthode pour créer une ligne avec des composants
    private JPanel createRow(String productName) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Layout horizontal
        rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Ajouter une image (placeholder ici)
        JLabel imageLabel = new JLabel("IMG");
        // imageLabel.setPreferredSize(new Dimension(50, 50));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Simule une image
        rowPanel.add(imageLabel);

        // Ajouter le nom du produit
        JLabel nameLabel = new JLabel(productName);
        nameLabel.setPreferredSize(new Dimension(150, 50));
        rowPanel.add(nameLabel);

        // Ajouter le bouton "SELL"
        JButton sellButton = new JButton("SELL");
        rowPanel.add(sellButton);

        // Ajouter le bouton "BUY"
        JButton buyButton = new JButton("BUY");
        rowPanel.add(buyButton);

        return rowPanel;
    }

    // Getter pour récupérer le JScrollPane configuré
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void resizeContent() {
        
    }
}
