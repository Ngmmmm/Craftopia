package item.bag;

import item.sprites.SuperObjet;
import java.util.ArrayList;

public class Inventaire {

    public final static ArrayList<SuperObjet> invAvatar = new ArrayList<>();
    public ArrayList<SuperObjet> invObject;

    public Inventaire() {
        invObject = new ArrayList<>();
    }

    public ArrayList<SuperObjet> getInv() {
        return invObject;
    }

    public boolean add(SuperObjet item, int quantity) {
        if (item == null) {
            return false;
        }
        for (int i = 0; i < invObject.size(); i++) {
            if (invObject.get(i).getName().equals(item.name)) {
                invObject.get(i).increaseQuantity(quantity);
                return true;
            }
        }
        invObject.add(item);
        item.increaseQuantity(quantity);
        return true;
    }

    public boolean remove(SuperObjet item, int quantity) {
        for (int i = 0; i < invObject.size(); i++) {
            if (invObject.get(i).equals(item)) {
                invObject.get(i).reduceQuantity(quantity);
                if (invObject.get(i).quantity <= 0) {
                    invObject.remove(i);
                }
            }
        }
        return true;
    }

    public int getQuantity(SuperObjet item) {
        for (int i = 0; i < invObject.size(); i++) {
            if (invObject.get(i).getName().equals(item.name)) {
                return invObject.get(i).quantity;
            }
        }
        return 0;
    }

    

    // ! Méthode pour l'inventaire de L'avatar
    public static boolean addToAvatarInv(SuperObjet item, int quantity) {
        if (item == null) {
            return false;
        }
        for (int i = 0; i < invAvatar.size(); i++) {
            if (invAvatar.get(i).getName().equals(item.name)) {
                invAvatar.get(i).increaseQuantity(quantity);
                return true;
            }
        }
        invAvatar.add(item);
        item.increaseQuantity(quantity);
        return true;
    }

    public static boolean removeFromAvatarInv(SuperObjet item, int quantity) {
        for (int i = 0; i < invAvatar.size(); i++) {
            if (invAvatar.get(i).equals(item)) {
                invAvatar.get(i).reduceQuantity(quantity);
                if (invAvatar.get(i).quantity <= 0) {
                    invAvatar.remove(i);
                }
            }
        }
        return true;
    }

    public static ArrayList<SuperObjet> getInvAvatar() {
        return invAvatar;
    }

    public static int getQuantityFromAvatarInv(SuperObjet item) {
        for (int i = 0; i < invAvatar.size(); i++) {
            if (invAvatar.get(i).getName().equals(item.name)) {
                return invAvatar.get(i).quantity;
            }
        }
        return 0;
    }
}
