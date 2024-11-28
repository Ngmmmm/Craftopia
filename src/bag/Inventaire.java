package bag;
import java.util.ArrayList;
import ressources.Objet;

public class Inventaire {

    public static ArrayList<Objet>[][] stock;
    public Inventaire() {
        stock = new ArrayList[5][5];
    }

    public boolean add (Objet objet){
        for(int i = 0; i < stock.length; i++){
            for(int j = 0; j < stock[i].length; j++){
                if(stock[i][j] != null){
                    if (stock[i][j].get(0).name.equals(objet.name)){
                        stock[i][j].add(objet);
                        return true; 
                    }
                }else if(stock[i][j] == null){
                    stock[i][j] = new ArrayList<Objet>();
                    stock[i][j].add(objet);
                    return true;
                }
            }
        }
        return false;
    }
    
}
