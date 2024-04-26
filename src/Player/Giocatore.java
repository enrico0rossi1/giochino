package Player;

import main.InputTastiera;
import main.Pannello;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Giocatore extends Entità {

    Pannello gp;
    InputTastiera keyh;
    public final int ScreenX, ScreenY;
    public static int numKeys=0;
    public boolean speedUp = false;
    
    

    public Giocatore(Pannello gp, InputTastiera keyh){

        this.gp=gp;
        this.keyh=keyh;
       
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
        GiocatoreX=gp.ingame_size*25;  
        GiocatoreY=gp.ingame_size*25; //posizione iniziale su mappa 
        velocità=3;
        direzione="down";
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
        DownAnimation=loadAnimation(4, "Sprites/NewSprites/Down/down");
        RightAnimation=loadAnimation(6, "Sprites/NewSprites/Right/right");
        LeftAnimation=loadAnimation(6, "Sprites/NewSprites/Left/left");

        AttackDown=loadAnimation(4, "Sprites/NewSprites/Down/attackDown");
        AttackUp=loadAnimation(4, "Sprites/NewSprites/Up/attackUp");
        AttackLeft=loadAnimation(4, "Sprites/NewSprites/Left/attackLeft");
        AttackRight=loadAnimation(4, "Sprites/NewSprites/Right/attackRight");

        
    }


    public void update(){
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
                  velocità=5;
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
            int objVerifier = gp.CollisionManager.checkObject(this,true);
            pickUpObj(objVerifier);

            if (solid == false){
                switch(direzione){
                    case "up": GiocatoreY -= velocità; break;
                    case "down": GiocatoreY += velocità; break;
                    case "right": GiocatoreX += velocità; break;
                    case "left": GiocatoreX-= velocità; break;
                }

            }

        }
    }
    
    public void pickUpObj (int i){
        if(i !=999){
            
            String objName = gp.obj[i].name;

            switch(objName){
                case "key":
                    gp.obj[i]= null;
                    gp.ui.showMessage("u got a key!,that's cool");
                    gp.playSFX(1);
                    
                    numKeys++;
                   
                    
                break;

                case "door":
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
    
        graphics2.drawImage(image, ScreenX, ScreenY, gp.ingame_size, gp.ingame_size,null);
    }
        

       
}


    
