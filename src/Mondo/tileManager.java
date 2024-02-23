package Mondo;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.Pannello;


public class tileManager {
    
    Pannello pezzo;
    tile[] Tile;
    public static int[][] mapGraphic;

    public tileManager(Pannello pezzo){
        this.pezzo=pezzo;

        Tile= new tile[5];
        mapGraphic= new int[Pannello.pix_row][Pannello.pix_cols];
        
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
            while (row<Pannello.pix_row && cols<Pannello.pix_cols){
                String app = qp.readLine();
                while(cols<Pannello.pix_cols){
                    
                    
                    String[]number = app.split(" ");
                    int num = Integer.parseInt(number[cols]);

                    mapGraphic[row][cols]=num;
                    cols++;
                }
                if(cols==Pannello.pix_cols) {
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
        int x=0;
        int y=0;

        while (row<Pannello.pix_row && cols<Pannello.pix_cols){
            while(cols<Pannello.pix_cols){
                    numTile = mapGraphic[row][cols];
                g2.drawImage(Tile[numTile].image,x*48,y*48,Pannello.ingame_size, Pannello.ingame_size,null);
                cols++;
                x++;
            }
            if(cols==Pannello.pix_cols) {
                cols=0;
                x=0;
                row++;
                y++;
            }

            

        
    
    }

  
}
}
