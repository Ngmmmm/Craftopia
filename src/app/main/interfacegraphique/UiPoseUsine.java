package main.interfacegraphique;

import item.bag.Inventaire;
import item.machines.Usine;
import item.recettes.Recette;
import main.GamePanel;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UiPoseUsine extends Ui {

    JPanel mainPanel;
    JScrollPane scrollPane;
    Point pos;
    public static Usine act;
    public boolean rebuild = false;

    public UiPoseUsine(GamePanel gamePanel) {
        super(gamePanel);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement
        // Panneau principal pour le JScrollPane
        JPanel posUsinePanel = new JPanel();
        posUsinePanel.setLayout(new BoxLayout(posUsinePanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        for (int i = 0; i < Recette.getRecettes().size(); i++) {
            posUsinePanel.add(createRow(Recette.getRecettes().get(i)));
        }

        // Configure le JScrollPane
        scrollPane = new JScrollPane(posUsinePanel);
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

    public JPanel createRow(Recette recette) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        JLabel recetteName = new JLabel(recette.getName() + "  ");
        ImageIcon icon = new ImageIcon(recette.getRessource().getImage());
        JLabel recetteImg = new JLabel(resizeIcon(icon, 30, 30));

        if (gamePanel.avatar.checkNine() && act != null && !rebuild) {
            JButton poseUsine = new JButton("use");
            poseUsine.addActionListener(l -> {
                if (build()) {

                    System.out.println(act.getName());
                    System.out.println(pos);
                    gamePanel.avatar.setPos(new Point(pos.x, (pos.y) + GamePanel.TAILLE_BLOC));
                    GamePanel.obj.add(act);
                    GamePanel.obj.get(GamePanel.obj.size() - 1).pos = pos;
                    ((Usine) GamePanel.obj.get(GamePanel.obj.size() - 1)).setRecetteAct(recette);
                    ((Usine) GamePanel.obj.get(GamePanel.obj.size() - 1)).uiUsine.updateInventory();
                    Inventaire.removeFromAvatarInv(act, 1);
                    gamePanel.layeredPane.add(act.uiUsine.getMainPanel(), JLayeredPane.PALETTE_LAYER);
                    rebuild = true;
                    Ui.gameState = Ui.playState;
                    this.setVisible(false);
                }
            });
            row.add(poseUsine);

        } else if (rebuild) {
            JButton rebuildButton = new JButton("rebuild");
            rebuildButton.addActionListener(k -> {
                act.setRecetteAct(recette);
                act.uiUsine.updateInventory();
                Ui.gameState = Ui.playState;
                this.setVisible(false);
            });
            row.add(rebuildButton);
        }
        row.add(recetteName);
        row.add(recetteImg);
        return row;
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        if (icon == null) {
            return null;
        }
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    public boolean build() {
        switch (gamePanel.avatar.getDirection()) {
            case "up" -> {
            if (gamePanel.avatar.checkNine()) {
                Point verif = new Point((int) gamePanel.avatar.getPosition().getX() / GamePanel.TAILLE_BLOC, (int) (gamePanel.avatar.getPosition().getY() / GamePanel.TAILLE_BLOC));
                if (gamePanel.tileM.getTile(verif.x * GamePanel.TAILLE_BLOC, verif.y * GamePanel.TAILLE_BLOC).isGrass && !verif.equals(gamePanel.avatar.getPosition())) {
                pos = new Point(verif.x * GamePanel.TAILLE_BLOC, (verif.y) * GamePanel.TAILLE_BLOC);
                return true;
                }
            }
            return false;
            }
            case "down" -> {
            if (gamePanel.avatar.checkNine()) {
                Point verif = new Point((int) gamePanel.avatar.getPosition().getX() / GamePanel.TAILLE_BLOC, (int) (gamePanel.avatar.getPosition().getY() / GamePanel.TAILLE_BLOC) + 1);
                if (gamePanel.tileM.getTile(verif.x * GamePanel.TAILLE_BLOC, verif.y * GamePanel.TAILLE_BLOC).isGrass && !verif.equals(gamePanel.avatar.getPosition())) {
                pos = new Point(verif.x * GamePanel.TAILLE_BLOC, (verif.y) * GamePanel.TAILLE_BLOC);
                return true;
                }
            }
            return false;
            }
            case "right" -> {
            if (gamePanel.avatar.checkNine()) {
                Point verif = new Point((int) gamePanel.avatar.getPosition().getX() / GamePanel.TAILLE_BLOC + 1, (int) (gamePanel.avatar.getPosition().getY() / GamePanel.TAILLE_BLOC));
                if (gamePanel.tileM.getTile(verif.x * GamePanel.TAILLE_BLOC, verif.y * GamePanel.TAILLE_BLOC).isGrass && !verif.equals(gamePanel.avatar.getPosition())) {
                pos = new Point(verif.x * GamePanel.TAILLE_BLOC, (verif.y) * GamePanel.TAILLE_BLOC);
                return true;
                }
            }
            return false;
            }
            case "left" -> {
            if (gamePanel.avatar.checkNine()) {
                Point verif = new Point((int) gamePanel.avatar.getPosition().getX() / GamePanel.TAILLE_BLOC - 1, (int) (gamePanel.avatar.getPosition().getY() / GamePanel.TAILLE_BLOC));
                if (gamePanel.tileM.getTile(verif.x * GamePanel.TAILLE_BLOC, verif.y * GamePanel.TAILLE_BLOC).isGrass && !verif.equals(gamePanel.avatar.getPosition())) {
                pos = new Point(verif.x * GamePanel.TAILLE_BLOC, (verif.y) * GamePanel.TAILLE_BLOC);
                return true;
                }
            }
            return false;
            }
            default -> {
            }
        }
        return false;

    }

    public void rebuildPoseUsine() {
        JPanel posePanel = (JPanel) scrollPane.getViewport().getView();
        posePanel.removeAll();
        for (int i = 0; i < Recette.getRecettes().size(); i++) {
            posePanel.add(createRow(Recette.getRecettes().get(i)));
        }
        posePanel.revalidate();
        posePanel.repaint();

    }




    public void repositionAvatar(){
        // switch case 
    }

}
