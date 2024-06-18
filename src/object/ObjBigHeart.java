package object;

import java.awt.image.BufferedImage;

import Entità.Entità;
import main.Pannello;
public class ObjBigHeart extends Entità {

    Pannello gp;

    public ObjBigHeart(Pannello gp) {
        super(gp);  
        name = "BigHeart";
        BufferedImage handle[] = new BufferedImage[1];
        handle= loadAnimation(1, "Objects/BigHeart");
        image= handle[0];
       
        solid=true;
    }
}