package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UiRecolteuse;

public class Recolteuse extends Machine {

    public UiRecolteuse uiRecolteuse;
    public Ressource res;
    public Recolteuse() {
        name = "Recolteuse";
        this.stock = new InventaireMachine(this);
        this.stock.add(res, 0);
        prix = Argent.getPrice(Produits.RECOLTEUSE);
        produit = Produits.RECOLTEUSE;
        uiRecolteuse = new UiRecolteuse(this);
        try {
            File f1 = new File("./img/machines/recolteuse.png");
            image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addRessource() {
        this.res.increaseQuantity(1);
    }

    @Override
    public void backToPlayer(Ressource r, int quantity) {
        Ressource ressource = (Ressource) SuperObjet.getSuperObjet(r.getName());
        Inventaire.addToAvatarInv(ressource, quantity);
        this.stock.remove(r, quantity);
    }

    @Override
    public InventaireMachine getStock() {
        return stock;
    }

    public void setRessource(Ressource r) {
        this.res = r;
    }

    public ArrayList<Ressource> getSurroundingRessources() {
        ArrayList<Ressource> surroundingRessources = new ArrayList<>();
        int posX = (int) pos.getX() / GamePanel.TAILLE_BLOC;
        int posY = (int) pos.getY() / GamePanel.TAILLE_BLOC;

        int[][] directions = {
            {0, -1},
            {-1, 0}, {1, 0},
            {0, 1}
        };

        for (int[] direction : directions) {
            int checkX = posX + direction[0];
            int checkY = posY + direction[1];
            Ressource ressource = getRessourceAt(checkX, checkY);
            if (ressource != null && ressource.time > 0) {
                surroundingRessources.add(ressource);
            }
        }
        return surroundingRessources;
    }

    private Ressource getRessourceAt(int x, int y) {
        for(int i = 0; i<GamePanel.obj.size();i++){
            if(GamePanel.obj.get(i).pos.x / GamePanel.TAILLE_BLOC == x && GamePanel.obj.get(i).pos.y / GamePanel.TAILLE_BLOC == y && GamePanel.obj.get(i) instanceof Ressource){
                return (Ressource) GamePanel.obj.get(i);
            }
        }
        return null; // Remplacez par la logique réelle
    }

}
