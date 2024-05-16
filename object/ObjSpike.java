package object;

import java.io.IOException;
import javax.imageio.ImageIO;
public class ObjSpike extends GameObject {

    public ObjSpike() {
        name = "Spike";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/Spike.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        objWidth = 32;
        objHeight = 32;
    }
}