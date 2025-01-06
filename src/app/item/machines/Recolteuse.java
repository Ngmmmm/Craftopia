package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import main.UiRecolteuse;

public class Recolteuse extends Machine {

    public UiRecolteuse uiRecolteuse;
    public Ressource res;

    public Recolteuse() {
        this.name = "Recolteuse";
        this.stock = new InventaireMachine(this);
        this.prix = Argent.getPrice(Produits.RECOLTEUSE);
        this.produit = Produits.RECOLTEUSE;
        this.uiRecolteuse = new UiRecolteuse(this);
        this.capacite = 5;
        this.time = 5;
        try {
            File f1 = new File("./img/machines/recolteuse.png");
            this.image = ImageIO.read(f1);
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

    public void createRessource() {
        int compteur = 0;
        for (SuperObjet item : this.stock.getInv()) {
            Ressource ress = (Ressource) item;
            compteur += ress.getQuantity();
        }

        if (compteur < this.capacite) {
            this.isActive = true;
            createRessourceRecursively(compteur);
        }
    }

    private void createRessourceRecursively(int compteur) {
        if (compteur < this.capacite && this.isActive) {
            new Thread(() -> {
                try {
                    Thread.sleep(this.time * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("on est good");
                    this.stock.add(res, 1);
                    this.uiRecolteuse.updateInventory();

                    if (this instanceof RecolteuseFragile) {
                        Random random = new Random();
                        double probability = random.nextDouble(); // Génère un nombre entre 0.0 et 1.0.

                        if (probability < 0.3) { // 30 % de chance
                            this.isActive = false;
                            System.out.println("RecolteuseFragile est désactivée (probabilité atteinte).");
                        } else {
                            System.out.println("RecolteuseFragile reste active.");
                        }
                    }
                    // Appel récursif après avoir ajouté la ressource
                    createRessourceRecursively(compteur + 1);
                }
            }).start();
        } else {
            this.isActive = false;
        }
    }

}
