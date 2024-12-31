package tile;
/**
 * La classe Point représente un point dans un espace 2D avec des coordonnées x et y.
 * 
 * <p>
 * Elle permet de manipuler les coordonnées d'un point, en offrant des
 * accesseurs (getters) et des mutateurs (setters) pour chacune des deux dimensions.
 * </p>
 * 
 * <p>
 * Les principales méthodes de cette classe incluent :
 * </p>
 * 
 * <ul>
 * <li><strong>getX()</strong> : retourne la coordonnée x du point.</li>
 * <li><strong>setX(int x)</strong> : modifie la coordonnée x du point.</li>
 * <li><strong>getY()</strong> : retourne la coordonnée y du point.</li>
 * <li><strong>setY(int y)</strong> : modifie la coordonnée y du point.</li>
 * </ul>
 * 
 * <p>
 * Cette classe est utile dans des contextes où des objets doivent être manipulés dans un espace cartésien,
 * comme dans des jeux ou des applications graphiques.
 * </p>
 * 
 * @author Ouahid Houat
 * @version 1.0
 */
public class Point {
    private int x, y; // Coordonnées du point (x pour l'axe horizontal, y pour l'axe vertical)

    /**
     * Retourne la coordonnée x du point.
     * 
     * @return La valeur actuelle de la coordonnée x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Modifie la coordonnée x du point.
     * 
     * @param x La nouvelle valeur à affecter à la coordonnée x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la coordonnée y du point.
     * 
     * @return La valeur actuelle de la coordonnée y.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Modifie la coordonnée y du point.
     * 
     * @param y La nouvelle valeur à affecter à la coordonnée y.
     */
    public void setY(int y) {
        this.y = y;
    }
}
