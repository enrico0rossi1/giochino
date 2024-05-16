package Mondo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.Pannello;


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
            Tile[0]=new tile();
            Tile[0].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/DirtTile/Dirt1.png"));
            
         
            Tile[1]=new tile();
            Tile[1].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/GrassTile/grass1.png"));
           

            Tile[2]=new tile();
            Tile[2].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/WaterTile/Water.png"));
            Tile[2].collisione =true;

            Tile[3]=new tile();
            Tile[3].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/FencesVert.png"));
            Tile[3].collisione = true;

            Tile[4]=new tile();
            Tile[4].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/Albero1.png"));
            Tile[4].collisione=true;

            Tile[5]=new tile();
            Tile[5].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/vuoto.png"));

            Tile[6]=new tile();
            Tile[6].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/FencesHor.png"));
            Tile[6].collisione=true;

            Tile[7]=new tile();
            Tile[7].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/wallBottom.png"));
            Tile[7].collisione=true;

            Tile[8]=new tile();
            Tile[8].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/wallMid.png"));
            Tile[8].collisione=true;

            Tile[9]=new tile();
            Tile[9].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/topWall.png"));
            Tile[9].collisione=true;

            Tile[10]=new tile();
            Tile[10].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/wooden_doorLeft.png"));
            Tile[10].collisione=true;
            
            Tile[11]=new tile();
            Tile[11].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/wooden_doorRight.png"));
            Tile[11].collisione=true;

            Tile[12]=new tile();
            Tile[12].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/DarkTile/darkdown.png"));

            Tile[13]=new tile();
            Tile[13].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/DarkTile/darkleft.png"));

            Tile[14]=new tile();
            Tile[14].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/DarkTile/darkright.png"));

            Tile[15]=new tile();
            Tile[15].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/DarkTile/dark1.png"));

            Tile[16]=new tile();
            Tile[16].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/DarkTile/darkMid.png"));

            Tile[17]=new tile();
            Tile[17].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/alberosciucco.png"));
            Tile[17].collisione=true;
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
      {

                g2.drawImage(Tile[numTile].image,screenX,screenY,gp.ingame_size, gp.ingame_size,null);
                g3.drawImage(Tile[numTile2].image,screenX,screenY,gp.ingame_size, gp.ingame_size,null);
                
                

            }
            cols++; 

            if(cols==gp.worldCol) {
                cols=0;
                row++;
               
            }
        }
        

  
}
}
