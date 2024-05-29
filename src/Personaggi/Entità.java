package Personaggi;



import java.awt.image.BufferedImage;

import main.Pannello;

import java.awt.Rectangle;


public class Entità {

    public Pannello gp;
    public int posizioneX,posizioneY;
    public int velocità;

    public String direzione;
    public String anmImage_Moveup,anmImage_Moveright,anmImage_Moveleft,anmImage_Movedown;
    public String anmImage_up,anmImage_down,anmImage_right,anmImage_left;
    public String anmImage_atUp,anmImage_atDown,anmImage_atRight,anmImage_atLeft;

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

    public Rectangle collArea;//= new Rectangle(0,0,gp.ingame_size,gp.ingame_size);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean solid = false;

    //stato dell'entità
    public int vitaMax;
    public int vita;

    public Entità(Pannello gp) {
        this.gp = gp;
    }
}
