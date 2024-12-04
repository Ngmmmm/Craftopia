package bag;

import java.util.ArrayList;
import ressources.Objet;

public class Case {

    private ArrayList<Objet> objet = new ArrayList<>();
    private String name;

    public ArrayList<Objet> getObjet(){
        return this.objet;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void add(Objet obj){
        this.objet.add(obj);
    }
    
    
    
}
