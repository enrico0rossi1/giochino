package Player;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;


public class Entità {

    public int GiocatoreX,GiocatoreY;
    public int velocità;

    public BufferedImage down1,down2,down3,down4,down5,down6,moveDown1,moveDown2,moveDown3,moveDown4,moveDown5,moveDown6,attackDown1,attackDown2,attackDown3,attackDown4;
    public BufferedImage up1,up2,up3,up4,up5,up6,moveUp1,moveUp2,moveUp3,moveUp4,moveUp5,moveUp6,attackUp1,attackUp2,attackUp3,attackUp4;
    public BufferedImage left1,left2,left3,left4,left5,left6,moveLeft1,moveLeft2,moveLeft3,moveLeft4,moveLeft5,moveLeft6,attackLeft1,attackLeft2,attackLeft3,attackLeft4;
    public BufferedImage right1,right2,right3,right4,right5,right6,moveRight1,moveRight2,moveRight3,moveRight4,moveRight5,moveRight6,attackRight1,attackRight2,attackRight3,attackRight4;
    public String direzione;

    
    public BufferedImage[]UpAnimation=new BufferedImage[6];
    public BufferedImage[]DownAnimation=new BufferedImage[6];
    public BufferedImage[]RightAnimation=new BufferedImage[6];
    public BufferedImage[]LeftAnimation=new BufferedImage[6];
    public BufferedImage[]MoveUpAnimation=new BufferedImage[6];
    public BufferedImage[]MoveDownAnimation=new BufferedImage[6];
    public BufferedImage[]MoveRightAnimation=new BufferedImage[6];
    public BufferedImage[]MoveLeftAnimation=new BufferedImage[6];
    public BufferedImage[]AttackDown = new BufferedImage[6];
    public BufferedImage[]AttackUp = new BufferedImage[6];
    public BufferedImage[]AttackLeft = new BufferedImage[6];
    public BufferedImage[]AttackRight = new BufferedImage[6];
    
    public String attack;
    

    public int spriteCount=0;
    public int spriteNum=0;

    public Rectangle collArea;
    public boolean solid = false;
}
