package main;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputTastiera implements KeyListener {

    public boolean w,a,s,d,p,o,m,n,f,esc,enter;
    Pannello gp;
    
    //debug 
    public boolean z=false;

    public InputTastiera(Pannello gp){
        this.gp = gp;
    }
     public void keyTyped(KeyEvent t){
         
    }
    
    @Override 
    public void keyPressed(KeyEvent e) {

        int premuto = e.getKeyCode();


if (gp.gameState == gp.titleState) {
    if(premuto == KeyEvent.VK_W){
          gp.stopMusic(10);
          gp.playMusic(gp.eventHandler.currentMap);
          gp.gameState = gp.playState;
     }

}


if (gp.gameState == gp.playState) {

        if(premuto == KeyEvent.VK_M){
            gp.gameState = gp.pauseState;
            gp.stopMusic(2);
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

else if (gp.gameState == gp.pauseState){

    if(premuto == KeyEvent.VK_M){
            gp.gameState = gp.playState;
            gp.playMusic(gp.eventHandler.currentMap);
    }

  else if (premuto == KeyEvent.VK_N){
            
                 gp.gameState = gp.optionsState;
            }

    }

else if (gp.gameState == gp.dialogueState){

    if(premuto == KeyEvent.VK_W){
          
        gp.gameState = gp.playState;
        
    }   
  }

if (gp.gameState == gp.optionsState){
    if (premuto == KeyEvent.VK_N){
       
                        gp.gameState = gp.pauseState;
            
   }
     if(premuto == KeyEvent.VK_ESCAPE)  {
            if (gp.fullScreenOn == true) {
            
                gp.fullScreenOn = false;
            }
            
            if(gp.fullScreenOn == false)  {

                gp.fullScreenOn = true;
            }
        }
}


else if (gp.gameState == gp.gameOver){

    if(premuto == KeyEvent.VK_M){
       gp.gameState =gp.titleState;
            gp.stopMusic(2);
            gp.playMusic(0);
            gp.giocatore.worldX= gp.ingame_size*25 ;
            gp.giocatore.worldY = gp.ingame_size*25;
            gp.giocatore.vita = gp.giocatore.vitaMax;
    }

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
