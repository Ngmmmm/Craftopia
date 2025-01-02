package item.bag;

import item.sprites.SuperObjet;
import java.util.ArrayList;

public class Inventaire {

    public final static ArrayList<SuperObjet> invAvatar = new ArrayList<>();

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
            if (invAvatar.get(i).getName().equals(item.name)) {
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

    public static boolean isIn(SuperObjet item) {
        if (item == null) {
            return false;
        }
        for (int i = 0; i < invAvatar.size(); i++) {
            if (invAvatar.get(i).getName().equals(item.name)) {
                return true;
            }
        }
        return false;
    }
}
