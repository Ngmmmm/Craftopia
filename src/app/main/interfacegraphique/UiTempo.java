
package main.interfacegraphique;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import main.GamePanel;

public class UiTempo extends Ui {
    private static JProgressBar progressBar;
    private JPanel panel;
    private static int time;

    public UiTempo(GamePanel gamePanel, int displayTime) {
        super(gamePanel);
        time = displayTime;
        progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension(400, 30));
        progressBar.setStringPainted(true);

        panel = new JPanel();
        panel.add(progressBar);
        panel.setBounds(
                (gamePanel.getWidth() - panel.getPreferredSize().width) / 2,
                gamePanel.ui.menuPanel.menuBar.getY() + gamePanel.ui.menuPanel.menuBar.getHeight(),
                panel.getPreferredSize().width,
                panel.getPreferredSize().height);
        gamePanel.add(panel);

        fill();
    }

    public static void fill() {
        int i = 0;
        try {
            while (i <= time) {
                // fill the menu bar
                progressBar.setValue(i + 10);

                // delay the thread
                Thread.sleep(1000);
                i += 20;
                progressBar.repaint();
            }
        } catch (Exception e) {
        }
    }

    public void setVisible(boolean visible) {
        panel.setVisible(visible);
    }

    public void remove() {
        gamePanel.remove(panel);
    }

    
}
