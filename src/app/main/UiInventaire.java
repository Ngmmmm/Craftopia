package main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class UiInventaire {

    JScrollPane scrlpane;
    GamePanel gp;


    public UiInventaire(GamePanel gp) {
        this.gp = gp;
    }

    

    public void inner() {

        // to storing the string values
        String ctg[] = { "CCNA", "Design Patterns", "Java",
                "Python", "Algorithms",
                "JAVASCRIPT", "Operating System", "CS Subject",
                "Data Structure", "PHP language", "Concurrency",
                "C #" };

        // Declaration of objects of the JPanel class.


        // method to set the Layout of the JFrame.

        // Adding header labels
        gp.add(new JLabel("Category"));
        gp.add(new JLabel("Buy"));
        gp.add(new JLabel("Sell"));

        // for loop
        for (int r = 0; r < 12; r++) {
            JLabel act = new JLabel(ctg[r]);
            // adding the new JLabel for category
            act.setBounds(30,100*r,500,500);
            gp.add(act);


            // creating and adding the Buy button
            JButton buyButton = new JButton("BUY");
            gp.add(buyButton);

            // creating and adding the Sell button
            JButton sellButton = new JButton("SELL");
            gp.add(sellButton);
        }

        // Declaring the objects of the JScrollPane class.

        // for getting the content pane
    }


    public void applyEffect(){
        inner();
    }

    
}
