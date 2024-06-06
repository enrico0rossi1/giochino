package Personaggi;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;
import main.UtilityTool;

public class SquaredCat extends NPC {
    
    public SquaredCat(Pannello gp) {
     super(gp);
     name = "SquaredCat";
     direzione = "down";
     velocità = 0; 
  
         try {
           image = ImageIO.read(getClass().getResourceAsStream("Sprites\\NPCSprites\\NPCSqrdCat\\Down\\Down1.png"));
        image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        }catch(IOException e){
            e.printStackTrace();
        }  
        collision=true;
       
    //getNPCImage();
    //printSprite();
    }

        public BufferedImage[] loadAnimation(int dimension, String importPath) {
        BufferedImage[] animation = new BufferedImage[dimension];
        UtilityTool uTool = new UtilityTool(gp);
        int ingameSize = gp.ingame_size; // Variabile locale per migliorare leggibilità e ridurre chiamate ripetitive

          try {
            for (int i = 0; i < dimension; i++) {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(importPath + (i + 1) + ".png"));
                animation[i] = uTool.scaleImage(image, ingameSize, ingameSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
      } 

        return animation;
    }

public void getNPCImage() {

    UpAnimation=loadAnimation(1, "Personaggi\\Sprites\\NPCSprites\\NPCSqrdCat\\Down\\Down");
    DownAnimation=loadAnimation(1, "Personaggi\\Sprites\\NPCSprites\\NPCSqrdCat\\Down\\Down");
    RightAnimation=loadAnimation(1, "Personaggi\\Sprites\\NPCSprites\\NPCSqrdCat\\Down\\Down");
    LeftAnimation=loadAnimation(1, "Personaggi\\Sprites\\NPCSprites\\NPCSqrdCat\\Left\\Left");

 }

    public void printSprite() {
    switch (direzione) {
        case "up":
            image = UpAnimation[spriteNum];
            break;
        case "down":
            image = DownAnimation[spriteNum];
            break;
        case "right":
            image = RightAnimation[spriteNum];
            break;
        case "left":
            image = RightAnimation[spriteNum];
            break;
    }
 }  
}
