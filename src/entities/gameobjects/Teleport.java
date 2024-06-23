package entities.gameobjects;

import entities.GameEntity;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Teleport extends GameEntity{
    public Teleport(GamePanel gp) {
        super(gp); 
        name = "Teleport";
        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/teleport");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        
    
        solid=true;
    }
    
}
