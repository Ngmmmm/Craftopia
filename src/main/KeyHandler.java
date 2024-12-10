package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// KeyHandler hérite de KeyListener
public class KeyHandler implements KeyListener {
    // Déclaration de 2 variables booléenne pour savoir si les touches flèche droit
    // ou flèche gauche sont actives
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean leftPressed, rightPressed, upPressed, downPressed;

    @Override
    public void keyTyped(KeyEvent e) { // Méthode obligatoire pour l'implémentation de la classe

    }

    @Override
    public void keyPressed(KeyEvent e) {// Méthode qui check les touches appuyées et mets leurs valeur à true
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

    @Override
    public void keyReleased(KeyEvent e) { // Méthode qui check les touches non appuyées et mets leurs valeur à false
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
