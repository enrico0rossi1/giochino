package object;

import java.awt.image.BufferedImage;

import Entità.Entità;
import main.Pannello;

public class GoldCoin extends Entità {
  

    public GoldCoin(Pannello gp) {
        super(gp); 
        name = "GoldCoin";

        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "Objects/GoldCoin");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);

        objWidth = 18;
        objHeight = 18;
        
        
        solid = true;
    }
}