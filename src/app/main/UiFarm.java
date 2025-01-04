package main;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import item.machines.Recolteuse;
import item.ressources.Ressource;
import item.ressources.RessourceVide;
import java.awt.FlowLayout;

public class UiFarm {
    private Recolteuse recolteuse;
    private Ressource ressource;

    JPanel mainPanel;
    private JScrollPane farmPanel;

    public UiFarm(Recolteuse recolteuse) {
        this.recolteuse = recolteuse;
        if (recolteuse.res != null) {
            this.ressource = recolteuse.res;
        } else {
            this.ressource = new RessourceVide();
        }

        createRessourceContent();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(farmPanel);

    }

    private void createRessourceContent() {
        farmPanel = new JScrollPane(createLigneRessource());
        farmPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        farmPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel createLigneRessource() {
        JPanel ressourceContent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        if (ressource != null) {

            // Quantité
            JLabel ressourceFarm = new JLabel(ressource.getName());

            // Image redimensionnée
            ImageIcon img = resizeIcon(new ImageIcon(ressource.getImage()), 50, 50);
            JLabel imgRess = new JLabel(img);

            ressourceContent.add(imgRess);
            ressourceContent.add(ressourceFarm);

            // ! Bouton changeFarm redimensionné
            /*
             * ImageIcon btnChange = resizeIcon(new ImageIcon("./img/boutons/Change.png"),
             * 80, 40);
             * JButton changeFarm = new JButton(btnChange);
             * changeFarm.setPreferredSize(new Dimension(80, 40));
             * changeFarm.setBorderPainted(false);
             * changeFarm.setFocusPainted(false);
             * changeFarm.setContentAreaFilled(false);
             * 
             * changeFarm.addActionListener(l -> {
             * 
             * recolteuse.setRessourceAFarmer();
             * 
             * recolteuse.uirecolteuse.updateInventory();
             * });
             * 
             * ressourceContent.add(ressourceName);
             * ressourceContent.add(changeFarm);
             */

            return ressourceContent;
        }
        return null;

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        if (icon == null)
            return null;
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    public void setBounds(int x, int y, int width, int height) {
        mainPanel.setBounds(x, y, width, height);
    }

    public void rebuildFarmRessourcePanel() {
        if (recolteuse.res != null) {
            this.ressource = recolteuse.res;
        }
        JPanel rePanel = (JPanel) farmPanel.getViewport().getView();
        if (rePanel != null) {
            rePanel.removeAll();
            rePanel.add(createLigneRessource());
            rePanel.revalidate();
            rePanel.repaint();
        }
    }

}
