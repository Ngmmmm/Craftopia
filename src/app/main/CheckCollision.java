package main;

import sprites.Sprite;

/**
 * La classe CheckCollision gère la détection des collisions entre les sprites
 * et les blocs de la carte.
 * <p>
 * Cette classe utilise les propriétés des {@link Sprite} et les données de la
 * carte pour déterminer si un sprite rencontre un bloc avec laquelle il ne peut pas
 * passer
 * (c'est-à-dire un bloc avec la propriété de collision activée).
 * </p>
 * 
 * <p>
 * Elle est utilisée principalement dans le contexte du déplacement des sprites,
 * afin de prévenir leur mouvement à travers des objets ou des zones interdites.
 * </p>
 * 
 * @author Ouahid Houat
 * @version 1.0
 */
public class CheckCollision {
    GamePanel gp; // Instance du panneau de jeu, contenant les informations sur la carte

    /**
     * Constructeur de la classe CheckCollision.
     * 
     * @param gp le panneau de jeu principal, utilisé pour accéder aux informations
     *           de la carte.
     */
    public CheckCollision(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Vérifie les collisions entre un sprite et les blocs de la carte, en fonction
     * de
     * la direction et de la position actuelle du sprite.
     * <p>
     * Si une collision est détectée, la propriété {@code collisionOn} du sprite est
     * définie sur {@code true}.
     * </p>
     * 
     * @param sprite le sprite pour lequel la collision doit être vérifiée.
     */
    public void checkTile(Sprite sprite) {
        // Calcul des limites du sprite sur la carte
        int spriteLeftMapX = sprite.mapPoint.x + sprite.solidArea.x;
        int spriteRightMapX = sprite.mapPoint.x + sprite.solidArea.x + sprite.solidArea.width;
        int spriteTopMapY = sprite.mapPoint.y + sprite.solidArea.y;
        int spriteBottomMapY = sprite.mapPoint.y + sprite.solidArea.y + sprite.solidArea.height;

        // Détermination des colonnes et lignes correspondant aux bords du sprite
        int spriteLeftCol = spriteLeftMapX / gp.TAILLE_BLOC;
        int spriteRightCol = spriteRightMapX / gp.TAILLE_BLOC;
        int spriteTopLigne = spriteTopMapY / gp.TAILLE_BLOC;
        int spriteBottomLigne = spriteBottomMapY / gp.TAILLE_BLOC;

        int tileNum1, tileNum2; // Numéros des blocs à vérifier

        // Vérification des collisions en fonction de la direction du sprite
        switch (sprite.direction) {
            case "up": // Si le sprite se déplace vers le haut
                spriteTopLigne = (spriteTopMapY - sprite.speed) / gp.TAILLE_BLOC; // Nouvelle ligne après le mouvement
                tileNum1 = gp.tileM.mapTileNum[spriteLeftCol][spriteTopLigne];
                tileNum2 = gp.tileM.mapTileNum[spriteRightCol][spriteTopLigne];
                // Si l'un des blocs a une collision, activer la collision pour le sprite
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;
            case "down": // Si le sprite se déplace vers le bas
                spriteBottomLigne = (spriteBottomMapY + sprite.speed) / gp.TAILLE_BLOC;
                tileNum1 = gp.tileM.mapTileNum[spriteLeftCol][spriteBottomLigne];
                tileNum2 = gp.tileM.mapTileNum[spriteRightCol][spriteBottomLigne];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;
            case "left": // Si le sprite se déplace vers la gauche
                spriteLeftCol = (spriteLeftMapX - sprite.speed) / gp.TAILLE_BLOC;
                tileNum1 = gp.tileM.mapTileNum[spriteLeftCol][spriteTopLigne];
                tileNum2 = gp.tileM.mapTileNum[spriteLeftCol][spriteBottomLigne];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;
            case "right": // Si le sprite se déplace vers la droite
                spriteRightCol = (spriteRightMapX + sprite.speed) / gp.TAILLE_BLOC;
                tileNum1 = gp.tileM.mapTileNum[spriteRightCol][spriteTopLigne];
                tileNum2 = gp.tileM.mapTileNum[spriteRightCol][spriteBottomLigne];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;

            default:
                break;
        }
    }
}
