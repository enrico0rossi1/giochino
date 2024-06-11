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
    
   
    
    public tileManager(Pannello gp){
        this.gp=gp;

        Tile= new tile[30];
        
        
        getTileImage();
        

    }

    public void getTileImage(){
        try {
            setup(0,"DirtTile/Dirt1",false);
            setup(1,"GrassTile/grass1",false);
            setup(2,"WaterTile/Water",true);
            setup(20,"WaterTile/Water1",true);
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
            setup(21,"DirtTile/Grassy_dirt_vert1",false);
            setup(22,"DirtTile/Grassy_dirt_vert2",false);
            setup(23,"DirtTile/Grassy_dirt_hor1",false);
            setup(24,"DirtTile/Grassy_dirt_hor2",false);

            
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

    
 
}
 