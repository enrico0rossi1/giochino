package object;

import java.awt.image.BufferedImage;

import Entità.Entità;
import main.Pannello;

public class Door_Left extends Entità{

    public Door_Left(Pannello gp) {
        super(gp); 
        name = "Door_Left";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "Objects/wooden_doorLeft");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
       
       
        solid=true;
    }
    
}

