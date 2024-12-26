import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollPaneDemo1 extends JFrame {

    // Declaring the objects of the JScrollPane class
    JScrollPane scrlpane;

    // Constructor of the ScrollPaneDemo1 class
    public ScrollPaneDemo1() {

        // using to invoke the super class methods and variables
        super("JScrollPane Buy/Sell Display");

        // Method to determine the size of the JFrame.
        setSize(310, 210);

        // Method to set the Default terminating operation of the JFrame.
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inner(); // invoking the method inner

        // Method for setting the visibility of the JFrame.
        setVisible(true);
    }

    // method inner
    public void inner() {

        // to storing the string values
        String ctg[] = { "CCNA", "Design Patterns", "Java",
                "Python", "Algorithms",
                "JAVASCRIPT", "Operating System", "CS Subject",
                "Data Structure", "PHP language", "Concurrency",
                "C #" };

        // Declaration of objects of the JPanel class.
        JPanel panel = new JPanel();

        // method to determine the size of the JFrame.
        panel.setSize(610, 410);

        // method to set the Layout of the JFrame.
        panel.setLayout(new GridLayout(13, 3, 10, 0));

        // Adding header labels
        panel.add(new JLabel("Category"));
        panel.add(new JLabel("Buy"));
        panel.add(new JLabel("Sell"));

        // for loop
        for (int r = 0; r < 12; r++) {

            // adding the new JLabel for category
            panel.add(new JLabel(ctg[r]));

            // creating and adding the Buy button
            JButton buyButton = new JButton("BUY");
            panel.add(buyButton);

            // creating and adding the Sell button
            JButton sellButton = new JButton("SELL");
            panel.add(sellButton);
        }

        // Declaring the objects of the JScrollPane class.
        scrlpane = new JScrollPane(panel);

        // for getting the content pane
        getContentPane().add(scrlpane, BorderLayout.CENTER);
    }

    // main method
    public static void main(String argvs[]) {
        new ScrollPaneDemo1();
    }
}