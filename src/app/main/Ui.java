package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * La classe Ui gère la génération des boutons du Menu et leur affichage
 * 
 * <p>
 * Cette classe utilise les propriétés des JButton de Swing
 * </p>
 * 
 * 
 * @author Noa Gregoire--Mellier
 * @version 1.0
 */
public class Ui {

    GamePanel gp; // Instance du panneau de jeu
    Graphics2D g2; // 
    Icon startIcon = new ImageIcon("./img/PlayButton.png"); // Icon correspondante au bouton start
    Icon exitIcon = new ImageIcon("./img/QuitButton.png"); // Icon correspondante au bouton exit
    JButton start = new JButton(startIcon); // Instance correspondante au bouton start
    JButton exit = new JButton(exitIcon); // Instance correspondante au bouton exit

    /**
     * Constructeur de la classe Ui.
     * 
     * @param gp le panneau de jeu principal, utilisé pour afficher le menu et le jeu
     */
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
        Icon startIcon = new ImageIcon("./img/PlayButton.png");
        Icon exitIcon = new ImageIcon("./img/QuitButton.png");
        start = new JButton(startIcon);
        exit = new JButton(exitIcon);
        start.setBounds(300, 250, 160, 65);
        exit.setBounds(300, 350, 160, 65);
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
    /**
     *Déplace les boutons a la position demandée en fonction de la dimension du Panel de jeu
     * @param scaleX Double représentant le facteur de modification X par rapport au précédant panel
     * @param scaleY Double représentant le facteur de modification Y par rapport au précédant panel
     */
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

    /**
     *Dessine la base du Menu tel que son fond et le titre du jeu
     * @param g2 
     */
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
