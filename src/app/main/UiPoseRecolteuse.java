package main;

import item.bag.Inventaire;
import item.machines.Recolteuse;
import item.ressources.Ressource;
import item.sprites.SuperObjet;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Point;

class UiPoseRecolteuse extends Ui {
    JPanel mainPanel;
    JScrollPane scrollPane;
    Point pos;
    public static Recolteuse act;
    public ArrayList<Ressource> closeRessources = new ArrayList<Ressource>();

    public UiPoseRecolteuse(GamePanel gamePanel) {
        super(gamePanel);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement
        // Panneau principal pour le JScrollPane
        JPanel posRecolteusePanel = new JPanel();
        posRecolteusePanel.setLayout(new BoxLayout(posRecolteusePanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        if (closeRessources != null && act != null) {
            for (int i = 0; i < closeRessources.size(); i++) {
                posRecolteusePanel.add(createRow(closeRessources.get(i)));
            }

        }

        // Configure le JScrollPane
        scrollPane = new JScrollPane(posRecolteusePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Taille du JScrollPane

        mainPanel.add(scrollPane);
        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT); // Centre le bouton horizontalement
        mainPanel.add(exit);
        gamePanel.layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        mainPanel.setVisible(false);
    }

    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }

    public JPanel createRow(Ressource r) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        JLabel ressourceName = new JLabel(r.getName() + "  ");
        ImageIcon icon = new ImageIcon(r.getImage());
        JLabel resourceImg = new JLabel(resizeIcon(icon, 30, 30));

        // Bouton addButton
        JButton addButton = new JButton("Choose");
        addButton.setPreferredSize(new Dimension(80, 40));
        closeRessources = gamePanel.avatar.isPossibleToPose();
        
            addButton.addActionListener(l -> {
                if(whatRessourceClose()){
                SuperObjet nouveau = SuperObjet.getSuperObjet(r.getName());
                act.setRessource((Ressource) nouveau);
                act.pos = pos;
                GamePanel.obj.add(act);
                Inventaire.removeFromAvatarInv(act, 1);
                gamePanel.avatar.setPos(new Point((int)pos.x,(int)pos.getY() + gamePanel.TAILLE_BLOC));
                Ui.gameState = Ui.playState;
                this.setVisible(false);
                gamePanel.layeredPane.add(act.uiRecolteuse.getMainPanel(),JLayeredPane.PALETTE_LAYER);
                }

            });
        
        // Ajouter les composants au sous-panel
        row.add(addButton);
        row.add(ressourceName);
        row.add(resourceImg);
        row.add(addButton);
        // Ajouter le sous-panel au panel principal
        return row;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        if (icon == null)
            return null;
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    public boolean whatRessourceClose() {
        System.out.println("1");
        switch (gamePanel.avatar.getDirection()) {
            case "up" -> {
                if (gamePanel.avatar.checkUp()) {
                    System.out.println("2");
                    Point verif = new Point((int) gamePanel.avatar.getPosition().getX() / gamePanel.TAILLE_BLOC,
                            (int) (gamePanel.avatar.getPosition().getY() / gamePanel.TAILLE_BLOC));
                    if (gamePanel.tileM.getTile(verif.x*gamePanel.TAILLE_BLOC, verif.y*gamePanel.TAILLE_BLOC).isGrass) {
                        pos = new Point(verif.x * gamePanel.TAILLE_BLOC, (verif.y) * gamePanel.TAILLE_BLOC);
                        System.out.println(true);
                        return true;

                    }
                    return false;

                }
                return false;
            }
            case "down" -> {
                if (gamePanel.avatar.checkdown()) {
                    Point verif = new Point((int) gamePanel.avatar.getPosition().getX() / gamePanel.TAILLE_BLOC,
                            (int) (gamePanel.avatar.getPosition().getY() / gamePanel.TAILLE_BLOC) + 1);
                    if (gamePanel.tileM.getTile(verif.x*gamePanel.TAILLE_BLOC, verif.y*gamePanel.TAILLE_BLOC).isGrass) {
                        pos = new Point(verif.x * gamePanel.TAILLE_BLOC, (verif.y) * gamePanel.TAILLE_BLOC);
                        return true;
                       
                    }
                    return false;
                }
                return false;
            }
            case "right" -> {
                if (gamePanel.avatar.checkRight()) {
                    Point verif = new Point((int) gamePanel.avatar.getPosition().getX() / gamePanel.TAILLE_BLOC + 1,
                            (int) (gamePanel.avatar.getPosition().getY() / gamePanel.TAILLE_BLOC));
                    if (gamePanel.tileM.getTile(verif.x*gamePanel.TAILLE_BLOC, verif.y*gamePanel.TAILLE_BLOC).isGrass) {
                        pos = new Point(verif.x * gamePanel.TAILLE_BLOC, (verif.y) * gamePanel.TAILLE_BLOC);
                        return true;
                        
                    }
                    return false;
                }
                return false;
            }
            case "left" -> {
                if (gamePanel.avatar.checkLeft()) {
                    Point verif = new Point((int) gamePanel.avatar.getPosition().getX() / gamePanel.TAILLE_BLOC - 1,
                            (int) (gamePanel.avatar.getPosition().getY() / gamePanel.TAILLE_BLOC));
                    if (gamePanel.tileM.getTile(verif.x*gamePanel.TAILLE_BLOC, verif.y*gamePanel.TAILLE_BLOC).isGrass) {
                        pos = new Point(verif.x * gamePanel.TAILLE_BLOC, (verif.y) * gamePanel.TAILLE_BLOC);
                        return true;
                        

                    }
                    return false;
                }
                return false;
            }
            default -> {
                return false;
            }
        }
    }

    public void rebuildRecolteusePanel() {
        JPanel posePanel = (JPanel) scrollPane.getViewport().getView();
        posePanel.removeAll();
        closeRessources = gamePanel.avatar.isPossibleToPose();
        for (int i = 0; i < closeRessources.size(); i++) {
            posePanel.add(createRow(closeRessources.get(i)));
        }
        posePanel.revalidate();
        posePanel.repaint();
    }

}