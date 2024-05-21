package object;

import main.Pannello;
import main.UtilityTool;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Personaggi.Entità;

import java.awt.Rectangle;

public class GameObject extends Entità {

    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public int objWidth = 48;
    public int objHeight = 48;
   
    public Rectangle solidArea =new Rectangle(worldX,worldY,objWidth,objHeight);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;

    public UtilityTool uTool=new UtilityTool();

    public void draw(Graphics2D graphics2,Pannello gp){

        int screenX = worldX-gp.giocatore.posizioneX + gp.giocatore.ScreenX;
        int screenY = worldY-gp.giocatore.posizioneY + gp.giocatore.ScreenY; 
       
        if(worldX + gp.ingame_size>gp.giocatore.posizioneX-gp.giocatore.ScreenX &&
        worldX - gp.ingame_size<gp.giocatore.posizioneX+gp.giocatore.ScreenX &&
        worldY + gp.ingame_size>gp.giocatore.posizioneY-gp.giocatore.ScreenY &&
        worldY - gp.ingame_size<gp.giocatore.posizioneY+gp.giocatore.ScreenY){ 
        graphics2.drawImage(image,screenX,screenY,null);
      }
    }
}
