package Entità;



import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import main.Pannello;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Entità{

    public Pannello gp;
   
    public int velocità;
    public String direzione="down";
    public String anmImage_Moveup,anmImage_Moveright,anmImage_Moveleft,anmImage_Movedown;
    public String anmImage_up,anmImage_down,anmImage_right,anmImage_left;
    public String anmImage_atUp,anmImage_atDown,anmImage_atRight,anmImage_atLeft;
    public BufferedImage image,image2,image3;
    public String name;
    public int actionLockCounter=0;
    public int objWidth,objHeight;
    public UtilityTool uTool = new UtilityTool();
    public int mapVerifier;
    public int worldY,worldX;
    

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
    
    public String attack;
    

    public int spriteCount=0;
    public int spriteNum=0;

    public Rectangle collArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean solid;

    //stato dell'entità
    public int vitaMax;
    public int vita;

    public Entità(Pannello gp) {
        this.gp = gp;
        collArea= new Rectangle(0,0,gp.ingame_size,gp.ingame_size);
    }

    public void animationRoller(){
        final int frameInterval = 200* 1000000;
        long currentTime = System.nanoTime();
        
        
        if (currentTime - gp.lastTime >= frameInterval) {
            gp.giocatore.spriteNum = (gp.giocatore.spriteNum + 1) % gp.giocatore.MoveDownAnimation.length;
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

    public void update(){
        
        
    }


    public void draw(Graphics2D graphics2){
        
        int screenX = worldX-gp.giocatore.worldX + gp.giocatore.ScreenX;
        int screenY = worldY-gp.giocatore.worldY + gp.giocatore.ScreenY; 
       
        if(worldX + gp.ingame_size>gp.giocatore.worldX-gp.giocatore.ScreenX &&
        worldX - gp.ingame_size<gp.giocatore.worldX+gp.giocatore.ScreenX &&
        worldY + gp.ingame_size>gp.giocatore.worldY-gp.giocatore.ScreenY &&
        worldY - gp.ingame_size<gp.giocatore.worldY+gp.giocatore.ScreenY){ 
        graphics2.drawImage(image,screenX,screenY,null);
        }
      
    }
}
