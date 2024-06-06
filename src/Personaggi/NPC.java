package Personaggi;

import main.Pannello;
import main.UtilityTool;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.awt.Rectangle;

public class NPC extends Entità {

  

    public BufferedImage image;
    public String name;
    public boolean collision = true;
   
    public Rectangle solidArea =new Rectangle(posizioneX,posizioneY,gp.ingame_size,gp.ingame_size);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
   
    public int mapVerifier=0;

    public UtilityTool uTool=new UtilityTool(gp);

    public NPC(Pannello gp) {
    super(gp);
    this.gp = gp; 
  }
    public void draw(Graphics2D graphics2,Pannello gp){

      
        int screenX = posizioneX-gp.giocatore.posizioneX + gp.giocatore.ScreenX;
        int screenY = posizioneY-gp.giocatore.posizioneY + gp.giocatore.ScreenY; 
     
     
        graphics2.drawImage(image,screenX,screenY,null);
      
    }
}
