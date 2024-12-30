package main;

import item.bag.Argent;
import item.marche.Marche;
import item.sprites.Avatar;
import item.sprites.SuperObjet;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class UiMarche {

    private static JScrollPane scrollPane;
    private static ArrayList<SuperObjet> obj = Marche.getList();
    private static JPanel mainPanel;

    public UiMarche() {

        // Panneau principal pour le JScrollPane
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        System.out.println(obj.size());
        for (int i = 0; i < obj.size(); i++) {
            mainPanel.add(createRow(obj.get(i).getName(), i, obj.get(i)));
        }
        JButton exit = new JButton("exit");
        exit.addActionListener(l->{
            GamePanel.gameState = GamePanel.playState;
        });
        mainPanel.add(exit);

        // Configure le JScrollPane
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Taille du JScrollPane
    }

    // Méthode pour créer une ligne avec des composants
    private static JPanel createRow(String productName, int index, SuperObjet item) {
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
        nameLabel.setPreferredSize(new Dimension(100, 50));
        rowPanel.add(nameLabel);

        // Ajouter le bouton "SELL"
        if(Avatar.getInv().isIn(item)){
            JButton sellButton = new JButton();
            JSpinner spinner  = new JSpinner(new SpinnerNumberModel(1,1,Avatar.getInv().getQuantityOf(item),1));
            JLabel prixVente = new JLabel(Integer.toString(Argent.getPrice(item.getProduit()))+"$");
            prixVente.setPreferredSize(new Dimension(30, 50));
            sellButton.setIcon(btnSell);
            sellButton.setBounds(50, 50, 90, 40);
            // Redimensionner le bouton "SELL"
            sellButton.setPreferredSize(new Dimension(90, 40));
            resizeButtonImages(sellButton, btnSell);
            sellButton.addActionListener(l->{
                Marche.sell(item.getProduit(),(int)spinner.getValue());
                GamePanel.editInv = true;
                rebuildMarketPanel();
                UiGame.actArgent();
            });
            rowPanel.add(prixVente);
            rowPanel.add(spinner);
            rowPanel.add(sellButton);
        }else{
            JButton cannotSellButton = new JButton();
            JSpinner spinner  = new JSpinner(new SpinnerNumberModel(1,1,1,1));
            JLabel prixVente = new JLabel(Integer.toString(Argent.getPrice(item.getProduit()))+"$");
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
        if(Argent.getMonnaiePlayer() > item.getPrix()){
            JButton buyButton = new JButton();
            JSpinner spinner  = new JSpinner(new SpinnerNumberModel(1,1,Argent.getMonnaiePlayer()/item.getPrix(),1));
            JLabel prixAchat = new JLabel(Integer.toString(item.getPrix())+"$");
            prixAchat.setPreferredSize(new Dimension(30, 50));
            buyButton.setIcon(btnBuy);
            // Redimensionner le bouton "BUY"
            buyButton.setPreferredSize(new Dimension(90, 40));
            buyButton.setBounds(50, 50, 90, 40);
            resizeButtonImages(buyButton, btnBuy);
            buyButton.addActionListener(l->{
                Marche.buy(item.getProduit(), (int)spinner.getValue());
                GamePanel.editInv = true;
                rebuildMarketPanel();
                UiGame.actArgent();
            });
            rowPanel.add(prixAchat);
            rowPanel.add(spinner);
            rowPanel.add(buyButton);
        }else{
            JButton cannotbuyButton = new JButton();
            JSpinner spinner  = new JSpinner(new SpinnerNumberModel(1,1,1,1));
            JLabel prixAchat = new JLabel(Integer.toString(item.getPrix())+"$");
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
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Redimensionne l'image d'un bouton pour qu'elle corresponde à sa taille
     * actuelle.
     * 
     * @param button Le bouton à redimensionner
     * @param icon   L'icône à redimensionner
     */
    private static void resizeButtonImages(JButton button, Icon icon) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        // Redimensionner l'icône en fonction de la taille du bouton
        ImageIcon resizedIcon = new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(buttonWidth, buttonHeight,
                java.awt.Image.SCALE_SMOOTH));
        button.setIcon(resizedIcon);
    }

    public void resizeContent() {
        // TODO : Réajuster la position des composants par rapport à la taille de la
        // fenêtre
    }


    public static void rebuildMarketPanel() {
        mainPanel = (JPanel) scrollPane.getViewport().getView();
        mainPanel.removeAll();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Empile verticalement

        // Ajoute des lignes au panneau principal (chaque ligne contient IMG, Nom, SELL,
        // BUY)
        System.out.println(obj.size());
        for (int i = 0; i < obj.size(); i++) {
            System.out.println(obj.get(i).getName());
            mainPanel.add(createRow(obj.get(i).getName(), i, obj.get(i)));
        }
        JButton exit = new JButton("exit");
        exit.addActionListener(l->{
            GamePanel.gameState = GamePanel.playState;
        });
        mainPanel.add(exit);
    }
    
}
