package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import object.GoldCoin;
import object.ObjHeart;
import object.ObjKey;
import object.ObjShoes;


public class UI {

    Pannello gp;
    Font zeldaFont;
    Font zeldaFont60;
    Font eightBitFont;
    Font eightBitFont15;
    Graphics2D graphics2;
    InputTastiera keyh;
    ObjHeart heart;
    ObjKey key;
    ObjShoes shoes;
    GoldCoin coin;
    
    public boolean messageOn = false;
    public boolean message2On = false;
    public String message = "";
    public String message2= "";
    int messageCounter = 0;
    int message2Counter = 0;
    public boolean endGame;
    public String currentDialogue = "";
    public String dialogueChoice1 = "";
    public String dialogueChoice2 = "";
    int subState = 0;
    int commandNum = 0;
    public int dialogueChoice = 0;
    int gameOverChoice;
   

    public UI (Pannello gp) {
        
        this.gp = gp;
        this.key = new ObjKey(gp);
        this.heart = new ObjHeart(gp);
        this.shoes = new ObjShoes(gp);
        this.coin = new GoldCoin(gp);
        
        
        //elenco dei font utilizzati nell'interfaccia

        try {

            zeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("Font/ZeldaFont.otf")).deriveFont(30f);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading font: " + e.getMessage());
        }

        try {
            eightBitFont = Font.createFont(Font.TRUETYPE_FONT, new File("Font/8BitFont.ttf")).deriveFont(20f);
        } catch (IOException | FontFormatException f) {
            f.printStackTrace();
            System.err.println("Error loading font: " + f.getMessage());
        } 


        zeldaFont60 = zeldaFont.deriveFont(60f);
        eightBitFont15 = eightBitFont.deriveFont(15f);
    }


    
    public void draw (Graphics2D graphics2) {
        
        this.graphics2=graphics2;

        drawTitleScreen();
        drawPlayState();  
        drawDialogueState();
        drawOptionsScreen();
        drawPauseScreen();
        drawGameOverScreen();
        drawEndScreen();
      
    }
    
    
    public void drawTitleScreen(){

        if (gp.gameState == gp.titleState) {

            
            BufferedImage titleScreen = loadImage("main/WarriorAdventureTitleScreen.jpeg");

               graphics2.drawImage(titleScreen,0,0,gp.screen_width,gp.screen_height, null);
               graphics2.setFont(zeldaFont60);
               graphics2.setColor(Color.GRAY);
               graphics2.drawString("WARRIOR ADVENTURE",getCenteredXForText ("WARRIOR ADVENTURE",graphics2)-2,gp.screen_height/2-130);
               graphics2.setColor(Color.red);
               graphics2.drawString("WARRIOR ADVENTURE", getCenteredXForText ("WARRIOR ADVENTURE",graphics2),gp.screen_height/2-126);
               graphics2.setColor(Color.WHITE);
               graphics2.drawString("PREMI W PER COMINCIARE", getCenteredXForText ("PREMI W PER COMINCIARE",graphics2),gp.screen_height/2+180);
              
           }
    
    }

    private static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void drawPlayState() {
        
        
        if (gp.gameState == gp.playState) {
            
            drawPlayerKeys();
         
        
            // Disegna la vita del giocatore
            drawPlayerLife();
    
            // Disegna i messaggi
            drawMessages();
        }
    }
      public void drawPlayerLife() {
        Image heartFullImage = heart.image;
        Image heartHalvedImage = heart.image2;
        Image heartVoidImage = heart.image3;
    
        int x = gp.pix_row / 2;
        int y = gp.pix_cols / 2; 
        int height = gp.ingame_size;
        int width = gp.ingame_size * 4;
      

        Color bg = new Color(0,0,0,180);
        Color bord = new Color(255,0,0);
          
          drawColoredSubWindow(x,y,height,width,bg,bord);
        
        // Disegna cuori vuoti
        for (int i = 0; i < gp.giocatore.vitaMax / 2; i++) {
            graphics2.drawImage(heartVoidImage, x, y, null);
            x += 3 * gp.pix_row;
        }
    
        // Ripristina le coordinate per disegnare i cuori pieni e mezzi
        x = gp.pix_row / 2;
        int vita = gp.giocatore.vita;
        
        for (int i = 0; i < vita / 2; i++) {
            graphics2.drawImage(heartFullImage, x, y, null);
            x += 3 * gp.pix_row;
        }
        
        // Disegna mezzo cuore se la vita è dispari
        if (vita % 2 == 1) {
            graphics2.drawImage(heartHalvedImage, x, y, null);
        }
    }

        
    public void drawPlayerKeys() {
        int x = 5;
        int y = gp.screen_height - (gp.ingame_size*2) ;
        int height = 40;
        int width = 25 * 3;
        Image keyImage = key.image;
        Color bg = new Color(0,0,0,180);
        Color bord = new Color(204,119,34);
          
          graphics2.setFont(eightBitFont);
          drawColoredSubWindow(x,y,height,width,bg,bord);
          graphics2.drawString(" x " + gp.giocatore.numKeys, x + 25, y + 27);
          graphics2.drawImage(keyImage, x+5, y+8, 25, 25, null);

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
        graphics2.setFont(eightBitFont15);
        graphics2.setColor(Color.WHITE);
        int messageX = (gp.screen_width / 4*3);
        int messageY = gp.screen_height - (gp.screen_height / 6) + offsetY;
        graphics2.drawString(message, messageX, messageY);
    }
    
    public void drawDialogueState () {
       
        if (gp.gameState == gp.dialogueState) {
           
            drawDialogueScreen();

        }


    }

    // disegna i dialoghi
    public void drawDialogueScreen() {
     
        //window
        int x = gp.ingame_size * 2;
        int y = gp.ingame_size/2 ;
        int height = gp.screen_height/4 ;
        int width = gp.screen_width - (gp.ingame_size * 4)  ;

        // disegna la finestra in cui inserire i dialoghi
        drawSubWindow(x,y,height,width);

        graphics2.setFont(eightBitFont);
        x += gp.ingame_size;
        y += gp.ingame_size;

        for (String line : currentDialogue.split("\n")) {
        graphics2.drawString(line,x,y);
        y += 40;
       }
       graphics2.drawString(dialogueChoice1,x*2,y);
       if (dialogueChoice == 1) {
        graphics2.drawString(">",x*2 - 20,y);
    }
       graphics2.drawString(dialogueChoice2,x*4,y);
       if (dialogueChoice == 2) {
        graphics2.drawString(">",x*4 - 20,y);
    }

    }


    
    public void drawOptionsScreen() {

        int x = gp.ingame_size * 5 ;
        int y = gp.ingame_size;
        int width = gp.ingame_size * 10;
        int height = gp.ingame_size* 10;
      

        if (gp.gameState == gp.optionsState) {

            drawSubWindow(x, y, width, height);

            switch (subState) {

                case 0: options_top(x,y); break;
                case 1: showControls(x,y); break;
                case 2: quitGame(x,y); break;



            }

        }

}
     
public void options_top (int titleX,int titleY) {



    graphics2.setFont(zeldaFont);
    graphics2.setColor(Color.BLUE);

    int textX = getCenteredXForText("Opzioni", graphics2);
    int textY = titleY + gp.ingame_size;
    final int lineHeight = 64;


    graphics2.drawString("Opzioni",textX,textY);
    textY +=lineHeight;
    textX -=lineHeight * 2 +10;
    graphics2.setColor(Color.WHITE);


    graphics2.drawString("Schermo intero  ",textX, textY);
    if (commandNum == 0) {
        graphics2.drawString(">",textX - 25,textY);
    }
    if (gp.fullScreenOn == true) {graphics2.drawString(" ON",textX*2,textY);
}
else if (gp.fullScreenOn == false) {graphics2.drawString(" OFF",textX*2,textY);
}
    textY += lineHeight;


    graphics2.drawString("Musica Volume",textX,textY);
    if (commandNum == 1) {
        graphics2.drawString(">",textX - 25,textY);
    }
    graphics2.drawRect(textX+(gp.ingame_size*6),textY+ -lineHeight/3,120,24);
    int volumeWidth = 24 * gp.music.volumeScale;
    graphics2.fillRect(textX+(gp.ingame_size*6),textY+ -lineHeight/3,volumeWidth,24);  // (textX+(gp.ingame_size*6))/ 5
    textY += lineHeight;


    graphics2.drawString("SFX Volume",textX,textY);
    if (commandNum == 2) {
        graphics2.drawString(">",textX - 25,textY);
    }    graphics2.drawRect(textX+(gp.ingame_size*6),textY+ -lineHeight/3,120,24);
    int sfxWidth = 24 * gp.sfx.volumeScale;
    graphics2.fillRect(textX+(gp.ingame_size*6),textY+ -lineHeight/3,sfxWidth,24);
    textY += lineHeight;


    graphics2.drawString("Comandi",textX,textY);
    if (commandNum == 3) {
        graphics2.drawString(">",textX - 25,textY);
    }
    textY += lineHeight;


    graphics2.drawString("Esci",textX,textY);
    if (commandNum == 4) {
        graphics2.drawString(">",textX - 25,textY);
    }
    textY += lineHeight + 6;


    graphics2.drawString("Indietro",textX,textY);
    if (commandNum == 5) {
        graphics2.drawString(">",textX - 25,textY);
    }

   
  
} 

    public void showControls(int textX, int textY) {

    int x = textX +20;
    int y = textY + gp.ingame_size;
    final int lineHeight = 64;
   
    graphics2.setFont(zeldaFont);
    graphics2.setColor(Color.RED);
    graphics2.drawString("COMANDI",getCenteredXForText("COMANDI", graphics2),y);
    y += lineHeight;
    graphics2.setColor(Color.WHITE);
    graphics2.drawString("Spostati        < WASD >",x,y);
    y += lineHeight;
    graphics2.drawString("Conferma/Indietro        < ENTER >",x,y);
    y += lineHeight;
    graphics2.drawString("Pausa      < M >",x,y);
    y += lineHeight;
    graphics2.drawString("Attacca      < P >",x,y);
    y += lineHeight;
    graphics2.drawString("Corrrrrrere      < O >",x,y);
    y += lineHeight;
    }

    public void quitGame(int titleX,int titleY) {
        
        graphics2.setFont(zeldaFont);
        graphics2.setColor(Color.GREEN);
    
        int textX = getCenteredXForText("Rinunci da ex-avventuriero?", graphics2);
        int textY = titleY + gp.ingame_size;

        graphics2.setFont(eightBitFont);
        graphics2.drawString("Rinunci da ex-avventuriero?",textX,textY);
      
        textY += 80;

        graphics2.drawString("Sì, ho paura",textX,textY);
         if (commandNum == 0) {
            graphics2.drawString(">",textX - 25,textY);
        }
        textY += 60;


        graphics2.drawString("No, sono un eroe",textX,textY);
         if (commandNum == 1) {
            graphics2.drawString(">",textX - 25,textY);
        }
    }

    public void drawPauseScreen () {
        
        if (gp.gameState==gp.pauseState){
           
          
            final int x = gp.screen_width/2+50;
            final int y = 20 ;
        
            graphics2.setFont(zeldaFont);
            graphics2.setColor(Color.WHITE);
        
            int textX = x +20;
            int textY = y + gp.ingame_size;
            final int lineHeight = 48;
        
        
            graphics2.drawString("RIPOSINO",textX,textY);
            textY +=lineHeight*3;
            graphics2.setColor(Color.WHITE);
            graphics2.drawString("Premi M per continuare",textX,textY);
            graphics2.setColor(Color.BLACK);
            graphics2.drawString("Premi M per continuare",textX-2,textY+2);
            textY += lineHeight*2;
            textX += 10;
            graphics2.setColor(Color.WHITE);
            graphics2.drawString("Premi O per visitare",textX,textY);
            graphics2.setColor(Color.BLACK);
            graphics2.drawString("Premi O per visitare",textX-2,textY+2);
            textY += lineHeight;
            textX += 30;
            graphics2.setColor(Color.WHITE);
            graphics2.drawString("   le opzioni",textX,textY);
            graphics2.setColor(Color.BLACK);
            graphics2.drawString("   le opzioni",textX-2,textY+2);

            drawCharacterScreen(); 
    }
       
    
    }  
        
    public void drawGameOverScreen(){
        Image heartHalvedImage = heart.image2;
        Image heartFullImage = heart.image;
        
        if (gp.gameState == gp.gameOver) {

            int textX = gp.ingame_size*2;
            final int textY = gp.ingame_size * 8 ;

            graphics2.setColor(new Color(0,0,0,100));
            graphics2.fillRect(0,0,gp.screen_width,gp.screen_height);
            graphics2.setColor(Color.WHITE);
            graphics2.setFont(zeldaFont60);
            
            graphics2.drawString("NOOOOOOOO... sei morto",(getCenteredXForText("NOOOOOOOO... sei morto", graphics2)),gp.screen_height/4+10);

            graphics2.setFont(zeldaFont);
            
            graphics2.drawString("Riprova",textX,textY);
            if (gameOverChoice == 0) {
                graphics2.drawImage(heartFullImage,textX+36,textY-64,50,40,null);
            }

            textX = textX*7 ;
        
        
            graphics2.drawString("Torna al menu",textX,textY);
            if (gameOverChoice == 1) {
                

            graphics2.drawImage(heartHalvedImage,textX+68,textY-64,50,40,null);

            }

        }

    } 
    public void drawEndScreen(){
        if (endGame == true) {
            graphics2.setFont(zeldaFont60);
            graphics2.drawString("Avventura Completata",(getCenteredXForText("Avventura Completata", graphics2)),gp.screen_height/2-80);

            gp.gameTimer = null;
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
        graphics2.drawRoundRect(x,y,width-2,height-2,25,25);
    
    } 
    public void drawColoredSubWindow (int x,int y,int height, int width,Color background,Color border) {

       
        graphics2.setColor(background);
        graphics2.fillRoundRect(x,y,width,height,35,35);
        
        graphics2.setColor(border);
        graphics2.setStroke(new BasicStroke(3));
        graphics2.drawRoundRect(x,y,width-2,height-2,25,25);
    
    } 
public void drawCharacterScreen () {
    final int x = gp.ingame_size;
    final int y = gp.ingame_size ;
    final int width = gp.ingame_size*5;
    final int height = gp.ingame_size*10 ;

    drawSubWindow(x, y, height, width);

    graphics2.setFont(zeldaFont);
    graphics2.setColor(Color.BLUE);

    int textX = x +20;
    int textY = y + gp.ingame_size;
    final int lineHeight = 48;


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
    graphics2.drawImage(shoes.image,textX + (gp.ingame_size*2),textY - lineHeight/4*3,null);
    textY += lineHeight;}
    if (gp.giocatore.rich == true) {graphics2.drawString("riccone",textX,textY);
    graphics2.drawImage(coin.image,textX + (gp.ingame_size*3),textY - lineHeight/4*3,null);
    textY += lineHeight;}
    if (gp.giocatore.rich == false) {graphics2.drawString("povero",textX,textY);
    textY += lineHeight;}
    graphics2.drawString("avventura",textX,textY);
    textY += lineHeight;
    graphics2.drawString("",textX,textY);
    textY += lineHeight;
    graphics2.drawString("guerriero",textX,textY);
    textY += lineHeight;

}

}

