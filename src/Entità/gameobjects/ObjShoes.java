package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import main.GamePanel;
public class ObjShoes extends GameEntity {
   

    public ObjShoes(GamePanel gp) {
        super(gp); 
        name = "Shoes";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/Shoes");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        
    
        solid=true;
    }
}