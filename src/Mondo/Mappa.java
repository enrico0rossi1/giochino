package Mondo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.awt.Graphics2D;

import main.Pannello;

public class Mappa {

    Pannello gp;
    public int[][]ground;
    public int[][]deco;
    String Ground,Deco;
    


    public Mappa(Pannello gp,String Ground, String Deco){
        this.gp=gp;
        this.Ground=Ground;
        this.Deco=Deco;
        this.ground= new int[gp.worldRow][gp.worldCol];
        this.deco=new int [gp.worldCol][gp.worldRow];

        loadMap();

    }

    public void loadMap(){

        try{
        InputStream mid = getClass().getResourceAsStream(Ground+".txt");
        InputStream mid2 = getClass().getResourceAsStream(Deco+".txt");
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
                    

                    ground[row][cols]=num;
                    deco[row][cols]=num2;
                    cols++;
                }
                if(cols==gp.worldCol) {
                    cols=0;
                    row++;
                }
                
                

            } 
            qp.close();
            qp2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D g2,Graphics2D g3, tileManager tm){

        int row=0;
        int cols=0;
        int numTile,numTile2;
    

        while (row<gp.worldRow && cols<gp.worldCol){
            
            numTile = ground[row][cols];
            numTile2 = deco[row][cols];

            int worldX= cols*gp.ingame_size;
            int worldY= row*gp.ingame_size;
            int screenX = worldX-gp.giocatore.posizioneX + gp.giocatore.ScreenX;
            int screenY = worldY-gp.giocatore.posizioneY + gp.giocatore.ScreenY;
      

            g2.drawImage(tm.Tile[numTile].image,screenX,screenY,null);
            g3.drawImage(tm.Tile[numTile2].image,screenX,screenY,null);
                
                

            
            cols++; 

            if(cols==gp.worldCol) {
                cols=0;
                row++;
               
            }
        }
    }
    
}
