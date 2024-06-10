package object;

import java.awt.image.BufferedImage;

import Personaggi.Entità;
import main.Pannello;
public class ObjShoes extends Entità {
   

    public ObjShoes(Pannello gp) {
        super(gp); 
        name = "Shoes";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "Objects/Shoes");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        
    
        solid=true;
    }
}