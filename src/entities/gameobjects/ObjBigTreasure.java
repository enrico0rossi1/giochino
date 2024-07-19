package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import warrioradventure.GamePanel;

public class ObjBigTreasure extends GameEntity {
    GamePanel gp;

    public ObjBigTreasure(GamePanel gp) {
        super(gp); 
        name = "BigTreasure";

        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/BigTreasureChest",true);
        image = handle[0];
        image = imgOpt.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
     
        solid = true;
        
    }
}