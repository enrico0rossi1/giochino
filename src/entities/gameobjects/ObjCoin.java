package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import warrioradventure.GamePanel;

public class ObjCoin extends GameEntity {
  

    public ObjCoin(GamePanel gp) {
        super(gp); 
        name = "GoldCoin";

        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/GoldCoin",true);
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size/2,  gp.ingame_size/2);

        objWidth = 18;
        objHeight = 18;
        
        
        solid = true;
    }
}