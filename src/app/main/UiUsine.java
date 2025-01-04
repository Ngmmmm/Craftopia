package main;

import item.machines.Usine;
import item.machines.UsinePolyvalente;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UiUsine {
    UiRecette uiRecette;
    UiStock uiStock;
    JPanel mainPanel;
    JPanel contentPanel; // Nouveau panel pour contenir uiRecette et uiStock

    public UiUsine(Usine usine) {
        uiRecette = new UiRecette(usine);
        uiStock = new UiStock(usine);

        mainPanel = new JPanel(new BorderLayout());
        contentPanel = new JPanel(new GridLayout(1, 2)); // 1 ligne, 2 colonnes pour égaliser les tailles

        contentPanel.add(uiRecette.getMainPanel());
        contentPanel.add(uiStock.getMainPanel());
        JButton reconfigure = new JButton("Reconfigure");
        reconfigure.addActionListener(l -> {
            Ui.gameState = Ui.poseUsineState;
            Ui.poseUsinePanel.rebuild = true;
            Ui.poseUsinePanel.rebuildPoseUsine();
            this.setVisible(false);
        });
        if(usine instanceof UsinePolyvalente){
            mainPanel.add(reconfigure, BorderLayout.NORTH);
        }

        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        //exit.setAlignmentX(JButton.CENTER_ALIGNMENT);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(exit, BorderLayout.SOUTH);
        mainPanel.setVisible(false);
    }

    

    public void setBoundsInside(int x, int y, int width, int height) {
        // Calcule la largeur des sous-panels pour qu'ils soient égaux
        int halfWidth = width / 2;
        uiRecette.getMainPanel().setBounds(x, y, halfWidth, height);
        uiStock.getMainPanel().setBounds(x, y, halfWidth, height);
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
