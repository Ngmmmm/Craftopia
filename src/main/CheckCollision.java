package main;

import sprites.Sprite;

public class CheckCollision {
    GamePanel gp;

    public CheckCollision(GamePanel gp) {
        this.gp = gp;

    }

    public void checkTile(Sprite sprite) {
        int spriteLeftMapX = sprite.mapPoint.x + sprite.solidArea.x;
        int spriteRightMapX = sprite.mapPoint.x + sprite.solidArea.x + sprite.solidArea.width;
        int spriteTopMapY = sprite.mapPoint.y + sprite.solidArea.y;
        int spriteBottomMapY = sprite.mapPoint.y + sprite.solidArea.y + sprite.solidArea.height;

        int spriteLeftCol = spriteLeftMapX / gp.TAILLE_BLOC;
        int spriteRightCol = spriteRightMapX / gp.TAILLE_BLOC;
        int spriteTopLigne = spriteTopMapY / gp.TAILLE_BLOC;
        int spriteBottomLigne = spriteBottomMapY / gp.TAILLE_BLOC;

        int tileNum1, tileNum2;

        switch (sprite.direction) {
            case "up":
                spriteTopLigne = (spriteTopMapY - sprite.speed) / gp.TAILLE_BLOC;
                tileNum1 = gp.tileM.mapTileNum[spriteLeftCol][spriteTopLigne];
                tileNum2 = gp.tileM.mapTileNum[spriteRightCol][spriteTopLigne];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;
            case "down":
                spriteTopLigne = (spriteTopMapY - sprite.speed) / gp.TAILLE_BLOC;
                break;
            case "left":
                spriteTopLigne = (spriteTopMapY - sprite.speed) / gp.TAILLE_BLOC;
                break;
            case "right":
                spriteTopLigne = (spriteTopMapY - sprite.speed) / gp.TAILLE_BLOC;
                break;

            default:
                break;
        }

    }

}
