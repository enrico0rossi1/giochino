package entities;

import main.KeyboardInput;
import main.GamePanel;
import main.ScreenManager;

import java.awt.Font;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends GameEntity {

    GamePanel gp;
    KeyboardInput keyh;
    ScreenManager screenManager;
    public int ScreenX;
    public int ScreenY;
    public BufferedImage image;
    public int numKeys=0;
    public boolean speedUp = false;
    public boolean rich = false;

    
    public Player(GamePanel gp, KeyboardInput keyh,ScreenManager screenManager){

        super(gp);
        this.gp=gp;
        this.keyh=keyh;
        this.screenManager=screenManager;
       
        invincibleTime=30;
        collArea.x=12;
        collArea.y=10;
        solidAreaDefaultX = collArea.x;
        solidAreaDefaultY = collArea.y;
        collArea.width=gp.ingame_size-(collArea.x*2);
        collArea.height=gp.ingame_size-(collArea.y*2); //area di collisione del giocatore
        
        ScreenX = (gp.screen_width/2)-(gp.ingame_size/2);
        ScreenY = (gp.screen_height/2)-(gp.ingame_size/2); //coordinate centrali

        attackArea.x=5;
        attackArea.y=8;
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
        
        MoveUpAnimation=loadAnimation(6,"PlayerSprites/NewSprites/Up/moveUp");
        MoveDownAnimation=loadAnimation(6,"PlayerSprites/NewSprites/Down/moveDown");
        MoveRightAnimation=loadAnimation(6,"PlayerSprites/NewSprites/Right/moveRight");
        MoveLeftAnimation=loadAnimation(6,"PlayerSprites/NewSprites/Left/moveLeft");

        UpAnimation=loadAnimation(6, "PlayerSprites/NewSprites/Up/up");
        DownAnimation=loadAnimation(6, "PlayerSprites/NewSprites/Down/down");
        RightAnimation=loadAnimation(6, "PlayerSprites/NewSprites/Right/right");
        LeftAnimation=loadAnimation(6, "PlayerSprites/NewSprites/Left/left");

        AttackDown=loadAnimation(4, "PlayerSprites/NewSprites/Down/attackDown");
        AttackUp=loadAnimation(4, "PlayerSprites/NewSprites/Up/attackUp");
        AttackLeft=loadAnimation(4, "PlayerSprites/NewSprites/Left/attackLeft");
        AttackRight=loadAnimation(4, "PlayerSprites/NewSprites/Right/attackRight");
        
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
        if(gp.giocatore.invincible){
            gp.graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        graphics2.drawImage(image, ScreenX, ScreenY,null);
        gp.graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        graphics2.setFont(new Font("Arial", Font.PLAIN, 26));
        graphics2.setColor(Color.white);
        graphics2.drawString(""+invincibleCounter, 10,400 );

        // DEBUG
		// AttackArea
		// int tempScreenX = ScreenX + attackArea.x;
		// int tempScreenY = ScreenY + attackArea.y;		
		// switch(direzione) {
		    // case "up": tempScreenY = ScreenY - attackArea.height; break;
		    // case "down": tempScreenY = ScreenY + gp.ingame_size; break; 
		    // case "left": tempScreenX = ScreenX - attackArea.width; break;
		    // case "right": tempScreenX = ScreenX + gp.ingame_size; break;
		// }				
		// gp.graphics2.setColor(Color.red);
        // gp.graphics2.setStroke(new BasicStroke(1));
        
        
		// gp.graphics2.drawRect(tempScreenX, tempScreenY, attackArea.width, attackArea.height);
        // gp.graphics2.drawRect(ScreenX+collArea.x, ScreenY+collArea.y, collArea.height, collArea.width);
    }
        

       
}


    
