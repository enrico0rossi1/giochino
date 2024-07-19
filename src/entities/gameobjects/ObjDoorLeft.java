package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import warrioradventure.GamePanel;

public class ObjDoorLeft extends GameEntity{

    public ObjDoorLeft(GamePanel gp) {
        super(gp); 
        name = "Door_Left";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/wooden_doorLeft",true);
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
       
       
        solid=true;
    }
    
}

