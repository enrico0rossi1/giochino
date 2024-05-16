package object;

import java.io.IOException;
import javax.imageio.ImageIO;
public class ObjShoes extends GameObject {

    public ObjShoes() {
        name = "Shoes";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/Shoes.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}