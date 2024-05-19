package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;

public class ObjKey extends GameObject {


    Pannello gp;


    public ObjKey(Pannello gp){
        name = "key";
        this.gp=gp;
        
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/GoldKey.png"));
           image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
        objWidth = 16;
        objHeight = 16;
        
    }
    
}
