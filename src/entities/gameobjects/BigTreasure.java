package entities.gameobjects;

import java.awt.image.BufferedImage;

import entities.GameEntity;
import main.GamePanel;

public class BigTreasure extends GameEntity {
    GamePanel gp;

    public BigTreasure(GamePanel gp) {
        super(gp); 
        name = "BigTreasure";

        BufferedImage handle[]=new BufferedImage[1];
        handle = loadAnimation(1, "objectssprites/BigTreasureChest");
        image = handle[0];
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
     
        solid = true;
        
    }
}