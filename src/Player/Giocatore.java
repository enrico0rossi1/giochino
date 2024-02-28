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
        direzione="up";
        
    }

    public BufferedImage[] loadAnimation (int Dimension, String Passata){
        BufferedImage[] appoggio = new BufferedImage [Dimension];
        
        try {
           
            for(int i=1; i<=Dimension;i++){
                appoggio[i-1]=ImageIO.read(getClass().getResourceAsStream(Passata+i+".png"));
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appoggio;
        
    }

    public void getPlayerImage(){
        
        MoveUpAnimation=loadAnimation(6,"Sprites/NewSprites/Up/moveUp");
        MoveDownAnimation=loadAnimation(6,"Sprites/NewSprites/Down/moveDown");
        MoveRightAnimation=loadAnimation(6,"Sprites/NewSprites/Right/moveRight");
        MoveLeftAnimation=loadAnimation(6,"Sprites/NewSprites/Left/moveLeft");

        UpAnimation=loadAnimation(6, "Sprites/NewSprites/Up/up");
        DownAnimation=loadAnimation(6, "Sprites/NewSprites/Down/down");
        RightAnimation=loadAnimation(6, "Sprites/NewSprites/Right/right");
        LeftAnimation=loadAnimation(6, "Sprites/NewSprites/Left/left");

        AttackDown=loadAnimation(4, "Sprites/NewSprites/Down/attackDown");
        AttackUp=loadAnimation(4, "Sprites/NewSprites/Up/attackUp");
        AttackLeft=loadAnimation(4, "Sprites/NewSprites/Left/attackLeft");
        AttackRight=loadAnimation(4, "Sprites/NewSprites/Right/attackRight");

        
    }


    public void update(){
        if(keyh.w==true||keyh.a==true||keyh.s==true||keyh.d==true||keyh.o==true){

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

            if(keyh.o==true){
                velocità=5;
            }
            if(keyh.o==false){
                velocità=3;
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
                if(keyh.p==true){
                    spriteNum=0;
                }else{
                    spriteNum=4;
                }
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
                image = AttackUp[spriteNum];
        }
             else if (keyh.w==false){
                image = UpAnimation[spriteNum];
             
        }
            break;
            case "down": if (keyh.s==true && keyh.p==false){ 
               image = MoveDownAnimation[spriteNum]; 
                  
            } 
            else if (keyh.p==true){
                image =AttackDown[spriteNum];
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
                image=AttackRight[spriteNum];
            }
            else if (keyh.d==false){ 
                image = RightAnimation[spriteNum];
                
            };
            break;
      
            case "left": 
            if (keyh.a==true && keyh.p==false){ 
                image = MoveLeftAnimation[spriteNum];
                
            }
            else if (keyh.p==true){
                image =AttackLeft[spriteNum];
                }
            
            else if (keyh.a==false){ 
                image = LeftAnimation[spriteNum];
            };
            break;
        }
        graphics2.drawImage(image, ScreenX, ScreenY, gp.ingame_size, gp.ingame_size,null);
    }
        

       
    }


    