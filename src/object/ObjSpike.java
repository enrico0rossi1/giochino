package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;
public class ObjSpike extends GameObject {
    Pannello gp;

    public ObjSpike(Pannello gp) {
        name = "Spike";
        this.gp=gp;
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/Spike.png"));
           image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        }catch(IOException e){
            e.printStackTrace();
        }
        objWidth = 32;
        objHeight = 32;
    }
}