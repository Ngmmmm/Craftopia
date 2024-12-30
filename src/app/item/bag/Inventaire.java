package item.bag;

import item.sprites.SuperObjet;
import java.util.ArrayList;

public class Inventaire {

    public final static ArrayList<SuperObjet> inv = new ArrayList<>();
    
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
                if (inv.get(i).getName().equals(item.name)){
                    inv.get(i).reduceQuantity(quantity);
                    if(inv.get(i).quantity <= 0){
                        inv.remove(i);
                    }
                }
            }
            return true;
        }
        public static ArrayList<SuperObjet> getInv() {
            return inv;
        }

        public boolean isIn(SuperObjet item){
            if (item==null) {
                return false;
            }
            for (int i = 0; i < inv.size(); i++) {
                if (inv.get(i).getName().equals(item.name)) {
                    return true;
                }
            }
            return false;
        }

        public int getQuantityOf(SuperObjet item){
            if (item==null) {
                return 0;
            }
            for (int i = 0; i < inv.size(); i++) {
                if (inv.get(i).getName().equals(item.name)) {
                    return inv.get(i).quantity;
                }
            }
            return 0;

        }
}
