package item.bag;

import item.sprites.SuperObjet;
import java.util.ArrayList;

public class Inventaire {

    public final ArrayList<Case<SuperObjet>> inv = new ArrayList<>();

    public boolean add(SuperObjet item, int quantity) {
        for (int i = 0; i < inv.size(); i++) {
            if (inv.get(i).getText().equals(item.name)) {
                inv.get(i).getItem().increaseQuantity(quantity);
                return true;
            }
        }
        Case<SuperObjet> newItem = new Case<SuperObjet>();
        newItem.setItem(item);
        inv.add(newItem);
        inv.get(inv.size() - 1).getItem().increaseQuantity(quantity);
        inv.get(inv.size() - 1).setText(item.name);
        return true;
    }

    public boolean remove(SuperObjet item, int quantity){
        for (int i = 0; i < inv.size(); i++) {
            if (inv.get(i).getText().equals(item.name)) {
                inv.get(i).getItem().reduceQuantity(quantity);
                if(inv.get(i).getItem().quantity < 0){
                    inv.remove(i);
                }
            }
        }
        return true;
    }

}
