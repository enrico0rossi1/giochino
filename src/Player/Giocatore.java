package Player;


import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.InputTastiera;
import main.Pannello;

import java.io.IOException;
import java.awt.image.BufferedImage;

public class Giocatore extends Entità {

    Pannello gp;
    InputTastiera keyh;
    

    public Giocatore(Pannello gp, InputTastiera keyh){

        this.gp=gp;
        this.keyh=keyh;
        setValoriPredefiniti();
        getPlayerImage();
    }
    public void setValoriPredefiniti(){
        x=100;
        y=100;
        velocità=4;
        direzione="down";
    }
    public void getPlayerImage(){
        try{
            down1=ImageIO.read(getClass().getResourceAsStream("Sprites/Down_asset/down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("Sprites/Down_asset/down2.png"));
            down3=ImageIO.read(getClass().getResourceAsStream("Sprites/Down_asset/down3.png"));
            down4=ImageIO.read(getClass().getResourceAsStream("Sprites/Down_asset/down4.png"));
            down5=ImageIO.read(getClass().getResourceAsStream("Sprites/Down_asset/down5.png"));
            down6=ImageIO.read(getClass().getResourceAsStream("Sprites/Down_asset/down6.png"));
            
            up1=ImageIO.read(getClass().getResourceAsStream("Sprites/Up_asset/up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("Sprites/Up_asset/up2.png"));
            up3=ImageIO.read(getClass().getResourceAsStream("Sprites/Up_asset/up3.png"));
            up4=ImageIO.read(getClass().getResourceAsStream("Sprites/Up_asset/up4.png"));
            up5=ImageIO.read(getClass().getResourceAsStream("Sprites/Up_asset/up5.png"));
            up6=ImageIO.read(getClass().getResourceAsStream("Sprites/Up_asset/up6.png"));
            
            
            left1=ImageIO.read(getClass().getResourceAsStream("Sprites/Left_asset/left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("Sprites/Left_asset/left2.png"));
            left3=ImageIO.read(getClass().getResourceAsStream("Sprites/Left_asset/left3.png"));
            left4=ImageIO.read(getClass().getResourceAsStream("Sprites/Left_asset/left4.png"));
            left5=ImageIO.read(getClass().getResourceAsStream("Sprites/Left_asset/left5.png"));
            left6=ImageIO.read(getClass().getResourceAsStream("Sprites/Left_asset/left6.png"));
            
            
            right1=ImageIO.read(getClass().getResourceAsStream("Sprites/Right_asset/right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("Sprites/Right_asset/right2.png"));
            right3=ImageIO.read(getClass().getResourceAsStream("Sprites/Right_asset/right3.png"));
            right4=ImageIO.read(getClass().getResourceAsStream("Sprites/Right_asset/right4.png"));
            right5=ImageIO.read(getClass().getResourceAsStream("Sprites/Right_asset/right5.png"));
            right6=ImageIO.read(getClass().getResourceAsStream("Sprites/Right_asset/right6.png"));

            

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyh.w==true||keyh.a==true||keyh.s==true||keyh.d==true){
            if(keyh.s==true){
                y =y+ velocità;
                direzione = "down";
            }
    
            if(keyh.w==true){
                y =y- velocità;
                direzione="up";
            }
    
            if(keyh.d==true){
                x =x+velocità;
                direzione="right";
            }
            
            if(keyh.a==true){
                x=x-velocità;
                direzione="left";
            }

            spriteCount++;
            if (spriteCount>7){
                if(spriteNum==1){
                    spriteNum=2;
                }else if(spriteNum==2){
                    spriteNum=3;
                }else if(spriteNum==3){
                    spriteNum=4;
                }else if(spriteNum==4){
                    spriteNum=5;
                }else if (spriteNum==5){
                    spriteNum=6;
                }else if (spriteNum==6){
                    spriteNum=1;
                }
            
            spriteCount=0;
        }

        }
    }
    public void draw(Graphics2D graphics2){

        
        BufferedImage image = null;
        

        switch (direzione) {
            case "up":
                if(spriteNum==1){
                    image = up1;
                }else if(spriteNum==2){
                    image=up2;
                }else if(spriteNum==3){
                    image=up3;
                }else if(spriteNum==4){
                    image=up4;
                
                }else if (spriteNum==5){
                    image=up5;
                }else if (spriteNum==6){
                    image=up6;
                }break;
            case "down":
                if(spriteNum==1){
                    image = down1;
                }else if(spriteNum==2){
                    image=down2;
                }else if(spriteNum==3){
                    image=down3;
                }else if(spriteNum==4){
                    image=down4;
                }else if (spriteNum==5){
                    image=down5;
                }else if (spriteNum==6){
                    image=down6;
                }break;
            case "right":
                if(spriteNum==1){
                    image = right1;
                }else if(spriteNum==2){
                    image=right2;
                }else if(spriteNum==3){
                    image=right3;
                }else if(spriteNum==4){
                    image=right4;
                }else if (spriteNum==5){
                    image=right5;
                }else if (spriteNum==6){
                    image=right6;
                }break;
            case "left":
                if(spriteNum==1){
                    image = left1;
                }else if(spriteNum==2){
                    image=left2;
                }else if(spriteNum==3){
                    image=left3;
                }else if(spriteNum==4){
                    image=left4;
                }else if (spriteNum==5){
                    image=left5;
                }else if (spriteNum==6){
                    image=left6;
                }break;    
            }
        
        
        

        graphics2.drawImage(image, x, y, Pannello.ingame_size, Pannello.ingame_size,null);

       
    }
}







