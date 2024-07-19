package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import warrioradventure.GamePanel;

public class ObjKey extends GameEntity {

    public ObjKey(GamePanel gp){
        super(gp); 
        name = "Key";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/Key",true);
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
    
        solid=true;
        objWidth = 16;
        objHeight = 16;


    } 
}
