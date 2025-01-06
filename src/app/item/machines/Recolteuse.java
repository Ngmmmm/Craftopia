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

/**
 * La classe Recolteuse représente une machine de récolte dans le jeu.
 *
 * <p>
 * Cette classe gère la création de ressources, l'interaction avec le joueur, et l'inventaire spécifique
 * de la machine. Elle implémente les comportements définis par l'interface MachineBehavior.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'ajout et la suppression de ressources, la création récursive de ressources,
 * et la gestion de l'état de la machine. Cette classe permet de simuler le fonctionnement d'une
 * machine de récolte dans le jeu.
 * </p>
 *
 * @author Noa Gregoire-Mellier
 * @version 1.0
 */
public class Recolteuse extends Machine {

    // Interface graphique associée à la Recolteuse
    public UiRecolteuse uiRecolteuse;

    // Ressource courante de la Recolteuse
    public Ressource res;

    /**
     * Constructeur par défaut pour initialiser la Recolteuse.
     */
    public Recolteuse() {
        this.name = "Recolteuse"; // Nom de la machine
        this.stock = new InventaireMachine(this); // Inventaire de la machine
        this.prix = Argent.getPrice(Produits.RECOLTEUSE); // Prix de la machine
        this.produit = Produits.RECOLTEUSE; // Type de produit
        this.uiRecolteuse = new UiRecolteuse(this); // Interface graphique de la Recolteuse
        this.capacite = 5; // Capacité maximale de la machine
        this.time = 5; // Temps de fonctionnement en secondes

        try {
            // Chargement de l'image de la Recolteuse
            File f1 = new File("./img/machines/recolteuse.png");
            this.image = ImageIO.read(f1);
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs de chargement de l'image
        }
    }

    /**
     * Ajoute une ressource à la machine.
     */
    public void addRessource() {
        this.res.increaseQuantity(1); // Augmente la quantité de la ressource de 1
    }

    /**
     * Retourne des ressources au joueur.
     * 
     * @param r la ressource à retourner.
     * @param quantity la quantité de la ressource à retourner.
     */
    @Override
    public void backToPlayer(Ressource r, int quantity) {
        Ressource ressource = (Ressource) SuperObjet.getSuperObjet(r.getName());
        Inventaire.addToAvatarInv(ressource, quantity); // Ajoute les ressources à l'inventaire de l'avatar
        this.stock.remove(r, quantity); // Retire les ressources de l'inventaire de la machine
    }

    /**
     * Obtient l'inventaire de la machine.
     * 
     * @return l'inventaire de la machine.
     */
    @Override
    public InventaireMachine getStock() {
        return stock; // Retourne l'inventaire de la machine
    }

    /**
     * Définit la ressource de la machine.
     * 
     * @param r la ressource à définir.
     */
    public void setRessource(Ressource r) {
        this.res = r; // Définit la ressource courante de la machine
    }

    /**
     * Crée des ressources de manière récursive jusqu'à ce que la capacité soit atteinte.
     */
    @Override
    public void createRessource() {
        int compteur = 0;
        for (SuperObjet item : this.stock.getInv()) {
            Ressource ress = (Ressource) item;
            compteur += ress.getQuantity(); // Calcule la quantité totale de ressources dans l'inventaire
        }

        if (compteur < this.capacite) {
            this.isActive = true; // Active la machine
            createRessourceRecursively(compteur); // Lance la création récursive des ressources
        }
    }

    /**
     * Méthode récursive pour créer des ressources.
     * 
     * @param compteur le compteur de ressources créées.
     */
    private void createRessourceRecursively(int compteur) {
        if (compteur < this.capacite && this.isActive) {
            new Thread(() -> {
                try {
                    Thread.sleep(this.time * 1000); // Attente avant de créer la ressource
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Gestion des interruptions
                } finally {
                    System.out.println("on est good");
                    this.stock.add(res, 1); // Ajoute une ressource à l'inventaire de la machine
                    this.uiRecolteuse.updateInventory(); // Met à jour l'inventaire graphique

                    if (this instanceof RecolteuseFragile) {
                        Random random = new Random();
                        double probability = random.nextDouble(); // Génère un nombre entre 0.0 et 1.0

                        if (probability < 0.3) { // 30 % de chance
                            this.isActive = false; // Désactive la RecolteuseFragile
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
            this.isActive = false; // Désactive la machine si la capacité est atteinte
        }
    }

    /**
     * Obtient les ressources environnantes autour de la machine.
     * 
     * @return une liste des ressources environnantes.
     */
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
            Ressource ressource = getRessourceAt(checkX, checkY); // Obtient la ressource à la position spécifiée
            if (ressource != null && ressource.time > 0) {
                surroundingRessources.add(ressource); // Ajoute la ressource à la liste si elle est valide
            }
        }
        return surroundingRessources;
    }

    /**
     * Obtient la ressource à une position spécifique.
     * 
     * @param x la position x de la ressource.
     * @param y la position y de la ressource.
     * @return la ressource à la position spécifiée, ou null si aucune ressource n'est trouvée.
     */
    private Ressource getRessourceAt(int x, int y) {
        for (int i = 0; i < GamePanel.obj.size(); i++) {
            if (GamePanel.obj.get(i).pos.x / GamePanel.TAILLE_BLOC == x && GamePanel.obj.get(i).pos.y / GamePanel.TAILLE_BLOC == y && GamePanel.obj.get(i) instanceof Ressource) {
                return (Ressource) GamePanel.obj.get(i); // Retourne la ressource trouvée à la position spécifiée
            }
        }
        return null; // Retourne null si aucune ressource n'est trouvée
    }
}
