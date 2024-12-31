package save;

import item.sprites.Avatar;
import item.sprites.SuperObjet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import main.GamePanel;

public class SaveInvAvatar {
    

    GamePanel gp;

    public SaveInvAvatar(GamePanel gp){
        this.gp = gp;

    }

    public void saveInvAvatar(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("saveGame/saveInvAvatar.txt"));

            for(int i = 0; i<Avatar.inv.inv.size();i++){
                String itemInfo = Avatar.inv.inv.get(i).name + " " +Integer.toString(Avatar.inv.inv.get(i).quantity);
                bw.write(itemInfo);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadInvAvatar(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("saveGame/saveInvAvatar.txt"));

            String itemInfo = br.readLine();
            while(itemInfo != null){
                String[] itemInfoArray = itemInfo.split(" ");
                Avatar.inv.add(SuperObjet.getSuperObjet(itemInfoArray[0]),Integer.parseInt(itemInfoArray[1]));
                itemInfo = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
