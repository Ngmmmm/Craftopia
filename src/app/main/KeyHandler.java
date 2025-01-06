package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La classe KeyHandler implémente KeyListener pour gérer les événements de
 * clavier.
 *
 * <p>
 * Cette classe gère les pressions et les relâchements des touches fléchées
 * (haut, bas, gauche, droite).
 * Elle met à jour les indicateurs correspondants (leftPressed, rightPressed,
 * upPressed, downPressed)
 * pour suivre l'état des touches.
 * </p>
 *
 * @version 1.0
 * @author Ouahid Houat
 */
public class KeyHandler implements KeyListener {
    // Déclaration de variables booléennes pour savoir si les touches flèche gauche,
    // droite, haut ou bas sont actives
    GamePanel gp;

    /**
     * Constructeur pour initialiser le KeyHandler avec un panneau de jeu.
     * 
     * @param gp le panneau de jeu associé au KeyHandler.
     */
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean leftPressed, rightPressed, upPressed, downPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // Méthode obligatoire pour l'implémentation de la classe KeyListener, ne fait
        // rien
    }

    /**
     * Méthode qui vérifie les touches appuyées et met leurs valeurs à true.
     * 
     * @param e l'événement de touche pressée.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int toucheCode = e.getKeyCode();

        if (toucheCode == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (toucheCode == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (toucheCode == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (toucheCode == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }

    /**
     * Méthode qui vérifie les touches relâchées et met leurs valeurs à false.
     * 
     * @param e l'événement de touche relâchée.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int toucheCode = e.getKeyCode();

        if (toucheCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (toucheCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (toucheCode == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (toucheCode == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }
}
