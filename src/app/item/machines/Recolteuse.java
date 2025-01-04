package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.io.File;
import javax.imageio.ImageIO;

public class Recolteuse extends Machine {

    public Ressource res;
    public Recolteuse() {
        name = "Recolteuse";
        this.stock = new InventaireMachine(this);
        prix = Argent.getPrice(Produits.RECOLTEUSE);
        produit = Produits.RECOLTEUSE;
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
        System.out.println(r.getQuantity());
        Inventaire.addToAvatarInv(ressource, quantity);
        stock.remove(r,quantity);
    }

    @Override
    public InventaireMachine getStock() {
        return stock;
    }

    public void setRessource(Ressource r) {
        this.res = r;
    }

}
