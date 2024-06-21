package entities.Monsters;

import entities.GameEntity;
import main.GamePanel;

public class Mon_Slime extends GameEntity{

    public Mon_Slime(GamePanel gp){
        super(gp);
        
        name = "Log";
        type=2;
        speed=2;
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

        MoveUpAnimation = loadAnimation(6, "MonsterSprites/Slime/Up");
        MoveDownAnimation = loadAnimation(6, "MonsterSprites/Slime/Left");
        MoveLeftAnimation = loadAnimation(6, "MonsterSprites/Slime/Left");
        MoveRightAnimation = loadAnimation(6, "MonsterSprites/Slime/Right");
        //DeathAnimation = loadAnimation(4, "MonsterSprites/Log/Death");

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
                spriteNum=3;
            }else if(spriteNum==3){
                spriteNum=4;
            }else if(spriteNum==4){
                spriteNum=5;
            }else if(spriteNum==5){
                spriteNum=0;
            }
            spriteCount=0;
        }
        
        checkStatus();
        movement();
        invincible(invincibleTime);

    }
}