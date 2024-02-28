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
    public int[][] mapGraphic2; 

    public tileManager(Pannello pezzo){
        this.pezzo=pezzo;

        Tile= new tile[6];
        mapGraphic= new int[pezzo.worldCol][pezzo.worldRow];
        mapGraphic2= new int[pezzo.worldCol][pezzo.worldRow];
        
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
            

            Tile[4]=new tile();
            Tile[4].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/Albero1.png"));
            Tile[4].collisione=true;

            Tile[5]=new tile();
            Tile[5].image = ImageIO.read(getClass().getResourceAsStream("Costruzioni/Additional/vuoto.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void caricaMappa(){
        try {
            InputStream mid = getClass().getResourceAsStream("Mappe/mappa.txt");
            InputStream mid2 = getClass().getResourceAsStream("Mappe/decorazioni.txt");
            BufferedReader qp = new BufferedReader(new InputStreamReader(mid));
            BufferedReader qp2 = new BufferedReader(new InputStreamReader(mid2));
            int row=0;
            int cols=0;
            while (row<pezzo.worldRow && cols<pezzo.worldCol){
                String app = qp.readLine();
                String app2 = qp2.readLine();
                while(cols<pezzo.worldCol){
                    
                    
                    String[]number = app.split(" ");
                    String[]number2 =app2.split(" ");
                    int num = Integer.parseInt(number[cols]);
                    int num2 = Integer.parseInt(number2[cols]);

                    mapGraphic[row][cols]=num;
                    mapGraphic2[row][cols]=num2;
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




    public void draw (Graphics2D g2,Graphics2D g3){

        int row=0;
        int cols=0;
        int numTile,numTile2;
    

        while (row<pezzo.worldRow && cols<pezzo.worldCol){
            
            numTile = mapGraphic[row][cols];
            numTile2 = mapGraphic2[row][cols];

            int worldX= cols*pezzo.ingame_size;
            int worldY= row*pezzo.ingame_size;
            int screenX = worldX-pezzo.giocatore.GiocatoreX + pezzo.giocatore.ScreenX;
            int screenY = worldY-pezzo.giocatore.GiocatoreY + pezzo.giocatore.ScreenY;

            if(worldX + pezzo.ingame_size>pezzo.giocatore.GiocatoreX-pezzo.giocatore.ScreenX &&
               worldX - pezzo.ingame_size<pezzo.giocatore.GiocatoreX+pezzo.giocatore.ScreenX &&
               worldY + pezzo.ingame_size>pezzo.giocatore.GiocatoreY-pezzo.giocatore.ScreenY &&
               worldY - pezzo.ingame_size<pezzo.giocatore.GiocatoreY+pezzo.giocatore.ScreenY){

                g2.drawImage(Tile[numTile].image,screenX,screenY,pezzo.ingame_size, pezzo.ingame_size,null);
                g3.drawImage(Tile[numTile2].image,screenX,screenY,pezzo.ingame_size, pezzo.ingame_size,null);
                

            }
            cols++; 

            if(cols==pezzo.worldCol) {
                cols=0;
                row++;
               
            }
        }
        

  
}
}
