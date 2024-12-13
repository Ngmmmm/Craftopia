package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Ui {

    GamePanel gp;
    Graphics2D g2;
    int commandeNum = 0;
    Icon startIcon = new ImageIcon("./img/PlayButton.png");
    Icon exitIcon = new ImageIcon("./img/QuitButton.png");
    JButton start = new JButton(startIcon);
    JButton exit = new JButton(exitIcon);

    public Ui(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gameState == gp.TITLE_STATE) {
            drawTitleScreen(g2);
        } else {
            g2.setFont(new Font("Arial", Font.PLAIN, 40));
            g2.setColor(Color.WHITE);
            g2.drawString("Oui", 330, 200);

        }

    }

    public boolean initButton() {
        start.setBounds(gp.getWidth()/3, gp.getHeight()/2, 160, 65);
        exit.setBounds(gp.getWidth()/3, gp.getHeight()/2 + 100, 160, 65);
        start.addActionListener(l -> {
            gp.gameState = gp.playState;
            gp.remove(start);
        });
        exit.addActionListener(l -> {
            System.exit(0);
            gp.remove(exit);
        });
        start.setFocusable(false);
        exit.setFocusable(false);
        gp.add(start);
        gp.add(exit);
        gp.setLayout(null);
        gp.setVisible(true);
        return true;
    }

    public void resizeButton(double scaleX, double scaleY){
        gp.remove(start);
        gp.remove(exit);
        int newButtonX = (int) (300 * scaleX);
        int newButtonY = (int) (250 * scaleY);
        int newButtonWidth = (int) (160 * scaleX);
        int newButtonHeight = (int) (65 * scaleY);
        start.setBounds(newButtonX, newButtonY, newButtonWidth, newButtonHeight);
        exit.setBounds(newButtonX, newButtonY + 100, newButtonWidth, newButtonHeight);
        gp.add(start);
        gp.add(exit);
    }

    public void drawTitleScreen(Graphics2D g2) {
        this.g2 = g2;
        g2.setColor(Color.GREEN);
        g2.fillRect(0, 0, gp.LARGEUR_ECRAN, gp.HAUTEUR_ECRAN);
        g2.setFont(new Font("Arial", Font.BOLD, 80));
        String text = "CRAFTOPIA";
        Point pos = new Point(160, 200);
        g2.setColor(Color.WHITE);
        g2.drawString(text, pos.x, pos.y);

    }
}
