package main;

import item.bag.Inventaire;
import item.sprites.SuperObjet;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class UiInventaire extends Ui {

    private JScrollPane scrollPane;
    JPanel mainPanel;

    public UiInventaire(GamePanel gamePanel) {
        super(gamePanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout()); // Utilisation de GridBagLayout pour le positionnement

        // Panneau principal pour le JScrollPane
        JPanel invPanel = new JPanel();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom)
        for (SuperObjet item : Inventaire.getInvAvatar()) {
            invPanel.add(createRow(item));
        }

        // Ajout du bouton exit
        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT); // Centre le bouton horizontalement
        invPanel.add(exit);

        scrollPane = new JScrollPane(invPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Positionne le JScrollPane dans le panneau principal
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(scrollPane, gbc);

        gamePanel.add(mainPanel);
    }

    private JPanel createRow(SuperObjet item) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Aligne les éléments à gauche

        // Dimensions dynamiques en fonction de la largeur du panneau
        int panelWidth = mainPanel.getWidth();
        int rowHeight = 50;

        // Label pour la quantité
        JLabel nbLabel = new JLabel("x" + Inventaire.getQuantityFromAvatarInv(item));
        nbLabel.setPreferredSize(new Dimension(panelWidth / 6, rowHeight));
        rowPanel.add(nbLabel);

        // Image de l'objet
        ImageIcon image = new ImageIcon(item.getImage());
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setPreferredSize(new Dimension(panelWidth / 6, rowHeight));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rowPanel.add(imageLabel);

        // Nom de l'objet
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setPreferredSize(new Dimension(panelWidth / 2, rowHeight));
        rowPanel.add(nameLabel);
        

    

        return rowPanel;
    }

    public JPanel getJPanel() {
        return mainPanel;
    }

    public void updateInventory() {
        rebuildInventoryPanel();
    }

    private void rebuildInventoryPanel() {
        JPanel invPanel = (JPanel) scrollPane.getViewport().getView();
        invPanel.removeAll();
        for (SuperObjet item : Inventaire.getInvAvatar()) {
            invPanel.add(createRow(item));
        }
        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT); // Centre le bouton horizontalement
        invPanel.add(exit);
        invPanel.revalidate();
        invPanel.repaint();
    }

    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }
}
