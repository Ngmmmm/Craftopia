package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ui {

    GamePanel gp;
    Graphics2D g2;
    int commandeNum = 0;

    public Ui(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gameState == gp.TITLE_STATE) {
            drawTitleScreen();
        } else {
            g2.setFont(new Font("Arial", Font.PLAIN, 40));
            g2.setColor(Color.WHITE);
            g2.drawString("Oui", 330, 200);

        }

    }

    public void drawTitleScreen() {

        g2.setColor(Color.BLUE);
        g2.fillRect(0, 0, gp.LARGEUR_ECRAN, gp.HAUTEUR_ECRAN);
        g2.setFont(new Font("Arial", Font.BOLD, 80));
        String text = "CRAFTOPIA";
        Point pos = new Point(160, 200);

        g2.setColor(Color.WHITE);
        g2.drawString(text, pos.x, pos.y);
    

        g2.setFont(new Font("Arial", Font.BOLD, 40));
        text = "New Game";
        pos.x = 200;
        pos.y = 300;
        g2.drawString(text, pos.x, pos.y);
        if (commandeNum == 0) {
            g2.drawString(">", pos.x - gp.TAILLE_BLOC, pos.y);
        }

        g2.setFont(new Font("Arial", Font.BOLD, 40));
        text = "Continue";
        pos.x = 200;
        pos.y = 350;
        g2.drawString(text, pos.x, pos.y);
        if (commandeNum == 1) {
            g2.drawString(">", pos.x - gp.TAILLE_BLOC, pos.y);
        }

        g2.setFont(new Font("Arial", Font.BOLD, 40));
        text = "Exit";
        pos.x = 200;
        pos.y = 400;
        g2.drawString(text, pos.x, pos.y);
        if (commandeNum == 2) {
            g2.drawString(">", pos.x - gp.TAILLE_BLOC, pos.y);
        }
    }
}
