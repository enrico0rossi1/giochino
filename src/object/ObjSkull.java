package object;

import java.io.IOException;
import javax.imageio.ImageIO;
public class ObjSkull extends GameObject {

    public ObjSkull() {
        name = "Skull";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("/object/OSprites/Skull.jpeg"));
        }catch(IOException e) {e.printStackTrace();
        }
    }
}