package save;

import main.GamePanel;

public class SaveInventaireMachine {

    GamePanel gp;

    public SaveInventaireMachine(GamePanel gp) {
        this.gp = gp;

    }

    // public void saveInvAvatar() {
    //     try {
    //         BufferedWriter bw = new BufferedWriter(new FileWriter("saveGame/saveInvAvatar.txt"));
    //         String iteminfo ="";
    //         for (int i = 0; i < gp.obj.size(); i++) {
    //             if(gp.obj.get(i) instanceof Usine){
    //                 iteminfo = iteminfo + i + " ";
    //                 iteminfo = iteminfo + (Usine)(gp.obj.get(i)).getRecette().getName() + " ";
                    
                    
    //             }
    //             bw.write(itemInfo);
    //             bw.newLine();
    //         }
    //         bw.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    
}
