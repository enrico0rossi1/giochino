package object;

import java.awt.image.BufferedImage;

import Entità.Entità;
import main.Pannello;

public class BigTreasure extends Entità {
    Pannello gp;

    public BigTreasure(Pannello gp) {
        super(gp); 
        name = "BigTreasure";

        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "Objects/BigTreasureChest");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
     
        solid = true;
        
    }
}