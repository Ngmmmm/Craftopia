package bag;

import ressources.Ressource;

// ? Proposition avec code générique

public class Case<E extends Ressource> {
    private E item;
    private String name;

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Case() {
        this.item = null;
    }

    public void increaseQuantity(E item, int quantity) {
        item.setQuantity(item.getQuantity() + quantity);
    }

}
