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
  
gp.retry();

    if(premuto == KeyEvent.VK_W){
          gp.stopMusic(4);
          gp.playMusic(0);
          gp.eventHandler.currentMapIndex = 0;
          gp.gameState = gp.playState;
     }

}


if (gp.gameState == gp.playState) {

        if(premuto == KeyEvent.VK_M){
            gp.gameState = gp.pauseState;
            gp.playSFX(11);
            gp.stopMusic(2);
            gp.playMusic(5);
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
            gp.playSFX(10);
            gp.stopMusic(5);
            gp.playMusic(gp.eventHandler.currentMapIndex);
    }

     if (premuto == KeyEvent.VK_O){
            
                 gp.gameState = gp.optionsState;
                 gp.playSFX(11);
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
          if (gp.fullScreenOn == true && gp.ui.currentDialogue =="Per ridurre le dimensioni dello schermo riavviare il gioco."){
          gp.playSFX(10);
          gp.gameState = gp.optionsState;}
          else {
        gp.gameState = gp.playState;

        if (gp.ui.dialogueChoice == 2){
            gp.eventHandler.teleport(gp.eventHandler.nextMap);
            gp.playSFX(7);
        }
        if (gp.ui.dialogueChoice == 1)
        gp.playSFX(10);
    }
    }   
  }

else if (gp.gameState == gp.optionsState){
   
   int maxCommandNum = 5;
       
    if(premuto == KeyEvent.VK_W){
        if (gp.ui.commandNum > 0)  {
        gp.playSFX(11);
        gp.ui.commandNum--;
        }
    }  
   
    if(premuto == KeyEvent.VK_S){
      
        if (gp.ui.subState == 0 && gp.ui.commandNum < maxCommandNum){
        gp.playSFX(11);
        gp.ui.commandNum++;
        }
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
            gp.playSFX(11);
  
        }
    }


    if(premuto == KeyEvent.VK_D){
        if (gp.ui.subState == 0 && gp.ui.commandNum ==1 && gp.music.volumeScale <5) {
            gp.music.volumeScale++;
            gp.music.checkVolume();

        }
        if (gp.ui.subState == 0 && gp.ui.commandNum ==2 && gp.sfx.volumeScale <5) {
            gp.sfx.volumeScale++;
            gp.playSFX(11);

        }
    }
   
    if(premuto == KeyEvent.VK_ENTER)  {
       
        if (gp.ui.subState == 0) {
        maxCommandNum = 5;
        
            if (gp.ui.commandNum == 0) {
                gp.playSFX(9);
        if (gp.fullScreenOn == true) {
            gp.ui.currentDialogue = "Per ridurre le dimensioni dello schermo riavviare il gioco.";
            gp.ui.dialogueChoice1 = "";
            gp.ui.dialogueChoice2 = "";
            gp.ui.dialogueChoice = 3;
             gp.gameState = gp.dialogueState;
           }
           
          else if(gp.fullScreenOn == false)  {
               gp.fullScreenOn = true;
           }
        }

        if (gp.ui.commandNum == 3) {
        gp.ui.subState = 1;
        gp.playSFX(9);
        }

        if (gp.ui.commandNum == 4) {
            gp.playSFX(9);
          gp.ui.subState = 2;
          gp.ui.commandNum = 1;  
        }

        if (gp.ui.commandNum == 5) {
            gp.playSFX(10);
            gp.gameState = gp.pauseState;
        }
       }
       else if (gp.ui.subState == 1) {
        gp.ui.subState = 0;
        gp.playSFX(10);
       }

       else if (gp.ui.subState ==2) {
        maxCommandNum = 1;
       
        if (gp.ui.commandNum == 1)
        gp.playSFX(10);
        gp.ui.subState = 0;
        if (gp.ui.commandNum == 0) {
        gp.gameState = gp.titleState;
        gp.ui.subState = 0;
        gp.stopMusic(5);
        gp.playMusic(4);
        }
       }
      }
    }

else if (gp.gameState == gp.gameOver){

    if(premuto == KeyEvent.VK_ENTER){

        if (gp.ui.gameOverChoice ==1){
            gp.gameState =gp.titleState;
            gp.playMusic(4);
            gp.retry();
    }
         if (gp.ui.gameOverChoice ==0){
             gp.playMusic(gp.eventHandler.startingWoodsMap);
             gp.retry();
             gp.gameState = gp.playState;
    }
}

    if(premuto == KeyEvent.VK_D){
        if (gp.ui.gameOverChoice ==0)
           gp.playSFX(11);
           gp.ui.gameOverChoice=1;
      
    }
   
    if(premuto == KeyEvent.VK_A){
        if (gp.ui.gameOverChoice ==1)
           gp.playSFX(11);
           gp.ui.gameOverChoice=0;
    
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
