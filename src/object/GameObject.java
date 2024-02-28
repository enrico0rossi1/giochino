package object;

import main.Pannello;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX,worldY;

    public void draw(Graphics2D g2,Pannello gp){

            int screenX = worldX-gp.giocatore.GiocatoreX + gp.giocatore.ScreenX;
            int screenY = worldY-gp.giocatore.GiocatoreY + gp.giocatore.ScreenY;

           /*  if(worldX + gp.ingame_size>gp.giocatore.GiocatoreX-gp.giocatore.ScreenX &&
               worldX - gp.ingame_size<gp.giocatore.GiocatoreX+gp.giocatore.ScreenX &&
               worldY + gp.ingame_size>gp.giocatore.GiocatoreY-gp.giocatore.ScreenY &&
               worldY - gp.ingame_size<gp.giocatore.GiocatoreY+gp.giocatore.ScreenY){*/

                g2.drawImage(image,screenX,screenY,gp.ingame_size, gp.ingame_size,null);
                
    
  }
}