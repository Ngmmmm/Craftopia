package bag;
import java.util.ArrayList;
import ressources.Objet;

public class Inventaire {

    private final ArrayList<Case> inv  = new ArrayList<>();

    public boolean add(Objet obj){
        for (int i = 0; i<inv.size();i++){
            if(inv.get(i).getName().equals(obj.getName())){
                inv.get(i).add(obj);
                return true;
            }
        }
        inv.add(new Case());
        inv.get(inv.size()-1).add(obj);
        inv.get(inv.size()-1).setName(obj.getName());
        return true;
    }
   
    
}
