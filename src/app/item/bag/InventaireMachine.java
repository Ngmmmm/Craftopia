package item.bag;

import item.machines.Machine;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.util.ArrayList;

/**
 * La classe InventaireMachine gère l'inventaire des machines dans le jeu.
 *
 * <p>
 * Cette classe permet d'ajouter, de supprimer et de vérifier les objets dans l'inventaire spécifique des machines.
 * Elle contient une liste des objets que la machine possède actuellement ainsi que des méthodes pour
 * manipuler cette liste.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'ajout et la suppression d'objets, l'obtention de la quantité d'un objet
 * spécifique, et la vérification de la présence d'un objet dans l'inventaire de la machine. Cela permet de
 * gérer efficacement les objets et leurs quantités pour chaque machine.
 * </p>
 *
 * @author Ouahid Houat
 * @version 1.0
 */
public class InventaireMachine extends Inventaire {

    // Liste des objets dans l'inventaire de la machine
    public ArrayList<SuperObjet> invObject;

    /**
     * Constructeur pour initialiser l'inventaire de la machine.
     * 
     * @param machine l'objet Machine auquel cet inventaire est associé.
     */
    public InventaireMachine(Machine machine) {
        // Initialise la liste des objets de l'inventaire de la machine.
        invObject = new ArrayList<SuperObjet>();
    }

    /**
     * Obtient l'inventaire de la machine.
     * 
     * @return une liste contenant tous les objets de l'inventaire de la machine.
     */
    public ArrayList<SuperObjet> getInv() {
        return invObject; // Retourne la liste des objets de l'inventaire de la machine.
    }

    /**
     * Ajoute un objet à l'inventaire de la machine.
     * 
     * @param item l'objet à ajouter.
     * @param quantity la quantité de l'objet à ajouter.
     * @return `true` si l'ajout a réussi, `false` sinon.
     */
    public boolean add(SuperObjet item, int quantity) {
        if (item == null) { // Vérifie si l'objet à ajouter est nul.
            return false; // Retourne false si l'objet est nul.
        }
        for (int i = 0; i < invObject.size(); i++) { // Parcourt tous les objets de l'inventaire de la machine.
            if (invObject.get(i).getName().equals(item.name)) { // Vérifie si l'objet existe déjà dans l'inventaire.
                invObject.get(i).increaseQuantity(quantity); // Augmente la quantité de l'objet existant.
                return true; // Retourne true pour indiquer que l'ajout a réussi.
            }
        }
        invObject.add(item); // Ajoute l'objet à l'inventaire.
        item.increaseQuantity(quantity); // Augmente la quantité de l'objet.
        return true; // Retourne true pour indiquer que l'ajout a réussi.
    }

    /**
     * Supprime une quantité spécifiée d'un objet de l'inventaire de la machine.
     * 
     * @param item l'objet à supprimer.
     * @param quantity la quantité de l'objet à supprimer.
     * @return `true` si la suppression a réussi, `false` sinon.
     */
    public boolean remove(SuperObjet item, int quantity) {
        for (int i = 0; i < invObject.size(); i++) { // Parcourt tous les objets de l'inventaire de la machine.
            Ressource ressource = (Ressource) SuperObjet.getSuperObjet(item.getName());
            if (invObject.get(i).getName().equals(ressource.getName())) { // Vérifie si l'objet existe dans l'inventaire.
                invObject.get(i).reduceQuantity(quantity); // Réduit la quantité de l'objet.
                if (invObject.get(i).quantity <= 0) { // Si la quantité de l'objet est inférieure ou égale à zéro,
                    invObject.remove(i); // Supprime l'objet de l'inventaire.
                }
            }
        }
        return true; // Retourne true pour indiquer que la suppression a réussi.
    }

    /**
     * Obtient la quantité d'un objet spécifique dans l'inventaire de la machine.
     * 
     * @param item l'objet dont la quantité est demandée.
     * @return la quantité de l'objet dans l'inventaire de la machine.
     */
    public int getQuantity(SuperObjet item) {
        for (int i = 0; i < invObject.size(); i++) { // Parcourt tous les objets de l'inventaire de la machine.
            if (invObject.get(i).getName().equals(item.name)) { // Vérifie si l'objet existe dans l'inventaire.
                return invObject.get(i).quantity; // Retourne la quantité de l'objet.
            }
        }
        return 0; // Retourne 0 si l'objet n'est pas trouvé dans l'inventaire.
    }
}
