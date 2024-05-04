package object;

import main.Pannello;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Player.Entità;

import java.awt.Rectangle;

public class GameObject extends Entità {

    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;
    public int objWidth = 48;
    public int objHeight = 48;
   
    public Rectangle solidArea =new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;

    public void draw(Graphics2D graphics2,Pannello gp){

        int screenX = worldX-gp.giocatore.GiocatoreX + gp.giocatore.ScreenX;
        int screenY = worldY-gp.giocatore.GiocatoreY + gp.giocatore.ScreenY; 
       
        // if(worldX + gp.ingame_size>gp.giocatore.GiocatoreX-gp.giocatore.ScreenX &&
        // worldX - gp.ingame_size<gp.giocatore.GiocatoreX+gp.giocatore.ScreenX &&
        // worldY + gp.ingame_size>gp.giocatore.GiocatoreY-gp.giocatore.ScreenY &&
        // worldY - gp.ingame_size<gp.giocatore.GiocatoreY+gp.giocatore.ScreenY){ 
        graphics2.drawImage(image,screenX,screenY,objWidth,objHeight,null);
        
    
  }
}
