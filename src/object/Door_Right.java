package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.Pannello;

public class Door_Right extends GameObject{
    Pannello gp;

    public Door_Right(Pannello gp) {
        super(gp); 
        name = "Door_Right";
       
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/wooden_doorRight.png"));
           image = uTool.scaleImage(image, gp.ingame_size, gp.ingame_size);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
    
}
