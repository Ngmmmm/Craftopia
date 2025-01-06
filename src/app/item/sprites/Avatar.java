package item.sprites;

import item.bag.Argent; // Permet de dessiner l'avatar avec une qualité graphique
import item.bag.Inventaire; // Définit l'aire solide pour la détection des collisions
import item.recettes.Recette;
import item.ressources.Ressource;
import java.awt.Graphics2D;
import java.awt.Point; // Optimise le rendu graphique
import java.awt.Rectangle;
import java.awt.RenderingHints; // Stocke les images de l'avatar
import java.awt.image.BufferedImage; // Gère les fichiers d'images
import java.io.File; // Gère les exceptions liées aux fichiers
import java.io.IOException; // Permet de lire les fichiers d'images
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.interfacegraphique.Ui;
import tile.TileManager; // Gère les entrées clavier du joueur

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
 * l'affichage correct de l'avatar sur l'écran.
 * </p>
 *
 * @author Ouahid Houat
 * @version 5.2
 */
public final class Avatar extends Sprite {

    GamePanel gp; // Fenêtre du jeu
    KeyHandler keyH; // Entrées clavier du joueur
    public static int argent = Argent.getMonnaiePlayer(); // Argent du joueur
    public final int screenX; // Position de l'avatar au centre de l'écran sur l'axe X
    public final int screenY; // Position de l'avatar au centre de l'écran sur l'axe Y
    public static final Inventaire inv = new Inventaire();
    public boolean immobile; // Vérifie si l'avatar est immobile
    public int lastRessourceIndex = 999;

    /**
     * Constructeur de la classe Avatar.
     *
     * @param gp   le panneau de jeu principal, utilisé pour les dimensions de
     *             l'écran.
     * @param keyH le gestionnaire des entrées clavier, permettant de détecter
     *             les actions du joueur.
     */
    public Avatar(GamePanel gp, KeyHandler keyH) {
        this.gp = gp; // Initialisation du panneau de jeu
        this.keyH = keyH; // Initialisation du gestionnaire des entrées clavier
        this.screenX = (this.gp.LARGEUR_ECRAN / 2) - (GamePanel.TAILLE_BLOC / 2); // Calcul pour centrer l'avatar sur
                                                                                  // l'écran
        // (X)
        this.screenY = (this.gp.HAUTEUR_ECRAN / 2) - (GamePanel.TAILLE_BLOC / 2); // Calcul pour centrer l'avatar sur
                                                                                  // l'écran
        // (Y)

        solidArea = new Rectangle(8, 16, 32, 32); // Définit la zone de collision de l'avatar
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setValeurParDefaut(); // Définit les valeurs initiales par défaut
        getAvatarImage(); // Charge les images de l'avatar
    }

    /**
     * Définit les valeurs par défaut de l'avatar, incluant sa position, sa
     * vitesse, et sa direction initiale.
     */
    public void setValeurParDefaut() {
        setSpawnPoint(); // Position initiale

        this.speed = 4; // Vitesse de déplacement de l'avatar
        direction = "down"; // Direction initiale
    }

    public void setSpawnPoint() {
        mapPoint.x = TileManager.spawnPoint.x; // Position initiale sur l'axe X
        mapPoint.y = TileManager.spawnPoint.y; // Position initiale sur l'axe Y
    }

    public void setPos(Point p) {
        mapPoint = p;
    }

    /**
     * Charge les images nécessaires à l'animation de l'avatar, incluant ses
     * mouvements dans les quatre directions (haut, bas, gauche, droite) et ses
     * positions immobiles.
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

    public static int getArgent() {
        return argent;
    }

    public static Inventaire getInv() {
        return inv;
    }

    /**
     * Met à jour l'état de l'avatar, incluant son mouvement, son animation, et
     * la gestion des collisions.
     */
    public void update(int gameState) {
        immobile = false; // Variable pour gérer l'immobilité

        // Vérifie les entrées clavier pour détecter un mouvement
        if ((keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
                && gameState == Ui.playState) {

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
            gp.ui.itemManager.resetButton();
            lastRessourceIndex = 999;
        }
        // Si aucune collision et que l'avatar est mobile, met à jour la position
        if (!collisionOn && !immobile) {
            switch (direction) {
                case "up" ->
                    this.mapPoint.y -= this.speed;
                case "down" ->
                    this.mapPoint.y += this.speed;
                case "left" ->
                    this.mapPoint.x -= this.speed;
                case "right" ->
                    this.mapPoint.x += this.speed;
                default -> {
                }
            }
        }
    }

    public void pickUpItem(int index) {
        if (index != lastRessourceIndex) {
            lastRessourceIndex = index;
            gp.ui.itemManager.initButton(gp.LARGEUR_ECRAN / 2, gp.HAUTEUR_ECRAN / 2, GamePanel.obj.get(index), index);
        }
    }

    /**
     * Dessine l'avatar sur le panneau de jeu.
     *
     * <p>
     * Cette méthode sélectionne l'image correspondant à la direction actuelle
     * et à l'étape d'animation, puis l'affiche à la position de l'avatar sur
     * l'écran.
     * </p>
     *
     * @param g2 l'objet Graphics2D utilisé pour dessiner l'avatar.
     */
    public void draw(Graphics2D g2, double scaleX, double scaleY) {
        BufferedImage image = null; // Image à afficher

        // Sélectionne l'image en fonction de la direction et de l'animation
        switch (direction) {
            case "up" ->
                image = switch (spriteNum) {
                    case 1 ->
                        up1;
                    case 2 ->
                        up3;
                    case 3 ->
                        up2;
                    default ->
                        null;
                };
            case "down" ->
                image = switch (spriteNum) {
                    case 1 ->
                        down1;
                    case 2 ->
                        down3;
                    case 3 ->
                        down2;
                    default ->
                        null;
                };
            case "right" ->
                image = switch (spriteNum) {
                    case 1 ->
                        right1;
                    case 2 ->
                        right3;
                    case 3 ->
                        right2;
                    default ->
                        null;
                };
            case "left" ->
                image = switch (spriteNum) {
                    case 1 ->
                        left1;
                    case 2 ->
                        left3;
                    case 3 ->
                        left2;
                    default ->
                        null;
                };
            default -> {
            }
        }

        // Optimise la qualité du rendu graphique
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessine l'image sélectionnée
        g2.drawImage(image, (int) (screenX * scaleX), (int) (screenY * scaleY), (int) (GamePanel.TAILLE_BLOC * scaleX),
                (int) (GamePanel.TAILLE_BLOC * scaleY), null);
    }

    /**
     * Récupère la position actuelle de l'avatar sur la carte.
     *
     * @return un objet {@link Point} représentant la position de l'avatar.
     */
    public Point getPosition() {
        return mapPoint;
    }

    /**
     * Récupère la direction actuelle dans laquelle l'avatar se déplace.
     *
     * @return une chaîne de caractères représentant la direction ("up", "down",
     *         "left", ou "right").
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Vérifie si les 9 cases autour de l'avatar sont libres.
     * Cela inclut la case actuelle de l'avatar et les 8 cases voisines.
     *
     * @return {@code true} si aucune collision n'est détectée, {@code false} sinon.
     */
    public boolean checkNine() {
        Point verif = new Point((int) mapPoint.getX() / GamePanel.TAILLE_BLOC,
                (int) (mapPoint.getY() / GamePanel.TAILLE_BLOC));
        for (int i = 0; i < GamePanel.obj.size(); i++) {
            if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y) {
                return false;
            } else if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y - 1) {
                return false;
            } else if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y + 1) {
                return false;
            } else if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x - 1
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y) {
                return false;
            } else if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x + 1
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y) {
                return false;
            } else if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x - 1
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y - 1) {
                return false;
            } else if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x + 1
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y + 1) {
                return false;
            } else if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x - 1
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y + 1) {
                return false;
            } else if ((int) (GamePanel.obj.get(i).pos.x) / GamePanel.TAILLE_BLOC == (int) verif.x + 1
                    && (int) (GamePanel.obj.get(i).pos.y) / GamePanel.TAILLE_BLOC == (int) verif.y - 1) {
                return false;
            } else if ((int) TileManager.spawnPoint.getX() / GamePanel.TAILLE_BLOC == (int) verif.x + 1
                    && (int) TileManager.spawnPoint.getY() / GamePanel.TAILLE_BLOC == (int) verif.y) {
                return false;
            } else if ((int) TileManager.spawnPoint.getX() / GamePanel.TAILLE_BLOC == (int) verif.x - 1
                    && (int) TileManager.spawnPoint.getY() / GamePanel.TAILLE_BLOC == (int) verif.y) {
                return false;
            } else if ((int) TileManager.spawnPoint.getX() / GamePanel.TAILLE_BLOC == (int) verif.x
                    && (int) TileManager.spawnPoint.getY() / GamePanel.TAILLE_BLOC == (int) verif.y + 1) {
                return false;
            } else if ((int) TileManager.spawnPoint.getX() / GamePanel.TAILLE_BLOC == (int) verif.x
                    && (int) TileManager.spawnPoint.getY() / GamePanel.TAILLE_BLOC == (int) verif.y - 1) {
                return false;
            } else if ((int) TileManager.spawnPoint.getX() / GamePanel.TAILLE_BLOC == (int) verif.x
                    && (int) TileManager.spawnPoint.getY() / GamePanel.TAILLE_BLOC == (int) verif.y - 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si toutes les cases entourant l'avatar contiennent uniquement des
     * ressources valides.
     *
     * @return {@code true} si toutes les cases sont valides, {@code false} sinon.
     */
    public boolean validNine() {
        return checkList(getSurroundingSuperObjects());
    }

    /**
     * Vérifie qu'une liste de super objets contient uniquement des ressources
     * valides.
     *
     * @param list une liste de {@link SuperObjet} à vérifier.
     * @return {@code true} si tous les objets sont des ressources valides,
     *         {@code false} sinon.
     */
    public boolean checkList(ArrayList<SuperObjet> list) {
        for (int i = 0; i < list.size(); i++) {
            // Vérifie que l'objet est une ressource et qu'elle est toujours valide.
            if (!(list.get(i) instanceof Ressource) || list.get(i).time <= 0) {
                System.out.println("Objet invalide : " + list.get(i).getName());
                return false;
            }
        }
        return true; // Tous les objets sont valides.
    }

    /**
     * Récupère tous les objets entourant l'avatar, y compris ceux situés sur sa
     * propre case.
     *
     * @return une liste d'objets {@link SuperObjet} autour de l'avatar.
     */
    public ArrayList<SuperObjet> getSurroundingSuperObjects() {
        ArrayList<SuperObjet> surroundingSuperObjects = new ArrayList<>();
        int avatarX = (int) mapPoint.getX() / GamePanel.TAILLE_BLOC;
        int avatarY = (int) mapPoint.getY() / GamePanel.TAILLE_BLOC;

        int[][] directions = {
                { -1, -1 }, { 0, -1 }, { 1, -1 },
                { -1, 0 }, { 0, 0 }, { 1, 0 },
                { -1, 1 }, { 0, 1 }, { 1, 1 }
        };

        for (int[] direction : directions) {
            int checkX = avatarX + direction[0];
            int checkY = avatarY + direction[1];
            SuperObjet superObjet = getSuperObjetAt(checkX, checkY);
            if (superObjet != null) {
                surroundingSuperObjects.add(superObjet);
            }
        }
        return surroundingSuperObjects;
    }

    /**
     * Obtient un SuperObjet à une position spécifique.
     *
     * <p>
     * Cette méthode parcourt tous les objets dans le panneau de jeu et retourne
     * celui qui se trouve aux coordonnées spécifiées.
     * </p>
     *
     * @param x la position X de l'objet.
     * @param y la position Y de l'objet.
     * @return le SuperObjet à la position spécifiée, ou null si aucun objet n'est
     *         trouvé.
     */
    private SuperObjet getSuperObjetAt(int x, int y) {
        for (SuperObjet obj : GamePanel.obj) {
            if (obj.pos.x / GamePanel.TAILLE_BLOC == x && obj.pos.y / GamePanel.TAILLE_BLOC == y) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Obtient les ressources environnantes autour de l'avatar.
     *
     * <p>
     * Cette méthode parcourt les neuf cases entourant l'avatar et retourne toutes
     * les ressources
     * qui ont un temps positif.
     * </p>
     *
     * @return une liste de ressources environnantes.
     */
    public ArrayList<Ressource> getSurroundingRessources() {
        ArrayList<Ressource> surroundingRessources = new ArrayList<>();
        int avatarX = (int) mapPoint.getX() / GamePanel.TAILLE_BLOC;
        int avatarY = (int) mapPoint.getY() / GamePanel.TAILLE_BLOC;

        int[][] directions = {
                { -1, -1 }, { 0, -1 }, { 1, -1 },
                { -1, 0 }, { 0, 0 }, { 1, 0 },
                { -1, 1 }, { 0, 1 }, { 1, 1 }
        };

        for (int[] direction : directions) {
            int checkX = avatarX + direction[0];
            int checkY = avatarY + direction[1];
            Ressource ressource = getRessourceAt(checkX, checkY);
            if (ressource != null && ressource.time > 0) {
                surroundingRessources.add(ressource);
            }
        }
        return surroundingRessources;
    }

    /**
     * Obtient une ressource à une position spécifique.
     *
     * <p>
     * Cette méthode parcourt tous les objets dans le panneau de jeu et retourne
     * celui qui se trouve aux coordonnées spécifiées et qui est une instance de
     * Ressource.
     * </p>
     *
     * @param x la position X de la ressource.
     * @param y la position Y de la ressource.
     * @return la Ressource à la position spécifiée, ou null si aucune ressource
     *         n'est trouvée.
     */
    private Ressource getRessourceAt(int x, int y) {
        for (int i = 0; i < GamePanel.obj.size(); i++) {
            if (GamePanel.obj.get(i).pos.x / GamePanel.TAILLE_BLOC == x
                    && GamePanel.obj.get(i).pos.y / GamePanel.TAILLE_BLOC == y
                    && GamePanel.obj.get(i) instanceof Ressource) {
                return (Ressource) GamePanel.obj.get(i);
            }
        }
        return null;
    }

    /**
     * Réinitialise le déplacement de l'avatar en mettant à false les indicateurs de
     * mouvement.
     */
    public void resetDeplacement() {
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
    }

    /**
     * Crée une ressource en suivant une recette spécifique.
     *
     * <p>
     * Cette méthode vérifie si la recette est possible avec les ingrédients
     * disponibles
     * dans l'inventaire de l'avatar, crée la ressource et met à jour l'inventaire
     * en conséquence.
     * </p>
     *
     * @param recette la recette à suivre pour créer la ressource.
     * @return true si la ressource a été créée avec succès, false sinon.
     */
    public boolean createRessource(Recette recette) {
        if (recette == null) {
            return false;
        }
        if (recette.isPossible(Inventaire.getInvAvatar())) {
            Ressource ressource = (Ressource) SuperObjet.getSuperObjet(recette.getRessource().getName());
            Inventaire.addToAvatarInv(ressource, 1);
            for (int i = 0; i < recette.getIngredients().length; i++) {
                Inventaire.removeFromAvatarInv(recette.getIngredients()[i], recette.getIngredients()[i].getQuantity());
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtient la ressource qui se trouve directement devant l'avatar en fonction de
     * sa direction.
     *
     * <p>
     * Cette méthode vérifie les cases devant l'avatar dans les quatre directions
     * cardinales
     * et retourne la ressource trouvée.
     * </p>
     *
     * @return la Ressource devant l'avatar, ou null si aucune ressource n'est
     *         trouvée.
     */
    public Ressource getRessourceFront() {
        Ressource res = null;
        int avatarX = (int) mapPoint.getX() / GamePanel.TAILLE_BLOC;
        int avatarY = (int) mapPoint.getY() / GamePanel.TAILLE_BLOC;

        int[][] directions = {
                { 0, -1 },
                { -1, 0 }, { 0, 0 }, { 1, 0 },
                { 0, 1 }
        };
        int checkX;
        int checkY;
        switch (direction) {
            case ("up"):
                checkX = avatarX + directions[0][0];
                checkY = avatarY + directions[0][1];
                res = getRessourceAt(checkX, checkY);
                if (res == null) {
                    checkX = avatarX + directions[2][0];
                    checkY = avatarY + directions[2][1];
                    res = getRessourceAt(checkX, checkY);
                }
                break;
            case ("down"):
                checkX = avatarX + directions[4][0];
                checkY = avatarY + directions[4][1];
                res = getRessourceAt(checkX, checkY);
                if (res == null) {
                    checkX = avatarX + directions[2][0];
                    checkY = avatarY + directions[2][1];
                    res = getRessourceAt(checkX, checkY);
                }
                break;
            case ("right"):
                checkX = avatarX + directions[3][0];
                checkY = avatarY + directions[3][1];
                res = getRessourceAt(checkX, checkY);
                if (res == null) {
                    checkX = avatarX + directions[2][0];
                    checkY = avatarY + directions[2][1];
                    res = getRessourceAt(checkX, checkY);
                }
                break;
            case ("left"):
                checkX = avatarX + directions[1][0];
                checkY = avatarY + directions[1][1];
                res = getRessourceAt(checkX, checkY);
                if (res == null) {
                    checkX = avatarX + directions[2][0];
                    checkY = avatarY + directions[2][1];
                    res = getRessourceAt(checkX, checkY);
                }
                break;
            default:
                break;
        }
        return res;
    }

}
