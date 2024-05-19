package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;
public class GoldCoin extends GameObject {
    Pannello gp;

    public GoldCoin(Pannello gp) {
        name = "GoldCoin";
        this.gp=gp;
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/GoldCoin.png"));
           image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        }catch(IOException e){
            e.printStackTrace();
        }
        objWidth = 22;
        objHeight = 22;
        collision = true;
    }
}