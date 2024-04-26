package main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import Player.Giocatore;
import object.ObjKey;


public class UI {

    Pannello gp;
    Font arial_30;
    Font winnerFont;

    ObjKey key = new ObjKey();
    Image keyImage = key.image;

    public boolean messageOn = false;
    public boolean message2On = false;
    public String message = "";
    public String message2= "";
    int messageCounter = 0;
    int message2Counter = 0;
    public boolean endGame;
    public boolean gameOver;

    public UI (Pannello gp) {

        this.gp = gp;
        //elenco dei font utilizzati nell'interfaccia
       arial_30 = new Font ("Arial", Font.BOLD,30);
       winnerFont = new Font("Arial", Font.BOLD, 36);

    }
 public void showMessage (String text){
        messageOn = true;
        message = text;
        messageCounter = 1;
      

    }

    public void showMessage2 (String text){
        message2On = true;
        message2 = text;
        message2Counter = 1;
      

    }

    
    public void draw (Graphics2D g2) {
if (endGame == true) {
    g2.setFont(winnerFont);
    g2.drawString("Congratulations \r you are the Champion",(gp.screen_width)/4,gp.screen_height/2-80);
    
    gp.ThreadGioco = null;
}
else if(gp.gameState==gp.playState){
        g2.setFont(arial_30);
        g2.setColor(Color.red);
        g2.drawString(" = "+Giocatore.numKeys, 32, 553);  
      
        g2.drawImage(keyImage,10,530,25,25,null);

        if(messageOn == true) {
            int messageX = (gp.screen_width /2)- 80 ;
            int messageY = gp.screen_height - (gp.screen_height / 6) ;
            g2.drawString(message, messageX,messageY);
            messageCounter++;
        }

        if(message2On == true){
            int message2X = (gp.screen_width /2)- 80 ;
            int message2Y = gp.screen_height - (gp.screen_height / 6 - 20) ; 
            g2.drawString(message2, message2X,message2Y); 
            message2Counter++;

             // considerando 60 frame 
            if (messageCounter >gp.FPS*5 ){
                messageOn = false;
            }
           
            if (message2Counter >gp.FPS*5 ){
                message2On = false;
            }
        }
    }
       else if (gp.gameState==gp.pauseState){
            g2.setFont(winnerFont);
            g2.drawString("Resting a bit \n press M to resume",(gp.screen_width)/4,gp.screen_height/2-4);
        }
      }
    

   
      
}

