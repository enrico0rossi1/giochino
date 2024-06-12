package Entità;

import java.util.Random;



import main.Pannello;

public class Mon_sqrdCat extends Entità {

    public Mon_sqrdCat(Pannello gp){
        super(gp);
        name = "Squared_cat";
        velocità=2;
        vitaMax=4;
        vita=vitaMax;

        collArea.x=0;
        collArea.y=0;
        collArea.width=48;
        collArea.height=48;
        solidAreaDefaultX=collArea.x;
        solidAreaDefaultY=collArea.y;

        getImage();
    }
    
    public void getImage(){
        MoveUpAnimation = loadAnimation(1, "Monsters/Sprites/Up");
        MoveDownAnimation = loadAnimation(1, "Monsters/Sprites/Down");
        MoveLeftAnimation = loadAnimation(1, "Monsters/Sprites/Left");
        MoveRightAnimation = loadAnimation(1, "Monsters/Sprites/Right");
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
        
        
        gp.CollisionManager.checkTile(this);
        gp.CollisionManager.checkPlayer(this);
        if(solid==false){    
         switch (direzione) {
            case "up": 
                worldY-=velocità; 
                image = MoveUpAnimation[0];
            break;
            case "right": 
                worldX+=velocità;
                image = MoveRightAnimation[0]; 
            break;
            case "left": 
                worldX-=velocità; 
                image = MoveLeftAnimation[0];
            break;
            case "down": 
                worldY+=velocità; 
                image = MoveDownAnimation[0];
            break;
        }
    }
        

        
    }

    public void update(){
        
        setAction();
        movement();
        
    }

}
