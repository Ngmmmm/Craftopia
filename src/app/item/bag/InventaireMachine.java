package item.bag;

import item.machines.Machine;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.util.ArrayList;

public class InventaireMachine extends Inventaire {
    public ArrayList<SuperObjet> invObject;

    public InventaireMachine(Machine machine) {

        invObject = new ArrayList<SuperObjet>();
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
            Ressource ressource = (Ressource) SuperObjet.getSuperObjet(item.getName());
            if (invObject.get(i).getName().equals(ressource.getName())) {

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
}
