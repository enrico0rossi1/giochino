
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;
public class ObjHeart extends GameObject {

    Pannello gp;

    

    public ObjHeart(Pannello gp) {
        name = "Heart";
        this.gp=gp;
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/Heart.png"));
           image2 = ImageIO.read(getClass().getResourceAsStream("Objects/HeartHalved.png"));
           image3 = ImageIO.read(getClass().getResourceAsStream("Objects/HeartVoid.png"));

           image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
           image2 = uTool.scaleImage(image2,  gp.ingame_size,  gp.ingame_size);
           image3 = uTool.scaleImage(image3,  gp.ingame_size,  gp.ingame_size);
         
            



        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}