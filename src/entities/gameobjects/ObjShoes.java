package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import warrioradventure.GamePanel;
public class ObjShoes extends GameEntity {
   

    public ObjShoes(GamePanel gp) {
        super(gp); 
        name = "Shoes";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/Shoes",true);
        image = handle[0];
        image = imgOpt.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        
    
        solid=true;
    }
}