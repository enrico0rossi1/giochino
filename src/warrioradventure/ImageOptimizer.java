package warrioradventure;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class ImageOptimizer {

    

    public ImageOptimizer (){
        
    }

    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage=new BufferedImage(width, height, original.getType());
        Graphics g2=scaledImage.createGraphics();
        g2.drawImage(original, 0,0,width, height, null);
        g2.dispose();
        return scaledImage;

    }

    

    
    
}
