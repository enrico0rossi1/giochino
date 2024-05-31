package Personaggi;



import main.InputTastiera;
import main.Pannello;
import main.ScreenManager;
import main.UtilityTool;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Giocatore extends Entità {

    Pannello gp;
    InputTastiera keyh;
    ScreenManager screenManager;
    public int ScreenX;
    public int ScreenY;
    public BufferedImage image;
    public static int numKeys=0;
    public boolean speedUp = false;

    
    public Giocatore(Pannello gp, InputTastiera keyh,ScreenManager screenManager){

        super(gp);
        this.gp = gp;
        this.keyh=keyh;
        this.screenManager=screenManager;
       
        collArea = new Rectangle();
        collArea.x=12;
        collArea.y=18;
        solidAreaDefaultX = collArea.x;
        solidAreaDefaultY = collArea.y;
        collArea.width=gp.ingame_size-(collArea.x*2);
        collArea.height=(gp.ingame_size-collArea.y)-9; //area di collisione del giocatore
        
        ScreenX = (gp.screen_width/2)-(gp.ingame_size/2);
        ScreenY = (gp.screen_height/2)-(gp.ingame_size/2); //coordinate centrali

        setValoriPredefiniti();
        getPlayerImage();
    }

    public void setValoriPredefiniti(){
        posizioneX=gp.ingame_size*25;  
        posizioneY=gp.ingame_size*25; //posizione iniziale su mappa 
        velocità=3;
        direzione="down";

        vitaMax = 10;
        vita = vitaMax;
    }
    
    public BufferedImage[] loadAnimation (int Dimension, String Import){
        BufferedImage[] animation = new BufferedImage [Dimension];
        UtilityTool uTool = new UtilityTool();
        
        try {
           
            for(int i=1; i<=Dimension;i++){
                animation[i-1]=ImageIO.read(getClass().getResourceAsStream(Import+i+".png"));
                animation[i-1]= uTool.scaleImage(animation[i-1], gp.ingame_size,gp.ingame_size);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return animation;
        
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

        AttackDown=loadAnimation(6, "Sprites/NewSprites/Down/attackDown");
        AttackUp=loadAnimation(6, "Sprites/NewSprites/Up/attackUp");
        AttackLeft=loadAnimation(6, "Sprites/NewSprites/Left/attackLeft");
        AttackRight=loadAnimation(6, "Sprites/NewSprites/Right/attackRight");

        
    }
    public void pickUpObj (int i){
        if(i !=999){
            
            String objName = gp.obj[i].name;

            if(gp.eventHandler.telNum == gp.obj[i].mapVerifier){  
                switch(objName){
                case "Key":
                    gp.obj[i]= null;
                    gp.ui.showMessage("u got a key!,that's cool");
                    gp.playSFX(1);
                    
                    numKeys++;
                   
                    
                break;

                case "Door_Right":
                    if(numKeys>0){
                        gp.obj[i]=null;
                        gp.ui.showMessage("u unlocked a door! Let's gooo");
                        numKeys--;
                        gp.playSFX(1);}
                        if (numKeys==0){
                            gp.ui.showMessage2("no keys? so lame...");
                        }

                    
                break;

                case "Door_Left":
                    if(numKeys>0){
                        gp.obj[i]=null;
                        gp.ui.showMessage("u unlocked a door! Let's gooo");
                        numKeys--;
                        gp.playSFX(1);}
                        if (numKeys==0){
                            gp.ui.showMessage2("no keys? so lame...");
                        }

                    
                break;

                case "GoldCoin":
                {
                    gp.obj[i]=null;
                    gp.ui.showMessage2("richer");
                    gp.playSFX(1);

                }
            break;
                case "Shoes":
                    {
                        gp.obj[i]=null;
                        speedUp=true;
                        gp.ui.showMessage("press O to run");
                        gp.playSFX(1);

                    }
                break;

                case "BigTreasure":
                {
                    gp.obj[i]=null;
                    gp.playSFX(1);
                    gp.stopMusic(0);
                    gp.ui.endGame = true;
                    
                    

                }
            break;

            }
            }
        
        
        }

    }
    public void printSprite(){
        
        switch (direzione) {
            case "up": 
           
                if (keyh.w==true && keyh.p==false){ 
               image = MoveUpAnimation[spriteNum]; 
            } 
            else if (keyh.p==true){
                image = AttackUp[spriteNum];
            }
            else if (keyh.w==false && keyh.p==true){
                image = AttackUp[spriteNum];
            }
        
            else {
                image = UpAnimation[spriteNum];
            }
            break;

                case "down": if (keyh.s==true && keyh.p==false){ 
                   image = MoveDownAnimation[spriteNum]; 
                      
                } 
                else if (keyh.p==true){
                    image =AttackDown[spriteNum];
                }
                else
                {
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
                else 
                { 
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
                
                else 
                { 
                    image = LeftAnimation[spriteNum];
                };
            break;
            
        }
        

    }
    
    public void moveOBJChecker(){


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

            if(
               (keyh.w==true ||
                keyh.a==true ||
                keyh.s==true ||
                keyh.d==true) && keyh.o==true && speedUp==true ){
                  velocità = 5 ;
             } else if(
                (keyh.w==true ||
                 keyh.a==true ||
                 keyh.s==true ||
                 keyh.d==true) && keyh.o==true){
                    velocità = 3;
                 }
                  else {velocità = 0;
                    }
            
            if(keyh.o==false){
                velocità=3;
            }

            solid = false;
            gp.CollisionManager.checkTile(this);
            int objVerifier=999;
            //oggetti in base alla mappa
            
            objVerifier = gp.CollisionManager.checkObject(this,true);
            pickUpObj(objVerifier);
            

            gp.eventHandler.checkEvent();
            

            if (solid == false){
                switch(direzione){
                    case "up": posizioneY -= velocità*screenManager.scaleY; break;
                    case "down": posizioneY += velocità*screenManager.scaleY; break;
                    case "right": posizioneX += velocità*screenManager.scaleX; break;
                    case "left": posizioneX-= velocità*screenManager.scaleX; break;
                }

            }
        }

        
    }
    public void animationRoller(){
        spriteCount++;
     
        if(spriteCount>3){
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

    public void update(){
    
        if (vita == 0) {
            gp.gameState = gp.gameOver;
        }
   
        
        moveOBJChecker();
        animationRoller();
        printSprite();
        

        
    }
    public void draw(Graphics2D graphics2){
    
        graphics2.drawImage(image, ScreenX, ScreenY,null);
    }
        

       
}


    
