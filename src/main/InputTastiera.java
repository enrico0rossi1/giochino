package main;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputTastiera implements KeyListener {

    public boolean w,a,s,d,p,o,m;
    Pannello gp;

    public InputTastiera(Pannello gp){
        this.gp = gp;
    }

    

    public void keyTyped(KeyEvent e){}
    
    @Override 
    public void keyPressed(KeyEvent e) {

        int premuto = e.getKeyCode();

        if(premuto == KeyEvent.VK_M){
           if (gp.gameState == gp.playState){
            gp.gameState = gp.pauseState;
            gp.stopMusic(0);
           }
          else if (gp.gameState == gp.pauseState){
            gp.gameState = gp.playState;
            gp.playMusic(0);
           } 
        }
        
        if(premuto == KeyEvent.VK_W){
            w=true;
            
        }

        if(premuto == KeyEvent.VK_A){
            a=true;
        }

        if(premuto == KeyEvent.VK_S){
            s=true;
        }

        if(premuto == KeyEvent.VK_D){
            d=true;

        }

        if(premuto == KeyEvent.VK_P){
            p=true;

        }

        if(premuto == KeyEvent.VK_O){
            o=true;

        }
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int rilasciato = e.getKeyCode();

        if(rilasciato == KeyEvent.VK_W){
            w=false;
        }

        if(rilasciato == KeyEvent.VK_A){
            a=false;
        }

        if(rilasciato == KeyEvent.VK_S){
            s=false;
        }

        if(rilasciato == KeyEvent.VK_D){
            d=false;
        }
        if(rilasciato == KeyEvent.VK_P){
            p=false;
        } 
        if(rilasciato == KeyEvent.VK_O){
            o=false;
        } 
    }
    
}
