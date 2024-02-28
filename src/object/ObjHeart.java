package object;

import java.io.IOException;
import javax.imageio.ImageIO;
public class ObjHeart extends GameObject {

    public ObjHeart() {
        name = "Heart";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("/object/OSprites/Heart.jpeg"));
        }catch(IOException e) {e.printStackTrace();
        }
    }
}