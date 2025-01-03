package main;

import item.bag.Argent;
import item.bag.Inventaire;
import item.marche.Marche;
import item.sprites.SuperObjet;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class UiMarche extends Ui {
    JPanel mainPanel;
    private JScrollPane scrollPane;
    private ArrayList<SuperObjet> obj = Marche.getList();

    public UiMarche(GamePanel gamePanel) {
        super(gamePanel);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement
        // Panneau principal pour le JScrollPane
        JPanel invPanel = new JPanel();
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        System.out.println(obj.size());
        for (int i = 0; i < obj.size(); i++) {
            System.out.println(obj.get(i).getName());
            invPanel.add(createRow(obj.get(i).getName(), i));
        }

        // Configure le JScrollPane
        scrollPane = new JScrollPane(invPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Taille du JScrollPane

        mainPanel.add(scrollPane);
        JButton exit = new JButton("exit");
        exit.addActionListener(l -> {
            Ui.gameState = Ui.playState;
            this.setVisible(false);
        });
        exit.setAlignmentX(JButton.CENTER_ALIGNMENT); // Centre le bouton horizontalement
        mainPanel.add(exit);
        gamePanel.layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        mainPanel.setVisible(false);

    }

    // Méthode pour créer une ligne avec des composants
    private JPanel createRow(String productName, int index) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Layout horizontal
        rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ImageIcon image = new ImageIcon(obj.get(index).getImage());
        ImageIcon btnSell = new ImageIcon("./img/boutons/SellButton.png");
        ImageIcon btnBuy = new ImageIcon("./img/boutons/BuyButton.png");
        ImageIcon btncannotBuy = new ImageIcon("./img/boutons/cannotBuyButton.png");
        ImageIcon btncannotSell = new ImageIcon("./img/boutons/cannotSellButton.png");

        // Ajouter une image (placeholder ici)
        JLabel imageLabel = new JLabel();

        imageLabel.setIcon(image); // Remplacer par une image
        imageLabel.setPreferredSize(new Dimension(50, 50));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        rowPanel.add(imageLabel);

        // Ajouter le nom du produit
        JLabel nameLabel = new JLabel(productName);
        nameLabel.setPreferredSize(new Dimension(150, 50));
        rowPanel.add(nameLabel);

        SuperObjet item = obj.get(index);

        // // Ajouter le bouton "SELL"
        // JButton sellButton = new JButton();
        // sellButton.setIcon(btnSell);
        // sellButton.setBounds(50, 50, 90, 40);
        // // Redimensionner le bouton "SELL"
        // sellButton.setPreferredSize(new Dimension(90, 40));
        // resizeButtonImages(sellButton, btnSell);
        // rowPanel.add(sellButton);

        // // Ajouter le bouton "BUY"
        // JButton buyButton = new JButton();
        // buyButton.setIcon(btnBuy);
        // // Redimensionner le bouton "BUY"
        // buyButton.setPreferredSize(new Dimension(90, 40));
        // buyButton.setBounds(50, 50, 90, 40);
        // resizeButtonImages(buyButton, btnBuy);
        // rowPanel.add(buyButton);

        // Ajouter le bouton "SELL"
        if (Inventaire.isIn(item)) {
            JButton sellButton = new JButton();
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, Inventaire.getQuantityFromAvatarInv(item), 1));
            JLabel prixVente = new JLabel(Integer.toString(Argent.getPrice(item.getProduit())) + "$");
            prixVente.setPreferredSize(new Dimension(30, 50));
            sellButton.setIcon(btnSell);
            sellButton.setBounds(50, 50, 90, 40);
            // Redimensionner le bouton "SELL"
            sellButton.setPreferredSize(new Dimension(90, 40));
            resizeButtonImages(sellButton, btnSell);
            sellButton.addActionListener(l -> {
                Marche.sell(item.getProduit(), (int) spinner.getValue());
                GamePanel.editInv = true;
                rebuildMarketPanel();

            });
            rowPanel.add(prixVente);
            rowPanel.add(spinner);
            rowPanel.add(sellButton);
        } else {
            JButton cannotSellButton = new JButton();
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 1, 1));
            JLabel prixVente = new JLabel(Integer.toString(Argent.getPrice(item.getProduit())) + "$");
            prixVente.setPreferredSize(new Dimension(30, 50));
            cannotSellButton.setIcon(btncannotSell);
            // Redimensionner le bouton "BUY"
            cannotSellButton.setPreferredSize(new Dimension(90, 40));
            cannotSellButton.setBounds(50, 50, 90, 40);
            resizeButtonImages(cannotSellButton, btncannotSell);
            rowPanel.add(prixVente);
            rowPanel.add(spinner);
            rowPanel.add(cannotSellButton);
        }

        // Ajouter le bouton "BUY"
        if (Argent.getMonnaiePlayer() > item.getPrix()) {
            JButton buyButton = new JButton();
            JSpinner spinner = new JSpinner(
                    new SpinnerNumberModel(1, 1, Argent.getMonnaiePlayer() / item.getPrix(), 1));
            JLabel prixAchat = new JLabel(Integer.toString(item.getPrix()) + "$");
            prixAchat.setPreferredSize(new Dimension(30, 50));
            buyButton.setIcon(btnBuy);
            // Redimensionner le bouton "BUY"
            buyButton.setPreferredSize(new Dimension(90, 40));
            buyButton.setBounds(50, 50, 90, 40);
            resizeButtonImages(buyButton, btnBuy);
            buyButton.addActionListener(l -> {
                Marche.buy(item.getProduit(), (int) spinner.getValue());
                GamePanel.editInv = true;
                rebuildMarketPanel();

            });
            rowPanel.add(prixAchat);
            rowPanel.add(spinner);
            rowPanel.add(buyButton);
        } else {
            JButton cannotbuyButton = new JButton();
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 1, 1));
            JLabel prixAchat = new JLabel(Integer.toString(item.getPrix()) + "$");
            prixAchat.setPreferredSize(new Dimension(30, 50));
            cannotbuyButton.setIcon(btncannotBuy);
            // Redimensionner le bouton "BUY"
            cannotbuyButton.setPreferredSize(new Dimension(90, 40));
            cannotbuyButton.setBounds(50, 50, 90, 40);
            resizeButtonImages(cannotbuyButton, btncannotBuy);
            rowPanel.add(prixAchat);
            rowPanel.add(spinner);
            rowPanel.add(cannotbuyButton);
        }
        return rowPanel;
    }

    // Getter pour récupérer le JScrollPane configuré
    public JPanel getJPanel() {
        return mainPanel;
    }

    public void setVisible(boolean visible) {
        mainPanel.setVisible(visible);
    }

    // TODO : à vérifier
    public void rebuildMarketPanel() {
        JPanel invPanel = (JPanel) scrollPane.getViewport().getView();
        invPanel.removeAll();
        for (int i = 0; i < obj.size(); i++) {
            invPanel.add(createRow(obj.get(i).getName(), i));
        }
        invPanel.revalidate();
        invPanel.repaint();
    }
}
