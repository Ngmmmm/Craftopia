package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import item.machines.Recolteuse;

public class UiRecolteuse {
    UiFarm uiFarm;
    UiStock uiStock;
    JPanel mainPanel;
    JPanel contentPanel; // Nouveau panel pour contenir uiFarm et uiStock

    public UiRecolteuse(Recolteuse recolteuse) {
        uiFarm = new UiFarm(recolteuse);
        uiStock = new UiStock(recolteuse);

        mainPanel = new JPanel(new BorderLayout());
        contentPanel = new JPanel(new GridLayout(2, 1)); // 2 lignes, 1 colonne pour égaliser les tailles

        contentPanel.add(uiFarm.getMainPanel());
        contentPanel.add(uiStock.getMainPanel());

        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });

        JPanel buttonPanel = new JPanel(); // Nouveau panel pour contenir le bouton
        buttonPanel.add(exit); // Ajouter le bouton au nouveau panel

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Ajouter le nouveau panel au layout principal
        mainPanel.setVisible(false);
    }

    public void setBounds(int x, int y, int width, int height) {
        mainPanel.setBounds(x, y, width, height);
    }

    public void setVisible(boolean visible) {
        uiFarm.getMainPanel().setVisible(visible);
        uiStock.getMainPanel().setVisible(visible);
        mainPanel.setVisible(visible);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateInventory() {
        uiStock.rebuildStockPanel();
        uiFarm.rebuildFarmRessourcePanel();
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
