package main;

import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;


import object.ObjHeart;
import object.ObjKey;
import object.ObjShoes;


public class UI {

    Pannello gp;
    Font arial_30;
    Font winnerFont;
   // Font zeldaFont;
    Graphics2D graphics2;
    InputTastiera keyh;
    ObjHeart heart;
    ObjKey key;
    ObjShoes shoes;
    
    public boolean messageOn = false;
    public boolean message2On = false;
    public String message = "";
    public String message2= "";
    int messageCounter = 0;
    int message2Counter = 0;
    public boolean endGame;
    public String currentDialogue = "la mamma di Enrico gioca a fare la gym bro alla McFit e le \npiacciono i fagioli";
    
   

    public UI (Pannello gp) {
        
        this.gp = gp;
        this.key = new ObjKey(gp);
        this.heart = new ObjHeart(gp);
        this.shoes = new ObjShoes(gp);
        
        
        //elenco dei font utilizzati nell'interfaccia
       arial_30 = new Font ("Arial", Font.BOLD,30);
       winnerFont = new Font("Arial", Font.BOLD, 36);
       
  //     InputStream is = getClass().getResourceAsStream("font/ZeldaFont.ttf");
  //     
  //     try {
  //     zeldaFont = Font.createFont(Font.TRUETYPE_FONT, is);
  //  } catch (FontFormatException e) {
  //      e.printStackTrace();
  //  } catch (IOException e)         {
  //          e.printStackTrace();
  //  }

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
        drawDialogueState();
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
    
    public void drawDialogueState () {
       
        if (gp.gameState == gp.dialogueState) {
           
            drawDialogueScreen();

        }


    }

    public void drawDialogueScreen() {
     
        //window
        int x = gp.ingame_size * 2;
        int y = gp.ingame_size/2 ;
        int height = gp.screen_height/4 ;
        int width = gp.screen_width - (gp.ingame_size * 4)  ;
        drawSubWindow(x,y,height,width);

        graphics2.setFont(graphics2.getFont().deriveFont(Font.PLAIN));
        x += gp.ingame_size;
        y += gp.ingame_size;

        for (String line : currentDialogue.split("\n")) {
        graphics2.drawString(line,x,y);
        y += 40;
       }

    }

    public void drawPauseScreen () {
        
        if (gp.gameState==gp.pauseState){
            // drawPauseScreen();
            graphics2.setFont(arial_30);
            graphics2.drawString("Resting a bit",gp.screen_width/2+50,gp.screen_height/2+10);
            graphics2.drawString("press M to resume",gp.screen_width/2+40,gp.screen_height/2+30); 
            drawCharacterScreen(); 
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
    public void drawSubWindow (int x,int y,int height, int width) {

        Color c = new Color(0,0,0,180);
        graphics2.setColor(c);
        graphics2.fillRoundRect(x,y,width,height,35,35);
        
        c = new Color(255,255,255);
        graphics2.setColor(c);
        graphics2.setStroke(new BasicStroke(3));
        graphics2.drawRoundRect(x+3,y+3,width-5,height-5,25,25);
    
    } 
public void drawCharacterScreen () {
    final int x = gp.ingame_size;
    final int y = gp.ingame_size ;
    final int width = gp.ingame_size*5;
    final int height = gp.ingame_size*10 ;

    drawSubWindow(x, y, height, width);

    graphics2.setFont(winnerFont);
    graphics2.setColor(Color.BLUE);

    int textX = x +20;
    int textY = y + gp.ingame_size;
    final int lineHeight = 32;


    graphics2.drawString("STATUS",textX,textY);
    textY +=lineHeight;
    graphics2.setColor(Color.WHITE);
    graphics2.drawString("vita  "+ gp.giocatore.vita+"/"+gp.giocatore.vitaMax,textX,textY);
    textY += lineHeight;
    graphics2.drawString("armato",textX,textY);
    textY += lineHeight;
    if (gp.giocatore.speedUp == false) {graphics2.drawString("no drip",textX,textY);
    textY += lineHeight;}
    if (gp.giocatore.speedUp == true) {graphics2.drawString("flash",textX,textY);
    graphics2.drawImage(shoes.image,textX + (gp.ingame_size*2),textY - lineHeight,null);
    textY += lineHeight;}
    graphics2.drawString("povero",textX,textY);
    textY += lineHeight;

}

}

