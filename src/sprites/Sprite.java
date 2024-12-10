package sprites;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * La classe Sprite représente un personnage ou un objet graphique dans le jeu.
 * 
 * <p>
 * Cette classe est utilisée pour gérer les déplacements, les animations et les
 * collisions
 * des sprites. Elle inclut des informations comme la position du sprite, sa
 * vitesse,
 * sa direction actuelle, ainsi que les images nécessaires à ses animations.
 * </p>
 * 
 * <p>
 * Les sprites peuvent interagir avec l'environnement grâce à une zone de
 * collision définie
 * et peuvent être animés avec un ensemble d'images représentant les différentes
 * étapes de leur mouvement.
 * </p>
 * 
 * @author Ouahid Houat
 * @version 1.0
 */
public class Sprite {
    public Point mapPoint = new Point(); // position du sprite dans la map
    public int speed; // vitesse de déplacement du sprite

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3; // Les
                                                                                                          // différentes
                                                                                                          // images d'un
                                                                                                          // sprite pour
                                                                                                          // l'animer
    public String direction; // direction vers laquelle le sprite pointe (utile uniquement pour l'animation)

    public int spriteCounter = 0; // compteur qui permet de synchroniser les changements d'images pour l'animation
    public int spriteNum = 1; // Initialisé à 1 mais change pour indique quelle image est utilisée pour
                              // l'animation

    public Rectangle solidArea; // La hitbox est représenté par un rectangle
    public boolean collisionOn = false; // Indique si le sprite possède une Hitbox
}
