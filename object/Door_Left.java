package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Door_Left extends GameObject{

    public Door_Left() {
        name = "door";
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/wooden_doorLeft.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
    
}

