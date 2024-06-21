package entities.Monsters;

import entities.GameEntity;
import main.GamePanel;

public class Mon_Log extends GameEntity{

    public Mon_Log(GamePanel gp){
        super(gp);
        
        name = "Log";
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

        MoveUpAnimation = loadAnimation(4, "MonsterSprites/Log/Up");
        MoveDownAnimation = loadAnimation(4, "MonsterSprites/Log/Down");
        MoveLeftAnimation = loadAnimation(4, "MonsterSprites/Log/Left");
        MoveRightAnimation = loadAnimation(4, "MonsterSprites/Log/Right");
        DeathAnimation = loadAnimation(4, "MonsterSprites/Log/Death");

    }

    public void update(){
        
        setAction();
        animationRoller();
        checkStatus();
        movement();
        invincible(invincibleTime);

    }
}