package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import item.bag.Inventaire;
import item.recettes.Recette;
import item.ressources.Ressource;
import item.sprites.SuperObjet;

public class UiUsine {

    private static JPanel mainPanel;
    private static JScrollPane recettes;
    private static JScrollPane inventaireUsine;
    private static ArrayList<Recette> livres;
    private static ArrayList<SuperObjet> inventoryItems;

    public UiUsine() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2)); // Divise en 2 colonnes
        // Récupère les recettes disponibles
        livres = Recette.getRecettes();

        // Récupère les objets de l'inventaire
        inventoryItems = Inventaire.getInv();

        // Panneau principal pour le JScrollPane
        JPanel usinePanel = new JPanel();
        usinePanel.setLayout(new BoxLayout(usinePanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom,
        // Bouton)
        for (Recette item : livres) {
            usinePanel.add(createRow(item));
        }
        JButton exit = new JButton("EXIT");
        exit.addActionListener(l -> {
            GamePanel.gameState = GamePanel.playState;
        });

        // Configure le JScrollPane
        recettes = new JScrollPane(usinePanel);
        recettes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recettes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        recettes.setPreferredSize(new Dimension(500, 300)); // Taille du JScrollPane
        recettes.setCorner(JScrollPane.UPPER_RIGHT_CORNER, exit);

        usinePanel.add(exit);

        inventaireUsine = new JScrollPane();
        mainPanel.add(recettes);
        mainPanel.add(inventaireUsine);
    }

    // Méthode pour créer une ligne avec des composants
    private static JPanel createRow(Recette item) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        Ressource[] ingredients = item.getIngredients();

        int maxIngredients = 0;
        for (Recette recette : livres) {
            int nombreDeRessources = getNombreDeRessourcesDifferentes(recette);
            if (nombreDeRessources > maxIngredients) {
                maxIngredients = nombreDeRessources;
            }
        }

        for (int i = 0; i < maxIngredients; i++) {
            if (i < ingredients.length) {
                // Récupérer l'ingrédient et sa quantité
                Ressource ingredient = ingredients[i];

                // Créer un JLabel pour afficher la quantité de l'ingrédient
                JLabel nbIngredient = new JLabel(String.valueOf(ingredient.getQuantity()));
                ImageIcon imageIngredient = new ImageIcon(ingredient.getImage());
                JLabel ingredientLabel = new JLabel();

                // Ajouter une image (placeholder ici)
                ingredientLabel.setIcon(imageIngredient);
                ingredientLabel.setPreferredSize(new Dimension(50, 50));
                ingredientLabel.setHorizontalAlignment(SwingConstants.CENTER);

                // Ajouter le JLabel de la quantité et l'image de l'ingrédient à la ligne
                rowPanel.add(nbIngredient);
                rowPanel.add(ingredientLabel);

                // Ajouter un JLabel "+" entre les ingrédients sauf pour le dernier
                if (i < ingredients.length - 1) {
                    JLabel plusLabel = new JLabel("+");
                    plusLabel.setPreferredSize(new Dimension(10, 50));
                    rowPanel.add(plusLabel);
                }
            } else {
                // Ajouter des espaces vides pour combler l'espace manquant
                JLabel emptyLabel = new JLabel();
                emptyLabel.setPreferredSize(new Dimension(75, 50));
                rowPanel.add(emptyLabel);

                if (i < maxIngredients - 1) {
                    JLabel plusLabel = new JLabel("+");
                    plusLabel.setPreferredSize(new Dimension(10, 50));
                    rowPanel.add(plusLabel);
                }
            }
        }

        // Ajouter un gap de 5 pixels entre chaque élément
        rowPanel.add(javax.swing.Box.createRigidArea(new Dimension(10, 10)));

        JLabel equalsLabel = new JLabel("=");
        equalsLabel.setPreferredSize(new Dimension(10, 50));
        rowPanel.add(equalsLabel);
        // Ajouter une image (placeholder ici)
        JLabel recetteLabel = new JLabel();

        recetteLabel.setPreferredSize(new Dimension(50, 50));
        recetteLabel.setHorizontalAlignment(SwingConstants.CENTER);

        rowPanel.add(recetteLabel);

        // Ajouter le nom du produit
        JLabel nameLabel = new JLabel(item.getRessource().getName());
        nameLabel.setPreferredSize(new Dimension(150, 50));
        rowPanel.add(nameLabel);

        // Ajouter le bouton "BUILD"
        ImageIcon imageRecette = new ImageIcon(item.getRessource().getImage());
        ImageIcon btnBuild = new ImageIcon("./img/boutons/BuildButton.png");
        recetteLabel.setIcon(imageRecette); // Remplacer par une image
        JButton buildButton = new JButton();
        buildButton.setIcon(btnBuild);
        buildButton.setBounds(50, 50, 90, 40);
        // Redimensionner le bouton "BUILD"
        buildButton.setPreferredSize(new Dimension(90, 40));
        resizeButtonImages(buildButton, btnBuild);
        rowPanel.add(buildButton);

        return rowPanel;
    }

    // Getter pour récupérer le JScrollPane configuré
    public JPanel getJPanel() {
        return mainPanel;
    }

    /**
     * Redimensionne l'image d'un bouton pour qu'elle corresponde à sa taille
     * actuelle.
     * 
     * @param button Le bouton à redimensionner
     * @param icon   L'icône à redimensionner
     */
    private static void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(buttonWidth, buttonHeight,
                java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }

    /**
     * Retourne le nombre de ressources différentes pour une recette.
     * 
     * @param recette La recette pour laquelle compter les ressources.
     * @return Le nombre de ressources différentes.
     */
    public static int getNombreDeRessourcesDifferentes(Recette recette) {
        return recette.getIngredients().length;
    }

    public static void updateInventory() {
        inventoryItems = Inventaire.getInv();
        rebuildUsinePanel();
    }

    private static void rebuildUsinePanel() {
        JPanel usinePanel = (JPanel) recettes.getViewport().getView();
        usinePanel.removeAll();
        for (Recette item : livres) {
            usinePanel.add(createRow(item));
        }
        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            GamePanel.gameState = GamePanel.playState;
        });
        usinePanel.add(exit);
        usinePanel.revalidate();
        usinePanel.repaint();
    }
}
