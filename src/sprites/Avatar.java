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
        this.x = 100;
        this.y = 100;
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
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            right1 = ImageIO.read(f5);
            right2 = ImageIO.read(f6);
            left1 = ImageIO.read(f7);
            left2 = ImageIO.read(f8);
        } catch (IOException e) {
            System.out.println("Image non chargée: " + e);
        }
    }

    public void update() {
        if (keyH.upPressed == true) {
            direction = "up";
            this.y -= this.speed;
        } else if (keyH.downPressed == true) {
            direction = "down";
            this.y += this.speed;
        } else if (keyH.leftPressed == true) {
            direction = "left";
            this.x -= this.speed;
        } else if (keyH.rightPressed == true) {
            direction = "right";
            this.x += this.speed;
        }
        spriteCounter++;
        if (spriteCounter>200) {
            if (spriteNum==1) {
                spriteNum=2;
            }
            else if(spriteNum==2){
                spriteNum=1;
            }
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.RED);
        // g2.fillRect(this.x, this.y, gp.tailleBloc, gp.tailleBloc);

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            default -> {
            }
        }

        g2.drawImage(image, x, y, gp.tailleBloc, gp.tailleBloc, null);
    }
}
