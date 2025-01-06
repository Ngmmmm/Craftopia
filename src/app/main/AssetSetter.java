package main;

import item.bag.Argent;
import item.marche.Marche;
import item.ressources.Bois;
import item.ressources.Fer;
import item.ressources.Feuille;
import item.ressources.Pierre;
import item.sprites.SuperObjet;
import tile.TileManager;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * La classe AssetSetter est responsable de l'initialisation des objets (items
 * et ressources)
 * dans le jeu, en fonction de l'état sauvegardé ou de manière aléatoire.
 */
public class AssetSetter {

    // Référence au panneau de jeu principal
    GamePanel gp;

    // Liste des points utilisés pour éviter la superposition d'objets
    ArrayList<Point> listPoint = new ArrayList<Point>();

    /**
     * Constructeur de la classe AssetSetter.
     * 
     * @param gp Référence au GamePanel qui est le panneau principal du jeu.
     */
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Initialise tous les objets (items, ressources, etc.) en fonction de l'état
     * sauvegardé.
     * Réduit la monnaie du joueur à zéro et charge l'état de la carte et
     * l'inventaire.
     */
    public void setAllItemsBySave() {
        Argent.decreaseMoney((Argent.getMonnaiePlayer())); // Réduction de la monnaie
        GamePanel.obj = new ArrayList<SuperObjet>(); // Réinitialisation de la liste des objets
        gp.saveEtatMap.loadEtatMap(); // Chargement de l'état de la carte
        gp.saveInvAvatar.loadInvAvatar(); // Chargement de l'inventaire du joueur
    }

    /**
     * Initialise tous les objets de manière aléatoire. Les objets sont placés
     * autour du point de départ.
     * Les ressources et le marché sont ajoutés dans des positions aléatoires, tout
     * en évitant les superpositions.
     */
    public void setAllItemsByRandom() {
        Argent.decreaseMoney((Argent.getMonnaiePlayer())); // Réduction de la monnaie
        GamePanel.obj = new ArrayList<SuperObjet>(); // Réinitialisation de la liste des objets
        listPoint = new ArrayList<Point>(); // Réinitialisation de la liste des points
        // Calcul du point de départ en fonction des coordonnées du spawn
        Point spawnPoint = new Point(TileManager.spawnPoint.x / GamePanel.TAILLE_BLOC,
                TileManager.spawnPoint.y / GamePanel.TAILLE_BLOC);
        listPoint.add(spawnPoint); // Ajout du point de spawn
        // Ajout de points voisins pour les objets autour du spawn
        listPoint.add(new Point((spawnPoint.x) + 1, (spawnPoint.y)));
        listPoint.add(new Point((spawnPoint.x) - 1, (spawnPoint.y)));
        listPoint.add(new Point((spawnPoint.x) + 1, (spawnPoint.y) + 1));
        listPoint.add(new Point((spawnPoint.x) - 1, (spawnPoint.y) + 1));
        listPoint.add(new Point((spawnPoint.x) + 1, (spawnPoint.y) - 1));
        listPoint.add(new Point((spawnPoint.x) - 1, (spawnPoint.y) - 1));
        listPoint.add(new Point((spawnPoint.x), (spawnPoint.y) + 1));
        listPoint.add(new Point((spawnPoint.x), (spawnPoint.y) - 1));
        setMarcheRandom(); // Placement du marché aléatoire
        setRessourceRandom(); // Placement des ressources aléatoires
    }

    /**
     * Place des ressources (Feuille, Fer, Bois, Pierre) à des positions aléatoires
     * sur la carte.
     * Les positions sont vérifiées pour éviter la superposition d'objets.
     */
    public void setRessourceRandom() {
        Random randomNumber = new Random();
        int pointX = randomNumber.nextInt(9, 42); // Position X aléatoire
        int pointY = randomNumber.nextInt(8, 41); // Position Y aléatoire
        // Boucle pour placer différentes ressources
        for (int i = 0; i < 7; i++) {
            GamePanel.obj.add(new Feuille()); // Ajout d'une ressource Feuille
            Point p = new Point(pointX, pointY);
            // Vérification de la position pour éviter les superpositions
            while (isInList(p)) {
                pointX = randomNumber.nextInt(9, 42);
                pointY = randomNumber.nextInt(8, 41);
                p = new Point(pointX, pointY);
            }
            listPoint.add(p); // Ajout de la position dans la liste
            GamePanel.obj.get(GamePanel.obj.size() - 1).pos.x = p.x * GamePanel.TAILLE_BLOC;
            GamePanel.obj.get(GamePanel.obj.size() - 1).pos.y = p.y * GamePanel.TAILLE_BLOC;

            // Répétition pour les autres ressources (Fer, Bois, Pierre)
            GamePanel.obj.add(new Fer());
            pointX = randomNumber.nextInt(9, 42);
            pointY = randomNumber.nextInt(8, 41);
            p = new Point(pointX, pointY);
            while (isInList(p)) {
                pointX = randomNumber.nextInt(9, 42);
                pointY = randomNumber.nextInt(8, 41);
                p = new Point(pointX, pointY);
            }
            listPoint.add(p);
            GamePanel.obj.get(GamePanel.obj.size() - 1).pos.x = p.x * GamePanel.TAILLE_BLOC;
            GamePanel.obj.get(GamePanel.obj.size() - 1).pos.y = p.y * GamePanel.TAILLE_BLOC;

            GamePanel.obj.add(new Bois());
            pointX = randomNumber.nextInt(9, 42);
            pointY = randomNumber.nextInt(8, 41);
            p = new Point(pointX, pointY);
            while (isInList(p)) {
                pointX = randomNumber.nextInt(9, 42);
                pointY = randomNumber.nextInt(8, 41);
                p = new Point(pointX, pointY);
            }
            listPoint.add(p);
            GamePanel.obj.get(GamePanel.obj.size() - 1).pos.x = p.x * GamePanel.TAILLE_BLOC;
            GamePanel.obj.get(GamePanel.obj.size() - 1).pos.y = p.y * GamePanel.TAILLE_BLOC;

            GamePanel.obj.add(new Pierre());
            pointX = randomNumber.nextInt(9, 42);
            pointY = randomNumber.nextInt(8, 41);
            p = new Point(pointX, pointY);
            while (isInList(p)) {
                pointX = randomNumber.nextInt(9, 42);
                pointY = randomNumber.nextInt(8, 41);
                p = new Point(pointX, pointY);
            }
            listPoint.add(p);
            GamePanel.obj.get(GamePanel.obj.size() - 1).pos.x = p.x * GamePanel.TAILLE_BLOC;
            GamePanel.obj.get(GamePanel.obj.size() - 1).pos.y = p.y * GamePanel.TAILLE_BLOC;
        }
    }

    /**
     * Place un marché à une position aléatoire sur la carte, tout en évitant la
     * superposition d'objets.
     * Des points voisins sont également ajoutés pour diversifier les objets autour
     * du marché.
     */
    public void setMarcheRandom() {
        Random randomNumber = new Random();
        int pointX;
        int pointY;
        int newRandom = randomNumber.nextInt(1, 3);
        if (newRandom == 1) {
            newRandom = randomNumber.nextInt(1, 3);
            if (newRandom == 1) {
                pointX = 9;
                pointY = randomNumber.nextInt(8, 41);
            } else {
                pointX = 42;
                pointY = randomNumber.nextInt(9, 41);
            }
        } else {
            newRandom = randomNumber.nextInt(1, 3);
            if (newRandom == 1) {
                pointX = randomNumber.nextInt(9, 42);
                pointY = 8;
            } else {
                pointX = randomNumber.nextInt(9, 42);
                pointY = 41;
            }
        }
        // Création d'un point pour le marché et ajout de ses voisins
        Point pMarche = new Point(pointX, pointY);
        listPoint.add(pMarche);
        GamePanel.obj.add(new Marche());
        GamePanel.obj.get(GamePanel.obj.size() - 1).pos.x = pMarche.x * GamePanel.TAILLE_BLOC;
        GamePanel.obj.get(GamePanel.obj.size() - 1).pos.y = pMarche.y * GamePanel.TAILLE_BLOC;

        // Ajout de points voisins pour le marché
        listPoint.add(new Point((pMarche.x) + 1, (pMarche.y)));
        listPoint.add(new Point((pMarche.x) - 1, (pMarche.y)));
        listPoint.add(new Point((pMarche.x) + 1, (pMarche.y) + 1));
        listPoint.add(new Point((pMarche.x) - 1, (pMarche.y) + 1));
        listPoint.add(new Point((pMarche.x) + 1, (pMarche.y) - 1));
        listPoint.add(new Point((pMarche.x) - 1, (pMarche.y) - 1));
        listPoint.add(new Point((pMarche.x), (pMarche.y) + 1));
        listPoint.add(new Point((pMarche.x), (pMarche.y) - 1));
    }

    /**
     * Vérifie si un point donné est déjà présent dans la liste des points.
     * 
     * @param p Le point à vérifier.
     * @return true si le point est dans la liste, false sinon.
     */
    public boolean isInList(Point p) {
        for (int i = 0; i < listPoint.size(); i++) {
            if (p.x == listPoint.get(i).x && p.y == listPoint.get(i).y) {
                return true; // Le point est déjà dans la liste
            }
        }
        return false; // Le point n'est pas dans la liste
    }
}
