package Player;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;


public class Entità {

    public int GiocatoreX,GiocatoreY;
    public int velocità;

    public BufferedImage down1,down2,down3,down4,down5,down6;
    public BufferedImage up1,up2,up3,up4,up5,up6;
    public BufferedImage left1,left2,left3,left4,left5,left6;
    public BufferedImage right1,right2,right3,right4,right5,right6;
    public String direzione;

    public int spriteCount=0;
    public int spriteNum=1;

    public Rectangle collArea;
    public boolean solid = false;
}

    

    

