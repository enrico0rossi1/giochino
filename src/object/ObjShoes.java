package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Pannello;
public class ObjShoes extends GameObject {
    Pannello gp;

    public ObjShoes(Pannello gp) {
        super(gp); 
        name = "Shoes";
        
        try {
           image = ImageIO.read(getClass().getResourceAsStream("Objects/Shoes.png"));
           image = uTool.scaleImage(image,  gp.ingame_size,  gp.ingame_size);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision=true;
    }
}