package main;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputTastiera implements KeyListener {

    public boolean w,a,s,d,p,o,m;
    Pannello gp;

    public InputTastiera(Pannello gp){
        this.gp = gp;
    }

    

    public void keyTyped(KeyEvent t){
         
    }
    
    @Override 
    public void keyPressed(KeyEvent e) {

        int premuto = e.getKeyCode();

        if(premuto == KeyEvent.VK_M){
           if (gp.gameState == gp.playState){
            gp.gameState = gp.pauseState;
            gp.stopMusic(2);
           }
          else if (gp.gameState == gp.pauseState){
            gp.gameState = gp.playState;
            gp.playMusic(2);
           } 
           if (gp.gameState==gp.gameOver){
            gp.gameState =gp.titleState;
           }
        } 
        
        if(premuto == KeyEvent.VK_W){
            if (gp.gameState == gp.playState) {
            w=true;
            }
            if (gp.gameState == gp.titleState){
                gp.stopMusic(0);
                gp.playMusic(2);
                gp.gameState = gp.playState;
               

            }
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
