package Entità;


import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.AlphaComposite;
import java.awt.Color;


public class Entità{

    public Pannello gp;
    public UtilityTool uTool = new UtilityTool();

    //ATTRIBUTI FONDAMENTALI
    public String name;
    public String direzione="down";
    public int speed;
    public int mapVerifier; 
    public int invincibleTime;
    public int type; // 0 = player, 1 = npc, 2 = monster
    public int worldY,worldX;
    public int vitaMax,vita;
    public int killNum = 1;
    
    

    //GESTIONE ANIMAZIONI
    public int objWidth,objHeight;
    public String anmImage_Moveup,anmImage_Moveright,anmImage_Moveleft,anmImage_Movedown;
    public String anmImage_up,anmImage_down,anmImage_right,anmImage_left;
    public String anmImage_atUp,anmImage_atDown,anmImage_atRight,anmImage_atLeft;
    public BufferedImage image,image2,image3;
    public BufferedImage[]UpAnimation=new BufferedImage[6];
    public BufferedImage[]DownAnimation=new BufferedImage[6];
    public BufferedImage[]RightAnimation=new BufferedImage[6];
    public BufferedImage[]LeftAnimation=new BufferedImage[6];
    public BufferedImage[]MoveUpAnimation=new BufferedImage[6];
    public BufferedImage[]MoveDownAnimation=new BufferedImage[6];
    public BufferedImage[]MoveRightAnimation=new BufferedImage[6];
    public BufferedImage[]MoveLeftAnimation=new BufferedImage[6];
    public BufferedImage[]AttackDown = new BufferedImage[6];
    public BufferedImage[]AttackUp = new BufferedImage[6];
    public BufferedImage[]AttackLeft = new BufferedImage[6];
    public BufferedImage[]AttackRight = new BufferedImage[6];
    public BufferedImage[]DeathAnimation = new BufferedImage[4];

    //ELEMENTI DI GAMEPLAY
    public Rectangle attackArea=new Rectangle(0,0,0,0);
    public Rectangle collArea=new Rectangle(0,0,48,48);
    public boolean invincible=false;
    public boolean alive=true;
    public boolean dead = false;
    public boolean solid;
    public int solidAreaDefaultX, solidAreaDefaultY;

    //VARIABILI PER LA GESTIONE DEI METODI
    public int actionLockCounter=0;
    public int invincibleCounter=0;
    public boolean checked = false;
    public int spriteCount=0;
    public int spriteNum=0;
    public int deathCounter=60;
 

    public Entità(Pannello gp) {
        this.gp = gp;
        
    }

    //ENTITY TOOLS
    public void animationRoller(){
        final int frameInterval = 200* 1000000;
        long currentTime = System.nanoTime();
        
        
        if (currentTime - gp.lastTime >= frameInterval) {
            
            spriteNum = (spriteNum+1) % MoveDownAnimation.length;
            gp.lastTime = currentTime;
        }

    }

    public BufferedImage[] loadAnimation(int dimension, String importPath) {
        BufferedImage[] animation = new BufferedImage[dimension];
        UtilityTool uTool = new UtilityTool();
        int ingameSize = gp.ingame_size; // Variabile locale per migliorare leggibilità e ridurre chiamate ripetitive

        if (dimension==1){
           try {

                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(importPath + ".png"));
                animation[0]=uTool.scaleImage(image, ingameSize, ingameSize);
            }
                catch (Exception e) {
                e.printStackTrace();
            }
        }else if(dimension>1){
            try {
                for (int i = 0; i < dimension; i++) {
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream(importPath + (i + 1) + ".png"));
                    animation[i] = uTool.scaleImage(image, ingameSize, ingameSize);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 

        }
        

        return animation;
    
    }

    public void movement(){
        
        gp.CollisionManager.checkTile(this);
        boolean cPlayer=gp.CollisionManager.checkPlayer(this);

        if(this.type==2 && cPlayer && gp.giocatore.invincible==false){
            gp.giocatore.vita--;
            gp.giocatore.invincible=true;
        }
        if(solid==false && vita>0){    
         switch (direzione) {
            case "up": 
                worldY-=speed; 
                image = MoveUpAnimation[spriteNum];
            break;
            case "right": 
                worldX+=speed;
                image = MoveRightAnimation[spriteNum]; 
            break;
            case "left": 
                worldX-=speed; 
                image = MoveLeftAnimation[spriteNum];
            break;
            case "down": 
                worldY+=speed; 
                image = MoveDownAnimation[spriteNum];
            break;
            }
        }
    }

    public void setAction(){
        
        actionLockCounter++;

        if(actionLockCounter==40){
            Random random = new Random();
            int i = random.nextInt(80)+1;

            if(i>=0 && i<20){
                direzione="up";
            }
            if(i>=20 && i<40){
                direzione="right";
            }
            if(i>=40 && i<60){
                direzione="left";
            }
            if(i>=60 && i<80){
                direzione="down";
            }

            actionLockCounter=0;

        }


    }

    public void damageReaction(){
        actionLockCounter=0;
        direzione=gp.giocatore.direzione;
    }

    public void invincible(int time){
        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>time){
                invincible=false;
                invincibleCounter=0;
            }

        }
    }  

    public void checkStatus(){
        if (vita<=0 ){
            image=DeathAnimation[spriteNum];
            deathCounter--;
            if(deathCounter==0){
                dead=true;
                gp.giocatore.killNum++;
                deathCounter=60;
            }
        }
       
        
        
    }


    public void update(){
        
        
    }


    public void draw(Graphics2D graphics2){
        
        int screenX = worldX-gp.giocatore.worldX + gp.giocatore.ScreenX;
        int screenY = worldY-gp.giocatore.worldY + gp.giocatore.ScreenY;
        
        if(invincible){
            gp.graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        graphics2.drawImage(image, screenX, screenY,null);
        gp.graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
       
        if(worldX + gp.ingame_size>gp.giocatore.worldX-gp.giocatore.ScreenX &&
        worldX - gp.ingame_size<gp.giocatore.worldX+gp.giocatore.ScreenX &&
        worldY + gp.ingame_size>gp.giocatore.worldY-gp.giocatore.ScreenY &&
        worldY - gp.ingame_size<gp.giocatore.worldY+gp.giocatore.ScreenY){ 
            if(invincible){
                gp.graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
            }

            graphics2.drawImage(image, screenX, screenY,null);
            gp.graphics2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            
        }

        //MONSTER HEALTH BAR
        if(type==2){
            double oneScale = (double)gp.ingame_size/vitaMax;
            int hbValue = (int)oneScale*vita;
            gp.graphics2.setColor(new Color(35,35,35));
            gp.graphics2.fillRect(screenX-1, screenY-16, gp.ingame_size+2, 12);
            gp.graphics2.setColor(new Color(255,0,30));
            gp.graphics2.fillRect(screenX, screenY-15,hbValue, 10);
        }
      
    }
}
