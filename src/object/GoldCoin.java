package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;
public class GoldCoin extends GameObject {
    Pannello gp;

    public GoldCoin(Pannello gp) {
        super(gp); 
        name = "GoldCoin";
      
        objWidth = 18;
        objHeight = 18;
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/GoldCoin.png"));
           image = uTool.scaleImage(image,  objWidth, objHeight);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        collision = true;
    }
}