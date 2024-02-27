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
        //getPlayerImageSPERIMENTAL();
        
    }

    public void setValoriPredefiniti(){
        GiocatoreX=gp.ingame_size*25;  
        GiocatoreY=gp.ingame_size*25; //posizione iniziale su mappa 
        velocità=3;
        direzione="up";
        
    }

    public BufferedImage[] loadAnimation (int Dimension, String Passata){
        BufferedImage[] appoggio = new BufferedImage [Dimension];
        int index = 0;
        String help = "Sprites/NewSprites/"+Passata;
        
        
        try {
            while (index < Dimension){
                appoggio[index]= ImageIO.read(getClass().getResourceAsStream(help));
                index++;
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appoggio;
        
    }

    public void getPlayerImage(){

        
        try {
            int index=1;
            String indexString=null;
            String anmImage_Moveup,anmImage_Moveright,anmImage_Moveleft,anmImage_Movedown;
            String anmImage_up,anmImage_down,anmImage_right,anmImage_left;

            attackDown1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/attackDown1.png"));
            attackDown2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/attackDown2.png"));
            attackDown3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/attackDown3.png"));
            attackDown4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Down/attackDown4.png"));
            attackUp1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/attackUp1.png"));
            attackUp2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/attackUp2.png"));
            attackUp3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/attackUp3.png"));
            attackUp4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Up/attackUp4.png"));          
            attackLeft1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/attackLeft1.png"));
            attackLeft2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/attackLeft2.png"));
            attackLeft3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/attackLeft3.png"));
            attackLeft4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Left/attackLeft4.png"));
            attackRight1=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/attackRight1.png"));
            attackRight2=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/attackRight2.png"));
            attackRight3=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/attackRight3.png"));
            attackRight4=ImageIO.read(getClass().getResourceAsStream("Sprites/NewSprites/Right/attackRight4.png"));
             
            while (index<=6){
                indexString = Integer.toString(index);
                
                anmImage_Moveup = "Sprites/NewSprites/Up/moveUp"+indexString+".png";
                anmImage_Movedown= "Sprites/NewSprites/Down/moveDown"+indexString+".png";
                anmImage_Moveleft= "Sprites/NewSprites/Left/moveLeft"+indexString+".png";
                anmImage_Moveright= "Sprites/NewSprites/Right/moveRight"+indexString+".png";
                MoveUpAnimation[index-1]= ImageIO.read(getClass().getResourceAsStream(anmImage_Moveup));
                MoveDownAnimation[index-1]= ImageIO.read(getClass().getResourceAsStream(anmImage_Movedown));
                MoveRightAnimation[index-1]= ImageIO.read(getClass().getResourceAsStream(anmImage_Moveright));
                MoveLeftAnimation[index-1]= ImageIO.read(getClass().getResourceAsStream(anmImage_Moveleft));

                anmImage_up= "Sprites/NewSprites/Up/up"+indexString+".png";
                anmImage_down= "Sprites/NewSprites/Down/down"+indexString+".png";
                anmImage_right= "Sprites/NewSprites/Right/right"+indexString+".png";
                anmImage_left= "Sprites/NewSprites/Left/left"+indexString+".png";
                UpAnimation[index-1]=ImageIO.read(getClass().getResourceAsStream(anmImage_up));
                DownAnimation[index-1]=ImageIO.read(getClass().getResourceAsStream(anmImage_down));
                RightAnimation[index-1]=ImageIO.read(getClass().getResourceAsStream(anmImage_right));
                LeftAnimation[index-1]=ImageIO.read(getClass().getResourceAsStream(anmImage_left));
                index++;
            } index=1;

                
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void getPlayerImageSPERIMENTAL(){
        try {
            int index=1;
            String indexString;
            String anmImage_Moveup,anmImage_Moveright,anmImage_Moveleft,anmImage_Movedown;
            String anmImage_up,anmImage_down,anmImage_right,anmImage_left;

            while (index<=6){
                indexString = Integer.toString(index);
                anmImage_Moveup  = "Up/moveUp"+indexString+".png";
                anmImage_Movedown= "Down/moveDown"+indexString+".png";
                anmImage_Moveleft= "Left/moveLeft"+indexString+".png";
                anmImage_Moveright="Right/moveRight"+indexString+".png";
                MoveUpAnimation=loadAnimation(6,anmImage_Moveup);
                MoveDownAnimation=loadAnimation(6,anmImage_Movedown);
                MoveRightAnimation=loadAnimation(6,anmImage_Moveright);
                MoveLeftAnimation=loadAnimation(6,anmImage_Moveleft);
                 
            }
             
          } catch (Exception e) {
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
            if(spriteCount>7){
                if (spriteNum==0){
                    spriteNum=1;
            }else if (spriteNum==1){
                    spriteNum=2;
            }else if (spriteNum==2){
                    spriteNum=3;
            }else if (spriteNum==3){
                    spriteNum=4;
            }else if (spriteNum==4){
                    spriteNum=5;
            }else if (spriteNum==5){
                    spriteNum=0;
            }
            spriteCount=0; 
        }
    }
    }
    
    
    public void draw(Graphics2D graphics2){

        
       BufferedImage image = null;
        
  
    switch (direzione) {
        case "up": 
            if (keyh.w==true && keyh.p==false){ 
               image = MoveUpAnimation[spriteNum]; 
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
                image = UpAnimation[spriteNum];
             
        };
            break;
            case "down": if (keyh.s==true && keyh.p==false){ 
               image = MoveDownAnimation[spriteNum]; 
                  
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
                image = DownAnimation[spriteNum];
            };
            break;
      
            case "right": 
            if (keyh.d==true && keyh.p==false){ 
                image = MoveRightAnimation[spriteNum];
               
            }
            else if (keyh.p==true){
                if(spriteNum==1){
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
                image = RightAnimation[spriteNum];
                
            };
            break;
      
            case "left": 
            if (keyh.a==true || keyh.p==false){ 
                image = MoveLeftAnimation[spriteNum];
                
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
                image = LeftAnimation[spriteNum];
            };
            break;
        }
        graphics2.drawImage(image, ScreenX, ScreenY, gp.ingame_size, gp.ingame_size,null);

       
    }
}

    