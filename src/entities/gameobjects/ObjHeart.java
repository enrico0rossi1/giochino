
package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import main.GamePanel;
public class ObjHeart extends GameEntity {

    GamePanel gp;

    public ObjHeart(GamePanel gp) {
        super(gp);  
        name = "Heart";
        BufferedImage handle[] = new BufferedImage[3];
        handle= loadAnimation(3, "objectssprites/Heart");
        image= handle[0];
        image2=handle[1];
        image3=handle[2];
        solid=true;
    }
}