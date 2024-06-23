package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import main.GamePanel;
public class ObjBigHeart extends GameEntity {

    GamePanel gp;

    public ObjBigHeart(GamePanel gp) {
        super(gp);  
        name = "BigHeart";
        BufferedImage handle[] = new BufferedImage[1];
        handle= loadAnimation(1, "objectssprites/BigHeart",true);
        image= handle[0];
       
        solid=true;
    }
}