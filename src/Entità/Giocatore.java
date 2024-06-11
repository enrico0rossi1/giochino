package Entità;



import main.InputTastiera;
import main.Pannello;
import main.ScreenManager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Giocatore extends Entità {


    InputTastiera keyh;
    ScreenManager screenManager;
    public int ScreenX;
    public int ScreenY;
    public BufferedImage image;
    public int numKeys=0;
    public boolean speedUp = false;

    
    public Giocatore(Pannello gp, InputTastiera keyh,ScreenManager screenManager){

        super(gp);
        this.keyh=keyh;
        this.screenManager=screenManager;
       
        collArea = new Rectangle();
        collArea.x=12;
        collArea.y=18;
        solidAreaDefaultX = collArea.x;
        solidAreaDefaultY = collArea.y;
        collArea.width=gp.ingame_size-(collArea.x*2);
        collArea.height=(gp.ingame_size-collArea.y)-9; //area di collisione del giocatore
        
        ScreenX = (gp.screen_width/2)-(gp.ingame_size/2);
        ScreenY = (gp.screen_height/2)-(gp.ingame_size/2); //coordinate centrali

        setValoriPredefiniti();
        getPlayerImage();
    }

    public void setValoriPredefiniti(){
        posizioneX=gp.ingame_size*25;  
        posizioneY=gp.ingame_size*25; //posizione iniziale su mappa 
        velocità=3;
        direzione="down";

        vitaMax = 10;
        vita = vitaMax;
    }
    
    public void getPlayerImage(){
        
        MoveUpAnimation=loadAnimation(6,"Sprites/NewSprites/Up/moveUp");
        MoveDownAnimation=loadAnimation(6,"Sprites/NewSprites/Down/moveDown");
        MoveRightAnimation=loadAnimation(6,"Sprites/NewSprites/Right/moveRight");
        MoveLeftAnimation=loadAnimation(6,"Sprites/NewSprites/Left/moveLeft");

        UpAnimation=loadAnimation(6, "Sprites/NewSprites/Up/up");
        DownAnimation=loadAnimation(6, "Sprites/NewSprites/Down/down");
        RightAnimation=loadAnimation(6, "Sprites/NewSprites/Right/right");
        LeftAnimation=loadAnimation(6, "Sprites/NewSprites/Left/left");

        AttackDown=loadAnimation(6, "Sprites/NewSprites/Down/attackDown");
        AttackUp=loadAnimation(6, "Sprites/NewSprites/Up/attackUp");
        AttackLeft=loadAnimation(6, "Sprites/NewSprites/Left/attackLeft");
        AttackRight=loadAnimation(6, "Sprites/NewSprites/Right/attackRight");

        
    }
   
    public void update(){
    
        if (vita == 0) {
            gp.gameState = gp.gameOver;
        }
   
        mapVerifier=gp.eventHandler.currentMap;
        gp.pTools.moveOBJChecker();
        animationRoller();
        gp.pTools.printSprite();
        
        

        
    }
    
    public void draw(Graphics2D graphics2){
        graphics2.drawImage(image, ScreenX, ScreenY,null);
    }
        

       
}


    
