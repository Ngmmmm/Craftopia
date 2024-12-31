package main;

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
    // private Inventaire stock;

    JPanel mainPanel;
    // private JPanel usinePanel;

    // private JPanel stockContent;
    private JScrollPane recettePanel;
    // private JScrollPane stockPanel;

    public UiUsine(Usine usine) {
        this.usine = usine;
        this.recette = usine.recetteAct;
        // this.stock = usine.stock;

        createRecetteContent();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(recettePanel);
        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            mainPanel.setVisible(false);
        });
        mainPanel.add(exit);

    }

    private void createRecetteContent() {
        // recetteContent.removeAll();

        // ! Pour plusieurs lignes de recettes :
        /*
         * for (int i = 0; i < recette.length; i++) {
         * recetteContent.add(createLigneRecette(recette[i]));
         * }
         */

        recettePanel = new JScrollPane(createLigneRecette());
        recettePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recettePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel createLigneRecette() {
        JPanel recetteContent;
        recetteContent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (int i = 0; i < recette.getIngredients().length; i++) {
            Ressource ressource = recette.getIngredients()[i];
            ImageIcon img = new ImageIcon(ressource.getImage());
            JLabel quantity = new JLabel(ressource.getQuantity() + "");
            JLabel imgLabel = new JLabel(img);

            recetteContent.add(quantity);
            recetteContent.add(imgLabel);

            if (i < recette.getIngredients().length - 1) {
                recetteContent.add(new JLabel("+"));
            } else {
                recetteContent.add(new JLabel("="));
            }
        }
        ImageIcon resultat = new ImageIcon(recette.getRessource().getImage());
        JLabel quantity = new JLabel(recette.getRessource().getQuantity() + "");
        JLabel imgLabel = new JLabel(resultat);
        recetteContent.add(quantity);
        recetteContent.add(imgLabel);
        
        JLabel recetteName = new JLabel(recette.getRessource().getName());
        ImageIcon btnBuild = new ImageIcon("./img/boutons/BuildButton.png");
        JButton build = new JButton(btnBuild);
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
}
