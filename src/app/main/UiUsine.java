package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

        // Ajoutez un ComponentListener pour écouter les événements de redimensionnement
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponentSizes();
            }
        });
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
            ImageIcon img = resizeIcon(new ImageIcon(ressource.getImage()), 30, 30);
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
        ImageIcon resultat = resizeIcon(new ImageIcon(recette.getRessource().getImage()), 30, 30);
        JLabel quantity = new JLabel(recette.getRessource().getQuantity() + "");
        JLabel imgLabel = new JLabel(resultat);

        recetteContent.add(quantity);
        recetteContent.add(imgLabel);

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
            usine.createRessource();
        });

        recetteContent.add(recetteName);
        recetteContent.add(build);

        return recetteContent;
    }

    private void adjustComponentSizes() {
        int panelWidth = mainPanel.getWidth();
        int rowHeight = 40;

        JPanel recetteContent = (JPanel) recettePanel.getViewport().getView();
        for (int i = 0; i < recetteContent.getComponentCount(); i++) {
            if (recetteContent.getComponent(i) instanceof JPanel) {
                JPanel rowPanel = (JPanel) recetteContent.getComponent(i);
                for (int j = 0; j < rowPanel.getComponentCount(); j++) {
                    if (rowPanel.getComponent(j) instanceof JLabel) {
                        JLabel label = (JLabel) rowPanel.getComponent(j);
                        if (j == 0) { // quantity
                            label.setPreferredSize(new Dimension(panelWidth / 8, rowHeight));
                        } else if (j == 1) { // imgLabel
                            label.setPreferredSize(new Dimension(panelWidth / 8, rowHeight));
                        } else if (j == 2) { // recetteName
                            label.setPreferredSize(new Dimension(panelWidth / 2, rowHeight));
                        }
                    } else if (rowPanel.getComponent(j) instanceof JButton) {
                        JButton button = (JButton) rowPanel.getComponent(j);
                        button.setPreferredSize(new Dimension(panelWidth / 8, rowHeight));
                    }
                }
                rowPanel.revalidate();
                rowPanel.repaint();
            }
        }
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

    public void setBounds(int x, int y, int width, int height) {
        mainPanel.setBounds(x, y, width, height);
    }
}