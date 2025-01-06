package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.io.File;
import java.util.Random;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.interfacegraphique.UiRecolteuse;

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

    @Override
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
