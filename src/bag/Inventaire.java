package bag;
import ressources.Objet;

public class Inventaire {

    private final Case[][] stock = new Case[3][5];

    public boolean add (Objet objet){
        for(int i = 0; i < stock.length; i++){
            for(int j = 0; j < stock[i].length; j++){
                if(!stock[i][j].getObjet().isEmpty()){
                    if (stock[i][j].getName().equals(objet.name)){
                        stock[i][j].getObjet().add(objet);
                        return true; 
                    }
                }else if(stock[i][j].getObjet().isEmpty()){
                    stock[i][j].getObjet().add(objet);
                    stock[i][j].setName(objet.name);
                    return true;
                }
            }
        }
        return false;
    }
    
}
