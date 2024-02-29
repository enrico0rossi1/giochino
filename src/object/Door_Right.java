package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Door_Right extends GameObject{

    public Door_Right() {
        name = "door";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/wooden_doorRight.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
    
}
