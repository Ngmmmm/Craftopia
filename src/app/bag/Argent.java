package bag;

public class Argent {

    private static int monnaiePlayer = 0;
    private static final int VALFEUILLE = 1;
    private static final int VALBOIS = 2;
    private static final int VALFER = 3;
    private static final int VALPIOCHE = 5;
    private static final int VALPIERRE = 1;
    private static final int VALBLOCPIERRE = 5;
    private static final int PRIXRECOLTEUSE = 100;

    public static int getMonnaiePlayer() {
        return monnaiePlayer;
    }
    public static int getVALBOIS(){
        return VALBOIS;
    }

    public static int getVALFER() {
        return VALFER;
    }
    public static int getVALPIOCHE(){
        return VALPIOCHE;
    }
    public static int getVALPIERRE(){
        return VALPIERRE;
    }
    public static int getVALBLOCPIERRE(){
        return VALBLOCPIERRE;
    }
    public static int getPRIXRECOLTEUSE(){
        return PRIXRECOLTEUSE;
    }
    
    

}
