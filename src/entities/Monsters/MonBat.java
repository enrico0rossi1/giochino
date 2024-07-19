package entities.Monsters;

import entities.GameEntity;
import warrioradventure.GamePanel;

public class MonBat extends GameEntity{

    public MonBat(GamePanel gp){
        super(gp);

        name = "Bat";
        type=2;
        speed=7;
        vitaMax=2;
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

        MoveUpAnimation = loadAnimation(3, "MonsterSprites/Bat/Up",true);
        MoveDownAnimation = loadAnimation(3, "MonsterSprites/Bat/Down",true);
        MoveLeftAnimation = loadAnimation(3, "MonsterSprites/Bat/Left",true);
        MoveRightAnimation = loadAnimation(3, "MonsterSprites/Bat/Right",true);
        DeathAnimation = loadAnimation(3, "MonsterSprites/Bat/Death",true);

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
