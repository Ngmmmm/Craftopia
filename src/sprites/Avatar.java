package sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public final class Avatar extends Sprite {
    GamePanel gp;
    KeyHandler keyH;

    public Avatar(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setValeurParDefaut();
        getAvatarImage();
    }

    public void setValeurParDefaut() {
        pos.x = 100;
        pos.y = 100;
        this.speed = 4;
        direction = "down";
    }

    public void getAvatarImage() {
        try {
            File f1 = new File("./img/player/avatarUp1.png");
            File f2 = new File("./img/player/avatarUp2.png");
            File f3 = new File("./img/player/avatarDown1.png");
            File f4 = new File("./img/player/avatarDown2.png");
            File f5 = new File("./img/player/avatarRight1.png");
            File f6 = new File("./img/player/avatarRight2.png");
            File f7 = new File("./img/player/avatarLeft1.png");
            File f8 = new File("./img/player/avatarLeft2.png");
            File f9 = new File("./img/player/noMoveUp.png");
            File f10 = new File("./img/player/noMoveDown.png");
            File f11 = new File("./img/player/noMoveRight.png");
            File f12 = new File("./img/player/noMoveLeft.png");
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            up3 = ImageIO.read(f9);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            down3 = ImageIO.read(f10);
            right1 = ImageIO.read(f5);
            right2 = ImageIO.read(f6);
            right3 = ImageIO.read(f11);
            left1 = ImageIO.read(f7);
            left2 = ImageIO.read(f8);
            left3 = ImageIO.read(f12);
        } catch (IOException e) {
            System.out.println("Image non chargée: " + e);
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
                this.pos.y -= this.speed;
            } else if (keyH.downPressed) {
                direction = "down";
                this.pos.y += this.speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                this.pos.x -= this.speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                this.pos.x += this.speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                switch (spriteNum) {
                    case 1 -> spriteNum = 2;
                    case 2 -> spriteNum = 3;
                    case 3 -> spriteNum = 1;
                    default -> {
                    }
                }
            }
        } else {
            direction = "down";
            spriteNum = 2;
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up3;
                }
                if (spriteNum == 3) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down3;
                }
                if (spriteNum == 3) {
                    image = down2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right3;
                }
                if (spriteNum == 3) {
                    image = right2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left3;
                }
                if (spriteNum == 3) {
                    image = left2;
                }
            }
            default -> {

            }
        }

        g2.drawImage(image, pos.x, pos.y, gp.TAILLE_BLOC, gp.TAILLE_BLOC, null);
    }
}
