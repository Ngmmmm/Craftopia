package bag;

public class Argent {

    private static int monnaiePlayer = 0;
    private static final int VALFEUILLE = 1;
    private static final int VALBOIS = 2;
    private static final int VALPIERRE = 3;
    private static final int VALFER = 4;

    public static int getMonnaiePlayer() {
        return monnaiePlayer;
    }

    public static int getVALBOIS() {
        return VALBOIS;
    }

    public static int getVALFER() {
        return VALFER;
    }

    public static int getVALFEUILLE() {
        return VALFEUILLE;
    }

    public static int getVALPIERRE() {
        return VALPIERRE;
    }

}
