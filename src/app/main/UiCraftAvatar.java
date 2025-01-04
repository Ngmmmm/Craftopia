package main;

import item.recettes.Recette;
import item.ressources.Ressource;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UiCraftAvatar extends Ui {
    JPanel mainPanel;
    private JScrollPane recettePanel;

    public UiCraftAvatar(GamePanel gamePanel) {
        super(gamePanel);
        createRecetteContent();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(recettePanel);
        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        mainPanel.add(exit);

        gamePanel.add(mainPanel, JLayeredPane.PALETTE_LAYER);
    }

    private void createRecetteContent() {
        JPanel recetteContent = new JPanel();
        recetteContent.setLayout(new BoxLayout(recetteContent, BoxLayout.Y_AXIS)); // Empile verticalement

        ArrayList<Recette> recettes = Recette.getRecettes();
        for (Recette recette : recettes) {
            recetteContent.add(createLigneRecette(recette));
        }

        recettePanel = new JScrollPane(recetteContent);
        recettePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recettePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel createLigneRecette(Recette recette) {
        JPanel recetteRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        for (int i = 0; i < recette.getIngredients().length; i++) {
            Ressource ressource = recette.getIngredients()[i];

            // Quantité
            JLabel quantity = new JLabel(ressource.getQuantity() + "");

            // Image redimensionnée
            ImageIcon img = resizeIcon(new ImageIcon(ressource.getImage()), 30, 30);
            JLabel imgLabel = new JLabel(img);

            recetteRow.add(quantity);
            recetteRow.add(imgLabel);

            // Ajouter "+" ou "="
            if (i < recette.getIngredients().length - 1) {
                recetteRow.add(new JLabel("+"));
            } else {
                recetteRow.add(new JLabel("="));
            }
        }

        // Résultat de la recette
        ImageIcon resultat = resizeIcon(new ImageIcon(recette.getRessource().getImage()), 30, 30);
        JLabel quantity = new JLabel(recette.getRessource().getQuantity() + "");
        JLabel imgLabel = new JLabel(resultat);

        recetteRow.add(quantity);
        recetteRow.add(imgLabel);

        // Nom de la ressource produite
        JLabel recetteName = new JLabel(recette.getRessource().getName());

        // Bouton build redimensionné
        ImageIcon btnBuild = resizeIcon(new ImageIcon("./img/boutons/BuildButton.png"), 80, 40);
        JButton build = new JButton(btnBuild);
        build.setPreferredSize(new Dimension(80, 40));
        build.setBorderPainted(false);
        build.setFocusPainted(false);
        build.setContentAreaFilled(false);

        build.addActionListener(l -> {
            // Logique pour créer la ressource
        });

        recetteRow.add(recetteName);
        recetteRow.add(build);

        return recetteRow;
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

    public void rebuildRecettePanel() {
        JPanel rePanel = (JPanel) recettePanel.getViewport().getView();
        if (rePanel != null) {
            rePanel.removeAll();
            ArrayList<Recette> recettes = Recette.getRecettes();
            for (Recette recette : recettes) {
                rePanel.add(createLigneRecette(recette));
            }
            rePanel.revalidate();
            rePanel.repaint();
        }
    }
}
