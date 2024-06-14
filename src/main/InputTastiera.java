package main;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputTastiera implements KeyListener {

    public boolean w,a,s,d,p,o,m,f,enter;
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
          gp.stopMusic(4);
          gp.playMusic(0);
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

     if (premuto == KeyEvent.VK_O){
            
                 gp.gameState = gp.optionsState;
            }

    }

else if (gp.gameState == gp.dialogueState){

    if(premuto == KeyEvent.VK_D){
        if (gp.ui.dialogueChoice ==1)
        
        gp.ui.dialogueChoice++;
      
    }
   
    if(premuto == KeyEvent.VK_A){
        if (gp.ui.dialogueChoice ==2)
       
      gp.ui.dialogueChoice--;
    
        }

    if(premuto == KeyEvent.VK_ENTER){
          
        gp.gameState = gp.playState;

        if (gp.ui.dialogueChoice == 2){
            gp.eventHandler.teleport(gp.eventHandler.nextMap);
        }
        
    }   
  }

else if (gp.gameState == gp.optionsState){
   
   int maxCommandNum = 5;
       
    if(premuto == KeyEvent.VK_W){
        if (gp.ui.commandNum > 0)  
      // gp.playSFX(1);
        gp.ui.commandNum--;
        
    }  
   
    if(premuto == KeyEvent.VK_S){
        if (gp.ui.subState == 0 && gp.ui.commandNum < maxCommandNum)
          // gp.playSFX(1);
        gp.ui.commandNum++;
        if (gp.ui.subState == 2 && gp.ui.commandNum < 1)
        gp.ui.commandNum++;
    }
   
    if(premuto == KeyEvent.VK_A){
        if (gp.ui.subState == 0 && gp.ui.commandNum ==1 && gp.music.volumeScale >0) {
            gp.music.volumeScale--;
            gp.music.checkVolume();

        }
        if (gp.ui.subState == 0 && gp.ui.commandNum ==2 && gp.sfx.volumeScale >0) {
            gp.sfx.volumeScale--;
            gp.sfx.checkVolume();

        }
    }


    if(premuto == KeyEvent.VK_D){
        if (gp.ui.subState == 0 && gp.ui.commandNum ==1 && gp.music.volumeScale <5) {
            gp.music.volumeScale++;
            gp.music.checkVolume();

        }
        if (gp.ui.subState == 0 && gp.ui.commandNum ==2 && gp.sfx.volumeScale <5) {
            gp.sfx.volumeScale++;
            gp.sfx.checkVolume();

        }
    }
   
   //
   // if (premuto == KeyEvent.VK_N){
   //    
   //                     gp.gameState = gp.pauseState;
   //         
   //}
    if(premuto == KeyEvent.VK_ENTER)  {
        
        if (gp.ui.subState == 0) {
        maxCommandNum = 5;
            if (gp.ui.commandNum == 0) {
        if (gp.fullScreenOn == true) {
           
               gp.fullScreenOn = false;
           }
           
          else if(gp.fullScreenOn == false)  {
               gp.fullScreenOn = true;
           }
        }

        if (gp.ui.commandNum == 3)
        gp.ui.subState = 1;

        if (gp.ui.commandNum == 4) {
          gp.ui.subState = 2;
          gp.ui.commandNum = 1;
          
        }

        if (gp.ui.commandNum == 5) {
            gp.gameState = gp.pauseState;
        }
       }
       else if (gp.ui.subState == 1) {
        gp.ui.subState = 0;
       }

       else if (gp.ui.subState ==2) {
        maxCommandNum = 1;
       
        if (gp.ui.commandNum == 1)
        gp.ui.subState = 0;
        if (gp.ui.commandNum == 0) {
        gp.gameState = gp.titleState;
        gp.ui.subState = 0;
        gp.playMusic(4);
        }


       }
      }
    }

else if (gp.gameState == gp.gameOver){

    if(premuto == KeyEvent.VK_M){
       gp.gameState =gp.titleState;
            gp.stopMusic(gp.eventHandler.currentMap);
            gp.playMusic(4);
            gp.giocatore.worldX= gp.ingame_size*25 ;
            gp.giocatore.worldY = gp.ingame_size*25;
            gp.giocatore.vita = gp.giocatore.vitaMax;
            gp.eventHandler.currentMap = gp.eventHandler.startingWoodsMap;
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
