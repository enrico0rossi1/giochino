package Mondo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.Pannello;
import main.UtilityTool;


public class tileManager {
    
    Pannello gp;

    public tile[] Tile,Tile2;
    public BufferedImage[] scaledTile;
    public Graphics2D[] scalingTool;
    public int [][] mapGraphic,mapGraphic2;
    
    public tileManager(Pannello gp){
        this.gp=gp;

        Tile= new tile[30];
        
        mapGraphic= new int[gp.worldCol][gp.worldRow];
        mapGraphic2= new int[gp.worldCol][gp.worldRow];
        
        getTileImage();
        caricaMappa();

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
            setup(17,"Additional/alberosciucco",false);

            
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

    public void caricaMappa(){
        try {
            InputStream mid = getClass().getResourceAsStream("Mappe/StartingWoods.txt");
            InputStream mid2 = getClass().getResourceAsStream("Mappe/StartingWoodsDeco.txt");
            BufferedReader qp = new BufferedReader(new InputStreamReader(mid));
            BufferedReader qp2 = new BufferedReader(new InputStreamReader(mid2));
            int row=0;
            int cols=0;
            while (row<gp.worldRow && cols<gp.worldCol){
                String app = qp.readLine();
                String app2 = qp2.readLine();
                while(cols<gp.worldCol){
                    
                    
                    String[]number = app.split(" ");
                    String[]number2 =app2.split(" ");
                    int num = Integer.parseInt(number[cols]);
                    int num2 = Integer.parseInt(number2[cols]);

                    mapGraphic[row][cols]=num;
                    mapGraphic2[row][cols]=num2;
                    cols++;
                }
                if(cols==gp.worldCol) {
                    cols=0;
                    row++;
                }
                
                

            } 
            qp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    public void draw (Graphics2D g2,Graphics2D g3){

        int row=0;
        int cols=0;
        int numTile,numTile2;
    

        while (row<gp.worldRow && cols<gp.worldCol){
            
            numTile = mapGraphic[row][cols];
            numTile2 = mapGraphic2[row][cols];

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
