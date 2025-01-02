package save;

import item.sprites.SuperObjet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
                        + Double.toString(GamePanel.obj.get(i).pos.getX() / gp.TAILLE_BLOC) + " " +
                        Double.toString(GamePanel.obj.get(i).pos.getY() / gp.TAILLE_BLOC);
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

                GamePanel.obj.add(SuperObjet.getSuperObjet(itemInfoArray[0]));
                GamePanel.obj.get(obj).pos.x = (int) Double.parseDouble(itemInfoArray[1]) * gp.TAILLE_BLOC;
                GamePanel.obj.get(obj).pos.y = (int) Double.parseDouble(itemInfoArray[2]) * gp.TAILLE_BLOC;
                obj++;

                setterInfo = br.readLine();
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
