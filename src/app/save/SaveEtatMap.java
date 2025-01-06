package save;

import item.machines.Recolteuse;
import item.machines.Usine;
import item.recettes.Recette;
import item.ressources.Ressource;
import item.sprites.SuperObjet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JLayeredPane;
import main.GamePanel;

public class SaveEtatMap {

    GamePanel gp;

    public SaveEtatMap(GamePanel gp) {
        this.gp = gp;
    }

    public void saveEtatMap() {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter("saveGame/saveEtatMap.txt"));
            for (int i = 0; i < GamePanel.obj.size(); i++) {
                String setterInfo = GamePanel.obj.get(i).name + " "
                        + Double.toString(GamePanel.obj.get(i).pos.getX() / GamePanel.TAILLE_BLOC) + " " +
                        Double.toString(GamePanel.obj.get(i).pos.getY() / GamePanel.TAILLE_BLOC) + " ";
                if(GamePanel.obj.get(i) instanceof Usine){
                    setterInfo = setterInfo + ((Usine)GamePanel.obj.get(i)).recetteAct.getName()  + " ";
                    setterInfo = setterInfo + ((Usine)GamePanel.obj.get(i)).stock.invObject.size() + " ";
                    for(int k = 0; k < ((Usine)GamePanel.obj.get(i)).stock.invObject.size(); k++){
                        setterInfo = setterInfo + ((Usine)GamePanel.obj.get(i)).stock.invObject.get(k).name + " " + ((Usine)GamePanel.obj.get(i)).stock.invObject.get(k).quantity + " ";
                    }
                }
                if(GamePanel.obj.get(i) instanceof Recolteuse){
                    setterInfo = setterInfo + ((Recolteuse)GamePanel.obj.get(i)).res.getName()  + " ";
                    setterInfo = setterInfo + ((Recolteuse)GamePanel.obj.get(i)).stock.invObject.size() + " ";
                    for(int k = 0; k < ((Recolteuse)GamePanel.obj.get(i)).stock.invObject.size(); k++){
                        setterInfo = setterInfo + ((Recolteuse)GamePanel.obj.get(i)).stock.invObject.get(k).name + " " + ((Recolteuse)GamePanel.obj.get(i)).stock.invObject.get(k).quantity + " ";
                    }
                }

                bw.write(setterInfo);
                bw.newLine();
            }
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEtatMap() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("saveGame/saveEtatMap.txt"));

            String setterInfo = br.readLine();
            int obj = 0;
            while (setterInfo != null) {
                String[] itemInfoArray = setterInfo.split(" ");
                SuperObjet sObjet = SuperObjet.getSuperObjet(itemInfoArray[0]);
                GamePanel.obj.add(sObjet);
                GamePanel.obj.get(obj).pos.x = (int) Double.parseDouble(itemInfoArray[1]) * GamePanel.TAILLE_BLOC;
                GamePanel.obj.get(obj).pos.y = (int) Double.parseDouble(itemInfoArray[2]) * GamePanel.TAILLE_BLOC;
                if(sObjet instanceof Usine){
                    for(int j = 0; j<Recette.getRecettes().size(); j++){
                        if(Recette.getRecettes().get(j).getName().equals(itemInfoArray[3])){
                            ((Usine)GamePanel.obj.get(obj)).recetteAct = Recette.getRecettes().get(j);
                        }
                    }
                    int nbObj = Integer.parseInt(itemInfoArray[4]);
                    for(int k = 0; k < nbObj; k++){
                        ((Usine)GamePanel.obj.get(obj)).stock.invObject.add(SuperObjet.getSuperObjet(itemInfoArray[5 + 2*k]));
                        ((Usine)GamePanel.obj.get(obj)).stock.invObject.get(k).quantity = Integer.parseInt(itemInfoArray[6 + 2*k]);
                    }
                    gp.layeredPane.add(((Usine)(sObjet)).uiUsine.getMainPanel(),JLayeredPane.PALETTE_LAYER);
                }
                if(sObjet instanceof Recolteuse){
                    SuperObjet res = SuperObjet.getSuperObjet(itemInfoArray[3]);
                    ((Recolteuse)GamePanel.obj.get(obj)).res = (Ressource)res;
                    int nbObj = Integer.parseInt(itemInfoArray[4]);
                    for(int k = 0; k < nbObj; k++){
                        ((Recolteuse)GamePanel.obj.get(obj)).stock.invObject.add(SuperObjet.getSuperObjet(itemInfoArray[5 + 2*k]));
                        ((Recolteuse)GamePanel.obj.get(obj)).stock.invObject.get(k).quantity = Integer.parseInt(itemInfoArray[6 + 2*k]);
                    }
                    gp.layeredPane.add(((Recolteuse)(sObjet)).uiRecolteuse.getMainPanel(),JLayeredPane.PALETTE_LAYER);
                }
                
                obj++;
                setterInfo = br.readLine();
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
