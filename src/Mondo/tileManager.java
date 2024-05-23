package Mondo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;
import main.Pannello;
import main.UtilityTool;


public class tileManager {
    
    Pannello gp;

    public tile[] Tile,Tile2;
    public BufferedImage[] scaledTile;
    public Graphics2D[] scalingTool;
    public Mappa start;
   
    
    public tileManager(Pannello gp, Mappa start){
        this.gp=gp;

        Tile= new tile[30];
        this.start = start;
        
        getTileImage();
        start.loadMap("Mappe/StartingWoods", "Mappe/StartingWoodsDeco");

    }

    public void getTileImage(){
        try {
            setup(0,"DirtTile/Dirt1",false);
            setup(1,"GrassTile/grass1",false);
            setup(2,"WaterTile/Water",true);
            setup(3,"Additional/FencesVert",true);
            setup(4,"Additional/Albero1",true);
            setup(5,"Additional/vuoto",false);
            setup(6,"Additional/FencesHor",true);
            setup(7,"Additional/wallBottom",true);
            setup(8,"Additional/wallMid",true);
            setup(9,"Additional/topWall",true);
            setup(10,"Additional/wooden_doorLeft",true);
            setup(11,"Additional/wooden_doorRight",true);
            setup(12,"DarkTile/darkdown",false);
            setup(13,"DarkTile/darkleft",false);
            setup(14,"DarkTile/darkright",false);
            setup(15,"DarkTile/dark1",false);
            setup(16,"DarkTile/darkMid",false);
            setup(17,"Additional/alberosciucco",true);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setup(int index, String imagePath, boolean collision){

        UtilityTool uTool = new UtilityTool();
        try {
            Tile[index]=new tile();
            Tile[index].image=ImageIO.read(getClass().getResourceAsStream("Costruzioni/"+imagePath+".png"));
            Tile[index].image=uTool.scaleImage(Tile[index].image, gp.ingame_size, gp.ingame_size);
            Tile[index].collisione = collision; 

            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    
    public void draw (Graphics2D g2,Graphics2D g3){

        int row=0;
        int cols=0;
        int numTile,numTile2;
    

        while (row<gp.worldRow && cols<gp.worldCol){
            
            numTile = start.ground[row][cols];
            numTile2 = start.deco[row][cols];

            int worldX= cols*gp.ingame_size;
            int worldY= row*gp.ingame_size;
            int screenX = worldX-gp.giocatore.posizioneX + gp.giocatore.ScreenX;
            int screenY = worldY-gp.giocatore.posizioneY + gp.giocatore.ScreenY;
      

            g2.drawImage(Tile[numTile].image,screenX,screenY,null);
            g3.drawImage(Tile[numTile2].image,screenX,screenY,null);
                
                

            
            cols++; 

            if(cols==gp.worldCol) {
                cols=0;
                row++;
               
            }
        }
    }
}
