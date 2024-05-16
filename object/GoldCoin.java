package object;

import java.io.IOException;
import javax.imageio.ImageIO;
public class GoldCoin extends GameObject {

    public GoldCoin() {
        name = "GoldCoin";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/GoldCoin.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        objWidth = 22;
        objHeight = 22;
        collision = true;
    }
}