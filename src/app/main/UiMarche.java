package main;

import item.sprites.SuperObjet;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UiMarche {

    private GamePanel gp;
    private JScrollPane scrlpane;
    private JPanel panel;

    public UiMarche(GamePanel gp) {
        this.gp = gp;
        initMarchePanel();
    }

    private void initMarchePanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 columns: Image, Name, Price, Buy Button, Sell Button

        // Adding headers
        panel.add(new JLabel("Image"));
        panel.add(new JLabel("Nom"));
        panel.add(new JLabel("Prix"));
        panel.add(new JLabel("Acheter"));
        panel.add(new JLabel("Vendre"));

        // Adding SuperObjet items
        for (SuperObjet obj : gp.obj) {
            JLabel imageLabel = new JLabel(new ImageIcon(obj.getImage()));
            JLabel nameLabel = new JLabel(obj.getName());
            JLabel priceLabel = new JLabel(String.valueOf(obj.getPrix()));
            JButton buyButton = new JButton("Acheter");
            JButton sellButton = new JButton("Vendre");

            // Adding action listeners for buttons
            buyButton.addActionListener(e -> acheterObjet(obj));
            sellButton.addActionListener(e -> vendreObjet(obj));

            panel.add(imageLabel);
            panel.add(nameLabel);
            panel.add(priceLabel);
            panel.add(buyButton);
            panel.add(sellButton);
        }

        scrlpane = new JScrollPane(panel);
        gp.marchePanel.add(scrlpane, BorderLayout.CENTER);
    }

    private void acheterObjet(SuperObjet obj) {
        // Logic for buying the object
        System.out.println("Acheter: " + obj.getName());
    }

    private void vendreObjet(SuperObjet obj) {
        // Logic for selling the object
        System.out.println("Vendre: " + obj.getName());
    }
}