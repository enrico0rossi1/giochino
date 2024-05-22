package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.Pannello;

public class Door_Left extends GameObject{
    Pannello gp;

    public Door_Left(Pannello gp) {
        name = "Door_Left";
        this.gp=gp;
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/wooden_doorLeft.png"));
           image = uTool.scaleImage(image, gp.ingame_size, gp.ingame_size);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
    
}

