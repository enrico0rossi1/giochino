package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ObjKey extends GameObject {

    public ObjKey(){
        name = "Key";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Treasure/GoldKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
}
