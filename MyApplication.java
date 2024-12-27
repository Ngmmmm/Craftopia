import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyApplication {
    public static void main(String[] args) {
        // Créer le JFrame
        JFrame frame = new JFrame("Example JScrollPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Créer un JPanel et l'ajouter à un JScrollPane
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Ajouter plusieurs JLabels au JPanel pour remplir le JScrollPane
        for (int i = 1; i <= 50; i++) {
            panel.add(new JLabel("Label " + i));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(200, 150)); // Taille du JScrollPane

        // Créer un bouton pour fermer le JScrollPane
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(false); // Masque le JScrollPane
            }
        });

        // Ajouter le bouton à un coin du JScrollPane
        JPanel cornerPanel = new JPanel(new BorderLayout());
        cornerPanel.add(closeButton, BorderLayout.CENTER);
        scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, cornerPanel);

        // Ajouter le JScrollPane au centre du JFrame avec des marges
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(scrollPane);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Affichage de la fenêtre
        frame.setVisible(true);
    }
}