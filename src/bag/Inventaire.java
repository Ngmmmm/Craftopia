package bag;

import java.util.ArrayList;
import ressources.Ressource;

public class Inventaire {

    public final ArrayList<Case<Ressource>> inv = new ArrayList<>();

    public boolean add(Ressource ress, int quantity) {
        for (int i = 0; i < inv.size(); i++) {
            if (inv.get(i).getText().equals(ress.getName())) {
                inv.get(i).getItem().increaseQuantity(ress, quantity);
                return true;
            }
        }
        inv.add(new Case<Ressource>());
        inv.get(inv.size() - 1).getItem().increaseQuantity(ress, quantity);
        inv.get(inv.size() - 1).setText(ress.getName());
        return true;
    }

}
