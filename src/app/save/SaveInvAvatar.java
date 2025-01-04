package save;

import item.bag.Argent;
import item.bag.Inventaire;
import item.sprites.SuperObjet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import main.GamePanel;

public class SaveInvAvatar {

    GamePanel gp;

    public SaveInvAvatar(GamePanel gp) {
        this.gp = gp;

    }

    public void saveInvAvatar() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("saveGame/saveInvAvatar.txt"));
            String argentInfo = Integer.toString(Argent.getMonnaiePlayer());
            bw.write(argentInfo);
            bw.newLine();
            for (int i = 0; i < Inventaire.getInvAvatar().size(); i++) {
                String itemInfo = Inventaire.getInvAvatar().get(i).name + " "
                        + Integer.toString(Inventaire.getInvAvatar().get(i).quantity);
                bw.write(itemInfo);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadInvAvatar() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("saveGame/saveInvAvatar.txt"));
            Argent.increaseMoney(Integer.parseInt(br.readLine()));
            String itemInfo = br.readLine();
            while (itemInfo != null) {
                String[] itemInfoArray = itemInfo.split(" ");
                Inventaire.addToAvatarInv(SuperObjet.getSuperObjet(itemInfoArray[0]),
                        Integer.parseInt(itemInfoArray[1]));
                itemInfo = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
