
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
public class ObjHeart extends GameObject {

    public ObjHeart() {
        name = "Heart";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/Heart.png"));
           image2 = ImageIO.read(getClass().getResourceAsStream("Objects/HeartHalved.png"));
           image3 = ImageIO.read(getClass().getResourceAsStream("Objects/HeartVoid.png"));
         
         // metodo uTool per ingrandire le immagini, lo farò più avanti
           // image = scaleImage(image, gp.pix_row, gp.pix_cols);



        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}