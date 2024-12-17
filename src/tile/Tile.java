package tile;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * La classe Tile représente un bloc dans le jeu.
 * 
 * <p>
 * Chaque bloc peut contenir une image associée, des informations sur les collisions
 * et une hitbox pour gérer les interactions physiques avec d'autres objets.
 * </p>
 * 
 * <p>
 * Les principales caractéristiques de cette classe incluent :
 * </p>
 * 
 * <ul>
 * <li><strong>image</strong> : l'image associée au bloc.</li>
 * <li><strong>collision</strong> : indique si ce bloc possède une hitbox.</li>
 * <li><strong>solidArea</strong> : une zone rectangulaire définissant la hitbox du bloc.</li>
 * </ul>
 * 
 * @author Ouahid Houat
 * @version 1.0
 */
public class Tile {

    /** 
     * L'image associée au bloc, qui sera affichée dans le jeu.
     * Par exemple, cela peut représenter un mur, du sol, de l'eau, etc.
     */
    public BufferedImage image;

    /** 
     * Indique si ce bloc bloque les mouvements des objets.
     * Si cette valeur est définie à "true", le bloc sera considéré comme un obstacle.
     */
    public boolean collision = false;

    /** 
     * La zone de collision solide du bloc.
     * Elle définit précisément l'espace où des interactions physiques peuvent se produire.
     */
    public Rectangle solidArea;

}
