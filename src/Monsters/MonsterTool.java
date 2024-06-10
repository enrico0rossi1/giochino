package Monsters;

import java.util.Random;

import Personaggi.Entità;
import main.CollisionManager;
import main.Pannello;

public class MonsterTool extends Entità {

    public MonsterTool(Pannello gp){
        super(gp);
        
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
        switch (direzione) {
            case "up": worldY-=velocità; break;
            case "right": worldX+=velocità; break;
            case "left": worldX-=velocità; break;
            case "down": worldY+=velocità; break;
        }
    }
    
}
