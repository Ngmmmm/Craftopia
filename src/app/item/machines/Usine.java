package item.machines;

import item.bag.Argent;
import item.bag.Inventaire;
import item.bag.InventaireMachine;
import item.marche.Marche.Produits;
import item.recettes.Recette;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import main.interfacegraphique.UiUsine;

import java.io.File;
import javax.imageio.ImageIO;

/**
 * La classe Usine représente une usine dans le jeu.
 *
 * <p>
 * Cette classe gère la création de ressources, l'interaction avec le joueur, et
 * l'inventaire spécifique
 * de l'usine. Elle implémente les comportements définis par l'interface
 * MachineBehavior.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'ajout et la suppression de ressources, la création de
 * ressources,
 * et la gestion de l'état de l'usine. Cette classe permet de simuler le
 * fonctionnement d'une
 * usine dans le jeu.
 * </p>
 *
 * @author Noa Gregoire-Mellier
 * @version 1.0
 */
public class Usine extends Machine {

    // Recette actuellement active dans l'usine
    public Recette recetteAct;

    // Interface graphique associée à l'usine
    public UiUsine uiUsine;

    /**
     * Constructeur par défaut pour initialiser l'usine.
     */
    public Usine() {
        this.name = "Usine"; // Nom de la machine
        this.stock = new InventaireMachine(this); // Inventaire de la machine
        this.prix = Argent.getPrice(Produits.USINE); // Prix de la machine
        this.produit = Produits.USINE; // Type de produit
        this.uiUsine = new UiUsine(this); // Interface graphique de l'usine
        this.recetteAct = null; // Aucune recette active par défaut
        this.capacite = 5; // Capacité maximale de la machine
        this.time = 10; // Temps de fonctionnement en secondes

        try {
            // Chargement de l'image de l'usine
            File f1 = new File("./img/machines/usine.png");
            this.image = ImageIO.read(f1);
        } catch (Exception e) {
            System.out.println("Impossible d'afficher l'usine " + e); // Gestion des erreurs de chargement de l'image
        }
    }

    /**
     * Ajoute une ressource de l'avatar à l'inventaire de l'usine.
     * 
     * @param r la ressource à ajouter.
     */
    public void addFromPlayer(Ressource r) {
        stock.add(r, 1); // Ajoute la ressource à l'inventaire de l'usine
        Inventaire.removeFromAvatarInv(r, 1); // Retire la ressource de l'inventaire de l'avatar
    }

    /**
     * Retourne des ressources au joueur.
     * 
     * @param r        la ressource à retourner.
     * @param quantity la quantité de la ressource à retourner.
     */
    @Override
    public void backToPlayer(Ressource r, int quantity) {
        Ressource ressource = (Ressource) SuperObjet.getSuperObjet(r.getName());
        Inventaire.addToAvatarInv(ressource, quantity); // Ajoute les ressources à l'inventaire de l'avatar
        stock.remove(r, quantity); // Retire les ressources de l'inventaire de l'usine
    }

    /**
     * Définit la recette actuellement active dans l'usine.
     * 
     * @param recette la recette à définir comme active.
     */
    public void setRecetteAct(Recette recette) {
        this.recetteAct = recette; // Définit la recette active de l'usine
    }

    /**
     * Vérifie si la recette actuelle est possible avec les ressources disponibles.
     * 
     * @return `true` si la recette est possible, `false` sinon.
     */
    public boolean isPossible() {
        return recetteAct.isPossible(Inventaire.getInvAvatar()); // Vérifie si la recette est possible
    }

    /**
     * Crée des ressources en utilisant la recette active.
     */
    @Override
    public void createRessource() {
        int compteur = 0;
        for (SuperObjet item : this.stock.getInv()) {
            Ressource ress = (Ressource) item;
            compteur += ress.getQuantity(); // Calcule la quantité totale de ressources dans l'inventaire
        }
        if (isPossible() && compteur < this.capacite) {
            Ressource ressource = (Ressource) SuperObjet.getSuperObjet(recetteAct.getRessource().getName());
            for (int i = 0; i < recetteAct.getIngredients().length; i++) {
                Inventaire.removeFromAvatarInv(recetteAct.getIngredients()[i],
                        recetteAct.getIngredients()[i].getQuantity()); // Retire les ingrédients de l'inventaire de
                                                                       // l'avatar
            }
            stock.add(ressource, 1); // Ajoute la ressource créée à l'inventaire de l'usine
        } else {
            System.out.println("Recette impossible"); // Affiche un message si la recette n'est pas possible
        }
    }

    /**
     * Obtient l'inventaire de l'usine.
     * 
     * @return l'inventaire de l'usine.
     */
    @Override
    public InventaireMachine getStock() {
        return stock; // Retourne l'inventaire de l'usine
    }

    /**
     * Obtient la recette actuellement active dans l'usine.
     * 
     * @return la recette actuellement active.
     */
    public Recette getRecette() {
        return recetteAct; // Retourne la recette active
    }

    /**
     * Met à jour l'interface graphique de l'usine.
     */
    public void update() {
        uiUsine = new UiUsine(this); // Met à jour l'interface graphique de l'usine
    }
}
