package item.marche;

import java.io.File;

import javax.imageio.ImageIO;

import item.sprites.SuperObjet;

public class Marche extends SuperObjet {

    public Marche() {
        name = "marché";
        @SuppressWarnings("unused")
        final int quantity = 1; // Dans la version de base du jeu, il n'y a qu'un seul marché
        try {
            File f1 = new File("./img/marche/shop.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
