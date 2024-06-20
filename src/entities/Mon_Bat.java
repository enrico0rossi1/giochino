package entities;

import main.GamePanel;
import java.util.Random;

public class Mon_Bat extends GameEntity{

    public Mon_Bat(GamePanel gp){
        super(gp);

        name = "Bat";
        type=2;
        speed=1;
        vitaMax=4;
        vita=vitaMax;
        invincibleTime=20;
        
        collArea.x=0;
        collArea.y=0;
        collArea.width=gp.ingame_size;
        collArea.height=gp.ingame_size;
        solidAreaDefaultX=collArea.x;
        solidAreaDefaultY=collArea.y;
        Random random = new Random();
        int i = random.nextInt(4);
        switch (i) {
            case 0:direzione="up";break;
            case 1:direzione="down";break;
            case 2: direzione="right";break;
            case 3: direzione="left";break;
        }

        getImage();
    }

    public void getImage(){

        MoveUpAnimation = loadAnimation(3, "Monsters/MonsterSprites/Bat/Up");
        MoveDownAnimation = loadAnimation(3, "Monsters/MonsterSprites/Bat/Down");
        MoveLeftAnimation = loadAnimation(3, "Monsters/MonsterSprites/Bat/Left");
        MoveRightAnimation = loadAnimation(3, "Monsters/MonsterSprites/Bat/Right");
        DeathAnimation = loadAnimation(4, "Monsters/MonsterSprites/Bat/Death");

    }

    public void update(){
        
        setAction();
        spriteCount++;
        if(spriteCount>5){
            if(spriteNum==0){
                spriteNum=1;
            }else if (spriteNum==1){
                spriteNum=2;
            }else if (spriteNum==2){
                spriteNum=0;
            }
            spriteCount=0;
        }
        
        checkStatus();
        if(gp.eventHandler.currentMapIndex==this.mapVerifier){
            movement();
        }
        invincible(invincibleTime);
        
    }
    
}
