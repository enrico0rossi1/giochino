package gameworld;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.awt.Graphics2D;

import main.GamePanel;

public class Map {

    GamePanel gp;
    public int[][]ground;
    public int[][]deco;
    String Ground,Deco;
    


    public Map(GamePanel gp,String Ground, String Deco){
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

    public void draw(Graphics2D g2, Graphics2D g3, TileManager tm) {
        int numTile, numTile2;
        int ingameSize = gp.ingame_size;
        int playerX = gp.giocatore.worldX;
        int playerY = gp.giocatore.worldY;
        int screenXOffset = gp.giocatore.ScreenX;
        int screenYOffset = gp.giocatore.ScreenY;
        
        for (int row = 0; row < gp.worldRow; row++) {
            for (int col = 0; col < gp.worldCol; col++) {
                numTile = ground[row][col];
                numTile2 = deco[row][col];
    
                int worldX = col * ingameSize;
                int worldY = row * ingameSize;
                int screenX = worldX - playerX + screenXOffset;
                int screenY = worldY - playerY + screenYOffset;
    
                if (screenX + ingameSize > 0 && screenX < gp.screen_width && 
                    screenY + ingameSize > 0 && screenY < gp.screen_height) {
                    g2.drawImage(tm.Tile[numTile].image, screenX, screenY, null);
                    g3.drawImage(tm.Tile[numTile2].image, screenX, screenY, null);
                }
            }
        }
    }

    public boolean isComplete() {
        boolean res=true;
        for(int i=0;i<gp.mon.length;i++){
            if(gp.mon[i]!=null){
                res=false;
            }
        }
        return res;
      
  }

}