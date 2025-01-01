package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import item.machines.Usine;
import item.recettes.Recette;
import item.ressources.Ressource;

public class UiUsine {
    private Usine usine;
    private Recette recette;

    JPanel mainPanel;
    private JScrollPane recettePanel;

    public UiUsine(Usine usine) {
        this.usine = usine;
        this.recette = usine.recetteAct;

        createRecetteContent();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(recettePanel);

        // Bouton exit centré
        JButton exit = new JButton("exit");
        exit.setPreferredSize(new Dimension(150, 40)); // Largeur augmentée, hauteur réduite
        exit.setAlignmentX(JPanel.CENTER_ALIGNMENT); // Centre horizontalement dans le panel
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            mainPanel.setVisible(false);
        });

        mainPanel.add(exit);
    }

    private void createRecetteContent() {
        recettePanel = new JScrollPane(createLigneRecette());
        recettePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recettePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel createLigneRecette() {
        JPanel recetteContent = new JPanel(new FlowLayout(FlowLayout.LEFT));

        for (int i = 0; i < recette.getIngredients().length; i++) {
            Ressource ressource = recette.getIngredients()[i];

            // Quantité
            JLabel quantity = new JLabel(ressource.getQuantity() + "");

            // Image redimensionnée
            ImageIcon img = resizeIcon(new ImageIcon(ressource.getImage()), 50, 50);
            JLabel imgLabel = new JLabel(img);

            recetteContent.add(quantity);
            recetteContent.add(imgLabel);

            // Ajouter "+" ou "="
            if (i < recette.getIngredients().length - 1) {
                recetteContent.add(new JLabel("+"));
            } else {
                recetteContent.add(new JLabel("="));
            }
        }

        // Résultat de la recette
        ImageIcon resultat = resizeIcon(new ImageIcon(recette.getRessource().getImage()), 50, 50);
        JLabel quantity = new JLabel(recette.getRessource().getQuantity() + "");
        JLabel imgLabel = new JLabel(resultat);

        recetteContent.add(quantity);
        recetteContent.add(imgLabel);

        // Nom de la ressource produite
        JLabel recetteName = new JLabel(recette.getRessource().getName());

        // Bouton build redimensionné
        ImageIcon btnBuild = resizeIcon(new ImageIcon("./img/boutons/BuildButton.png"), 100, 50);
        JButton build = new JButton(btnBuild);
        build.setPreferredSize(new Dimension(100, 50));
        build.setBorderPainted(false);
        build.setFocusPainted(false);
        build.setContentAreaFilled(false);

        recetteContent.add(recetteName);
        recetteContent.add(build);

        return recetteContent;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        if (icon == null) return null;
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }
}
