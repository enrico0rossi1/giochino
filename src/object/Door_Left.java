package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.Pannello;

public class Door_Left extends GameObject{
    Pannello gp;

    public Door_Left(Pannello gp) {
        super(gp); 
        name = "Door_Left";
        
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/wooden_doorLeft.png"));
           image = uTool.scaleImage(image, gp.ingame_size, gp.ingame_size);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
    
}

