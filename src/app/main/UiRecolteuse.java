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
        contentPanel = new JPanel(new GridLayout(1, 2)); // 1 ligne, 2 colonnes pour égaliser les tailles

        contentPanel.add(uiFarm.getMainPanel());
        contentPanel.add(uiStock.getMainPanel());

        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(exit, BorderLayout.SOUTH);
        mainPanel.setVisible(false);
    }

    public void setBoundsInside(int x, int y, int width, int height) {
        // Calcule la largeur des sous-panels pour qu'ils soient égaux
        int halfWidth = width / 2;
        uiFarm.getMainPanel().setBounds(x, y, halfWidth, height);
        uiStock.getMainPanel().setBounds(x, y, halfWidth, height);
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
