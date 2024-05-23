package Mondo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import main.Pannello;

public class Mappa {

    Pannello gp;
    public int[][]ground;
    public int[][]deco;


    public Mappa(Pannello gp){
        this.gp=gp;
        this.ground= new int[gp.worldRow][gp.worldCol];
        this.deco=new int [gp.worldCol][gp.worldRow];

    }

    public void loadMap(String Ground, String Deco){

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
