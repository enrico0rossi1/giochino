package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ObjKey extends GameObject {



    public ObjKey(){
        name = "key";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/GoldKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
        objWidth = 16;
        objHeight = 16;
    
    }
    
}
