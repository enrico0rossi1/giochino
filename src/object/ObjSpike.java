package object;

import java.awt.image.BufferedImage;

import Entità.Entità;
import main.Pannello;
public class ObjSpike extends Entità {
    

    public ObjSpike(Pannello gp) {
        super(gp); 
        name = "Spike";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "Objects/Spike");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
    
        objWidth = 32;
        objHeight = 32;
    }
}