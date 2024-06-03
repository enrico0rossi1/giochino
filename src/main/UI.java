package main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import object.ObjHeart;
import object.ObjKey;


public class UI {

    Pannello gp;
    Font arial_30;
    Font winnerFont;
    Graphics2D graphics2;
    InputTastiera keyh;
    ObjHeart heart;
    ObjKey key;
    
    public boolean messageOn = false;
    public boolean message2On = false;
    public String message = "";
    public String message2= "";
    int messageCounter = 0;
    int message2Counter = 0;
    public boolean endGame;
   

    public UI (Pannello gp) {
        
        this.gp = gp;
        this.key = new ObjKey(gp);
        this.heart = new ObjHeart(gp);
        
        
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
        
        this.graphics2=graphics2;

        drawTitleScreen();
        drawPlayState();
        drawPauseScreen();
        drawGameOverScreen();
        drawEndScreen();
    }
    
    
    public void drawTitleScreen(){

        if (gp.gameState == gp.titleState) {
            
              graphics2.setColor(new Color(0,40,80));
               graphics2.fillRect(0,0,gp.screen_width,gp.screen_height);
               graphics2.setFont(winnerFont);
               graphics2.setColor(Color.GRAY);
               graphics2.drawString("WARRIOR ADVENTURE",getCenteredXForText ("WARRIOR ADVENTURE",graphics2)-2,gp.screen_height/2-83);
           
               graphics2.setColor(Color.red);
               graphics2.drawString("WARRIOR ADVENTURE", getCenteredXForText ("WARRIOR ADVENTURE",graphics2),gp.screen_height/2-80);
               graphics2.setColor(Color.WHITE);
               graphics2.drawString("PRESS W TO START GAME", getCenteredXForText ("PRESS W TO START GAME",graphics2),gp.screen_height/2+180);
               graphics2.drawImage(gp.giocatore.AttackDown[1],gp.screen_width/2-80,gp.screen_height/2,160,120,null);
           }
    
    }

    public void drawPlayerLife() {
        Image heartFullImage = heart.image;
        Image heartHalvedImage = heart.image2;
        Image heartVoidImage = heart.image3;
    
        int x = gp.pix_row / 2;
        int y = gp.pix_cols / 2;
        
        // Disegna cuori vuoti
        for (int i = 0; i < gp.giocatore.vitaMax / 2; i++) {
            graphics2.drawImage(heartVoidImage, x, y, null);
            x += 2 * gp.pix_row;
        }
    
        // Ripristina le coordinate per disegnare i cuori pieni e mezzi
        x = gp.pix_row / 2;
        int vita = gp.giocatore.vita;
        
        for (int i = 0; i < vita / 2; i++) {
            graphics2.drawImage(heartFullImage, x, y, null);
            x += 2 * gp.pix_row;
        }
        
        // Disegna mezzo cuore se la vita è dispari
        if (vita % 2 == 1) {
            graphics2.drawImage(heartHalvedImage, x, y, null);
        }
    }
    
    public void drawPlayState() {
        Image keyImage = key.image;
        
        if (gp.gameState == gp.playState) {
            graphics2.setFont(arial_30);
            graphics2.setColor(Color.red);
            graphics2.drawString(" = " + gp.giocatore.numKeys, 32, 553);
            graphics2.drawImage(keyImage, 10, 530, 25, 25, null);
    
            // Disegna la vita del giocatore
            drawPlayerLife();
    
            // Disegna i messaggi
            drawMessages();
        }
    }
    
    private void drawMessages() {
        if (messageOn) {
            drawMessage(message, messageCounter);
            messageCounter++;
            if (messageCounter > gp.FPS * 5) {
                messageOn = false;
            }
        }
    
        if (message2On) {
            drawMessage(message2, message2Counter, -20);
            message2Counter++;
            if (message2Counter > gp.FPS * 5) {
                message2On = false;
            }
        }
    }
    
    private void drawMessage(String message, int counter) {
        drawMessage(message, counter, 0);
    }
    
    private void drawMessage(String message, int counter, int offsetY) {
        int messageX = (gp.screen_width / 2) - 80;
        int messageY = gp.screen_height - (gp.screen_height / 6) + offsetY;
        graphics2.drawString(message, messageX, messageY);
    }
    

    public void drawPauseScreen () {
        
        if (gp.gameState==gp.pauseState){
            // drawPauseScreen();
            graphics2.setFont(arial_30);
            graphics2.drawString("Resting a bit",gp.screen_width/2+50,gp.screen_height/2+10);
            graphics2.drawString("press M to resume",gp.screen_width/2+40,gp.screen_height/2+30); 
        }
    
    }  
        
    public void drawGameOverScreen(){
        Image heartHalvedImage = heart.image2;
        
        if (gp.gameState == gp.gameOver) {
            // draw gOverScreen ();
            graphics2.drawImage(heartHalvedImage,gp.screen_width/2,gp.screen_height/2+10,300,200,null);
            graphics2.drawString("LOSER",gp.screen_width/2+50,gp.screen_height/2+10);
        }

    }
    public void drawEndScreen(){
        if (endGame == true) {
            graphics2.setFont(winnerFont);
            graphics2.drawString("Congratulations \r you are the Champion",(gp.screen_width)/4,gp.screen_height/2-80);

            gp.ThreadGioco = null;
        }
        
    }
    public int getCenteredXForText (String text,Graphics2D graphics2) {
         //   graphics2.setFont(arial_30);
        int length = (int)graphics2.getFontMetrics().getStringBounds(text, graphics2).getWidth();
        int centeredX = gp.screen_width/2 - length/2;
        return centeredX;
    }
      
}

