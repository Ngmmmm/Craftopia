package main.interfacegraphique;

import item.machines.Usine;
import item.machines.UsinePolyvalente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UiUsine {
    UiRecette uiRecette;
    UiStock uiStock;
    JPanel mainPanel;
    JPanel contentPanel; // Nouveau panel pour contenir uiRecette et uiStock
    JButton exit;

    public UiUsine(Usine usine) {
        uiRecette = new UiRecette(usine);
        uiStock = new UiStock(usine);

        mainPanel = new JPanel(new BorderLayout());
        contentPanel = new JPanel(new GridLayout(2, 1)); // 1 ligne, 2 colonnes pour égaliser les tailles

        contentPanel.add(uiRecette.getMainPanel());
        contentPanel.add(uiStock.getMainPanel());

        JButton reconfigure = new JButton("Reconfigure");
        reconfigure.addActionListener(l -> {
            Ui.gameState = Ui.poseUsineState;
            Ui.poseUsinePanel.rebuild = true;
            Ui.poseUsinePanel.rebuildPoseUsine();
            this.setVisible(false);
        });
        if (usine instanceof UsinePolyvalente) {
            mainPanel.add(reconfigure, BorderLayout.NORTH);
        }

        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });

        JPanel buttonPanel = new JPanel(); // Nouveau panel pour contenir le bouton
        buttonPanel.add(exit); // Ajouter le bouton au nouveau panel

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(new JLabel(usine.getName()),BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Ajouter le nouveau panel au layout principal
        mainPanel.setVisible(false);
    }

    public void setBounds(int x, int y, int width, int height) {
        mainPanel.setBounds(x, y, width, height);
    }

    public void setVisible(boolean visible) {
        uiRecette.getMainPanel().setVisible(visible);
        uiStock.getMainPanel().setVisible(visible);
        mainPanel.setVisible(visible);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateInventory() {
        uiStock.rebuildStockPanel();
        uiRecette.rebuildRecettePanel();
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
