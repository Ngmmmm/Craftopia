package item.bag;

import item.sprites.SuperObjet;
import java.util.ArrayList;

/**
 * La classe Inventaire gère l'inventaire de l'avatar dans le jeu.
 *
 * <p>
 * Cette classe permet d'ajouter, de supprimer et de vérifier les objets dans l'inventaire de l'avatar.
 * Elle contient une liste des objets que l'avatar possède actuellement ainsi que des méthodes pour
 * manipuler cette liste.
 * </p>
 *
 * <p>
 * Les méthodes incluent l'ajout et la suppression d'objets, l'obtention de la quantité d'un objet
 * spécifique, et la vérification de la présence d'un objet dans l'inventaire. Cela permet de
 * gérer efficacement les objets et leurs quantités pour l'avatar.
 * </p>
 *
 * @author Noa Gregoire-Mellier
 * @version 1.0
 */
public class Inventaire {

    // Liste statique contenant les objets de l'inventaire de l'avatar.
    public final static ArrayList<SuperObjet> invAvatar = new ArrayList<>();

    // ! Méthode pour l'inventaire de L'avatar

    /**
     * Ajoute un objet à l'inventaire de l'avatar.
     * 
     * @param item l'objet à ajouter.
     * @param quantity la quantité de l'objet à ajouter.
     * @return `true` si l'ajout a réussi, `false` sinon.
     */
    public static boolean addToAvatarInv(SuperObjet item, int quantity) {
        if (item == null) { // Vérifie si l'objet à ajouter est nul.
            return false; // Retourne false si l'objet est nul.
        }
        for (int i = 0; i < invAvatar.size(); i++) { // Parcourt tous les objets de l'inventaire.
            if (invAvatar.get(i).getName().equals(item.name)) { // Vérifie si l'objet existe déjà dans l'inventaire.
                invAvatar.get(i).increaseQuantity(quantity); // Augmente la quantité de l'objet existant.
                return true; // Retourne true pour indiquer que l'ajout a réussi.
            }
        }
        invAvatar.add(item); // Ajoute l'objet à l'inventaire.
        item.increaseQuantity(quantity); // Augmente la quantité de l'objet.
        return true; // Retourne true pour indiquer que l'ajout a réussi.
    }

    /**
     * Supprime une quantité spécifiée d'un objet de l'inventaire de l'avatar.
     * 
     * @param item l'objet à supprimer.
     * @param quantity la quantité de l'objet à supprimer.
     * @return `true` si la suppression a réussi, `false` sinon.
     */
    public static boolean removeFromAvatarInv(SuperObjet item, int quantity) {
        for (int i = 0; i < invAvatar.size(); i++) { // Parcourt tous les objets de l'inventaire.
            if (invAvatar.get(i).getName().equals(item.name)) { // Vérifie si l'objet existe dans l'inventaire.
                invAvatar.get(i).reduceQuantity(quantity); // Réduit la quantité de l'objet.
                if (invAvatar.get(i).quantity <= 0) { // Si la quantité de l'objet est inférieure ou égale à zéro,
                    invAvatar.remove(i); // Supprime l'objet de l'inventaire.
                }
            }
        }
        return true; // Retourne true pour indiquer que la suppression a réussi.
    }

    /**
     * Obtient l'inventaire de l'avatar.
     * 
     * @return une liste contenant tous les objets de l'inventaire de l'avatar.
     */
    public static ArrayList<SuperObjet> getInvAvatar() {
        return invAvatar; // Retourne la liste des objets de l'inventaire de l'avatar.
    }

    /**
     * Obtient la quantité d'un objet spécifique dans l'inventaire de l'avatar.
     * 
     * @param item l'objet dont la quantité est demandée.
     * @return la quantité de l'objet dans l'inventaire de l'avatar.
     */
    public static int getQuantityFromAvatarInv(SuperObjet item) {
        for (int i = 0; i < invAvatar.size(); i++) { // Parcourt tous les objets de l'inventaire.
            if (invAvatar.get(i).getName().equals(item.name)) { // Vérifie si l'objet existe dans l'inventaire.
                return invAvatar.get(i).quantity; // Retourne la quantité de l'objet.
            }
        }
        return 0; // Retourne 0 si l'objet n'est pas trouvé dans l'inventaire.
    }

    /**
     * Vérifie si un objet est présent dans l'inventaire de l'avatar.
     * 
     * @param item l'objet à vérifier.
     * @return `true` si l'objet est présent, `false` sinon.
     */
    public static boolean isIn(SuperObjet item) {
        if (item == null) { // Vérifie si l'objet est nul.
            return false; // Retourne false si l'objet est nul.
        }
        for (int i = 0; i < invAvatar.size(); i++) { // Parcourt tous les objets de l'inventaire.
            if (invAvatar.get(i).getName().equals(item.name)) { // Vérifie si l'objet existe dans l'inventaire.
                return true; // Retourne true si l'objet est trouvé dans l'inventaire.
            }
        }
        return false; // Retourne false si l'objet n'est pas trouvé dans l'inventaire.
    }
}
