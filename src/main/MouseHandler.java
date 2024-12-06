package main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseHandler implements MouseListener {

    GamePanel gp;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    
    @Override
    public void mousePressed(MouseEvent e){
        if (gp.gameState == gp.TITLE_STATE){
            if (e.getButton() == MouseEvent.BUTTON1){
                gp.gameState = gp.playState;
            }
            if (e.getButton() == MouseEvent.BUTTON2){
                System.exit(0);
            }
        }
    }
    
}
