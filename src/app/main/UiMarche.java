package main;

import javax.swing.*;
import java.awt.*;

public class UiMarche {

    private GamePanel gp;
    private JScrollPane scrlpane;
    private JPanel panel;

    public UiMarche(GamePanel gp) {
        this.gp = gp;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        scrlpane = new JScrollPane(panel);
        scrlpane.setPreferredSize(new Dimension(200, 200));
        scrlpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrlpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrlpane.setBounds(0, 0, 200, 200);
        scrlpane.setVisible(true);
        gp.marchePanel.add(scrlpane);
    }
}