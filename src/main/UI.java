package main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import Player.Giocatore;
import object.ObjHeart;
import object.ObjKey;


public class UI {

    Pannello gp;
    Font arial_30;
    Font winnerFont;
    Graphics2D graphics2;
    InputTastiera keyh;
    ObjKey key = new ObjKey();
    ObjHeart heart = new ObjHeart();
    Image keyImage = key.image;
    Image heartFullImage = heart.image;
    Image heartHalvedImage = heart.image2;
    Image heartVoidImage = heart.image3;


    public boolean messageOn = false;
    public boolean message2On = false;
    public String message = "";
    public String message2= "";
    int messageCounter = 0;
    int message2Counter = 0;
    public boolean endGame;
   

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
    
    public void draw (Graphics2D graphics2) {
//interfaccia al termine del gioco
if (endGame == true) {
    graphics2.setFont(winnerFont);
    graphics2.drawString("Congratulations \r you are the Champion",(gp.screen_width)/4,gp.screen_height/2-80);
    
    gp.ThreadGioco = null;
}
// interfaccia nel menu principale
if (gp.gameState == gp.titleState) {
   // drawTitleScreen();
    graphics2.setColor(new Color(0,40,80));
    graphics2.fillRect(0,0,gp.screen_width,gp.screen_height);
    graphics2.setFont(winnerFont);
    graphics2.setColor(Color.GRAY);
    graphics2.drawString("GIOCHINO PAZZO SGRAVATO",(gp.screen_width)/4-67,gp.screen_height/2-83);
  
    graphics2.setColor(Color.red);
    graphics2.drawString("GIOCHINO PAZZO SGRAVATO",(gp.screen_width)/4-65,gp.screen_height/2-80);
    graphics2.setColor(Color.WHITE);
    graphics2.drawString("PRESS W TO START GAME",(gp.screen_width)/4-60,gp.screen_height/2+180);
    graphics2.drawImage(gp.giocatore.AttackDown[1],gp.screen_width/2-80,gp.screen_height/2,160,120,null);
}
// interfaccia durante il gioco
if(gp.gameState==gp.playState){
        graphics2.setFont(arial_30);
        graphics2.setColor(Color.red);
        graphics2.drawString(" = "+Giocatore.numKeys, 32, 553);  
      
        graphics2.drawImage(keyImage,10,530,25,25,null);

 // vita del giocatore

      // drawPlayerLife();

       int x = gp.pix_row/2;
        int y = 0;
        int i = 0;

        while (i<gp.giocatore.vitaMax/2) {
            graphics2.drawImage(heartVoidImage, x , y,null);
            i++;
            x += 2*gp.pix_row;
            
        }
            x = gp.pix_row/2;
            y = 0;
            i = 0;

            while (i < gp.giocatore.vita){
                graphics2.drawImage(heartHalvedImage,x,y,null);
                i++;
                if(i < gp.giocatore.vita) {
                    graphics2.drawImage(heartFullImage,x,y,null);
                }
                i++;
                x += 2*gp.pix_row;
            }
        
   
   
        

        if(messageOn == true) {
            int messageX = (gp.screen_width /2)- 80 ;
            int messageY = gp.screen_height - (gp.screen_height / 6) ;
            graphics2.drawString(message, messageX,messageY);
            messageCounter++;
            
            if (messageCounter >gp.FPS*5 ){
                messageOn = false;
            }
        }

        if(message2On == true){
            int message2X = (gp.screen_width /2)- 80 ;
            int message2Y = gp.screen_height - (gp.screen_height / 6 - 20) ; 
            graphics2.drawString(message2, message2X,message2Y); 
            message2Counter++;

             // considerando 60 frame 
        
           
            if (message2Counter >gp.FPS*5 ){
                message2On = false;
            }
        }
    }

// descrizione dell'interfaccia nel menù di pausa
       if (gp.gameState==gp.pauseState){
       // drawPauseScreen();
       graphics2.setFont(arial_30);
       graphics2.drawString("Resting a bit",gp.screen_width/2+50,gp.screen_height/2+10);
       graphics2.drawString("press M to resume",gp.screen_width/2+40,gp.screen_height/2+30); }
      

   if (gp.gameState == gp.gameOver) {
    // draw gOverScreen ();
    graphics2.drawImage(heartVoidImage,gp.screen_width/2+50,gp.screen_height/2+10,100,100,null);
    graphics2.drawString("LOSER",gp.screen_width/2+50,gp.screen_height/2+10);
   }
   
}
    public void drawTitleScreen(){

        graphics2.setColor(new Color(0,40,80));
        graphics2.fillRect(0,0,gp.screen_width,gp.screen_height);
        graphics2.setFont(winnerFont);
        graphics2.setColor(Color.GRAY);
        graphics2.drawString("GIOCHINO PAZZO SGRAVATO",(gp.screen_width)/4-67,gp.screen_height/2-83);
      
        graphics2.setColor(Color.red);
        graphics2.drawString("GIOCHINO PAZZO SGRAVATO",(gp.screen_width)/4-65,gp.screen_height/2-80);
        graphics2.setColor(Color.WHITE);
        graphics2.drawString("PRESS W TO START GAME",(gp.screen_width)/4-60,gp.screen_height/2+180);
        graphics2.drawImage(gp.giocatore.AttackDown[1],gp.screen_width/2-80,gp.screen_height/2,160,120,null);
    
    }
    
        public void drawPauseScreen () {
          
            String pauseText = "Resting a bit \n press M to resume";
            int x = getCenteredXForText(pauseText, graphics2);
            int y = gp.screen_height/2 ;
         
            graphics2.drawString(pauseText,x,y);
    
    }    
    public void drawPlayerLife () {

        int x = gp.pix_row/2;
        int y = gp.pix_cols/2;
        int i = 0;

        while (i<gp.giocatore.vitaMax/2) {
            graphics2.drawImage(heartVoidImage, x , y,null);
            i++;
            x += 2*gp.pix_row;
            
        }
            x = gp.pix_row/2;
            y = gp.pix_cols/2;
            i = 0;

            while (i < gp.giocatore.vita){
                graphics2.drawImage(heartHalvedImage,x,y,null);
                i++;
                if(i < gp.giocatore.vita) {
                    graphics2.drawImage(heartFullImage,x,y,null);
                }
                i++;
                x += 2*gp.pix_row;
            }
    } 
        public int getCenteredXForText (String text,Graphics2D graphics2) {
         //   graphics2.setFont(arial_30);
        int length = (int)graphics2.getFontMetrics().getStringBounds(text, graphics2).getWidth();
        int centeredX = gp.screen_width/2 - length/2;
        return centeredX;
    }
      
}

