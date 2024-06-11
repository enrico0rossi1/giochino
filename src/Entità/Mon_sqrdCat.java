package Entità;

import java.awt.image.BufferedImage;
import java.util.Random;

import main.Pannello;

public class Mon_sqrdCat extends Entità {

    public Mon_sqrdCat(Pannello gp){
        super(gp);
        name = "Squared_cat";
        velocità=1;
        vitaMax=4;
        vita=vitaMax;

        collArea.x=3;
        collArea.y=6;
        collArea.width=16;
        collArea.height=16;
        solidAreaDefaultX=collArea.x;
        solidAreaDefaultY=collArea.y;

        getImage();
    }
    
    public void getImage(){
        BufferedImage handle[]=new BufferedImage[6];
        handle= loadAnimation(1, "Monsters/Sprites/Down");
        image = handle[0];
    }

    public void setAction(){
        
        actionLockCounter++;

        if(actionLockCounter==120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i>=0 && i<25){
                direzione="up";
            }
            if(i>=25 && i<50){
                direzione="right";
            }
            if(i>=50 && i<75){
                direzione="left";
            }
            if(i>=75 && i<100){
                direzione="down";
            }

            actionLockCounter=0;
        }


    }

    public void movement(){
        
        solid=false;
        gp.CollisionManager.checkTile(this);
        System.out.println(solid);
        if(solid==false){    
         switch (direzione) {
            case "up": worldY+=velocità; break;
            case "right": worldX+=velocità; break;
            case "left": worldX+=velocità; break;
            case "down": worldY+=velocità; break;
        }
    }
        

        
    }

    public void update(){
        
        setAction();
        movement();
        
    }

}
