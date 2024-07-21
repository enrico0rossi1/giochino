package warrioradventure;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.gameobjects.ObjCoin;
import entities.gameobjects.ObjHeart;
import entities.gameobjects.ObjKey;
import entities.gameobjects.ObjShoes;


public class UserInterface {

    GamePanel gp;
    Font zeldaFont30;
    Font zeldaFont60;
    Font zeldaFont80;
    Font eightBitFont20;
    Font eightBitFont15;
    Graphics2D graphics2;
    KeyboardInput keyh;
    ObjHeart heart;
    ObjKey key;
    ObjShoes shoes;
    ObjCoin coin;
    
    int titleSubState = 0;
    public boolean messageOn = false;
    public boolean message2On = false;
    public String message = "";
    public String message2= "";
    int messageCounter = 0;
    int message2Counter = 0;
    
   
    public String currentDialogue = "";
    public String dialogueChoice1 = "";
    public String dialogueChoice2 = "";
    int subState = 0;
    int commandNum = 0;

    int titleChoice = 0;
    public int dialogueChoice = 0;
    int gameOverChoice;
   

    public UserInterface (GamePanel gp) {
        
        this.gp = gp;
        this.key = new ObjKey(gp);
        this.heart = new ObjHeart(gp);
        this.shoes = new ObjShoes(gp);
        this.coin = new ObjCoin(gp);
        
        
        //elenco dei font utilizzati nell'interfaccia

        try {

            zeldaFont30 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("main_rsc/Font/ZeldaFont.otf")).deriveFont(30f);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading font: " + e.getMessage());
        }

        try {
            eightBitFont20 = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("main_rsc/Font/8BitFont.ttf")).deriveFont(20f);
        } catch (IOException | FontFormatException f) {
            f.printStackTrace();
            System.err.println("Error loading font: " + f.getMessage());
        } 


        zeldaFont60 = zeldaFont30.deriveFont(60f);
        zeldaFont80 = zeldaFont30.deriveFont(80f);
        eightBitFont15 = eightBitFont20.deriveFont(15f);
    }


    
    public void draw (Graphics2D graphics2) {
        
        this.graphics2=graphics2;

        gp.gScreens.drawTitleScreen();
        gp.gScreens.drawPlayState();  
        gp.gScreens.drawDialogueState();
        gp.gScreens.drawOptionsScreen();
        gp.gScreens.drawPauseScreen();
        gp.gScreens.drawGameOverScreen();
        gp.gScreens.drawEndScreen();
      
    }
    

    public void titleScreen() {
    
        int textY = gp.screen_height / 2 + 75;
    
       
        Color bg = new Color(0, 0, 0, 150);
        Color bord = new Color(0, 127, 255);
        Color yellow = Color.YELLOW;
    
        graphics2.setFont(zeldaFont80);
    
        String mainTitle = "WARRIOR ADVENTURE";
        int mainTitleX = getCenteredXForText(mainTitle, graphics2);
        drawColoredSubWindow(mainTitleX - 15, gp.screen_height / 4 - 90, gp.ingame_size * 2, gp.screen_width - gp.ingame_size * 3 / 2 + 5, bg, bord);
        graphics2.drawString(mainTitle, mainTitleX - 2, gp.screen_height / 2 - 160);
        graphics2.setColor(yellow);
        graphics2.drawString(mainTitle, mainTitleX, gp.screen_height / 2 - 156);
    
        graphics2.setFont(zeldaFont60);
    

        String[] menuOptions = { "NUOVA AVVENTURA", "OPZIONI", "SWAG" };
        int optionIndex = 0;
        drawColoredSubWindow(getCenteredXForText("NUOVA AVVENTURA",graphics2)-60,gp.screen_height/2,gp.screen_height/2-30,gp.screen_width-280,bg,bord);

    
        for (String option : menuOptions) {
            int optionX = getCenteredXForText(option, graphics2);
    
            graphics2.setColor(bord);
            graphics2.drawString(option, optionX, textY);
    
            // Highlight selected option
            if (titleChoice == optionIndex) {
                graphics2.setColor(yellow);
                graphics2.drawString(">", optionX - 30, textY);
                graphics2.drawString(option, optionX + 2, textY + 2);
            }
    
            // Move to the next option
            textY += 80;
            optionIndex++;
        }


    }
    
      
    public void titleOptions(){

       // int textY = gp.screen_height/2+75;
        Color bg = new Color(0,0,0,180);
        Color bord = new Color(0,127,255); 
        graphics2.setFont(zeldaFont30);
       
        int titleY = gp.ingame_size*2;
        int titleX = getCenteredXForText("Opzioni", graphics2)-30; 
        int textX = titleX - gp.ingame_size;
        int textY = titleY + gp.ingame_size/2;
        final int LINE_HEIGHT = 64;

        textY +=LINE_HEIGHT;
        textX -=LINE_HEIGHT * 2 +10;
           
        drawColoredSubWindow(textX-LINE_HEIGHT, titleY-LINE_HEIGHT, gp.screen_height*2/3+80,gp.screen_width*2/3, bg, bord); 
        graphics2.drawString("Opzioni",titleX,titleY);
        graphics2.setColor(Color.WHITE);   
        graphics2.drawString("Schermo intero  ",textX, textY);

        if (titleChoice == 0) {
                  graphics2.drawString(">",textX - 25,textY);
        }

        if (gp.fullScreenOn == true) {
            graphics2.drawString(" ON",textX*3,textY);
        }else if (gp.fullScreenOn == false) {
            graphics2.drawString(" OFF",textX*5/2,textY);
        }
              
        textY += LINE_HEIGHT;
        graphics2.drawString("Musica Volume",textX,textY);
             
        if (titleChoice == 1) {
            graphics2.drawString(">",textX - 25,textY);
        }

        graphics2.drawRect(textX+(gp.ingame_size*6),textY+ -LINE_HEIGHT/3,120,24);
        int volumeWidth = 24 * gp.music.volumeScale;
        graphics2.fillRect(textX+(gp.ingame_size*6),textY+ -LINE_HEIGHT/3,volumeWidth,24); 
        textY += LINE_HEIGHT;
        graphics2.drawString("SFX Volume",textX,textY);

        if (titleChoice == 2) {
            graphics2.drawString(">",textX - 25,textY);
        }  

        graphics2.drawRect(textX+(gp.ingame_size*6),textY+ -LINE_HEIGHT/3,120,24);
        int sfxWidth = 24 * gp.sfx.volumeScale;
        graphics2.fillRect(textX+(gp.ingame_size*6),textY+ -LINE_HEIGHT/3,sfxWidth,24);
        textY += LINE_HEIGHT;
        graphics2.drawString("Comandi",textX,textY);
        
        if (titleChoice == 3) {
            graphics2.drawString(">",textX - 25,textY);
        }
        textY += LINE_HEIGHT;
        graphics2.drawString("Indietro",textX,textY);

        if (titleChoice == 4) {
               graphics2.drawString(">",textX - 25,textY);
        }

    }

    public void drawFullScreenAlert () {
        drawSubWindow (12,4,gp.ingame_size, gp.ingame_size*15-16);
        String alert = "Riavvia il gioco per disattivare lo schermo intero" ;
        graphics2.setColor(Color.WHITE);
        graphics2.drawString(alert,18,38);
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
    
    public void drawMessage(String message, int counter) {
        drawMessage(message, counter, 0);
    }
    
    public void drawMessage(String message, int counter, int offsetY) {
        graphics2.setFont(eightBitFont20);
        graphics2.setColor(Color.WHITE);
        int messageX = (gp.screen_width / 6*4);
        int messageY = gp.screen_height - (gp.screen_height / 4) + offsetY;
        graphics2.drawString(message, messageX, messageY);
    
    }


     
    public void options_top (int titleX,int titleY) {
        graphics2.setFont(zeldaFont30);
        graphics2.setColor(Color.BLUE);

        int textX = getCenteredXForText("Opzioni", graphics2);
        int textY = titleY + gp.ingame_size;
        final int LINE_HEIGHT = 64;
        
        graphics2.drawString("Opzioni",textX,textY);
        textY +=LINE_HEIGHT;
        textX -=LINE_HEIGHT * 2 +10;
        graphics2.setColor(Color.WHITE);
        graphics2.drawString("Schermo intero  ",textX, textY);
        
        if (commandNum == 0) {
            graphics2.drawString(">",textX - 25,textY);
        }   
        
        if (gp.fullScreenOn == true) {
            graphics2.drawString(" ON",textX*2,textY);
        }else if (gp.fullScreenOn == false) {
            graphics2.drawString(" OFF",textX*2,textY);
        }
        
        textY += LINE_HEIGHT;
        graphics2.drawString("Musica Volume",textX,textY);
        
        if (commandNum == 1) {
            graphics2.drawString(">",textX - 25,textY);
        }
        graphics2.drawRect(textX+(gp.ingame_size*6),textY+ -LINE_HEIGHT/3,120,24);
        int volumeWidth = 24 * gp.music.volumeScale;
        graphics2.fillRect(textX+(gp.ingame_size*6),textY+ -LINE_HEIGHT/3,volumeWidth,24);  // (textX+(gp.ingame_size*6))/ 5
        textY += LINE_HEIGHT;
        graphics2.drawString("SFX Volume",textX,textY);
        
        if (commandNum == 2) {
            graphics2.drawString(">",textX - 25,textY);
        }    
        
        graphics2.drawRect(textX+(gp.ingame_size*6),textY+ -LINE_HEIGHT/3,120,24);
        int sfxWidth = 24 * gp.sfx.volumeScale;
        graphics2.fillRect(textX+(gp.ingame_size*6),textY+ -LINE_HEIGHT/3,sfxWidth,24);
        textY += LINE_HEIGHT;
        graphics2.drawString("Comandi",textX,textY);

        if (commandNum == 3) {
            graphics2.drawString(">",textX - 25,textY);
        }

        textY += LINE_HEIGHT;
        graphics2.drawString("Esci",textX,textY);

        if (commandNum == 4) {
            graphics2.drawString(">",textX - 25,textY);
        }
        textY += LINE_HEIGHT + 6;
        graphics2.drawString("Indietro",textX,textY);

        if (commandNum == 5) {
            graphics2.drawString(">",textX - 25,textY);
        }
    } 

    public void showControls(int textX, int textY) {

        int x = textX +20;
        int y = textY + gp.ingame_size;
        final int LINE_HEIGHT = 64;
        
        graphics2.setFont(zeldaFont30);
        graphics2.setColor(Color.RED);
        graphics2.drawString("COMANDI",getCenteredXForText("COMANDI", graphics2),y);
        y += LINE_HEIGHT;
        graphics2.setColor(Color.WHITE);
        graphics2.drawString("Spostati        < WASD >",x,y);
        y += LINE_HEIGHT;
        graphics2.drawString("Conferma/Indietro        < ENTER >",x,y);
        y += LINE_HEIGHT;
        graphics2.drawString("Pausa      < M >",x,y);
        y += LINE_HEIGHT;
        graphics2.drawString("Attacca      < P >",x,y);
        y += LINE_HEIGHT;
        graphics2.drawString("Correre      < O >",x,y);
        y += LINE_HEIGHT;
    }

    public void quitGame(int titleX,int titleY) {
        
        graphics2.setFont(zeldaFont30);
        graphics2.setColor(Color.WHITE);
    
        int textX = getCenteredXForText("Rinunci da ex-avventuriero?", graphics2);
        int textY = titleY + gp.ingame_size;

        graphics2.setFont(eightBitFont20);
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
    
    public void drawCharacterScreen () {
        final int x = gp.ingame_size;
        final int y = gp.ingame_size ;
        final int width = gp.ingame_size*5;
        final int height = gp.ingame_size*10 ;
        Color bg = new Color(0,0,0,150);
    
        drawColoredSubWindow(x, y, height, width,bg ,Color.BLUE  );
    
        graphics2.setFont(zeldaFont30);
        
    
        int textX = x +20;
        int textY = y + gp.ingame_size;
        final int LINE_HEIGHT = 48;
 
        graphics2.setColor(Color.BLUE);
        graphics2.drawString("STATUS",textX,textY);
        textY +=LINE_HEIGHT;
        graphics2.setColor(Color.WHITE);
        graphics2.drawString("vita  "+ gp.giocatore.vita+"/"+gp.giocatore.vitaMax,textX,textY);
        textY += LINE_HEIGHT;
        graphics2.drawString("armato",textX,textY);
        textY += LINE_HEIGHT;
        if (gp.giocatore.speedUp == false) {graphics2.drawString("no drip",textX,textY);
        textY += LINE_HEIGHT;}
        if (gp.giocatore.speedUp == true) {graphics2.drawString("flash",textX,textY);
        graphics2.drawImage(shoes.image,textX + (gp.ingame_size*2),textY - LINE_HEIGHT/4*3,null);
        textY += LINE_HEIGHT;}
        if (gp.giocatore.rich == true) {graphics2.drawString("riccone",textX,textY);
        graphics2.drawImage(coin.image,textX + (gp.ingame_size*3),textY - (LINE_HEIGHT/4*2)+5,null);
        textY += LINE_HEIGHT;}
        if (gp.giocatore.rich == false) {graphics2.drawString("povero",textX,textY);
        textY += LINE_HEIGHT;}
        graphics2.drawString("avventura",textX,textY);
        textY += LINE_HEIGHT;
        graphics2.drawString("",textX,textY);
        textY += LINE_HEIGHT;
        graphics2.drawString("guerriero",textX,textY);
        textY += LINE_HEIGHT;
    }  
   

    public BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCenteredXForText (String text,Graphics2D graphics2) {
       
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


}

