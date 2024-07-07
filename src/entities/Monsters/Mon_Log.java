package entities.Monsters;

import entities.GameEntity;
import main.GamePanel;

public class Mon_Log extends GameEntity{

    public Mon_Log(GamePanel gp){
        super(gp);
        
        name = "Log";
        type=2;
        speed=4;
        vitaMax=5;
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

        MoveUpAnimation = loadAnimation(4, "MonsterSprites/Log/Up",true);
        MoveDownAnimation = loadAnimation(4, "MonsterSprites/Log/Down",true);
        MoveLeftAnimation = loadAnimation(4, "MonsterSprites/Log/Left",true);
        MoveRightAnimation = loadAnimation(4, "MonsterSprites/Log/Right",true);
        DeathAnimation = loadAnimation(4, "MonsterSprites/Log/Death",true);

    }

    public void update(){
        
        setAction();
        if(vita>0){
            animationRoller();
        }else{
            deathAnimationRoller();
        }
        checkStatus();
        movement();
        invincible(invincibleTime);

    }
}