package Player;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class Animation {

    public Animation(){
        

    }

    public BufferedImage[] loadAnimation (int Dimension, String Passata){
        BufferedImage[] appoggio = new BufferedImage [Dimension];
        int index = 0;
        String help = "Sprites/NewSprites/"+Passata;
        
        
        try {
            while (index < Dimension){
                appoggio[index]= ImageIO.read(getClass().getResourceAsStream(help));
                index++;
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appoggio;
        
    }


    

    
}
