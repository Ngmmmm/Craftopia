import javax.swing.*;
import java.awt.*;

public class TwoScrollPanesExample {
    public static void main(String[] args) {
        // Créer une fenêtre
        JFrame frame = new JFrame("Deux JScrollPanes dans un JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Créer un JPanel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2)); // Divise en 2 colonnes

        // Première JScrollPane contenant un JTextArea
        JTextArea textArea1 = new JTextArea("Texte dans le premier JScrollPane");
        JScrollPane scrollPane1 = new JScrollPane(textArea1);

        // Deuxième JScrollPane contenant un JTextArea
        JTextArea textArea2 = new JTextArea("Texte dans le deuxième JScrollPane");
        JScrollPane scrollPane2 = new JScrollPane(textArea2);

        // Ajouter les JScrollPanes au JPanel
        panel.add(scrollPane1);
        panel.add(scrollPane2);

        // Ajouter le JPanel au JFrame
        frame.add(panel);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}
