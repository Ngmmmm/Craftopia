package ui;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class MenuButton {

    private int xPos, yPos, rowIndex;
    private GamePanel gp;
    private BufferedImage[] images;
    public MenuButton(int xPos, int yPos, int rowIndex, GamePanel gp){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.gp = gp;
        loadImage();
    }

    private void loadImage(){
        images = new BufferedImage[2];
        try {
        BufferedImage temp = ImageIO.read(new File("test"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
