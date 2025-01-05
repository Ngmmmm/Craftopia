package main;

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

public class UiSettings extends Ui {

    private JScrollPane scrollPane;
    private JPanel mainPanel;

    public UiSettings(GamePanel gamePanel) {
        super(gamePanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout()); // Utilisation de GridBagLayout pour le positionnement

        // Panneau principal pour le JScrollPane
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom)
        settingsPanel.add(createSettingsRow("Volume", "./img/boutons/VolumeButton.png", l -> {
            // Action pour le bouton "Volume"
        }));


        settingsPanel.add(createSettingsRow("Retour", "./img/boutons/ExitButton.png", l -> {
            // Retourne au menu principal
            Ui.gameState = Ui.TITLE_STATE;
            this.setVisible(false);
        }));

        scrollPane = new JScrollPane(settingsPanel);
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

    private JPanel createSettingsRow(String label, String iconPath, java.awt.event.ActionListener action) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Aligne les éléments à gauche

        // Dimensions dynamiques en fonction de la largeur du panneau
        int panelWidth = 150;
        int rowHeight = 50;

        // Label pour le nom du paramètre
        JLabel nameLabel = new JLabel(label);
        nameLabel.setPreferredSize(new Dimension(panelWidth, rowHeight));
        rowPanel.add(nameLabel);

        // Bouton avec l'image du paramètre
        ImageIcon image = new ImageIcon(iconPath);
        JButton button = new JButton();
        button.setIcon(image);
        button.setPreferredSize(new Dimension(panelWidth, rowHeight));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(action);
        rowPanel.add(button);

        return rowPanel;
    }

    public JPanel getJPanel() {
        return mainPanel;
    }

    public void updateSettings() {
        rebuildSettingsPanel();
    }

    private void rebuildSettingsPanel() {
        JPanel settingsPanel = (JPanel) scrollPane.getViewport().getView();
        settingsPanel.removeAll();

        settingsPanel.add(createSettingsRow("Volume", "./img/boutons/VolumeButton.png", l -> {
            // Action pour le bouton "Volume"
        }));

        settingsPanel.add(createSettingsRow("Retour", "./img/boutons/BackButton.png", l -> {
            // Retourne au menu principal
            Ui.gameState = Ui.TITLE_STATE;
            this.setVisible(false);
        }));

        settingsPanel.revalidate();
        settingsPanel.repaint();
    }

    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }
}
