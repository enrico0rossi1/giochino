package entities.Monsters;

import entities.GameEntity;
import main.GamePanel;

public class Mon_Slime extends GameEntity{

    public Mon_Slime(GamePanel gp){
        super(gp);
        
        name = "Log";
        type=2;
        speed=3;
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

        MoveUpAnimation = loadAnimation(6, "MonsterSprites/Slime/Up",true);
        MoveDownAnimation = loadAnimation(6, "MonsterSprites/Slime/Left",true);
        MoveLeftAnimation = loadAnimation(6, "MonsterSprites/Slime/Left",true);
        MoveRightAnimation = loadAnimation(6, "MonsterSprites/Slime/Right",true);
        DeathAnimation = loadAnimation(5, "MonsterSprites/Slime/Death",true);

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