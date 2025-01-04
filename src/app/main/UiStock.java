package main;

import item.bag.InventaireMachine;
import item.machines.Machine;
import item.machines.Recolteuse;
import item.machines.Usine;
import item.ressources.Ressource;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UiStock {
    private Machine machine;
    private InventaireMachine stock;

    private JPanel mainPanel;
    private JScrollPane stockPanel;

    public UiStock(Machine machine) {
        this.machine = machine;
        this.stock = machine.stock;

        createStockContent();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(stockPanel);

    }

    private void createStockContent() {
        stockPanel = new JScrollPane(createLigneRecette());
        stockPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        stockPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel createLigneRecette() {
        JPanel stockContent = new JPanel();
        stockContent.setLayout(new BoxLayout(stockContent, BoxLayout.Y_AXIS)); // Organisation verticale

        for (int i = 0; i < stock.getInv().size(); i++) {
            Ressource ressource = (Ressource) stock.getInv().get(i);

            // Créer un sous-panel pour chaque ressource
            JPanel ligne = new JPanel(new BorderLayout());
            ligne.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Espacement autour

            // Quantité et image
            JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5)); // Espacement horizontal et vertical
            JLabel quantity = new JLabel(ressource.getQuantity() + "");
            ImageIcon img = resizeIcon(new ImageIcon(ressource.getImage()), 30, 30);
            JLabel imgLabel = new JLabel(img);
            JLabel name = new JLabel(ressource.getName());

            leftPanel.add(quantity);
            leftPanel.add(imgLabel);
            leftPanel.add(name);

            // Bouton addButton
            ImageIcon btnAddImg = resizeIcon(new ImageIcon("./img/boutons/AddButton.png"), 80, 40);
            JButton addButton = new JButton(btnAddImg);
            addButton.setPreferredSize(new Dimension(80, 40));
            addButton.setBorderPainted(false);
            addButton.setFocusPainted(false);
            addButton.setContentAreaFilled(false);

            addButton.addActionListener(l -> {
                machine.backToPlayer(ressource, ressource.getQuantity());
                if (machine instanceof Usine) {
                    ((Usine) machine).uiUsine.updateInventory();
                }
                if (machine instanceof Recolteuse) {
                    ((Recolteuse) machine).uiRecolteuse.updateInventory();
                    
                }

            });

            // Ajouter le bouton dans un panel intermédiaire pour alignement
            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.add(addButton, BorderLayout.NORTH); // Alignement en haut

            // Ajouter les composants au sous-panel
            ligne.add(leftPanel, BorderLayout.WEST);
            ligne.add(rightPanel, BorderLayout.EAST);

            // Ajouter le sous-panel au panel principal
            stockContent.add(ligne);
        }

        return stockContent;
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

    public void rebuildStockPanel() {
        JPanel invPanel = (JPanel) stockPanel.getViewport().getView();
        invPanel.removeAll();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.Y_AXIS));
        invPanel.add(createLigneRecette());
        invPanel.revalidate();
        invPanel.repaint();
    }
}