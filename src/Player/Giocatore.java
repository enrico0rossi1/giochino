package Player;


import main.InputTastiera;
import main.Pannello;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;



public class Giocatore extends Entità {

    Pannello gp;
    InputTastiera keyh;
    public final int ScreenX, ScreenY;
    

    public Giocatore(Pannello gp, InputTastiera keyh){

        this.gp=gp;
        this.keyh=keyh;
        
        collArea = new Rectangle();
        collArea.x=8;
        collArea.y=16;
        collArea.width=20;
        collArea.height=20; //area di collisione del giocatore
        
        ScreenX = (gp.screen_width/2)-(gp.ingame_size/2);
        ScreenY = (gp.screen_height/2)-(gp.ingame_size/2); //coordinate centrali

        setValoriPredefiniti();
        getPlayerImage();
        
    }

    public void setValoriPredefiniti(){
        GiocatoreX=gp.ingame_size*25;  
        GiocatoreY=gp.ingame_size*25; //posizione iniziale su mappa 
        velocità=3;
        direzione="down";
    }

    public void getPlayerImage(){
        try{
            down1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/down2.png"));
            down3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/down3.png"));
            down4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/down4.png"));
            down5=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/down5.png"));
            down6=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/down6.png"));
            
            moveDown1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/moveDown1.png"));
            moveDown2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/moveDown2.png"));
            moveDown3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/moveDown3.png"));
            moveDown4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/moveDown4.png"));
            moveDown5=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/moveDown5.png"));
            moveDown6=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/moveDown6.png"));
            attackDown1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/attackDown1.png"));
            attackDown2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/attackDown2.png"));
            attackDown3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/attackDown3.png"));
            attackDown4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/attackDown4.png"));
            
            up1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/up2.png"));
            up3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/up3.png"));
            up4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/up4.png"));
            up5=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/up5.png"));
            up6=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/up6.png"));
            
            moveUp1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/moveUp1.png"));
            moveUp2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/moveUp2.png"));
            moveUp3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/moveUp3.png"));
            moveUp4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/moveUp4.png"));
            moveUp5=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/moveUp5.png"));
            moveUp6=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/moveUp6.png"));
            attackUp1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/attackUp1.png"));
            attackUp2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/attackUp2.png"));
            attackUp3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/attackUp3.png"));
            attackUp4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/attackUp4.png"));

            left1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/left2.png"));
            left3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/left3.png"));
            left4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/left4.png"));
            left5=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/left5.png"));
            left6=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/left6.png"));
            
            moveLeft1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/moveLeft1.png"));
            moveLeft5=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/moveLeft5.png"));
            moveLeft2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/moveLeft2.png"));
            moveLeft3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/moveLeft3.png"));
            moveLeft4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/moveLeft4.png"));
            moveLeft6=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/moveLeft6.png"));
            attackLeft1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/attackLeft1.png"));
            attackLeft2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/attackLeft2.png"));
            attackLeft3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/attackLeft3.png"));
            attackLeft4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/attackLeft4.png"));
            
            right1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/right2.png"));
            right3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/right3.png"));
            right4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/right4.png"));
            right5=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/right5.png"));
            right6=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/right6.png"));
            
            moveRight1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/moveRight1.png"));
            moveRight5=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/moveRight5.png"));
            moveRight2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/moveRight2.png"));
            moveRight3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/moveRight3.png"));
            moveRight4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/moveRight4.png"));
            moveRight6=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/moveRight6.png"));
            attackRight1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/attackRight1.png"));
            attackRight2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/attackRight2.png"));
            attackRight3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/attackRight3.png"));
            attackRight4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/attackRight4.png"));

            

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void update(){
        if(keyh.w==true||keyh.a==true||keyh.s==true||keyh.d==true){

            if(keyh.s==true){
                
                direzione = "down";
            }
           
            if(keyh.w==true){
                
                direzione="up";
            }
    
            if(keyh.d==true){
                
                direzione="right";
            }
            
            if(keyh.a==true){
                
                direzione="left";
            }

            solid = false;
            gp.CollisionManager.checkTile(this);

            if (solid == false){
                switch(direzione){
                    case "up": GiocatoreY -= velocità; break;
                    case "down": GiocatoreY += velocità; break;
                    case "right": GiocatoreX += velocità; break;
                    case "left": GiocatoreX-= velocità; break;
                }

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

            case "up": if (keyh.w==true && keyh.p==false){ 
                    if(spriteNum==1){
                        image = moveUp1;
                    }else if(spriteNum==2){
                        image=moveUp2;
                    }else if(spriteNum==3){
                        image=moveUp3;
                    }else if(spriteNum==4){
                        image=moveUp4;
                    
                    }else if (spriteNum==5){
                        image=moveUp5;
                    }else if (spriteNum==6){
                        image=moveUp6;}
               
                } 
                else if (keyh.p==true){
                    if(spriteNum==1){
                        image = attackUp1;
                    }else if(spriteNum==2){
                        image=attackUp2;
                    }else if(spriteNum==3){
                        image=attackUp2;
                    }else if(spriteNum==4){
                        image=attackUp3;
                    
                    }else if(spriteNum==5){
                        image=attackUp3;
                    }else if(spriteNum==6){
                        image=attackUp4;
                    }
            }
                 else if (keyh.w==false){
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
                }
            };
                break;
                case "down": if (keyh.s==true && keyh.p==false){ 
                    if(spriteNum==1){
                        image = moveDown1;
                    }else if(spriteNum==2){
                        image=moveDown2;
                    }else if(spriteNum==3){
                        image=moveDown3;
                    }else if(spriteNum==4){
                        image=moveDown4;
                    
                    }else if (spriteNum==5){
                        image=moveDown5;
                    }else if (spriteNum==6){
                        image=moveDown6;
                    }   
                } 
                else if (keyh.p==true){
                        if(spriteNum==1){
                        image = attackDown1;
                    }else if(spriteNum==2){
                        image=attackDown2;
                    }else if(spriteNum==3){
                        image=attackDown2;
                    }else if(spriteNum==4){
                        image=attackDown3;
                
                    }else if(spriteNum==5){
                        image=attackDown3;
                    }else if(spriteNum==6){
                        image=attackDown4;
                    }
                }
                else if (keyh.s==false){
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
                    }
                };
                     
                    break;
          
                case "right": 
                if (keyh.d==true && keyh.p==false){ 
                    if(spriteNum==1){
                        image = moveRight1;
                    }else if(spriteNum==2){
                        image=moveRight2;
                    }else if(spriteNum==3){
                        image=moveRight3;
                    }else if(spriteNum==4){
                        image=moveRight4;
                    }else if (spriteNum==5){
                        image=moveRight5;
                    }else if (spriteNum==6){
                        image=moveRight6;
                    }
                }
                else if (keyh.p==true){if(spriteNum==1){
                        image = attackRight1;
                    }else if(spriteNum==2){
                        image=attackRight2;
                    }else if(spriteNum==3){
                        image=attackRight2;
                    }else if(spriteNum==4){
                        image=attackRight3;
                    }else if(spriteNum==5){
                        image=attackRight3;
                    }else if(spriteNum==6){
                        image=attackRight4;
                    }
                }
                else if (keyh.d==false){ 
                    
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
                    }
                };
                break;
          
                case "left": 
                if (keyh.a==true && keyh.p==false){ 
                    if(spriteNum==1){
                        image = moveLeft1;
                    }else if(spriteNum==2){
                        image=moveLeft2;
                    }else if(spriteNum==3){
                        image=moveLeft3;
                    }else if(spriteNum==4){
                        image=moveLeft4;
                    }else if (spriteNum==5){
                        image=moveLeft5;
                    }else if (spriteNum==6){
                        image=moveLeft6;
                    }
                }
                else if (keyh.p==true){
                    if(spriteNum==1){
                        image = attackLeft1;
                    }else if(spriteNum==2){
                        image=attackLeft2;
                    }else if(spriteNum==3){
                        image=attackLeft2;
                    }else if(spriteNum==4){
                        image=attackLeft3;
                    }else if(spriteNum==5){
                    image=attackLeft3;
                    }else if(spriteNum==6){
                    image=attackLeft4;
                    }
                }
                else if (keyh.a==false){ 
                    
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
                    }
                };
                break;
            
            
            }
        graphics2.drawImage(image, ScreenX, ScreenY, gp.ingame_size, gp.ingame_size,null);

       
    }
}


    