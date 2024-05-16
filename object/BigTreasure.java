package object;

import java.io.IOException;
import javax.imageio.ImageIO;
public class BigTreasure extends GameObject {

    public BigTreasure() {
        name = "BigTreasure";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/BigTreasureChest.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
        
    }
}