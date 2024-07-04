package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class GameScreens{

   GamePanel gp;

    public GameScreens(GamePanel gp){
        this.gp=gp;
    }

    public void drawTitleScreen(){

        int frameX = gp.ingame_size;
        int frameY = gp.ingame_size;
        
        if (gp.gameState == gp.titleState) {
            
           BufferedImage titleScreen= gp.ui.loadImage("main_rsc/GameScreens/WarriorAdventureTitleScreen.jpeg");    
            gp.ui.graphics2.drawImage(titleScreen,0,0,gp.screen_width,gp.screen_height, null);
              
            switch (gp.ui.titleSubState) {
                case 0: gp.ui.titleScreen(); break;
                case 1: gp.ui.titleOptions(); break;
                case 2: gp.ui.drawSubWindow(frameX, frameY, gp.screen_height-frameX*3, gp.screen_width - frameY*2);
                        gp.ui.showControls(frameX,frameY); break;
                case 3: gp.ui.drawFullScreenAlert(); break;
                case 4: gp.graphics2.setFont(gp.ui.zeldaFont);
                        gp.graphics2.setColor(Color.WHITE);
                        gp.ui.drawSubWindow(0, 500, gp.screen_height/8, gp.screen_width/2+30);
                        gp.ui.graphics2.drawString("Premi ENTER per tornare indietro",10,gp.screen_height-30);
                break;

            }
        }   
    }

    public void drawPlayState() {
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawPlayerKeys();
            drawMessages();
        }
    }

    public void drawPlayerLife() {
        Image heartFullImage = gp.ui.heart.image;
        Image heartHalvedImage = gp.ui.heart.image2;
        Image heartVoidImage = gp.ui.heart.image3;
    
        int x = gp.pix_row / 2;
        int y = gp.pix_cols / 2; 
        int height = gp.ingame_size;
        int width = gp.ingame_size * 4;
      

        Color bg = new Color(0,0,0,100);
        Color bord = new Color(255,0,0);
      
        gp.ui.drawColoredSubWindow(x,y,height,width + gp.ingame_size*3/2,bg,bord);
         
        // Disegna cuori vuoti
        for (int i = 0; i < gp.giocatore.vitaMax / 2; i++) {
            gp.graphics2.drawImage(heartVoidImage, x, y, null);
            x += 3 * gp.pix_row;
        }
    
        // Ripristina le coordinate per disegnare i cuori pieni e mezzi
        x = gp.pix_row / 2;
        int vita = gp.giocatore.vita;
        
        for (int i = 0; i < vita / 2; i++) {
            gp.graphics2.drawImage(heartFullImage, x, y, null);
            x += 3 * gp.pix_row;
        }
        
        // Disegna mezzo cuore se la vita è dispari
        if (vita % 2 == 1) {
            gp.graphics2.drawImage(heartHalvedImage, x, y, null);
        }
    }

    public void drawPlayerKeys() {
        int x = 5;
        int y = gp.screen_height - (gp.ingame_size*3/2) ;
        int height = 40;
        int width = 25 * 3;
        Image keyImage = gp.ui.key.image;
        Color bg = new Color(0,0,0,180);
        Color bord = new Color(204,119,34);
          
        gp.graphics2.setFont(gp.ui.eightBitFont);
        gp.ui.drawColoredSubWindow(x,y,height,width,bg,bord);
        gp.graphics2.drawString(" x " + gp.giocatore.numKeys, x + 25, y + 27);
        gp.graphics2.drawImage(keyImage, x+5, y+8, 25, 25, null);

    }

    public void drawMessages() {
        if (gp.ui.messageOn) {
            gp.ui.drawMessage(gp.ui.message, gp.ui.messageCounter);
            gp.ui.messageCounter++;
            if (gp.ui.messageCounter > gp.FPS * 5) {
                gp.ui.messageOn = false;
            }
        }
    
        if (gp.ui.message2On) {
            gp.ui.drawMessage(gp.ui.message2, gp.ui.message2Counter, -20);
            gp.ui.message2Counter++;
            if (gp.ui.message2Counter > gp.FPS * 5) {
                gp.ui.message2On = false;
            }
        }
    }

    public void drawDialogueState () {
        if (gp.gameState == gp.dialogueState) {
            //window
            int x = gp.ingame_size * 2;
            int y = gp.ingame_size/2 ;
            int height = gp.screen_height/4 ;
            int width = gp.screen_width - (gp.ingame_size * 4)  ;

            // disegna la finestra in cui inserire i dialoghi
            gp.ui.drawSubWindow(x,y,height,width);

            gp.graphics2.setFont(gp.ui.eightBitFont);
            x += gp.ingame_size;
            y += gp.ingame_size;

            for (String line : gp.ui.currentDialogue.split("\n")) {
                gp.graphics2.drawString(line,x,y);
                y += 40;
            }

            gp.graphics2.drawString(gp.ui.dialogueChoice1,x*2,y);
            if (gp.ui.dialogueChoice == 1) {
                gp.graphics2.drawString(">",x*2 - 20,y);
            }
            gp.graphics2.drawString(gp.ui.dialogueChoice2,x*4,y);
        
            if (gp.ui.dialogueChoice == 2) {
                gp.graphics2.drawString(">",x*4 - 20,y);
            }

            gp.graphics2.setFont(gp.ui.eightBitFont15);
            gp.graphics2.setColor(Color.BLACK);
            gp.graphics2.drawString("Premi Enter per confermare", x+gp.ingame_size*7-2, y+38);
            gp.graphics2.setColor(Color.WHITE);
            gp.graphics2.drawString("Premi Enter per confermare", x+gp.ingame_size*7, y+40);
        }
    }

    public void drawOptionsScreen() {

        int x = gp.ingame_size * 5 ;
        int y = gp.ingame_size;
        int width = gp.ingame_size * 10;
        int height = gp.ingame_size* 10;
      

        if (gp.gameState == gp.optionsState) {
            gp.ui.drawSubWindow(x, y, width, height);
            switch (gp.ui.subState) {
                case 0: gp.ui.options_top(x,y); break;
                case 1: gp.ui.showControls(x,y); break;
                case 2: gp.ui.quitGame(x,y); break;
            }

        }

    }

    public void drawPauseScreen () {
        
        if (gp.gameState==gp.pauseState){
           
            final int x = gp.screen_width/2+60;
            final int y = 20 ;
            Color bg = new Color(0,0,0,150);
            
            gp.ui.drawColoredSubWindow(x, y, x-20, gp.ingame_size*8, bg, Color.YELLOW);

            gp.graphics2.setFont(gp.ui.zeldaFont);
            gp.graphics2.setColor(Color.WHITE);
        
            int textX = x +20;
            int textY = y + gp.ingame_size;
            final int LINE_HEIGHT = 48;
            gp.graphics2.setColor(Color.YELLOW);
            gp.graphics2.drawString("RIPOSINO",textX,textY);
            textY +=LINE_HEIGHT*3;
            gp.graphics2.setColor(Color.WHITE);
            gp.graphics2.drawString("Premi M per continuare",textX,textY);
            textY += LINE_HEIGHT*2;
            textX += 10;
            gp.graphics2.drawString("Premi O per visitare",textX-2,textY+2);
            textY += LINE_HEIGHT;
            textX += 30;
            gp.graphics2.setColor(Color.WHITE);
            gp.graphics2.drawString("   le opzioni",textX,textY);

            gp.ui.drawCharacterScreen(); 
        }
    }

    public void drawGameOverScreen(){
        Image heartHalvedImage = gp.ui.heart.image2;
        Image heartFullImage = gp.ui.heart.image;
        
        if (gp.gameState == gp.gameOver) {

            int textX = gp.ingame_size*2 + 8;
            final int textY = gp.ingame_size * 8 ;
            BufferedImage deathImage = gp.ui.loadImage("main_rsc/GameScreens/death2.png");

            gp.graphics2.setColor(new Color(0,0,0,100));
            gp.graphics2.fillRect(0,0,gp.screen_width,gp.screen_height);
            gp.graphics2.setColor(Color.WHITE);
            gp.graphics2.setFont(gp.ui.zeldaFont60);
         
            gp.graphics2.drawString("NOOOOOOOO... sei morto",(gp.ui.getCenteredXForText("NOOOOOOOO... sei morto", gp.graphics2)),gp.screen_height/4+10);

            gp.graphics2.setFont(gp.ui.zeldaFont);
            gp.graphics2.drawImage(deathImage,gp.screen_width/4 - 25,gp.screen_height/4,gp.screen_width/2,gp.screen_height/2,null);
          
            gp.graphics2.drawString("Riprova",textX,textY);
            if (gp.ui.gameOverChoice == 0) {
                gp.graphics2.drawImage(heartFullImage,textX+36,textY-64,50,40,null);
            }

            textX = textX*7 ;
            gp.graphics2.drawString("Torna al menu",textX,textY);
            
            if (gp.ui.gameOverChoice == 1) {
                

                gp.graphics2.drawImage(heartHalvedImage,textX+68,textY-64,50,40,null);

            }
        }

    } 

     
    public void drawEndScreen(){
        if (gp.gameState == gp.endGame) {
            
            Color bg = new Color(0,0,0,100);
            Color bord = new Color(255,0,0);
            BufferedImage endGameScreen = gp.ui.loadImage("main_rsc/GameScreens/gameEnding.jpeg");

            gp.graphics2.drawImage(endGameScreen,0,0,gp.screen_width,gp.screen_height,null);  
            gp.graphics2.setFont(gp.ui.zeldaFont60);
            gp.ui.drawColoredSubWindow(gp.ui.getCenteredXForText("Avventura Completata", gp.ui.graphics2)-8,gp.screen_height/6-10,gp.ingame_size*3/2,gp.screen_width/3*2+3,bg,bord);  
            gp.graphics2.drawString("Avventura Completata",(gp.ui.getCenteredXForText("Avventura Completata", gp.graphics2)),gp.screen_height/4);
            gp.graphics2.drawString("Sei il miglior GUERRO",(gp.ui.getCenteredXForText("Sei il miglior GUERRO", gp.graphics2)),gp.screen_height*2/3);
            gp.graphics2.setFont(gp.ui.eightBitFont);
            gp.graphics2.setColor(new Color(0,0,0));
            gp.graphics2.drawString("Premi Enter per tornare al menù principale",gp.ui.getCenteredXForText("Premi Enter per tornare al menù principale",gp.graphics2),gp.screen_height - 40);
            gp.gameTimer = null;
        }
        
    }

    
}
