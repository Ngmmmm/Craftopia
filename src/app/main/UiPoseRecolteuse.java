package main;

import item.machines.Usine;
import item.recettes.Recette;
import item.ressources.Ressource;
import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import tile.Point;

class UiPoseRecolteuse extends Ui {
    JPanel mainPanel;
    JScrollPane scrollPane;
    Point pos;
    public static Usine act;
    public ArrayList<Ressource> ressources = new ArrayList<Ressource>();

    public UiPoseRecolteuse(GamePanel gamePanel) {
        super(gamePanel);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement
        // Panneau principal pour le JScrollPane
        JPanel posRecolteusePanel = new JPanel();
        posRecolteusePanel.setLayout(new BoxLayout(posRecolteusePanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        for (int i = 0; i < Recette.getRecettes().size(); i++) {
            // posUsinePanel.add(createRow(Recette.getRecettes().get(i)));
        }

        // Configure le JScrollPane
        scrollPane = new JScrollPane(posRecolteusePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Taille du JScrollPane

        mainPanel.add(scrollPane);
        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT); // Centre le bouton horizontalement
        mainPanel.add(exit);
        gamePanel.layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        mainPanel.setVisible(false);
    }


    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }
}