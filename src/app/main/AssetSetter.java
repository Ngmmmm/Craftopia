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


public class AssetSetter {

    GamePanel gp;
    ArrayList<Point> listPoint = new ArrayList<Point>();

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setAllItemsBySave() {
        Argent.decreaseMoney((Argent.getMonnaiePlayer()));
        GamePanel.obj = new ArrayList<SuperObjet>();
        gp.saveEtatMap.loadEtatMap();
        gp.saveInvAvatar.loadInvAvatar();
    }
    public void setAllItemsByRandom() {
        Argent.decreaseMoney((Argent.getMonnaiePlayer()));
        Argent.increaseMoney(1000);
        GamePanel.obj = new ArrayList<SuperObjet>();
        listPoint = new ArrayList<Point>();
        listPoint.add(new Point(TileManager.spawnPoint.x/gp.TAILLE_BLOC, TileManager.spawnPoint.y/gp.TAILLE_BLOC));
        setMarcheRandom();
        setRessourceRandom();
    }

    public void setRessourceRandom() {

        Random randomNumber = new Random();
        int pointX = randomNumber.nextInt(9,42);
        int pointY = randomNumber.nextInt(8,41);
        for(int i = 0; i<7;i++){
            GamePanel.obj.add(new Feuille());
            Point p = new Point(pointX,pointY);
            while (isInList(p)) {
                pointX = randomNumber.nextInt(9,42);
                pointY = randomNumber.nextInt(8,41);
                p = new Point(pointX,pointY);
            }
            listPoint.add(p);
            GamePanel.obj.get(GamePanel.obj.size() -1).pos.x = p.x * gp.TAILLE_BLOC;
            GamePanel.obj.get(GamePanel.obj.size() -1).pos.y = p.y * gp.TAILLE_BLOC;

            GamePanel.obj.add(new Fer());
            pointX = randomNumber.nextInt(9,42);
            pointY = randomNumber.nextInt(8,41);
            p = new Point(pointX,pointY);
            while (isInList(p)) {
                pointX = randomNumber.nextInt(9,42);
                pointY = randomNumber.nextInt(8,41);
                p = new Point(pointX,pointY);
            }
            listPoint.add(p);
            GamePanel.obj.get(GamePanel.obj.size() -1).pos.x = p.x * gp.TAILLE_BLOC;
            GamePanel.obj.get(GamePanel.obj.size() -1).pos.y = p.y * gp.TAILLE_BLOC;

            GamePanel.obj.add(new Bois());
            pointX = randomNumber.nextInt(9,42);
            pointY = randomNumber.nextInt(8,41);
            p = new Point(pointX,pointY);
            while (isInList(p)) {
                pointX = randomNumber.nextInt(9,42);
                pointY = randomNumber.nextInt(8,41);
                p = new Point(pointX,pointY);
            }
            listPoint.add(p);
            GamePanel.obj.get(GamePanel.obj.size() -1).pos.x = p.x * gp.TAILLE_BLOC;
            GamePanel.obj.get(GamePanel.obj.size() -1).pos.y = p.y * gp.TAILLE_BLOC;

            GamePanel.obj.add(new Pierre());
            pointX = randomNumber.nextInt(9,42);
            pointY = randomNumber.nextInt(8,41);
            p = new Point(pointX,pointY);
            while (isInList(p)) {
                pointX = randomNumber.nextInt(9,42);
                pointY = randomNumber.nextInt(8,41);
                p = new Point(pointX,pointY);
            }
            listPoint.add(p);
            GamePanel.obj.get(GamePanel.obj.size() -1).pos.x = p.x * gp.TAILLE_BLOC;
            GamePanel.obj.get(GamePanel.obj.size() -1).pos.y = p.y * gp.TAILLE_BLOC;
            
        }
        




    }


    public void setMarcheRandom() {
        Random randomNumber = new Random();
        int pointX;
        int pointY;
        int newRandom = randomNumber.nextInt(1,3);
        if(newRandom == 1){
            newRandom = randomNumber.nextInt(1,3);
            if(newRandom == 1){
                pointX = 9;
                pointY = randomNumber.nextInt(8,41);
            }else{
                pointX = 42;
                pointY = randomNumber.nextInt(9,41);
            }
            
        }else{
            newRandom = randomNumber.nextInt(1,3);
            if(newRandom == 1){
                pointX = randomNumber.nextInt(9,42);
                pointY = 8;
            }else{
                pointX = randomNumber.nextInt(9,42);
                pointY = 41;
            }
        }
        Point pMarche = new Point(pointX,pointY);
        listPoint.add(pMarche);
        GamePanel.obj.add(new Marche());
        GamePanel.obj.get(GamePanel.obj.size() -1).pos.x = pMarche.x * gp.TAILLE_BLOC;
        GamePanel.obj.get(GamePanel.obj.size() -1).pos.y = pMarche.y * gp.TAILLE_BLOC;

        listPoint.add(new Point((pMarche.x) + 1,(pMarche.y)));
        listPoint.add(new Point((pMarche.x) - 1,(pMarche.y)));
        listPoint.add(new Point((pMarche.x) + 1,(pMarche.y)+ 1));
        listPoint.add(new Point((pMarche.x) - 1,(pMarche.y)+ 1));
        listPoint.add(new Point((pMarche.x) + 1,(pMarche.y)- 1));
        listPoint.add(new Point((pMarche.x) - 1,(pMarche.y)- 1));
        listPoint.add(new Point((pMarche.x) ,(pMarche.y)+ 1));
        listPoint.add(new Point((pMarche.x) ,(pMarche.y)- 1));
    }

    public boolean isInList(Point p){
        for(int i = 0; i<listPoint.size();i++){
            if(p.x == listPoint.get(i).x && p.y == listPoint.get(i).y){
                return true;
            }
        }
        return false;
    }




}
