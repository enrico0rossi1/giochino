package Personaggi;

import main.Pannello;
import main.UtilityTool;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class PlayerTools {

    Pannello gp;

    public PlayerTools(Pannello gp){
        this.gp=gp;
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
                    
                    gp.giocatore.numKeys++;
                   
                    
                break;

                case "Door_Right":
                    if(gp.giocatore.numKeys>0){
                        gp.obj[i]=null;
                        gp.ui.showMessage("u unlocked a door! Let's gooo");
                        gp.giocatore.numKeys--;
                        gp.playSFX(1);}
                        if (gp.giocatore.numKeys==0){
                            gp.ui.showMessage2("no keys? so lame...");
                        }

                    
                break;

                case "Door_Left":
                    if(gp.giocatore.numKeys>0){
                        gp.obj[i]=null;
                        gp.ui.showMessage("u unlocked a door! Let's gooo");
                        gp.giocatore.numKeys--;
                        gp.playSFX(1);}
                        if (gp.giocatore.numKeys==0){
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
                        gp.giocatore.speedUp=true;
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
    
    public BufferedImage[] loadAnimation (int Dimension, String Import){
        BufferedImage[] animation = new BufferedImage [Dimension];
        UtilityTool uTool = new UtilityTool(gp);
        
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

    public void printSprite(){
        
        switch (gp.giocatore.direzione) {
            case "up": 
           
                if (gp.keyh.w==true && gp.keyh.p==false){ 
               gp.giocatore.image = gp.giocatore.UpAnimation[gp.giocatore.spriteNum]; 
            } 
            else if (gp.keyh.p==true){
                gp.giocatore.image = gp.giocatore.AttackUp[gp.giocatore.spriteNum];
            }
            else if (gp.keyh.w==false && gp.keyh.p==true){
                gp.giocatore.image = gp.giocatore.AttackUp[gp.giocatore.spriteNum];
            }
        
            else {
                gp.giocatore.image = gp.giocatore.UpAnimation[gp.giocatore.spriteNum];
            }
            break;

                case "down": if (gp.keyh.s==true && gp.keyh.p==false){ 
                   gp.giocatore.image = gp.giocatore.MoveDownAnimation[gp.giocatore.spriteNum]; 
                      
                } 
                else if (gp.keyh.p==true){
                    gp.giocatore.image =gp.giocatore.AttackDown[gp.giocatore.spriteNum];
                }
                else
                {
                    gp.giocatore.image = gp.giocatore.DownAnimation[gp.giocatore.spriteNum];
                };
            break;
      
                case "right": 
                if (gp.keyh.d==true && gp.keyh.p==false){ 
                    gp.giocatore.image = gp.giocatore.MoveRightAnimation[gp.giocatore.spriteNum];
                
                }
                else if (gp.keyh.p==true){
                    gp.giocatore.image=gp.giocatore.AttackRight[gp.giocatore.spriteNum];
                }
                else 
                { 
                    gp.giocatore.image = gp.giocatore.RightAnimation[gp.giocatore.spriteNum];

                };
            break;
      
                case "left": 
                if (gp.keyh.a==true && gp.keyh.p==false){ 
                    gp.giocatore.image = gp.giocatore.MoveLeftAnimation[gp.giocatore.spriteNum];

                }
                else if (gp.keyh.p==true){
                    gp.giocatore.image =gp.giocatore.AttackLeft[gp.giocatore.spriteNum];
                    }
                
                else 
                { 
                    gp.giocatore.image = gp.giocatore.LeftAnimation[gp.giocatore.spriteNum];
                };
            break;
            
        }
        

    }

    public void moveOBJChecker(){


        if(gp.keyh.w==true||gp.keyh.a==true||gp.keyh.s==true||gp.keyh.d==true||gp.keyh.o==true){

            if(gp.keyh.s==true){
                gp.giocatore.direzione = "down";
            }
           
            if(gp.keyh.w==true){
                gp.giocatore.direzione="up";
            }
    
            if(gp.keyh.d==true){
                gp.giocatore.direzione="right";
            }
            
            if(gp.keyh.a==true){
                gp.giocatore.direzione="left";
            }

            if(
               (gp.keyh.w==true ||
                gp.keyh.a==true ||
                gp.keyh.s==true ||
                gp.keyh.d==true) && gp.keyh.o==true && gp.giocatore.speedUp==true ){
                  gp.giocatore.velocità = 5 ;
             } else if(
                (gp.keyh.w==true ||
                 gp.keyh.a==true ||
                 gp.keyh.s==true ||
                 gp.keyh.d==true) && gp.keyh.o==true){
                    gp.giocatore.velocità = 3;
                 }
                  else {gp.giocatore.velocità = 0;
                    }
            
            if(gp.keyh.o==false){
                gp.giocatore.velocità=3;
            }

            gp.giocatore.solid = false;
            gp.CollisionManager.checkTile(gp.giocatore);
            int objVerifier=999;
            //oggetti in base alla mappa
            
            objVerifier = gp.CollisionManager.checkObject(gp.giocatore,true);
            pickUpObj(objVerifier);
            

            gp.eventHandler.checkEvent();
            

            if (gp.giocatore.solid == false){
                switch(gp.giocatore.direzione){
                    case "up": gp.giocatore.posizioneY -= gp.giocatore.velocità*gp.screenManager.scaleY; break;
                    case "down": gp.giocatore.posizioneY += gp.giocatore.velocità*gp.screenManager.scaleY; break;
                    case "right": gp.giocatore.posizioneX += gp.giocatore.velocità*gp.screenManager.scaleX; break;
                    case "left": gp.giocatore.posizioneX-= gp.giocatore.velocità*gp.screenManager.scaleX; break;
                }

            }
        }

        
    }

    public void animationRoller(){
        final int frameInterval = 200* 1000000;
        long currentTime = System.nanoTime();
        
        
        if (currentTime - gp.lastTime >= frameInterval) {
            gp.giocatore.spriteNum = (gp.giocatore.spriteNum + 1) % gp.giocatore.MoveDownAnimation.length;
            gp.lastTime = currentTime;
        }

    }

    

}
