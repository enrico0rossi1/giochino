
package object;

import java.awt.image.BufferedImage;

import Personaggi.Entità;
import main.Pannello;
public class ObjHeart extends Entità {

    Pannello gp;

    public ObjHeart(Pannello gp) {
        super(gp);  
        name = "Heart";
        BufferedImage handle[] = new BufferedImage[3];
        handle= loadAnimation(3, "Objects/Heart");
        image= handle[0];
        image2=handle[1];
        image3=handle[2];
        
    }
}