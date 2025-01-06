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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

public class UiCraftAvatar extends Ui {
    JPanel mainPanel;
    private JScrollPane craftPanel;

    public UiCraftAvatar(GamePanel gamePanel) {
        super(gamePanel);
        createRecetteContent();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(craftPanel);
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

        ArrayList<Recette> recettes = new ArrayList<>();
        recettes.add(Recette.getRecette(Recette.Livre.BOULE_FEUILLE));
        recettes.add(Recette.getRecette(Recette.Livre.BLOC_PIERRE));

        for (Recette recette : recettes) {
            recetteContent.add(createLigneRecette(recette));
        }

        craftPanel = new JScrollPane(recetteContent);
        craftPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        craftPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel createLigneRecette(Recette recette) {
        JPanel craftRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        for (int i = 0; i < recette.getIngredients().length; i++) {
            Ressource ressource = recette.getIngredients()[i];

            // Quantité
            JLabel quantity = new JLabel(ressource.getQuantity() + "");

            // Image redimensionnée
            ImageIcon img = resizeIcon(new ImageIcon(ressource.getImage()), 30, 30);
            JLabel imgLabel = new JLabel(img);

            craftRow.add(quantity);
            craftRow.add(imgLabel);

            // Ajouter "+" ou "="
            if (i < recette.getIngredients().length - 1) {
                craftRow.add(new JLabel("+"));
            } else {
                craftRow.add(new JLabel("="));
            }
        }

        // Résultat de la recette
        ImageIcon resultat = resizeIcon(new ImageIcon(recette.getRessource().getImage()), 30, 30);
        JLabel quantity = new JLabel(recette.getRessource().getQuantity() + "");
        JLabel imgLabel = new JLabel(resultat);

        craftRow.add(quantity);
        craftRow.add(imgLabel);

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

            if (recette.getTime() > 0) {
                JProgressBar progressBar = new JProgressBar(0, recette.getTime());
                // Définir les bounds pour positionner la progressBar
                int progressBarWidth = 400;
                int progressBarHeight = 30;
                int progressBarX = (gamePanel.getWidth() - progressBarWidth) / 2;
                int progressBarY = gamePanel.ui.menuPanel.menuBar.getHeight() + 10;
                progressBar.setBounds(progressBarX, progressBarY, progressBarWidth, progressBarHeight);
                progressBar.setValue(0);
                progressBar.setStringPainted(true);
                gamePanel.add(progressBar);
                gamePanel.repaint();

                new Thread(() -> {
                    int previousGameState = gameState;
                    gamePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
                        public void componentResized(java.awt.event.ComponentEvent evt) {
                            int progressBarX = (gamePanel.getWidth() - progressBarWidth) / 2;
                            int progressBarY = gamePanel.ui.menuPanel.menuBar.getHeight() + 10;
                            progressBar.setBounds(progressBarX, progressBarY, progressBarWidth, progressBarHeight);
                        }
                    });
                    try {
                        for (int i = 0; i <= recette.getTime(); i++) {
                            if (gameState != previousGameState) {
                                gamePanel.remove(progressBar);
                                gamePanel.repaint();
                                return;
                            }
                            progressBar.setValue(i);
                            Thread.sleep(1000); // Sleep for 1 second
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (gameState == previousGameState) {
                            gamePanel.remove(progressBar);
                            if(!gamePanel.avatar.createRessource(recette)){
                                JOptionPane.showMessageDialog(gamePanel, "Impossible de créer la ressource", "Erreur", JOptionPane.ERROR_MESSAGE);     
                                gamePanel.avatar.resetDeplacement(); // Reset avatar movement
                            }
                            gamePanel.avatar.resetDeplacement();
                        }
                    }
                }).start();
            } else {
                if(!gamePanel.avatar.createRessource(recette)){
                    JOptionPane.showMessageDialog(gamePanel, "Impossible de créer la ressource", "Erreur", JOptionPane.ERROR_MESSAGE);     
                    gamePanel.avatar.resetDeplacement(); // Reset avatar movement
                }
                gamePanel.avatar.resetDeplacement(); // Reset avatar movement
            }
        });

        craftRow.add(recetteName);
        craftRow.add(build);

        return craftRow;
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

    public void updateInventory() {
        rebuildCraftPanel();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void rebuildCraftPanel() {
        JPanel rePanel = (JPanel) craftPanel.getViewport().getView();
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
