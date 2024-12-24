package item.bag;

import item.sprites.SuperObjet;
import java.util.ArrayList;

public class Inventaire {

    public final ArrayList<SuperObjet> inv = new ArrayList<>();

    public boolean add(SuperObjet item, int quantity) {
        if (item==null) {
            return false;
        }
        for (int i = 0; i < inv.size(); i++) {
            if (inv.get(i).getName().equals(item.name)) {
                inv.get(i).increaseQuantity(quantity);
                return true;
            }
        }
        inv.add(item);
        item.increaseQuantity(quantity);
        return true;
    }

    public boolean remove(SuperObjet item, int quantity){
        for (int i = 0; i < inv.size(); i++) {
            if (inv.get(i).equals(item)) {
                inv.get(i).reduceQuantity(quantity);
                if(inv.get(i).quantity <= 0){
                    inv.remove(i);
                }
            }
        }
        return true;
    }

}
