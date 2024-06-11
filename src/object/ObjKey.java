package object;

import java.awt.image.BufferedImage;

import Entità.Entità;
import main.Pannello;

public class ObjKey extends Entità {

    public ObjKey(Pannello gp){
        super(gp); 
        name = "Key";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "Objects/GoldKey");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
    
        solid=true;
        objWidth = 16;
        objHeight = 16;


    } 
}
