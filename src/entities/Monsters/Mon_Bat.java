package entities.Monsters;

import entities.GameEntity;
import main.GamePanel;

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

        getImage();
    }

    public void getImage(){

        MoveUpAnimation = loadAnimation(3, "MonsterSprites/Bat/Up");
        MoveDownAnimation = loadAnimation(3, "MonsterSprites/Bat/Down");
        MoveLeftAnimation = loadAnimation(3, "MonsterSprites/Bat/Left");
        MoveRightAnimation = loadAnimation(3, "MonsterSprites/Bat/Right");
        DeathAnimation = loadAnimation(4, "MonsterSprites/Bat/Death");

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
        movement();
        invincible(invincibleTime);
        
    }
    
}
