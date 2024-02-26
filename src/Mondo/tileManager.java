package Mondo;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.Pannello;


public class tileManager {
    
    Pannello pezzo;
    public tile[] Tile;
    public int[][] mapGraphic;

    public tileManager(Pannello pezzo){
        this.pezzo=pezzo;

        Tile= new tile[5];
        mapGraphic= new int[pezzo.worldCol][pezzo.worldRow];
        
        getTileImage();
        caricaMappa();

    }

    public void getTileImage(){
        try {
            Tile[0]=new tile();
            Tile[0].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/DirtTile/Dirt1.png"));
            

            Tile[1]=new tile();
            Tile[1].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/GrassTile/Grass1.png"));
            
            Tile[2]=new tile();
            Tile[2].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/WaterTile/Water.png"));
            Tile[2].collisione =true;

            Tile[3]=new tile();
            Tile[3].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/FencesVert.png"));
            

            Tile[4]=new tile();
            Tile[4].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/FencescHor.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void caricaMappa(){
        try {
            InputStream mid = getClass().getResourceAsStream("Mappe/mappa.txt");
            BufferedReader qp = new BufferedReader(new InputStreamReader(mid));
            int row=0;
            int cols=0;
            while (row<pezzo.worldRow && cols<pezzo.worldCol){
                String app = qp.readLine();
                while(cols<pezzo.worldCol){
                    
                    
                    String[]number = app.split(" ");
                    int num = Integer.parseInt(number[cols]);

                    mapGraphic[row][cols]=num;
                    cols++;
                }
                if(cols==pezzo.worldCol) {
                    cols=0;
                    row++;
                }
                
                

            } 
            qp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    public void draw (Graphics2D g2){

        int row=0;
        int cols=0;
        int numTile;
    

        while (row<pezzo.worldRow && cols<pezzo.worldCol){
            
            numTile = mapGraphic[row][cols];

            int worldX= cols*pezzo.ingame_size;
            int worldY= row*pezzo.ingame_size;
            int screenX = worldX-pezzo.giocatore.GiocatoreX + pezzo.giocatore.ScreenX;
            int screenY = worldY-pezzo.giocatore.GiocatoreY + pezzo.giocatore.ScreenY;

            if(worldX + pezzo.ingame_size>pezzo.giocatore.GiocatoreX-pezzo.giocatore.ScreenX &&
               worldX - pezzo.ingame_size<pezzo.giocatore.GiocatoreX+pezzo.giocatore.ScreenX &&
               worldY + pezzo.ingame_size>pezzo.giocatore.GiocatoreY-pezzo.giocatore.ScreenY &&
               worldY - pezzo.ingame_size<pezzo.giocatore.GiocatoreY+pezzo.giocatore.ScreenY){

                g2.drawImage(Tile[numTile].image,screenX,screenY,pezzo.ingame_size, pezzo.ingame_size,null);
                

            }
            cols++; 

            if(cols==pezzo.worldCol) {
                cols=0;
                row++;
               
            }
        }
        

  
}
}
