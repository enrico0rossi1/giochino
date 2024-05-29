package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;
public class BigTreasure extends GameObject {
    Pannello gp;

    public BigTreasure(Pannello gp) {
        super(gp); 
        name = "BigTreasure";
      
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/BigTreasureChest.png"));
           image = uTool.scaleImage(image, gp.ingame_size, gp.ingame_size);
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
        
    }
}