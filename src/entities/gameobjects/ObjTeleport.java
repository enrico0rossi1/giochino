package entities.gameobjects;

import entities.GameEntity;
import warrioradventure.GamePanel;

import java.awt.image.BufferedImage;

public class ObjTeleport extends GameEntity{
    public ObjTeleport(GamePanel gp) {
        super(gp); 
        name = "Teleport";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/teleport",true);
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        
    
        solid=true;
    }
    
}
