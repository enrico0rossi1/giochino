package entities;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import warrioradventure.GamePanel;
import warrioradventure.KeyboardInput;
import warrioradventure.ScreenSizeManager;

public class Player extends GameEntity {

    GamePanel gp;
    KeyboardInput keyh;
    ScreenSizeManager screenManager;
    public int ScreenX;
    public int ScreenY;
    public BufferedImage image;
    public int numKeys=0;
    public boolean speedUp = false;
    public boolean rich = false;

    
    public Player(GamePanel gp, KeyboardInput keyh,ScreenSizeManager screenManager){

        super(gp);
        this.gp=gp;
        this.keyh=keyh;
        this.screenManager=screenManager;
       
        invincibleTime=30;
        collArea.x=20;
        collArea.y=20;
        solidAreaDefaultX = collArea.x;
        solidAreaDefaultY = collArea.y;
        collArea.width=gp.ingame_size-(collArea.x*2);
        collArea.height=gp.ingame_size-(collArea.y*2); //area di collisione del giocatore
        
        ScreenX = (gp.screen_width/2)-(gp.ingame_size/2);
        ScreenY = (gp.screen_height/2)-(gp.ingame_size/2); //coordinate centrali

        attackArea.x=10;
        attackArea.y=10;
        attackArea.width=gp.ingame_size-(attackArea.x*2);
        attackArea.height=gp.ingame_size-(attackArea.y*2);

        setValoriPredefiniti();
        getPlayerImage();
    }

    public void setValoriPredefiniti(){
        worldX=gp.ingame_size*25+23;  
        worldY=gp.ingame_size*25-13; //posizione iniziale su mappa 
        speed=2;
        direzione="down";

        vitaMax = 10;
        vita = vitaMax;
        numKeys=0;
        speedUp = false;
        rich = false;
        invincible=false;
    }
    
    public void getPlayerImage(){
        
        MoveUpAnimation=loadAnimation(6,"PlayerSprites/NewSprites/Up/moveUp", true);
        MoveDownAnimation=loadAnimation(6,"PlayerSprites/NewSprites/Down/moveDown", true);
        MoveRightAnimation=loadAnimation(6,"PlayerSprites/NewSprites/Right/moveRight", true);
        MoveLeftAnimation=loadAnimation(6,"PlayerSprites/NewSprites/Left/moveLeft", true);

        UpAnimation=loadAnimation(6, "PlayerSprites/NewSprites/Up/up", true);
        DownAnimation=loadAnimation(6, "PlayerSprites/NewSprites/Down/down", true);
        RightAnimation=loadAnimation(6, "PlayerSprites/NewSprites/Right/right", true);
        LeftAnimation=loadAnimation(6, "PlayerSprites/NewSprites/Left/left", true);

        AttackDown=loadAnimation(4, "PlayerSprites/NewSprites/Down/attackDown", false);
        AttackUp=loadAnimation(4, "PlayerSprites/NewSprites/Up/attackUp",false);
        AttackLeft=loadAnimation(4, "PlayerSprites/NewSprites/Left/attackLeft",false);
        AttackRight=loadAnimation(4, "PlayerSprites/NewSprites/Right/attackRight",false);
        
    }
   
    public void update(){
    
        if (vita == 0) {
            gp.gameState = gp.gameOver;
          
            gp.stopMusic(gp.eventHandler.currentMapIndex);
            gp.playSFX(8);
        }
   
        mapVerifier=gp.eventHandler.currentMapIndex;
        gp.pTools.moveOBJChecker();
        
        if(this.keyh.p){
            gp.pTools.attackAnimationRoller();
            
        }else{
            animationRoller();
        }
        gp.pTools.chooseSprite();

    }

    public void draw(Graphics2D graphics2){
        int tempScreenX = ScreenX;
		int tempScreenY = ScreenY;	
        if(gp.giocatore.invincible){
            gp.graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if(keyh.p){
            switch (direzione) {
                case "left":
                    tempScreenX= ScreenX - gp.ingame_size;
                break;

                case "up":
                    tempScreenY=ScreenY-gp.ingame_size;
                break;
            }
        }
        graphics2.drawImage(image, tempScreenX, tempScreenY,null);
        gp.graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
     


    }
        

       
}


    
