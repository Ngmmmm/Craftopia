package item.sprites;

import item.bag.Argent; // Permet de dessiner l'avatar avec une qualité graphique
import item.bag.Inventaire; // Définit l'aire solide pour la détection des collisions
import java.awt.Graphics2D; // Optimise le rendu graphique
import java.awt.Rectangle; // Stocke les images de l'avatar
import java.awt.RenderingHints; // Gère les fichiers d'images
import java.awt.image.BufferedImage; // Gère les exceptions liées aux fichiers
import java.io.File; // Permet de lire les fichiers d'images
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel; // Importation du panneau principal du jeu
import main.KeyHandler; // Gère les entrées clavier du joueur

/**
 * La classe Avatar représente le personnage contrôlé par le joueur dans le jeu.
 * 
 * <p>
 * Elle hérite de la classe {@link Sprite} et contient des informations et
 * fonctionnalités spécifiques à l'avatar, telles que sa position, sa vitesse,
 * la gestion des collisions, les images pour ses animations, ainsi que les
 * interactions avec les entrées clavier du joueur.
 * </p>
 * 
 * <p>
 * Cette classe est responsable du mouvement du personnage, de la gestion des
 * directions, du changement d'animations en fonction des actions, et de
 * l'affichage
 * correct de l'avatar sur l'écran.
 * </p>
 * 
 * @author Ouahid Houat
 * @version 1.0
 */
public final class Avatar extends Sprite {
    GamePanel gp; // Fenêtre du jeu
    KeyHandler keyH; // Entrées clavier du joueur
    public static int argent = Argent.getMonnaiePlayer(); // Argent du joueur
    public final int screenX; // Position de l'avatar au centre de l'écran sur l'axe X
    public final int screenY; // Position de l'avatar au centre de l'écran sur l'axe Y
    public final Inventaire inv;
    public int lastRessourceIndex = 999;

    /**
     * Constructeur de la classe Avatar.
     * 
     * @param gp   le panneau de jeu principal, utilisé pour les dimensions de
     *             l'écran.
     * @param keyH le gestionnaire des entrées clavier, permettant de détecter les
     *             actions du joueur.
     */
    public Avatar(GamePanel gp, KeyHandler keyH) {
        this.gp = gp; // Initialisation du panneau de jeu
        this.keyH = keyH; // Initialisation du gestionnaire des entrées clavier
        this.screenX = (this.gp.LARGEUR_ECRAN / 2) - (gp.TAILLE_BLOC / 2); // Calcul pour centrer l'avatar sur l'écran
                                                                           // (X)
        this.screenY = (this.gp.HAUTEUR_ECRAN / 2) - (gp.TAILLE_BLOC / 2); // Calcul pour centrer l'avatar sur l'écran
                                                                           // (Y)
        this.inv = new Inventaire();

        solidArea = new Rectangle(8, 16, 32, 32); // Définit la zone de collision de l'avatar
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setValeurParDefaut(); // Définit les valeurs initiales par défaut
        getAvatarImage(); // Charge les images de l'avatar
    }

    /**
     * Définit les valeurs par défaut de l'avatar, incluant sa position, sa vitesse,
     * et sa direction initiale.
     */
    public void setValeurParDefaut() {
        mapPoint.x = gp.TAILLE_BLOC * 29; // Position initiale sur l'axe X
        mapPoint.y = gp.TAILLE_BLOC * 26; // Position initiale sur l'axe Y
        this.speed = 4; // Vitesse de déplacement de l'avatar
        direction = "down"; // Direction initiale
    }

    /**
     * Charge les images nécessaires à l'animation de l'avatar, incluant ses
     * mouvements
     * dans les quatre directions (haut, bas, gauche, droite) et ses positions
     * immobiles.
     */
    public void getAvatarImage() {
        try {
            // Chargement des fichiers d'images pour chaque direction et animation
            up1 = ImageIO.read(new File("img/player/avatarUp1.png"));
            up2 = ImageIO.read(new File("img/player/avatarUp2.png"));
            up3 = ImageIO.read(new File("img/player/noMoveUp.png"));
            down1 = ImageIO.read(new File("img/player/avatarDown1.png"));
            down2 = ImageIO.read(new File("img/player/avatarDown2.png"));
            down3 = ImageIO.read(new File("img/player/noMoveDown.png"));
            right1 = ImageIO.read(new File("img/player/avatarRight1.png"));
            right2 = ImageIO.read(new File("img/player/avatarRight2.png"));
            right3 = ImageIO.read(new File("img/player/noMoveRight.png"));
            left1 = ImageIO.read(new File("img/player/avatarLeft1.png"));
            left2 = ImageIO.read(new File("img/player/avatarLeft2.png"));
            left3 = ImageIO.read(new File("img/player/noMoveLeft.png"));
        } catch (IOException e) {
            // En cas d'erreur, affiche un message
            System.out.println("Image non chargée: " + e);
        }
    }

    /**
     * Met à jour l'état de l'avatar, incluant son mouvement, son animation,
     * et la gestion des collisions.
     */
    public void update(int gameState) {
        boolean immobile = false; // Variable pour gérer l'immobilité

        // Vérifie les entrées clavier pour détecter un mouvement
        if ((keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
                && gameState == gp.playState) {

            // Détermine la direction en fonction de la touche pressée
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // Gestion de l'animation (cycle entre 3 images)
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum % 3) + 1; // Passe à l'image suivante
                spriteCounter = 0; // Réinitialise le compteur
            }
        } else {
            // Si aucune touche n'est pressée, l'avatar reste immobile
            // direction = "down"; // Direction par défaut
            // spriteNum = 2; // Image par défaut
            immobile = true;
        }

        // Vérifie les collisions avec les blocs
        collisionOn = false;
        gp.collision.checkTile(this);

        // Vérifie les collisions avec les objets sur la map
        int ressourceIndex = gp.collision.collisionItem(this, true);
        if (ressourceIndex != 999) {
            pickUpItem(ressourceIndex);
        } else {
            gp.itemManager.resetButton();
            lastRessourceIndex = 999;
        }
        // Si aucune collision et que l'avatar est mobile, met à jour la position
        if (!collisionOn && !immobile) {
            switch (direction) {
                case "up" -> this.mapPoint.y -= this.speed;
                case "down" -> this.mapPoint.y += this.speed;
                case "left" -> this.mapPoint.x -= this.speed;
                case "right" -> this.mapPoint.x += this.speed;
                default -> {
                }
            }
        }
    }

    // TODO: la méthode qui permet de ramasser un item
    public void pickUpItem(int index) {
        if (index != lastRessourceIndex) {
            lastRessourceIndex = index;
            gp.itemManager.initButton(gp.LARGEUR_ECRAN / 2, gp.HAUTEUR_ECRAN / 2, GamePanel.obj.get(index), index);
        }
    }

    /**
     * Dessine l'avatar sur le panneau de jeu.
     * 
     * <p>
     * Cette méthode sélectionne l'image correspondant à la direction actuelle et
     * à l'étape d'animation, puis l'affiche à la position de l'avatar sur l'écran.
     * </p>
     * 
     * @param g2 l'objet Graphics2D utilisé pour dessiner l'avatar.
     */
    public void draw(Graphics2D g2, double scaleX, double scaleY) {
        BufferedImage image = null; // Image à afficher

        // Sélectionne l'image en fonction de la direction et de l'animation
        switch (direction) {
            case "up" -> image = switch (spriteNum) {
                case 1 -> up1;
                case 2 -> up3;
                case 3 -> up2;
                default -> null;
            };
            case "down" -> image = switch (spriteNum) {
                case 1 -> down1;
                case 2 -> down3;
                case 3 -> down2;
                default -> null;
            };
            case "right" -> image = switch (spriteNum) {
                case 1 -> right1;
                case 2 -> right3;
                case 3 -> right2;
                default -> null;
            };
            case "left" -> image = switch (spriteNum) {
                case 1 -> left1;
                case 2 -> left3;
                case 3 -> left2;
                default -> null;
            };
            default -> {
            }
        }

        // Optimise la qualité du rendu graphique
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessine l'image sélectionnée
        g2.drawImage(image, (int) (screenX * scaleX), (int) (screenY * scaleY), (int) (gp.TAILLE_BLOC * scaleX),
                (int) (gp.TAILLE_BLOC * scaleY), null);
    }
}
