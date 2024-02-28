package object;

import java.io.IOException;
import javax.imageio.ImageIO;
public class GoldCoin extends GameObject {

    public GoldCoin() {
        name = "GoldCoin";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Treasure/GoldCoin.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}